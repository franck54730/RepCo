package vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import controleur.EcouteurBoxType;

import modele.Constantes;
import modele.Modele;

public class ToolBar extends JToolBar implements Observer {

	private Modele modele;
	private JComboBox typeList;
	
	public ToolBar(Modele m){
		modele = m;
		modele.addObserver(this);
		
		typeList = new JComboBox(Constantes.LIST_TYPE_CASE_STRING);
		typeList.setSelectedIndex(0);
		typeList.addActionListener(new EcouteurBoxType(modele, typeList));
		add(new JLabel("Placer un : "));
		add(typeList);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Stub de la méthode généré automatiquement
		
	}
}
