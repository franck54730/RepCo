package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


import modele.Modele;

public class EcouteurNouveau implements ActionListener{
	
	protected Modele modele;
	
	public EcouteurNouveau(Modele m) {
		modele = m;
	}

	public void actionPerformed(ActionEvent e) {
		JTextField largeur = new JTextField();
		JTextField hauteur = new JTextField();
		final JComponent[] inputs = new JComponent[] {
				new JLabel("Largeur du labyrinthe"),
				largeur,
				new JLabel("Hauteur du labyrinthe"),
				hauteur,
		};
		JOptionPane.showMessageDialog(null, inputs, "Taille du labyrinthe", JOptionPane.PLAIN_MESSAGE);
		

		modele.initLabyrinthe(Integer.parseInt(largeur.getText()), Integer.parseInt(hauteur.getText()));
		modele.newVueGraphiquePlateau();
	}

}
