package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import modele.Labyrinthe.TypeCase;
import modele.Modele;

public class EcouteurBoxType implements ActionListener{
	
	protected Modele modele;
	protected JComboBox boxType;
	public EcouteurBoxType(Modele m, JComboBox type) {
		modele = m;
		boxType = type;
	}

	public void actionPerformed(ActionEvent e) {
		switch(boxType.getSelectedIndex()){
		case 0://Mur
			modele.setTypeSelectionner(TypeCase.MUR);
			break;
		case 1://Passage
			modele.setTypeSelectionner(TypeCase.PASSAGE);
			break;
		case 2://Passage
			modele.setTypeSelectionner(TypeCase.JOUEUR);
			break;
		case 3://Passage
			modele.setTypeSelectionner(TypeCase.FINISH);
			break;
		}
		System.out.println(modele.getTypeSelectionner());
	}

}
