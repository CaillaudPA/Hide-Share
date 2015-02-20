package com.hide_n_share.android.utilitaire;


import java.io.File;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class PhotoGalleryDownload {

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
		
		File aRetourner = new File(directory+"/"+i+"."+extensionFichier);
		return aRetourner.getPath();
	}
}
