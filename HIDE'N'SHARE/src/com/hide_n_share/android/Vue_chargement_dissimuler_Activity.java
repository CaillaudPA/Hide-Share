package com.hide_n_share.android;

import android.app.Activity;
import android.os.Bundle;

public class Vue_chargement_dissimuler_Activity extends Activity {
	
	String pathLettre = "";
	String pathEnveloppe = "";
	String motsDePasse = "";
	boolean compresser=false;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vue_chargement);
		
		pathLettre = getIntent().getStringExtra(Data.EXTRA_LETTRE);  
		pathEnveloppe = getIntent().getStringExtra(Data.EXTRA_ENVELOPPE);
		compresser = getIntent().getBooleanExtra(Data.EXTRA_MDP, false);
		motsDePasse = getIntent().getStringExtra(Data.EXTRA_MDP);
	}
}
