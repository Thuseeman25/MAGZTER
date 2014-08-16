package com.magzter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class ZoomActivity extends Activity implements AnimationListener{

	ImageView imgPoster;
    Button btnStart;

    // Animation
    Animation animZoomIn;
    Animation animZoomOut;
    Animation animBlurIn;
    Animation animBlurOut;
    
    int  images[];
    int position = 0;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);

        imgPoster = (ImageView) findViewById(R.id.imgLogo);
//        btnStart = (Button) findViewById(R.id.btnStart);
        images = new int[]{R.drawable.national, 
        		R.drawable.popular_mechanics, R.drawable.edge};
        
        // load the animation
        animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoon_in);
        animZoomIn.setDuration(5000);
        
        animZoomOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
        animZoomOut.setDuration(5000);
        
        animBlurIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blur_in);
        animBlurIn.setDuration(5000);
        
        animBlurOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blur_out);
        animBlurOut.setDuration(5000);
        
        // set animation listener
        animZoomIn.setAnimationListener(this);
        animZoomOut.setAnimationListener(this);
        animBlurIn.setAnimationListener(this);
        animBlurOut.setAnimationListener(this);
        
        imgPoster.startAnimation(animZoomIn);
    }

    @SuppressLint("NewApi")
	@Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation

        // check for zoom in animation
        if (animation == animZoomIn) {   
        	imgPoster.startAnimation(animBlurIn);
        	
        }
        if(animation == animBlurIn){
        	
        	if(position >= images.length-1){
        		position = -1;
        	}
        	position++;
        	
        	imgPoster.setImageResource(images[position]);        	
        	imgPoster.startAnimation(animBlurOut);
        }
        if(animation == animBlurOut){ 
        	imgPoster.startAnimation(animZoomIn);
        		
        }
        if(animation == animZoomOut){
        	imgPoster.startAnimation(animBlurIn);
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }

}