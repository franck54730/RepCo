package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import modele.Modele;
import modele.Constantes.Vitesse;

public class EcouteurDirect implements ActionListener{
	
	protected Modele modele;
	
	public EcouteurDirect(Modele m) {
		modele = m;
	}

	public void actionPerformed(ActionEvent e) {
		modele.setVitesse(Vitesse.DIRECT);
		System.out.println(modele.getVitesse());
		modele.miseAJour();
	}

}
