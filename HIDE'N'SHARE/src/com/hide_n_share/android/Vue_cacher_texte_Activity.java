package com.hide_n_share.android;

import java.io.File;

import com.hide_n_share.modele.classeStatic.GestionFichier;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Vue_cacher_texte_Activity extends Activity implements OnClickListener{

	private Button valider;
	private EditText zoneSaisie;
	
	
	final String EXTRA_LETTRE = "enveloppe";
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_cacher_texte);
        
		valider = (Button)findViewById(R.id.boutonVueCacherTexteValide);
		valider.setOnClickListener(this);	
		
		zoneSaisie = (EditText)findViewById(R.id.zoneSaisieVueCacherTexte);
    }
	
	@Override
	public void onClick(View arg0) {
		
		if(arg0.equals(valider)){
			String texteSaisie = zoneSaisie.getText().toString();
			
			if(texteSaisie.equals("")){

				new PopupErreur().display(this,"la zone de texte ne doit pas etre vide");
			}else{
				//creations fichier contenant le message à cacher
								
				File file = new File(getFilesDir(),"myfile");
				GestionFichier.fluxEnFichier(file.getPath(),texteSaisie.getBytes());
				
				Intent intent = new Intent(this, Vue_choix_enveloppe_Activity.class);
				intent.putExtra(EXTRA_LETTRE,file.getPath());
				startActivity(intent);
				finish();
			}
		}
	}
    
}
