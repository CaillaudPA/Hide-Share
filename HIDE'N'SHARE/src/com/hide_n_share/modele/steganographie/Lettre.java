package com.hide_n_share.modele.steganographie;

public class Lettre{
	private String message;

	public Lettre(String cheminAcces){
		this.message = cheminAcces;
	}

	public String getLettre(){
		return message;
	}
}

//javac -d bin -cp bin src/steganographie/*.java