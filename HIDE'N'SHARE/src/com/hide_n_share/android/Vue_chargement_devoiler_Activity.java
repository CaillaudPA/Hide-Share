package com.hide_n_share.android;

import com.hide_n_share.android.utilitaire.Data;
import com.hide_n_share.android.utilitaire.FonctionUtile;
import com.hide_n_share.android.utilitaire.PopupErreur;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class Vue_chargement_devoiler_Activity extends Activity{

	private ProgressBar mProgress;
	private int i = 0;
	
	String image_a_devoiler = "";
	String mdp = "";
	String cheminDestination = "";
	String enveloppeModifier = FonctionUtile.genererNomFichierInexistant(
			Data.cheminDossierDevoiler, "txt");
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_chargement);
        
        image_a_devoiler = getIntent().getStringExtra(Data.EXTRA_IMG_A_DEVOILER);  
        mdp = getIntent().getStringExtra(Data.EXTRA_MDP);
        cheminDestination = Data.cheminDossierDevoiler;
        
        new PopupErreur().display(this, image_a_devoiler+"\n"+mdp+"\n"+cheminDestination);        
        
        mProgress = (ProgressBar)findViewById(R.id.progressBar2);
        
        new Thread(new Runnable(){
        	public void run() {
        		// TODO Auto-generated method stub
        		while(i<100){
        			i++;
        			mProgress.setProgress(i);
        			try {
        				Thread.sleep(100);
        			} catch (InterruptedException e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}
        		}
        	}
        }).start();
        
        
    }
	//à appeler a la fin du chargement !
	public void finish(){
		new PopupErreur().display(this, "le fichier qui etait cacher est sauvegarder dans:\n"+ enveloppeModifier);
	}

}
