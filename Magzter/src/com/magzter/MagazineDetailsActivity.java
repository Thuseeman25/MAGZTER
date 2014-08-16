package com.magzter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class MagazineDetailsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_magazine_details);
		
		ImageView magazineImage = (ImageView) findViewById(R.id.magazine_image);
		magazineImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(MagazineDetailsActivity.this, MagazineViewerActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.magazine_viewer, menu);
		return true;
	}

}
