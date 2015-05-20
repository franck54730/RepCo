package modele;

import javax.swing.ImageIcon;

import vue.Case;

public class Constantes {
	public static final String[] LIST_TYPE_CASE_STRING = {"Mur","Passage","Joueur","Arrivé"};
	public enum Vitesse {RAPIDE,NORMAL,LENTE,DIRECT};
	
	public static String[] getListTypeCaseString(){
		String[] rep = {"Mur","Passage","Joueur","Arrivé"};
		return rep;
	}

	public static long getVitesseAStar(Modele modele) {
		// TODO Stub de la méthode généré automatiquement
		int rep = 0;
		switch (modele.getVitesse()) {
		case LENTE:
			rep = 200;
			break;
		case NORMAL:
			rep = 100;
			break;
		case RAPIDE:
			rep = 30;
			break;
		}
		return rep;
	}

	public static long getVitesseLargeur(Modele modele) {
		// TODO Stub de la méthode généré automatiquement
		int rep = 0;
		switch (modele.getVitesse()) {
		case LENTE:
			rep = 200;
			break;
		case NORMAL:
			rep = 100;
			break;
		case RAPIDE:
			rep = 30;
			break;
		}
		return rep;
	}

	public static long getVitesseProfondeur(Modele modele) {
		// TODO Stub de la méthode généré automatiquement
		int rep = 0;
		switch (modele.getVitesse()) {
		case LENTE:
			rep = 200;
			break;
		case NORMAL:
			rep = 100;
			break;
		case RAPIDE:
			rep = 30;
			break;
		}
		return rep;
	}
}
