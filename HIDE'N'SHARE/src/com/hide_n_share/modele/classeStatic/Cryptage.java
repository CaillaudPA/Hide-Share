package com.hide_n_share.modele.classeStatic;


import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Cryptage{


	public static byte[] chiffrement(byte[] octetsAchiffrer, byte[] cle) throws BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException {
		SecretKeySpec clef = new SecretKeySpec(cle,"blowfish");
		Cipher cipher=Cipher.getInstance("blowfish");
		cipher.init(Cipher.ENCRYPT_MODE,clef);			
		return cipher.doFinal(octetsAchiffrer);
	}

	public static byte[] deChiffrement(byte[] octetsADechiffrer, byte[] cle) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		//Blowfish
		SecretKeySpec clef = new SecretKeySpec(cle,"blowfish");
		Cipher cipher=Cipher.getInstance("blowfish");
		cipher.init(Cipher.DECRYPT_MODE,clef);
		return cipher.doFinal(octetsADechiffrer);
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