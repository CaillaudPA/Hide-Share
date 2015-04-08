package com.hide_n_share.android;

import java.io.File;

import com.hide_n_share.android.utilitaire.Data;
import com.hide_n_share.android.utilitaire.FonctionUtile;
import com.hide_n_share.android.utilitaire.PopupErreur;
import com.hide_n_share.android.utilitaire.QuitterOuEnvoyerImg;
import com.hide_n_share.modele.steganographie.Stegano_image;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Vue_chargement_dissimuler_Activity extends Activity {
    private ProgressBar mProgress;

    String pathLettre = "";
    String pathEnveloppe = "";
    String motsDePasse = "";
    boolean compresser = false;

    String imageDestination = FonctionUtile.genererNomFichierInexistant(Data.cheminDossierDissimuler, "png");

    final Activity act = this;
    int[] chargement = {0};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_chargement);

        mProgress = (ProgressBar)findViewById(R.id.progressBar2);

        pathLettre = getIntent().getStringExtra(Data.EXTRA_LETTRE);
        pathEnveloppe = getIntent().getStringExtra(Data.EXTRA_ENVELOPPE);
        compresser = getIntent().getBooleanExtra(Data.EXTRA_COMPRESSER, false);
        motsDePasse = getIntent().getStringExtra(Data.EXTRA_MDP);


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
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                Stegano_image stegano = new Stegano_image(pathLettre, pathEnveloppe,act,chargement);
                boolean resultat = stegano.dissimulerDonnee(imageDestination, compresser, motsDePasse);                

                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mProgress.setProgress(100);
                FonctionUtile.actualiseMedia(act, new File(imageDestination));
                
                if(resultat){
                	new QuitterOuEnvoyerImg().display(act,imageDestination);
                }
                
                if(new File(Data.cheminDeSauvegarde+"/cacherTexte.txt").exists()){
                	new File(Data.cheminDeSauvegarde+"/cacherTexte.txt").delete();
                }
                if(new File(Data.cheminDeSauvegarde+"/imgTmp.png").exists()){
                	new File(Data.cheminDeSauvegarde+"/imgTmp.png").delete();
                }
                
            }
        }).start();

    }
}

