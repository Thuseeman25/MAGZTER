package com.magzter;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.korovyansk.android.slideout.SlideoutActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MapActivity extends FragmentActivity {
	private GoogleMap googleMap;
	ImageView menuButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_map);
		
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
		 
		if (status == ConnectionResult.SUCCESS) {
			SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
			googleMap = supportMapFragment.getMap();
			googleMap.setMyLocationEnabled(true);
		}else {
			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
			dialog.show();
		}
		
		//get current location using gps.................................
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria criteria = new Criteria();
		        
		Location location = googleMap.getMyLocation();
		if(location == null){
		    location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
		}
		if (location != null){       	
		    LatLng currentLocaton = new LatLng(location.getLatitude(), location.getLongitude());
		    //Define camera position..........................................
		    CameraPosition cameraPosition = new CameraPosition.Builder()
		    .target(currentLocaton)      // Sets the center of the map to location user
		    .zoom(17)                   // Sets the zoom
		    .build();  
		            
		    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		    MarkerOptions markerOption = new MarkerOptions().position(currentLocaton);
		    googleMap.addMarker(markerOption);
		}		
		
		menuButton = (ImageView) findViewById(R.id.side_menu_button);
		menuButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 230, getResources().getDisplayMetrics());
				SlideoutActivity.prepare(MapActivity.this, R.id.inner_content, width);
				startActivity(new Intent(MapActivity.this, MenuActivity.class));
				overridePendingTransition(0, 0);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map, menu);
		return true;
	}

}
