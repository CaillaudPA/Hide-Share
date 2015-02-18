package com.hide_n_share.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Vue_devoiler_Activity extends Activity implements OnClickListener {

	private Button selectImage;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vue_devoile);
	}
	
	@Override
	public void onClick(View arg0) {

	}

}
