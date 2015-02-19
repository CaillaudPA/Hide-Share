package com.hide_n_share.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Vue_chargement_Activity extends Activity{

	private ProgressBar mProgress;
	private int i = 0;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_chargement);
        
        mProgress = (ProgressBar)findViewById(R.id.progressBar2);
        
        new Thread(new Runnable(){
        	public void run() {
        		// TODO Auto-generated method stub
        		while(i<100){
        			i++;
        			mProgress.setProgress(i);
        			try {
        				Thread.sleep(100);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		}
        	}
        }).start();
        
        
    }
	

}
