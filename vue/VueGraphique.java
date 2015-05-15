package vue;

import java.awt.Dimension;
import modele.Labyrinthe.TypeCase;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controleur.EcouteurCase;

import modele.Modele;

public class VueGraphique extends JPanel implements Observer{

	protected Modele modele;
	protected Case[][] cases;
	protected JPanel panelVue;
	
	
	public VueGraphique(Modele m) {
		// TODO Auto-generated constructor stub
		modele = m;
		modele.addObserver(this);
		modele.setVueGraphique(this);
		panelVue = new JPanel();
		this.add(panelVue);
	}
	
	/**
	 * methode qui reinitialise le plateau sert pour recommencer un labyrinthe
	 */
	public void newPlateau(){
		panelVue.setPreferredSize(new Dimension(modele.getLargeur()*50, modele.getHauteur()*50));
		cases = new Case[modele.getLargeur()][modele.getHauteur()];
		panelVue.removeAll();
		panelVue.setLayout(new GridLayout(modele.getHauteur(), modele.getLargeur()));
		for(int i = 0; i < modele.getHauteur(); i++){
			for(int j = 0; j < modele.getLargeur(); j++){
				cases[j][i] = new Case(j,i,modele.getTypePourCase(j, i));
				cases[j][i].addActionListener(new EcouteurCase(modele, j, i));
				panelVue.add(cases[j][i]);
//				panelVue.add(new JLabel(i+" "+j));
			}
		}
//		panelVue.setLayout(new GridLayout(modele.getHauteur(), modele.getLargeur()));
//		panelVue.setPreferredSize(new Dimension(modele.getLargeur()*50, modele.getHauteur()*50));
//		Case c;
//		c = new Case(0,0, TypeCase.MUR);
//		c.addActionListener(new EcouteurCase(modele, 0, 0));
//		panelVue.add(c, modele.getHauteur()-0, 0);
//		c = new Case(0, 1, TypeCase.PASSAGE);
//		c.addActionListener(new EcouteurCase(modele, 0, 1));
//		panelVue.add(c, modele.getHauteur()-0, 1);
//		c = new Case(0, 2, TypeCase.PASSAGE);
//		c.addActionListener(new EcouteurCase(modele, 0, 2));
//		panelVue.add(c, modele.getHauteur()-0, 2);
//		c = new Case(1, 0, TypeCase.MUR);
//		c.addActionListener(new EcouteurCase(modele, 1, 0));
//		panelVue.add(c, modele.getHauteur()-1, 0);
//		c = new Case(1, 1, TypeCase.PASSAGE);
//		c.addActionListener(new EcouteurCase(modele, 1, 1));
//		panelVue.add(c, modele.getHauteur()-1, 1);
//		c = new Case(1, 2, TypeCase.PASSAGE);
//		c.addActionListener(new EcouteurCase(modele, 1, 2));
//		panelVue.add(c, modele.getHauteur()-1, 2);
		revalidate();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		for(int i = 0; i < modele.getLargeur(); i++){
			for(int j = 0; j < modele.getHauteur(); j++){
				cases[i][j].setTypeCase(modele.getTypePourCase(i, j));
			}
		}
	}
	
}