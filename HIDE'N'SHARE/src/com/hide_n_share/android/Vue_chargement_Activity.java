package com.hide_n_share.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Vue_chargement_Activity extends Activity{

	private ProgressBar mProgress;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_chargement);
    }
	

}
