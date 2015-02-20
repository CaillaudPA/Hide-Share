package com.hide_n_share.modele.steganographie;

public class simulationChargement {
	
	public void dissimuler(){
		for (int i = 0; i < 100; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
