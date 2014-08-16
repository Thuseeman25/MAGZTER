package com.magzter.facebook;

import java.util.ArrayList;
import java.util.List;

import com.magzter.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.*;

public class FacebookLoginActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_facebook_login);
		
//		final TextView welcome = (TextView) findViewById(R.id.welcome);
		ImageView logingButton = (ImageView) findViewById(R.id.fb_login_button);
		
		List<String> PERMISSIONS = new ArrayList<String>();
		PERMISSIONS.add("publish_stream");
		
		final Session.NewPermissionsRequest newPermissionsRequest = new Session
                .NewPermissionsRequest(this, PERMISSIONS);
		
		
//		logingButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View arg0) {
				
				// start Facebook Login
				Session.openActiveSession(FacebookLoginActivity.this, true, new Session.StatusCallback() {

				    // callback when session changes state
				    @Override
				    public void call(Session session, SessionState state, Exception exception) {
				    	if (session.isOpened()) {
				    	
				    		if(!session.getPermissions().contains("publish_stream")){
				    			session.requestNewPublishPermissions(newPermissionsRequest);
				    		}
				    		Bundle params = getIntent().getExtras();
//				    		params.putString("link", "http://www.bbc.co.uk");
//				    		params.putString("description", "Description");
//				    		params.putString("name", "Thuseeman");
//				    		params.putString("picture", "https://www.google.lk/search?q=cathay+pacific&client=firefox-a&rls=org.mozilla:en-US:official&channel=nts&source=lnms&tbm=isch&sa=X&ei=xNvUU-z_OdSIuATjkoCICw&ved=0CAYQ_AUoAQ&biw=1304&bih=697#channel=nts&q=cat&rls=org.mozilla:en-US:official&tbm=isch&facrc=_&imgdii=_&imgrc=pV2pBajMQuzneM%253A%3BT-4a8OwfG_-j_M%3Bhttp%253A%252F%252Fstatic3.businessinsider.com%252Fimage%252F5238c9c5ecad047f12b2751a%252Finternet-famous-grumpy-cat-just-landed-an-endorsement-deal-with-friskies.jpg%3Bhttp%253A%252F%252Fwww.businessinsider.com%252Fgrumpy-cat-endorsement-friskies-2013-9%3B2800%3B2100");
				        	
				    		Request request = new Request(session, "me/feed", params, HttpMethod.POST);
				    		request.setCallback(new Request.Callback() {
				    		    @Override
				    		    public void onCompleted(Response response) {
				    		        if (response.getError() == null) {
				    		        	Toast.makeText(FacebookLoginActivity.this, "Post on Your wall", Toast.LENGTH_SHORT).show();
				    		        }else{
				    		        	Toast.makeText(FacebookLoginActivity.this, "Post cancelled", Toast.LENGTH_SHORT).show();
				    		        }
				    		    }
				    		});
//				    		Request request = Request.newStatusUpdateRequest(session, "Testing..",new Request.Callback() {
//								
//								@Override
//								public void onCompleted(Response response) {
//									 if (response.getError() == null) {
//					    		        	Toast.makeText(FacebookLoginActivity.this, "Post on Your wall", Toast.LENGTH_SHORT).show();
//					    		        }else{
//					    		        	Toast.makeText(FacebookLoginActivity.this, "Post cancelled", Toast.LENGTH_SHORT).show();
//					    		        }
//								}
//							});
				    		request.executeAsync();
				    		finish();
				    	}
				    }
				});
//			}
//		});
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.facebook_login, menu);
		return true;
	}

}
