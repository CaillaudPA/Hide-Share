package com.hide_n_share.android;

import java.io.File;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Vue_choix_enveloppe_Activity extends Activity implements OnClickListener{
	
	private Button galerie;
	private Button photo;
	
	final String EXTRA_LETTRE = "enveloppe";
	final String EXTRA_ENVELOPPE = "lettre";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_enveloppe);
        
        String pathDonneeCachee= getIntent().getStringExtra(EXTRA_LETTRE);        
        
		new PopupErreur().display(this, pathDonneeCachee);	

	}
	
	@Override
	public void onClick(View arg0) {
		if(arg0.equals(galerie)){
			
		}else if(arg0.equals(photo)){

		}
	}
}
