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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class Vue_saisir_mdp_Activity extends Activity{
	
	private Button boutonValider;
	private Spinner extensions;
	private EditText editText;
	
	String image_a_devoiler = "";
	String mdp="";
	Vue_saisir_mdp_Activity act = this;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_saisir_mdp);
        
        boutonValider = (Button)findViewById(R.id.boutonVueCacherCacherTexte);
        extensions = (Spinner) findViewById(R.id.extFichierCache); 
        boutonValider.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				mdp = editText.getText().toString();
				if(validerMDP(mdp)){
					String nomFichierCacher = extensions.getSelectedItem().toString();
					Toast.makeText(getApplicationContext(), nomFichierCacher, Toast.LENGTH_LONG).show();
					Intent intent = new Intent(act, Vue_chargement_devoiler_Activity.class);
					intent.putExtra(Data.EXTRA_IMG_A_DEVOILER,image_a_devoiler);
					intent.putExtra(Data.EXTRA_MDP, mdp);
					intent.putExtra(Data.EXTRA_NOM_FICHIER_CACHER, nomFichierCacher);
					startActivity(intent);
				}else{
					Toast.makeText(act, "le mots de passe doit contenir entre 4 et 56 caractère", Toast.LENGTH_LONG).show();
				}
			}
		});


		image_a_devoiler = getIntent().getStringExtra(Data.EXTRA_IMG_A_DEVOILER);
		
		editText = (EditText) findViewById(R.id.zoneSaisieVueCacherTexte);

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


