package com.hide_n_share.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Vue_cacher_Activity extends Activity implements OnClickListener {
	
	private Button photoExist;
	private Button photoAPrendre;
	private Button fichierQuelconque;
	private Button cacherTexte;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_cacher);
        
        photoExist = (Button)findViewById(R.id.boutonVueCacherPhotoExistant);
        photoExist.setOnClickListener(this);
        
        photoAPrendre = (Button)findViewById(R.id.boutonVueCacherPhotoAPrendre);
        photoAPrendre.setOnClickListener(this);
        
        fichierQuelconque = (Button)findViewById(R.id.boutonVueCacherFichierQuelconque);
        fichierQuelconque.setOnClickListener(this);
        
        cacherTexte = (Button)findViewById(R.id.boutonVueCacherCacherTexte);
        cacherTexte.setOnClickListener(this);
    }


	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.equals(photoExist)){
			//activité: galerie photo, puis retours vers l'activité choix_enveloppe
			
		}else if(arg0.equals(photoAPrendre)){
			//activité apareil photo puis retours sur l'activité choix_enveloppe apres avoirs pris la photo
			
		}else if(arg0.equals(fichierQuelconque)){
			//activité gestionnaire de fichier puis retours vers choix_enveloppe
			
		}else{
			Intent intent = new Intent(this, Vue_saisir_mdp_Activity.class);
			startActivity(intent);
			
		}
	} 

}
