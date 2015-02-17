package com.hide_n_share.modele.steganographie;

public abstract class Steganographie{

	protected Lettre lettre;
	protected Enveloppe enveloppe;

	public Steganographie(String l, String e)
	{
		this.lettre = new Lettre(l);
		this.enveloppe = new Enveloppe(e);
	}

	public abstract void dissimulerDonnee(String cheminEnveloppeModifier, boolean compresserLettre,String mdpChiffrement);

	public abstract void devoilerDonnee(String cheminEnveloppe,String PathLettre, String mdpDechiffrement);

	public abstract boolean verificationCompabilite(boolean compresser,String mdpChiffrement);
}

//javac -d bin -cp bin src/steganographie/*.java