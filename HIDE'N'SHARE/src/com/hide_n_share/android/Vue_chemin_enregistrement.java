package com.hide_n_share.android;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Vue_chemin_enregistrement extends Activity implements OnClickListener {
	
	String image_a_devoiler = "";
	String mdp="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vue_chemin_enregistrement);
		
		image_a_devoiler = getIntent().getStringExtra(Data.EXTRA_IMG_A_DEVOILER);
		mdp = getIntent().getStringExtra(Data.EXTRA_MDP);
	}

	
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
