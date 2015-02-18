package com.hide_n_share.android;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

public class PopupErreur extends DialogFragment {
	private String msg = "erreur non définie";
	private Button boutonAcceuil;
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 
		 builder.setMessage(msg)
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
	
	public void setMsg(String msgErreur){
		this.msg = msgErreur;
	}
	
}
