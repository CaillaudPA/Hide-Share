package com.hide_n_share.android;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import com.hide_n_share.modele.classeStatic.GestionFichier;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Vue_cacher_texte_Activity extends Activity implements OnClickListener{

	private Button valider;
	private String texteSaisie = "";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_cacher_texte);
        
		valider = (Button)findViewById(R.id.boutonVueCacherTexteValide);
		valider.setOnClickListener(this);	
    }
	
	@Override
	public void onClick(View arg0) {
		
		if(arg0.equals(valider)){
			EditText tonEdit = (EditText)findViewById(R.id.zoneSaisieVueCacherTexte);
			texteSaisie = tonEdit.getText().toString();
			
			if(texteSaisie.equals("")){
				FragmentManager fm = getFragmentManager();
				PopupErreur a = new PopupErreur();
				a.setMsg("la zone de texte ne doit pas etre vide");
				a.show(fm, "test");
			}else{
				//creations fichier contenant le message à cacher
								
				File file = new File(getFilesDir(),"myfile");
				GestionFichier.fluxEnFichier(file.getPath(),texteSaisie.getBytes());
				
				
				
				
				Intent intent = new Intent(this, Vue_choix_enveloppe_Activity.class);
				startActivity(intent);
				finish();
			}
		}
	}
    
}
