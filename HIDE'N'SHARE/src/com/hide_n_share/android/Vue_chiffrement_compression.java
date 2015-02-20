package com.hide_n_share.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;



public class Vue_chiffrement_compression extends Activity implements OnClickListener {
	
	String pathLettre = "";
	String pathEnveloppe = "";
	private boolean compresser=false;
	
	private EditText zoneSaisie;
	private Button suivant;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vue_chiffrement_compression);
		
		suivant = (Button)findViewById(R.id.boutonRetourVueChiffrement);
		suivant.setOnClickListener(this);	
		
		zoneSaisie = (EditText)findViewById(R.id.mdpVueChiffrement);
		
		ToggleButton toggle = (ToggleButton) findViewById(R.id.switchCompresserDonnee);
		
		pathLettre = getIntent().getStringExtra(Data.EXTRA_LETTRE);  
		pathEnveloppe = getIntent().getStringExtra(Data.EXTRA_ENVELOPPE);  
		
			
	}
	
	public void onClick(View arg0) {
		if(arg0.equals(suivant)){
			Intent intent = new Intent(this, Vue_choix_enveloppe_Activity.class);
			intent.putExtra(Data.EXTRA_LETTRE,pathLettre);
			intent.putExtra(Data.EXTRA_ENVELOPPE,pathEnveloppe);
			intent.putExtra(Data.EXTRA_LETTRE,pathEnveloppe);
			
			startActivity(intent);
			finish();
			
			
		}
	}
	
	public void onToggleClicked(View view) {
		compresser = ((ToggleButton) view).isChecked();
	}

}
