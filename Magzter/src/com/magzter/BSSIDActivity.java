package com.magzter;

import java.util.List;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class BSSIDActivity extends Activity {

	private EditText wifiStateText;
	private Handler progressHandler;
	private List<ScanResult> results;
	private WifiManager wifiManager;
	private Button searchButton;
	private ProgressDialog progressDialog = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bssid);
        
        wifiStateText = (EditText) findViewById(R.id.wifi_results);
        searchButton = (Button) findViewById(R.id.search_button);
        
        wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        
        searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(!wifiManager.isWifiEnabled()){      	
		        	turnOnWIfi(BSSIDActivity.this);
		        }else{
		        	loadWifiAvailableList();
		        }
			}
		});
        
    }
    
    @SuppressLint("HandlerLeak")
	public void turnOnWIfi(final Context context){
    	
    	progressHandler = new Handler(){
    		
    		@Override
    		public void handleMessage(Message msg) {
    			
    			if(msg.what==1){
    				progressDialog.dismiss();
    				loadWifiAvailableList();
//    				wifiStateText.setText("");
    				wifiManager.setWifiEnabled(false);
    			}else if(msg.what==0){
    				progressDialog = ProgressDialog.show(context, "", "Turning on wifi...");
    				wifiManager.setWifiEnabled(true);
    			}
    			super.handleMessage(msg);
    		}
    	};
    	new Thread(){
    		public void run(){
    				progressHandler.sendEmptyMessage(0);  				
					try {
						sleep(7000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					progressHandler.sendEmptyMessage(1);
    		}
    	}.start();
    	
    }
    
    public void loadWifiAvailableList() {
//        WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	    results = wifiManager.getScanResults();
	    
	    @SuppressWarnings("unused")
		String message = "Check wireless is on";	    	
			 if (results != null) {
				 final int size = results.size();
				 if (size == 0) wifiStateText.setText("No access points in range");
			     else {
			          for (ScanResult result : results) {
			        	  wifiStateText.append("SSID:"+result.SSID+", " +
			        	  		"BSSID:"+result.BSSID+", " +
			        	  				"Capabilities:"+result.capabilities+", " +
			        	  						"Level:"+result.level+", " +
			        	  								"Frequency:"+result.frequency+"\n\n");			            
			          }
			     }
			 }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.activity_bssid, menu);
        return true;
    }
    
}
