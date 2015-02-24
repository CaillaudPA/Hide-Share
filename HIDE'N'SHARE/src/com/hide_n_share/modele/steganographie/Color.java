package com.hide_n_share.modele.steganographie;

import java.nio.ByteBuffer;

public class Color {

	private int red;
	private int green;
	private int blue;
	private int RGB;
	
	public Color(int couleur){
		byte[] tmp = this.intEnQuatreOctets(couleur);
		red = android.graphics.Color.red(couleur);
		green = android.graphics.Color.green(couleur);
		blue = android.graphics.Color.blue(couleur);
        RGB = android.graphics.Color.argb(-1,red,green,blue);
	}
	
	public Color(int r, int g, int b){
		red = r;
		green = g;
		blue = b;
        RGB = android.graphics.Color.argb(-1,red,green,blue);
	}
	
	
	public void setRGB(){
		byte[] octets = {(byte) red,(byte) green,(byte) blue,-1};
		int aRetourner = 0;
		for(int i =octets.length-1;i>=0;i--){
			aRetourner = (aRetourner << 8)| (octets[i] & 0xFF);
		}
		RGB = aRetourner;
	}
	
	private byte[] intEnQuatreOctets(int a ){
		byte[] tmp = ByteBuffer.allocate(4).putInt(a).array();
		byte[] aRetourner = new byte[4];
		aRetourner[0] = tmp[3];
		aRetourner[1] = tmp[2];
		aRetourner[2] = tmp[1];
		aRetourner[3] = tmp[0];
		return aRetourner;
	}
	
	
	
	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}
	
	public int getRGB(){
		return RGB;
	}	
}