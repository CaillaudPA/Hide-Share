package com.hide_n_share.modele.classeStatic;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;

public class GestionFichier{
	//convertie un fichier en flux Binaire
	public static byte[] fichierEnFlux(String cheminAcces) {
		FileInputStream fileInputStream=null;
        File file = new File(cheminAcces);
        byte[] bFile = new byte[(int) file.length()];
 
        try {
		    fileInputStream = new FileInputStream(file);
		    fileInputStream.read(bFile);
		    fileInputStream.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
        return bFile;
	}



	//convertis un object java en fichier 
	public static void fluxEnFichier(String cheminDeDestination,byte[] sauvegarde){
		try{
			FileOutputStream fichier = new FileOutputStream(cheminDeDestination);
			fichier.write(sauvegarde);
			fichier.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	//affiche toute les valeur d'un tableau de byte
	public static String toStringBytes(byte[] octets){
		String aRetourner = "[";
		for(int i = 0;i<octets.length;i++){
			if (i == 0){
				aRetourner += " "+octets[i];
			}else{
				aRetourner += ", "+octets[i];
			}
		}
		
		aRetourner += "]";
		return aRetourner;
	}
}
