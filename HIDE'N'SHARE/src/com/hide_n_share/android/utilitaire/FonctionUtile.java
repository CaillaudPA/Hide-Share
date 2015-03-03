package com.hide_n_share.android.utilitaire;


import java.io.File;
import java.util.Date;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.*;
import android.net.Uri;
import android.provider.MediaStore;
import android.content.Intent;

import com.hide_n_share.modele.steganographie.Stegano_image;

public class FonctionUtile {

	public static String getRealPathFromURI(Activity act, Uri contentUri) {
	    String path = null;
	    String[] proj = { MediaStore.MediaColumns.DATA };
	    Cursor cursor = act.getContentResolver().query(contentUri, proj, null, null, null);
	    if (cursor.moveToFirst()) {
	       int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
	       path = cursor.getString(column_index);
	    }
	    cursor.close();
	    return path;
	}
	
	public static void creationDossier(){
		File dossierPrincipal = new File(Data.cheminDeSauvegarde);
		File dossierDissimuler = new File(Data.cheminDossierDissimuler);
		File dossierDevoiler = new File(Data.cheminDossierDevoiler);

		if (!dossierPrincipal.exists() && !dossierPrincipal.isDirectory()) {
			dossierPrincipal.mkdirs();
		}
		if(!dossierDissimuler.exists() && !dossierDissimuler.isDirectory()){
			dossierDissimuler.mkdirs();
		}
		if(!dossierDevoiler.exists() && !dossierDevoiler.isDirectory()){
			dossierDevoiler.mkdirs();
		}
    }

	public static String genererNomFichierInexistant(String directory,String extensionFichier){
		int i = 0;
		while((new File(directory+"/"+i+"."+extensionFichier).exists())){
			i++;
		}
        Date date = new Date();
		File aRetourner = new File(directory+"/"+date.getDay()+"_"+date.getMonth()+"_"+date.getYear()+"_"+i+"."+extensionFichier);
		return aRetourner.getPath();
	}

    public static void actualiseMedia(Activity act, File fichierAActualise){
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(fichierAActualise);
        mediaScanIntent.setData(contentUri);
        act.sendBroadcast(mediaScanIntent);
    }
}