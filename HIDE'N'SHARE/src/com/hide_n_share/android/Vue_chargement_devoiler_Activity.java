package com.hide_n_share.android;

import com.hide_n_share.android.utilitaire.Data;
import com.hide_n_share.android.utilitaire.FonctionUtile;
import com.hide_n_share.android.utilitaire.PopupErreur;
import com.hide_n_share.modele.steganographie.Stegano_image;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.io.File;

public class Vue_chargement_devoiler_Activity extends Activity{

	private ProgressBar mProgress;
	private int i = 0;
	
	String image_a_devoiler = "";
	String mdp = "";
	String cheminDestination = "";

    final Activity act = this;
    int[] chargement = {0};
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_chargement);
        
        image_a_devoiler = getIntent().getStringExtra(Data.EXTRA_IMG_A_DEVOILER);  
        mdp = getIntent().getStringExtra(Data.EXTRA_MDP);
        cheminDestination = Data.cheminDossierDevoiler + "/" + getIntent().getStringExtra(Data.EXTRA_NOM_FICHIER_CACHER);
        
        
        //new PopupErreur().display(this, image_a_devoiler+"\n"+mdp+"\n"+cheminDestination);
        
        mProgress = (ProgressBar)findViewById(R.id.progressBar2);


        new Thread(new Runnable(){
            public void run() {
                int tmp = 0;
                while(chargement[0]<100){
                    if (tmp != chargement[0]){
                        mProgress.setProgress(chargement[0]);
                        tmp = chargement[0];
                    }
                }
            }
        }).start();

        new Thread(new Runnable(){
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Stegano_image stegano = new Stegano_image("", "",act,chargement);
                stegano.devoilerDonnee(image_a_devoiler, cheminDestination, mdp);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mProgress.setProgress(100);
            }
        }).start();
        
        
    }
	//à appeler a la fin du chargement !
	public void finish(){
		new PopupErreur().display(this, "le fichier qui etait cacher est sauvegarder dans:\n"+ cheminDestination);
	}

}
