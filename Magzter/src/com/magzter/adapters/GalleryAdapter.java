package com.magzter.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {

	Context mContext;
	int[] mImages;
	public GalleryAdapter(Context context, int[] images){
		mContext = context;
		mImages = images;
	}
	
	@Override
	public int getCount() {
		
		return mImages.length;
	}

	@Override
	public Object getItem(int arg0) {
		
		return mImages[arg0];
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView == null){
			convertView = new ImageView(mContext);
		} 
		
		convertView.setBackgroundResource(mImages[position]);
//		convertView.setRotation(90);
		return convertView;
	}

}
