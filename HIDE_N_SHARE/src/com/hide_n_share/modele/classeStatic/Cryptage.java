package com.hide_n_share.modele.classeStatic;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cryptage{


	public static byte[] chiffrement(byte[] octetsAchiffrer, byte[] cle){		
		try
		{
			SecretKeySpec clef = new SecretKeySpec(cle,"blowfish");
			Cipher cipher=Cipher.getInstance("blowfish");
			cipher.init(Cipher.ENCRYPT_MODE,clef);			
			return cipher.doFinal(octetsAchiffrer);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}

	}

	public static byte[] deChiffrement(byte[] octetsADechiffrer, byte[] cle){
		try
		{
			//Blowfish
			SecretKeySpec clef = new SecretKeySpec(cle,"blowfish");
			Cipher cipher=Cipher.getInstance("blowfish");
			cipher.init(Cipher.DECRYPT_MODE,clef);
			return cipher.doFinal(octetsADechiffrer);
		}
		catch (Exception e)
		{
			System.out.println(e);
			return null;
		}
	}
	//utilisé lorsque le system de chiffrment nécéssite une taille minimum pour la cle
	/*private String ajoutBytes(String str){
		String aRetourner = str;
		while(aRetourner.getBytes().length<32){
			aRetourner += "0";
		}
		
		System.out.println(aRetourner.getBytes().length);
		return aRetourner;
	}*/
	
	
}

//javac -d bin -cp bin src/classeStatic/*.java