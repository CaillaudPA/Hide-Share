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
	
	final String EXTRA_PATH_CHEMIN = "path_chemin";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choix_enveloppe);
        
        
        String pathDonneeCachee= getIntent().getStringExtra(EXTRA_PATH_CHEMIN);        
        
		FragmentManager fm = getFragmentManager();
		PopupErreur a = new PopupErreur();
		a.setMsg(new File(pathDonneeCachee).exists());
		a.show(fm, "test");	

	}
	
	public void onClick(View arg0) {
		if(arg0.equals(galerie)){
			
		}else if(arg0.equals(photo)){

		}
	}
}
