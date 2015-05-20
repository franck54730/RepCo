package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import modele.Modele;
import modele.Constantes.Vitesse;

public class EcouteurNormal implements ActionListener{
	
	protected Modele modele;
	
	public EcouteurNormal(Modele m) {
		modele = m;
	}

	public void actionPerformed(ActionEvent e) {
		modele.setVitesse(Vitesse.NORMAL);
		System.out.println(modele.getVitesse());
		modele.miseAJour();
	}

}
