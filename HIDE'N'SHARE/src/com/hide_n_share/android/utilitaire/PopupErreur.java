package com.hide_n_share.android.utilitaire;

import com.hide_n_share.android.Acceuil;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class PopupErreur extends DialogFragment {
	private String msg = "message non définie";
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		 
		 builder.setMessage(msg)
		 	.setPositiveButton("quitter l'aplication", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       //quitter l'application 
                	   System.exit(0);
                	   
                   }}).setNegativeButton("retour a l'acceuil", new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int id) {
                           // lancer l'activité acceuil
                    	   Intent intent = new Intent(getActivity(), Acceuil.class);
                    	   getActivity().startActivityForResult(intent, 0);
                    	   System.exit(0);
                       }
                   });	 
		 return builder.create();
	}
	
	public void setMsg(String msgErreur){
		this.msg = msgErreur;
	}
	
	public void setMsg(boolean msgErreur){
		if (msgErreur)
			this.msg = "true";
		else
			this.msg = "false";
	}
	
	public void display(Activity fm,String texte){	
		this.setMsg(texte);
		this.show(fm.getFragmentManager(), "test");	
	}
	
	public void display(Activity fm,boolean bool){	
		this.setMsg(bool);
		this.show(fm.getFragmentManager(), "test");	
	}
	
	
}
