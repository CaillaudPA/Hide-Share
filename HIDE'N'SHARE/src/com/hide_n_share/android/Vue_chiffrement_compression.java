package com.hide_n_share.android;

import com.hide_n_share.android.utilitaire.Data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class Vue_chiffrement_compression extends Activity implements OnClickListener {
	
	String pathLettre = "";
	String pathEnveloppe = "";
	String motsDePasse = "";
	boolean compresser=false;
	
	
	private EditText zoneSaisie;
	private Button suivant;
	private Switch switch1;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vue_chiffrement_compression);
		
		
		suivant = (Button)findViewById(R.id.boutonValiderVueChiffrement);
		suivant.setOnClickListener(this);
		
		switch1 = (Switch) findViewById(R.id.switchCompresserDonnee);
		
		zoneSaisie = (EditText)findViewById(R.id.mdpVueChiffrement);


		pathLettre = getIntent().getStringExtra(Data.EXTRA_LETTRE);  
		pathEnveloppe = getIntent().getStringExtra(Data.EXTRA_ENVELOPPE);

        zoneSaisie.clearFocus();
	}
	
	public void onClick(View arg0) {
		if(arg0.equals(suivant)){
			compresser = switch1.isChecked();
			motsDePasse= zoneSaisie.getText().toString();
			
			Intent intent = new Intent(this, Vue_chargement_dissimuler_Activity.class);
			intent.putExtra(Data.EXTRA_LETTRE,pathLettre);
			intent.putExtra(Data.EXTRA_ENVELOPPE,pathEnveloppe);
			intent.putExtra(Data.EXTRA_COMPRESSER,compresser);
			intent.putExtra(Data.EXTRA_MDP,motsDePasse);
			
			startActivity(intent);
		}
	}
}
