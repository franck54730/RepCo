package vue;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controleur.EcouteurDirect;
import controleur.EcouteurLent;
import controleur.EcouteurNormal;
import controleur.EcouteurNouveau;
import controleur.EcouteurRapide;
import controleur.EcouteurReinit;

import modele.Constantes.Vitesse;
import modele.Modele;

public class MenuBar extends JMenuBar implements Observer {

	private Modele modele;
	private JMenuItem itemNouveau;
	private JMenuItem itemReinit;
	private JMenuItem itemRapide;
	private JMenuItem itemNormal;
	private JMenuItem itemLent;
	private JMenuItem itemDirect;
	private JMenuItem itemPasAPas;
	private JMenu menuVitesse;
	
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
		

		JMenu menuAlgo = new JMenu("Algo");
		menuVitesse = new JMenu("Vitesse");
		itemRapide = new JMenuItem("Rapide");
		itemRapide.addActionListener(new EcouteurRapide(modele));
		menuVitesse.add(itemRapide);
		itemNormal = new JMenuItem("Normal");
		itemNormal.addActionListener(new EcouteurNormal(modele));
		menuVitesse.add(itemNormal);
		itemLent = new JMenuItem("Lente");
		itemLent.addActionListener(new EcouteurLent(modele));
		menuVitesse.add(itemLent);
		menuAlgo.add(menuVitesse);

		JMenu menuType = new JMenu("Type");
		itemDirect = new JMenuItem("Direct");
		itemDirect.addActionListener(new EcouteurDirect(modele));
		menuType.add(itemDirect);
		itemPasAPas = new JMenuItem("Pas à pas");
		itemPasAPas.addActionListener(new EcouteurNormal(modele));
		menuType.add(itemPasAPas);
		menuAlgo.add(menuType);
		add(menuAlgo);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Stub de la méthode généré automatiquement
		itemLent.setEnabled(modele.getVitesse() != Vitesse.LENTE && modele.getVitesse() != Vitesse.DIRECT);
		itemNormal.setEnabled(modele.getVitesse() != Vitesse.NORMAL && modele.getVitesse() != Vitesse.DIRECT);
		itemRapide.setEnabled(modele.getVitesse() != Vitesse.RAPIDE && modele.getVitesse() != Vitesse.DIRECT);
		menuVitesse.setEnabled(modele.getVitesse() != Vitesse.DIRECT);
		itemDirect.setEnabled(modele.getVitesse() != Vitesse.DIRECT);
		itemPasAPas.setEnabled(modele.getVitesse() == Vitesse.DIRECT);
	}
}
