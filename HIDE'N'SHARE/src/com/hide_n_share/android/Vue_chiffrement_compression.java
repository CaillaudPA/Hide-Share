package com.hide_n_share.android;

import com.hide_n_share.android.utilitaire.Data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TextView.OnEditorActionListener;

public class Vue_chiffrement_compression extends Activity{
	
	String pathLettre = "";
	String pathEnveloppe = "";
	String motsDePasse = "";
	boolean compresser=false;
	
	Vue_chiffrement_compression act = this ;
	
	private EditText zoneSaisie;
	private Button suivant;
	private Switch switch1;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vue_chiffrement_compression);
		
		
		suivant = (Button)findViewById(R.id.boutonValiderVueChiffrement);
		suivant.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				motsDePasse= zoneSaisie.getText().toString();
				if(validerMDP(motsDePasse)){
					compresser = switch1.isChecked();
					Intent intent = new Intent(act, Vue_chargement_dissimuler_Activity.class);
					intent.putExtra(Data.EXTRA_LETTRE,pathLettre);
					intent.putExtra(Data.EXTRA_ENVELOPPE,pathEnveloppe);
					intent.putExtra(Data.EXTRA_COMPRESSER,compresser);
					intent.putExtra(Data.EXTRA_MDP,motsDePasse);
					startActivity(intent);
				}else{
					Toast.makeText(act, "le mots de passe doit contenir entre 4 et 56 caractère", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		switch1 = (Switch) findViewById(R.id.switchCompresserDonnee);
		
		zoneSaisie = (EditText)findViewById(R.id.mdpVueChiffrement);

		pathLettre = getIntent().getStringExtra(Data.EXTRA_LETTRE);  
		pathEnveloppe = getIntent().getStringExtra(Data.EXTRA_ENVELOPPE);

        zoneSaisie.clearFocus();
	}
	
	public boolean validerMDP(String motDePasse){
		if(motDePasse.equals("")){
			return true;			
		}
		
		if(motDePasse.getBytes().length<4){
			return false;
		}
		
		if(motDePasse.getBytes().length>=56){
			return false;
		}
		return true;
	}
}
