package vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controleur.EcouteurNouveau;
import controleur.EcouteurReinit;

import modele.Modele;

public class MenuBar extends JMenuBar implements Observer {

	private Modele modele;
	private JMenuItem itemNouveau;
	private JMenuItem itemReinit;
	
	public MenuBar(Modele m){
		modele = m;
		modele.addObserver(this);
		itemNouveau = new JMenuItem("Nouveau");
		itemNouveau.addActionListener(new EcouteurNouveau(modele));
		itemReinit = new JMenuItem("Réinitialiser");
		itemReinit.addActionListener(new EcouteurReinit(modele));
		JMenu menuFichier = new JMenu("Fichier");
		menuFichier.add(itemNouveau);
		add(menuFichier);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Stub de la méthode généré automatiquement
		
	}
}
