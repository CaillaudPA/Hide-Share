package com.hide_n_share.android.utilitaire;

import java.io.File;

import com.hide_n_share.android.Acceuil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class QuitterOuEnvoyerImg extends DialogFragment {
	private String cheminImage = "";
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 
		 builder.setMessage("envoyer l'image contenant les données cachées ?")
		 	.setPositiveButton("quitter l'aplication", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       //quitter l'application 
                       System.exit(0);
                	   
                   }}).setNegativeButton("envoyer l'image", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                           Intent intent = new Intent(Intent.ACTION_SEND);
                           intent.putExtra("sms_body", "message");
                           intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(cheminImage)));
                           intent.setType("image/png"); 
                           startActivity(Intent.createChooser(intent,"Send"));
                       }
                   });
		 return builder.create();
	}
	
	public void setCheminImage(String cheminImg){
		this.cheminImage = cheminImg;
	}
	
	
	public void display(Activity fm, String chemin){	
		this.setCheminImage(chemin);
		this.show(fm.getFragmentManager(), "test");	
	}
}
