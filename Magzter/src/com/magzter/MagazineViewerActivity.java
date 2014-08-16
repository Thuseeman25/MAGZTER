package com.magzter;

import com.magzter.adapters.FullScreenImageAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.Window;

public class MagazineViewerActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_magazine_viewer);
		
		ViewPager magazineViewer = (ViewPager) findViewById(R.id.pager);
		magazineViewer.setAdapter(new FullScreenImageAdapter(this, new int[]{
				R.drawable.popular_mechanics_1,R.drawable.popular_mechanics_2,
				R.drawable.popular_mechanics_3,R.drawable.popular_mechanics_4}));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.magazine_viewer, menu);
		return true;
	}

}
