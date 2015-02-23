package com.hide_n_share.android;

import java.io.File;

import com.hide_n_share.android.utilitaire.FonctionUtile;
import com.hide_n_share.modele.classeStatic.*;
import com.hide_n_share.modele.steganographie.*;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class test_activity extends Activity implements OnClickListener{
	
	private Button bTest;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vue_test);
		
		FonctionUtile.creationDossier();
		
		bTest = (Button)findViewById(R.id.boutonTest);
		bTest.setOnClickListener(this);
	}
	
	public void onClick(View arg0) {
		if(arg0.equals(bTest))
		{
			//File file = new File("/external/images/media/"/*getFilesDir()*/,"Christian.txt");
			//GestionFichier.fluxEnFichier(file.getPath(),"Christianne".getBytes());
			/*Stegano_image jeanMichel = new Stegano_image(file.getPath(), "/external/images/media/18");
			
			jeanMichel.dissimulerDonnee("/external/images/media/150", false, "");*/
		}
		
	}

}
