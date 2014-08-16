package com.magzter;

import java.util.List;

import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.Session;
import com.facebook.Session.StatusCallback;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.FacebookDialog;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;
import com.jess.ui.TwoWayAdapterView;
import com.jess.ui.TwoWayAdapterView.OnItemClickListener;
import com.jess.ui.TwoWayAdapterView.OnItemLongClickListener;
import com.jess.ui.TwoWayGridView;
import com.magzter.adapters.GalleryAdapter;
import com.magzter.dao.CategoryDataSource;
import com.magzter.dao.MagazineDataSource;
import com.magzter.facebook.FacebookLoginActivity;
import com.magzter.models.Category;
import com.magzter.models.Magazine;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CatalogueActivity extends Activity {
	
	TableLayout catelogueViewList;
	private UiLifecycleHelper uiHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_catalogue);
	    
		uiHelper = new UiLifecycleHelper(this, null);
	    uiHelper.onCreate(savedInstanceState);
	    
		Typeface fontTitle = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-MdCn.otf");
//		Typeface fontBody = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-ThCn.otf");
		
		ImageView backButton = (ImageView) findViewById(R.id.side_menu_button);
		backButton.setImageResource(R.drawable.back_button);	
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				onBackPressed();
			}
		});
		
		ImageView catalogueButton = (ImageView) findViewById(R.id.catalogue_button);
		catalogueButton.setVisibility(View.INVISIBLE);
		ImageView nearbyButton = (ImageView) findViewById(R.id.nearby_button);
		nearbyButton.setVisibility(View.INVISIBLE);
		
		TextView titleText = (TextView) findViewById(R.id.title_text);
		titleText.setVisibility(View.VISIBLE);
		titleText.setTypeface(fontTitle);		
		
		//Get the category details from DB....................
		CategoryDataSource categoryDataSource = new CategoryDataSource(this);
		categoryDataSource.openReadable();
		List<Category> catelogueList = categoryDataSource.getAllCategories();
		
		loadTableRows(catelogueList);
		
	}

	@Override
	protected void onResume() {
	    super.onResume();
	    uiHelper.onResume();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}

	@Override
	public void onPause() {
	    super.onPause();
	    uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
	    super.onDestroy();
	    uiHelper.onDestroy();
	}
	
	@SuppressLint("DefaultLocale")
	private void loadTableRows(List<Category> catelogueList){
		
		catelogueViewList = (TableLayout) findViewById(R.id.catalogue_list);
				
		for(int i = 0; i < catelogueList.size(); i++ ){
			
			Typeface fontBody = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-ThCn.otf");
			final int row = i;
			View linearLayout = (View) getLayoutInflater().inflate(R.layout.catalogue_item, null);
			
			TextView catelogueTitle = (TextView) linearLayout.findViewById(R.id.catelogue_text);
			final TwoWayGridView magazineGallery = (TwoWayGridView) linearLayout.findViewById(R.id.magazine_gallery);
			
			Category catelogue = catelogueList.get(i);
			
			//Get Magazine details from DB....................................
			
			MagazineDataSource magazineDataSource = new MagazineDataSource(this);
			magazineDataSource.openReadable();
			List<Magazine> magazines = magazineDataSource.getAllMagazines();
			
			//..................................................................
			catelogueTitle.setText(catelogue.getName().toUpperCase());
			catelogueTitle.setTypeface(fontBody);
			magazineGallery.setAdapter(new GalleryAdapter(this, new int[]{R.drawable.national,
					R.drawable.edge,R.drawable.time,R.drawable.popular_mechanics,R.drawable.time}));
			
			magazineGallery.setOnItemLongClickListener(new OnItemLongClickListener() {

				@Override
				public boolean onItemLongClick(TwoWayAdapterView<?> parent,
						View view, int position, long id) {
					registerForContextMenu(magazineGallery);				
					openContextMenu(magazineGallery);
					return false;
				}
			});
			
			magazineGallery.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(TwoWayAdapterView<?> parent, View view, int position, long id) {
					Toast.makeText(CatalogueActivity.this, row+","+position, Toast.LENGTH_SHORT).show();
					startActivity(new Intent(CatalogueActivity.this, MagazineDetailsActivity.class));
				}
			});
			
			catelogueViewList.addView(linearLayout);
		}		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	        ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	    menu.add(0, v.getId(), 0, "Share on Facebook");
	    menu.add(0, v.getId(), 0, "Share on whats app");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		//Select sharing option Facebook/Whats app..............
		
		if(item.getTitle().equals("Share on Facebook")){
			
			if (FacebookDialog.canPresentShareDialog(getApplicationContext(), 
                    FacebookDialog.ShareDialogFeature.SHARE_DIALOG)) {
				// Publish the post using the Share Dialog
				FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
				.setPicture("http://static3.businessinsider.com/image/5238c9c5ecad047f12b2751a/internet-famous-grumpy-cat-just-landed-an-endorsement-deal-with-friskies.jpg")
				.setLink("https://www.financeberry.com")
				.setDescription("This is my favorite magazine")
				.build();
				uiHelper.trackPendingDialogCall(shareDialog.present());

			} else {
				// Fallback. For example, publish the post using the Feed Dialog
				publishStatus();
			}
			
//			Bundle params = new Bundle();
//		    params.putString("link", "http://www.bbc.co.uk");
//		    params.putString("description", "Description");
//		    params.putString("name", "Amila Rangana kolambage");
//		    params.putString("picture", "http://static3.businessinsider.com/image/5238c9c5ecad047f12b2751a/internet-famous-grumpy-cat-just-landed-an-endorsement-deal-with-friskies.jpg");
//			startActivity(new Intent(CatalogueActivity.this, FacebookLoginActivity.class).putExtras(params));     	
//		   
		}else{
			Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
		}
		return super.onContextItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);

	    uiHelper.onActivityResult(requestCode, resultCode, data, new FacebookDialog.Callback() {
	        @Override
	        public void onError(FacebookDialog.PendingCall pendingCall, Exception error, Bundle data) {
	            Log.e("Activity", String.format("Error: %s", error.toString()));
	        }

	        @Override
	        public void onComplete(FacebookDialog.PendingCall pendingCall, Bundle data) {
	            Log.i("Activity", "Success!");
	        }
	    });
	}
	
	private void publishStatus() {
	    
	    if(Session.getActiveSession().isOpened()){
	    	publishFeedDialog();
	    }else{
	    	Session.openActiveSession(this, true, new Session.StatusCallback() {
				
				@Override
				public void call(Session session, SessionState state, Exception exception) {
					if(session.isOpened()){
						publishFeedDialog();
					}
				}
			});

	    }
	    	    
	}
	
	public void publishFeedDialog(){
		final Bundle params = new Bundle();
	    params.putString("name", "Magzter");
	    params.putString("caption", "Favorite magazine");
	    params.putString("description", "This is my favorite magazine");
	    params.putString("link", "https://www.financeberry.com");
	    params.putString("picture", "http://static3.businessinsider.com/image/5238c9c5ecad047f12b2751a/internet-famous-grumpy-cat-just-landed-an-endorsement-deal-with-friskies.jpg");
	    
		WebDialog feedDialog = (new WebDialog.FeedDialogBuilder(this,
	            Session.getActiveSession(),
	            params))
	        .setOnCompleteListener(new OnCompleteListener() {

				@Override
				public void onComplete(Bundle values, FacebookException error) {
					 if (error == null) {
		                    // When the story is posted, echo the success
		                    // and the post Id.
		                    final String postId = values.getString("post_id");
		                    if (postId != null) {
		                        Toast.makeText(CatalogueActivity.this,
		                            "Posted story ",
		                            Toast.LENGTH_SHORT).show();
		                    } else {
		                        // User clicked the Cancel button
		                        Toast.makeText(CatalogueActivity.this.getApplicationContext(), 
		                            "Publish cancelled", 
		                            Toast.LENGTH_SHORT).show();
		                    }
		                } else if (error instanceof FacebookOperationCanceledException) {
		                    // User clicked the "x" button
		                    Toast.makeText(CatalogueActivity.this.getApplicationContext(), 
		                        "Publish cancelled", 
		                        Toast.LENGTH_SHORT).show();
		                } else {
		                    // Generic, ex: network error
		                    Toast.makeText(CatalogueActivity.this.getApplicationContext(), 
		                        "Error posting story", 
		                        Toast.LENGTH_SHORT).show();
		                }
				}

	        })
	        .build();
	    feedDialog.show();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_catalogue, menu);
		return true;
	}

}
