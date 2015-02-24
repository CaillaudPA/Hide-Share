package com.hide_n_share.android;

import java.io.File;

import com.hide_n_share.android.utilitaire.Data;
import com.hide_n_share.android.utilitaire.FonctionUtile;
import com.hide_n_share.android.utilitaire.PopupErreur;
import com.hide_n_share.modele.steganographie.Stegano_image;

import android.app.Activity;
import android.os.Bundle;

public class Vue_chargement_dissimuler_Activity extends Activity {

    String pathLettre = "";
    String pathEnveloppe = "";
    String motsDePasse = "";
    boolean compresser=false;

    String imageDestination = FonctionUtile.genererNomFichierInexistant(Data.cheminDossierDissimuler, "png");

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_chargement);

        pathLettre = getIntent().getStringExtra(Data.EXTRA_LETTRE);
        pathEnveloppe = getIntent().getStringExtra(Data.EXTRA_ENVELOPPE);
        compresser = getIntent().getBooleanExtra(Data.EXTRA_COMPRESSER, false);
        motsDePasse = getIntent().getStringExtra(Data.EXTRA_MDP);

        //new PopupErreur().display(this, pathLettre+"\n"+pathEnveloppe+"\n"+compresser+"\n"+motsDePasse);





        Stegano_image stegano = new Stegano_image(pathLettre, pathEnveloppe);
        stegano.dissimulerDonnee(imageDestination, compresser, motsDePasse);
        FonctionUtile.actualiseMedia(this, new File(imageDestination));
        finish();

    }
    //à appeler a la fin du chargement !
    public void finish(){
        new PopupErreur().display(this, "la nouvelle image est cacher dans:\n"+ imageDestination);
    }
}

