package com.hide_n_share.modele;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.hide_n_share.modele.steganographie.Stegano_image;


public class application {
	private static ArrayList<String> listeStegano = new ArrayList<String>();
	private static Scanner lectureClavier = new Scanner(System.in);
	
	public static void main(String[] args) {
		listeStegano.add("image");
		listeStegano.add("usb");
		
		String typeStegano = typeDeStegano();
		if(typeStegano.equals(listeStegano.get(0))){
			
			if(cacheOuDevoile().equals("cacher")){
				Stegano_image appli = new Stegano_image(cheminLettre(),cheminImage());
				appli.dissimulerDonnee(cheminSauvegardeImageModifier(),compresserLettre(),mdp());				
			}else{
				Stegano_image appli = new Stegano_image("","");
				appli.devoilerDonnee(cheminImageDevoiler(),cheminDeSauvegardeDonnee(),mdpDevoiler());
			}
			
		}		
	}
	//choix
	private static String typeDeStegano(){
		System.out.println("Liste des steganographies disponible :");
		System.out.println(listeStegano);
		System.out.println("Choisir Steganographie à utiliser :");
		return lectureClavier.nextLine();
	}
	
	private static String cheminDestination(){
		System.out.println("Chemin où est sauvegardé la lettre :");
		String reponse = lectureClavier.nextLine();
		return reponse;
		
	}
	
	//image
	//choix cacher ou dévoiler
	private static String cacheOuDevoile(){
		System.out.println("Cacher ou dévoiler ? (cacher/dévoiler)");
		String reponse = lectureClavier.nextLine();
		
		while(!((reponse.equals("cacher")) || (reponse.equals("dévoiler")))){
			System.out.println("Répondre par cacher ou par dévoiler !");
			reponse = lectureClavier.nextLine();
		}

		return reponse;
	}
	// cacher donnée ---------------------------------------------------
	
	private static String cheminLettre(){
		System.out.println("Chemin de la lettre à cacher:");
		String chemin = lectureClavier.nextLine();
		if(! (new File(chemin).exists())){
			System.out.println("Le fichier n'existe pas !");
			return cheminLettre();
		}
		return chemin;
	}
	
	private static String cheminImage(){
		System.out.println("Image à utiliser pour dissimuler les données:");
		String chemin = lectureClavier.nextLine();
		if(! (new File(chemin).exists())){
			System.out.println("Le fichier n'existe pas !");
			return cheminImage();
		}
		return chemin;
	}
	
	
	private static boolean compresserLettre(){
		System.out.println("Compresser la lettre ?(oui/non)");
		
		String reponse = lectureClavier.nextLine();
		
		while(!((reponse.equals("oui")) || (reponse.equals("non")))){
			System.out.println("Répondre par oui ou par non");
			reponse = lectureClavier.nextLine();
		}
		if(reponse.equals("oui"))
			return true;
		else
			return false;
	}	

	private static String mdp(){
		System.out.println("Entrer mdp de chiffrement de la lettre: ");
		System.out.println("Laisser vide si aucun mot de passe");
		return lectureClavier.nextLine();
	}
	
	private static String cheminSauvegardeImageModifier(){
		System.out.println("Nom de la nouvelle image modifiée:");
		return lectureClavier.nextLine();
	}
	

	//dévoilerDonnée -----------------------------------------
	
	private static String cheminImageDevoiler(){
		System.out.println("Chemin de l'image où sont caché les données:");
		String chemin = lectureClavier.nextLine();
		if(! (new File(chemin).exists())){
			System.out.println("Le fichier n'existe pas !");
			return cheminImageDevoiler();
		}
		return chemin;
	}
	
	private static String cheminDeSauvegardeDonnee(){
		System.out.println("Chemin de destination des données cacher:");
		return lectureClavier.nextLine();
	}
	
	private static boolean lettreCompresser(){
		System.out.println("La lettre est compresser ? (oui/non)");
		
		String reponse = lectureClavier.nextLine();
		
		while(!((reponse.equals("oui")) || (reponse.equals("non")))){
			System.out.println("Répondre par oui ou par non");
			reponse = lectureClavier.nextLine();
		}
		if(reponse.equals("oui"))
			return true;
		else
			return false;
	}
	
	private static String mdpDevoiler(){
		System.out.println("Entrer mdp de chiffrement de la lettre: ");
		System.out.println("Laisser vide si aucun mot de passe");
		return lectureClavier.nextLine();
	}
	
}

//java -