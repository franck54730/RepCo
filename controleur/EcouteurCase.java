package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import modele.Labyrinthe.TypeCase;
import modele.Modele;

public class EcouteurCase implements ActionListener{
	
	protected Modele modele;
	protected int x;
	protected int y;
	
	public EcouteurCase(Modele m, int x, int y) {
		modele = m;
		this.x = x;
		this.y = y;
	}

	public void actionPerformed(ActionEvent e) {
		boolean peutModifier = true;
		if(modele.getTypeSelectionner() == TypeCase.FINISH){
			if(modele.isFinishPlacer()){
				peutModifier = false;
				JOptionPane.showMessageDialog(null, "Le point d'arriver est deja plac�e veuillez le retirer pour le replacer", 
						"Attention", JOptionPane.INFORMATION_MESSAGE, null);
			}else{
				modele.changeFinishPlacer();
			}
		}else if(modele.getTypeSelectionner() == TypeCase.JOUEUR){
			if(modele.isJoueurPlacer()){
				peutModifier = false;
				JOptionPane.showMessageDialog(null, "Le joueur est deja plac�e veuillez le retirer pour le replacer", 
						"Attention", JOptionPane.INFORMATION_MESSAGE, null);
			}else{
				modele.changeJoueurPlacer();
			}
		}else{
			if(modele.getTypePourCase(x, y) == TypeCase.JOUEUR){
				modele.changeJoueurPlacer();
			}else if(modele.getTypePourCase(x, y) == TypeCase.FINISH){
				modele.changeFinishPlacer();
			}
		}
		if(peutModifier)
			modele.setTypePourCase(x, y, modele.getTypeSelectionner());
	}

}
