package com.hide_n_share.modele.steganographie;

public class Enveloppe{
	private String enveloppe;

	public Enveloppe(String cheminAcces){
		this.enveloppe = cheminAcces;
	}

	public String getEnveloppe(){
		return enveloppe;
	}
	
	public void setEnveloppe(String nouvelleEnveloppe){
		enveloppe = nouvelleEnveloppe;
	}
}
//javac -d bin -cp bin src/steganographie/*.java