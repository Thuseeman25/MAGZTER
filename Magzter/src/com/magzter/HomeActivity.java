package com.magzter;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.maps.MapView;
import com.korovyansk.android.slideout.SlideoutActivity;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends FragmentActivity{

	MapView mapView;
    ImageView catelogueButton;
    ImageView nearByButton;
    ImageView menuButton;
    private GoogleMap googleMap;
    SupportMapFragment supportMapFragment;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        
        //Google map loading and its accessaries.....
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
		 
		if (status == ConnectionResult.SUCCESS) {
			supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapview);
			googleMap = supportMapFragment.getMap();
			googleMap.setMyLocationEnabled(true);
			
			Location location = googleMap.getMyLocation();
			
			//get current location using gps.................................
			LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	        Criteria criteria = new Criteria();
	        
	        if(location == null){
	        	location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
	        }
	        if (location != null)
	        {       	
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
		}else {
			int requestCode = 10;
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
			dialog.show();
		}
		googleMap.setOnMapClickListener(new OnMapClickListener() {
			
			@Override
			public void onMapClick(LatLng arg0) {
				startActivity(new Intent(HomeActivity.this, MapActivity.class));
			}
		});
		
		
 		
        Typeface fontMedium = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-MdCn.otf");
		Typeface fontLight = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-ThCn.otf");
		Typeface fontMediumOblique = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-MdCnO.otf");
		Typeface fontBoldCondensed = Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeueLTStd-BdCn.otf");
       
		menuButton = (ImageView) findViewById(R.id.side_menu_button);
		menuButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				int width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 230, getResources().getDisplayMetrics());
				SlideoutActivity.prepare(HomeActivity.this, R.id.inner_content, width);
				startActivity(new Intent(HomeActivity.this, MenuActivity.class));
				overridePendingTransition(0, 0);
			}
		});
		catelogueButton = (ImageView) findViewById(R.id.catalogue_button);
        catelogueButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(), CatalogueActivity.class));
				
			}
		});
        
        nearByButton = (ImageView) findViewById(R.id.nearby_button);
        nearByButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(HomeActivity.this, MapActivity.class));
				
			}
		});
        
        TextView currentLocationText = (TextView) findViewById(R.id.current_location_text);
        currentLocationText.setTypeface(fontLight);
        TextView currentLocationName = (TextView) findViewById(R.id.current_location_name);
        currentLocationName.setTypeface(fontMedium);
        TextView magazinesText = (TextView) findViewById(R.id.magazines_text);
        magazinesText.setTypeface(fontMediumOblique);
        TextView availableText = (TextView) findViewById(R.id.available_text);
        availableText.setTypeface(fontMediumOblique);
        TextView availableMagazinesNo = (TextView) findViewById(R.id.available_magazines_no);
        availableMagazinesNo.setTypeface(fontBoldCondensed);

        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_home, menu);
        return true;
    }
    
}
