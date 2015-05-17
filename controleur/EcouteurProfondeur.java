package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Labyrinthe;
import modele.Modele;
import recherche.ProfondeurDAbord;

public class EcouteurProfondeur implements ActionListener {

	protected Modele modele;

	public EcouteurProfondeur(Modele m) {
		modele = m;
	}

	public void actionPerformed(ActionEvent e) {
		ProfondeurDAbord profondeur = new ProfondeurDAbord();
		Labyrinthe chemin = (Labyrinthe) profondeur.existeChemin(modele.getLabyrinthe(), modele.getHistorique());
		modele.recupereChemin(chemin);
		modele.miseAJour();
	}

}
