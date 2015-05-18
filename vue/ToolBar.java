package vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import controleur.EcouteurAStar;
import controleur.EcouteurBoxType;
import controleur.EcouteurLargeur;
import controleur.EcouteurProfondeur;

import modele.Constantes;
import modele.Modele;

public class ToolBar extends JToolBar implements Observer {

	private Modele modele;
	private JComboBox typeList;
	private JButton buttonLargeur;
	private JButton buttonProfondeur;
	private JButton buttonAstar;
	
	public ToolBar(Modele m){
		modele = m;
		modele.addObserver(this);
		
		typeList = new JComboBox(Constantes.LIST_TYPE_CASE_STRING);
		typeList.setSelectedIndex(0);
		typeList.addActionListener(new EcouteurBoxType(modele, typeList));
		add(new JLabel("Placer un : "));
		add(typeList);
		add(new Separator());
		buttonLargeur = new JButton("Largeur");
		buttonLargeur.addActionListener(new EcouteurLargeur(modele));
		add(buttonLargeur);
		buttonProfondeur = new JButton("Profondeur");
		buttonProfondeur.addActionListener(new EcouteurProfondeur(modele));
		add(buttonProfondeur);
		buttonAstar = new JButton("A*");
		buttonAstar.addActionListener(new EcouteurAStar(modele));
		add(buttonAstar);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Stub de la méthode généré automatiquement
		
	}
}
