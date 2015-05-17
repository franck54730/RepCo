package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import modele.Modele;

public class EcouteurReinit implements ActionListener{
	
	protected Modele modele;
	
	public EcouteurReinit(Modele m) {
		modele = m;
	}

	public void actionPerformed(ActionEvent e) {
		modele.reinitialiser();
	}

}
