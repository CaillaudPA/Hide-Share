package com.hide_n_share.android.utilitaire;

import android.os.Environment;

public class Data {
	//échange d'information entre activité
	public final static String EXTRA_LETTRE = "lettre";
	public final static String EXTRA_ENVELOPPE = "enveloppe";
	public final static String EXTRA_COMPRESSER = "compresser ?";
	public final static String EXTRA_MDP = "mots de passe";
	public final static String EXTRA_IMG_A_DEVOILER = "image a dévoiler";
	
	
	//chemin du dossier de destination de l'image avec la donnée cachée
	public final static String cheminDeSauvegarde = 
			Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+"/HIDE_N_SHARE";
	
	public final static String cheminDossierDissimuler = cheminDeSauvegarde+"/dissimuler";
	public final static String cheminDossierDevoiler = cheminDeSauvegarde+"/devoiler";
	
	
	public final static int photoAPrendre = 1;
	public final static int photoExistante = 2;
	public final static int fichierQuelconque = 3;
	
	
	
	
}