package com.magzter.facebook;

import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.model.GraphUser;

public class FacebookMethods {

	private PendingAction pendingAction = PendingAction.NONE;
	private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");
	private GraphUser user;
	
	private enum PendingAction {
        NONE,
        POST_PHOTO,
        POST_STATUS_UPDATE
    }
	
	public FacebookMethods(){
		
	}
	
	public void performPublish(PendingAction action, Activity activity, Bundle params) {
        Session session = Session.getActiveSession();
        if (session != null) {
            pendingAction = action;
            if (hasPublishPermission()) {
                // We can do the action right away.
                handlePendingAction(params, activity);
            } else {
                // We need to get new permissions, then complete the action when we get called back.
                session.requestNewPublishPermissions(new Session.NewPermissionsRequest(activity, PERMISSIONS));
            }
        }
    }
	
	 private boolean hasPublishPermission() {
	        Session session = Session.getActiveSession();
	        return session != null && session.getPermissions().contains("publish_actions");
	 }
	 
	 @SuppressWarnings("incomplete-switch")
	    private void handlePendingAction(Bundle params, Activity activity) {
	        PendingAction previouslyPendingAction = pendingAction;
	        // These actions may re-set pendingAction if they are still pending, but we assume they
	        // will succeed.
	        pendingAction = PendingAction.NONE;

	        switch (previouslyPendingAction) {
	            case POST_PHOTO:
//	                postPhoto();
	                break;
	            case POST_STATUS_UPDATE:
	                postStatusUpdate(params, activity);
	                break;
	        }
	    }
	 
	 private void postStatusUpdate(Bundle params, final Activity activity) {
	        if (user != null && hasPublishPermission()) {
	            Request request = new Request(Session.getActiveSession(), "me/feed", params, HttpMethod.POST );
	            request.setCallback(new Request.Callback() {
					
					@Override
					public void onCompleted(Response response) {
						if (response.getError() == null) {
	    		        	Toast.makeText(activity, "Post on Your wall", Toast.LENGTH_SHORT).show();
	    		        }else{
	    		        	Toast.makeText(activity, "Post cancelled", Toast.LENGTH_SHORT).show();
	    		        }
					}
				});
	            request.executeAsync();
	        } else {
	            pendingAction = PendingAction.POST_STATUS_UPDATE;
	        }
	    }
}
