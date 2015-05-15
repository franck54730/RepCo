package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import recherche.LargeurDAbord;


import modele.Modele;

public class EcouteurLargeur implements ActionListener{
	
	protected Modele modele;
	
	public EcouteurLargeur(Modele m) {
		modele = m;
	}

	public void actionPerformed(ActionEvent e) {
		LargeurDAbord largeur = new LargeurDAbord();
		boolean chemin = largeur.existeChemin(modele.getLabyrinthe(), modele.getHistorique());
		System.out.println("existe t il un chemin : "+(chemin?"vrai":"faux"));
		modele.miseAJour();
	}

}
