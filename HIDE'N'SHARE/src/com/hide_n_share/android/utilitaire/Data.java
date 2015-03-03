package com.hide_n_share.android.utilitaire;

import android.os.Environment;

public class Data {
	//échange d'information entre activité
	public final static String EXTRA_LETTRE = "lettre";
	public final static String EXTRA_ENVELOPPE = "enveloppe";
	public final static String EXTRA_COMPRESSER = "compresser ?";
	public final static String EXTRA_MDP = "mots de passe";
	public final static String EXTRA_IMG_A_DEVOILER = "image a dévoiler";
	public final static String EXTRA_NOM_FICHIER_CACHER = "nomFichierCacher";
	
	
	
	//chemin du dossier de destination de l'image avec la donnée cachée
	public final static String cheminDeSauvegarde = 
			Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+"/HIDE_N_SHARE";
	public final static String fichierTexteCacher = cheminDeSauvegarde + "/cacherTexte.txt";
	public final static String imageConvertie = Data.cheminDeSauvegarde+"/imgTmp.png";
	
	public final static String cheminDossierDissimuler = cheminDeSauvegarde+"/HNS_dissimuler";
	public final static String cheminDossierDevoiler = cheminDeSauvegarde+"/HNS_devoiler";
	
	
	
	public final static int photoAPrendre = 1;
	public final static int photoExistante = 2;
	public final static int fichierQuelconque = 3;
	
}