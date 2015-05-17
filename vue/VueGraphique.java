package vue;

import java.awt.Dimension;
import java.awt.Point;

import modele.Labyrinthe.TypeCase;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import recherche.Historique;

import controleur.EcouteurCase;

import modele.Labyrinthe;
import modele.Modele;

public class VueGraphique extends JPanel implements Observer{

	protected Modele modele;
	protected Case[][] cases;
	protected JPanel panelVue;
	protected int caseLargeur = 75;
	protected ArrayList<Point> parcouru;
	
	
	public VueGraphique(Modele m) {
		modele = m;
		modele.addObserver(this);
		modele.setVueGraphique(this);
		parcouru = new ArrayList<Point>();
		panelVue = new JPanel();
		this.add(panelVue);
	}
	
	/**
	 * methode qui reinitialise le plateau sert pour recommencer un labyrinthe
	 */
	public void newPlateau(){
		panelVue.setPreferredSize(new Dimension(modele.getLargeur()*caseLargeur, modele.getHauteur()*caseLargeur));
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
		construireParcouru();
		for(int i = 0; i < modele.getLargeur(); i++){
			for(int j = 0; j < modele.getHauteur(); j++){
				cases[i][j].setTypeCase(modele.getTypePourCase(i, j));
			}
		}
		printParcouru();
		printChemin();
	}
	
	public void printChemin(){
		int i = 0;
		for(Point p : modele.getChemin()){
			if(i != 0 && i != modele.getChemin().size()-1){
				cases[p.x][p.y].setTypeCase(TypeCase.CHEMIN);
			}
			i++;
		}
	}
	
	public void printParcouru(){
		int i = 0;
		for(Point p : parcouru){
			if(modele.getTypePourCase(p.x, p.y) == TypeCase.PASSAGE){
				cases[p.x][p.y].setTypeCase(TypeCase.HISTORIQUE);
			}
			i++;
		}
	}
	
	public void construireParcouru(){
		Historique h = modele.getHistorique();
		parcouru.clear();
		for(int i = 0; i < h.size(); i++){
			Labyrinthe etape = (Labyrinthe) h.getValue(i);
			Point p = new Point(etape.getXJoueur(),etape.getYJoueur());
			if(!parcouru.contains(p)){
				parcouru.add(p);
			}
		}
	}
}
