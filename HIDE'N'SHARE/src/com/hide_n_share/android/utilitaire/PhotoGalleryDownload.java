package com.hide_n_share.android.utilitaire;


import java.io.File;

import com.hide_n_share.modele.classeStatic.GestionFichier;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
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
	
	//Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
	
	public static void creationDossier(){
		
		
		File dossierPrincipal = new File(Data.cheminDeSauvegarde);
		GestionFichier.fluxEnFichier(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+"a.txt","aaa".getBytes());
		if (!dossierPrincipal.exists() || !dossierPrincipal.isDirectory()) {
			System.out.println(dossierPrincipal.mkdirs());
		}else{
			
		}
		
	}
	
	public static String genererNomInexistant(File directory){
		
		return null;
	}
}
