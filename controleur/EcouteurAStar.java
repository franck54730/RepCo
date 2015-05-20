package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modele.Labyrinthe;
import modele.Modele;
import recherche.AStar;
import recherche.ProfondeurDAbord;

public class EcouteurAStar implements ActionListener {

	protected Modele modele;

	public EcouteurAStar(Modele m) {
		modele = m;
	}

	public void actionPerformed(ActionEvent e) {
		modele.reinitialiser();
		AStar astar = new AStar(modele);
		Thread t = new Thread(astar);
		t.start();
		modele.miseAJour();
	}

}
