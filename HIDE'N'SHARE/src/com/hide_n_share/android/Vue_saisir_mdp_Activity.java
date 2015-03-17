package com.hide_n_share.android;

import com.hide_n_share.android.utilitaire.Data;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Vue_saisir_mdp_Activity extends Activity implements OnClickListener{
	
	private Button boutonValider;
	
	String image_a_devoiler = "";
	String mdp="";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_saisir_mdp);
        
        boutonValider = (Button)findViewById(R.id.boutonVueCacherCacherTexte);
        boutonValider.setOnClickListener(this);


		image_a_devoiler = getIntent().getStringExtra(Data.EXTRA_IMG_A_DEVOILER);
		
    }
    
	public void onClick(View arg0) {
		if (arg0.equals(boutonValider)) {
			
			mdp = ((EditText) findViewById(R.id.zoneSaisieVueCacherTexte)).getText().toString();
			
			String nomFichierCacher = ((EditText)findViewById(R.id.nomFichierCache)).getText().toString();
			
			
			Intent intent = new Intent(this, Vue_chargement_devoiler_Activity.class);
			
			intent.putExtra(Data.EXTRA_IMG_A_DEVOILER,image_a_devoiler);
			intent.putExtra(Data.EXTRA_MDP, mdp);
			intent.putExtra(Data.EXTRA_NOM_FICHIER_CACHER, nomFichierCacher);
			startActivity(intent);
		}
		
	} 
}


