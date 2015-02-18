package com.hide_n_share.android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class popupErreur extends DialogFragment {

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 
		 builder.setMessage("message d'erreur")
		 	.setPositiveButton("quitter l'aplication", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       //quitter l'application 
                   }}).setNegativeButton("retour a l'acceuil", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                           // lancer l'activité acceuil
                       }
                   });		
		 return builder.create();
	}
	
}
