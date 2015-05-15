package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RepaintManager;

import recherche.LargeurDAbord;


import modele.Labyrinthe;
import modele.Modele;

public class EcouteurLargeur implements ActionListener{
	
	protected Modele modele;
	
	public EcouteurLargeur(Modele m) {
		modele = m;
	}

	public void actionPerformed(ActionEvent e) {
		LargeurDAbord largeur = new LargeurDAbord();
		Labyrinthe chemin = (Labyrinthe) largeur.existeChemin(modele.getLabyrinthe(), modele.getHistorique());
		modele.recupereChemin(chemin);
		modele.miseAJour();
	}

}
