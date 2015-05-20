package vue;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.ScrollPane;

import modele.Labyrinthe.TypeCase;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import recherche.Historique;

import controleur.EcouteurCase;

import modele.Labyrinthe;
import modele.Modele;

public class VueGraphique extends JPanel implements Observer, MouseMotionListener, MouseListener{

	protected Modele modele;
	protected Case[][] cases;
	protected JPanel panelVue;
	protected JScrollPane scrollPane;
	protected int caseLargeur = 75;
	protected ArrayList<Point> parcouru;
	private int xOri;
	private int yOri;
	private int x;
	private int y;
	
	
	public VueGraphique(Modele m) {
		modele = m;
		modele.addObserver(this);
		modele.setVueGraphique(this);
		parcouru = new ArrayList<Point>();
		panelVue = new JPanel();
		scrollPane = new JScrollPane();
		scrollPane.getViewport().add( panelVue );
		scrollPane.setAutoscrolls(true);

		this.add(scrollPane);
		panelVue.addMouseMotionListener(this);
		panelVue.addMouseListener(this);;
		scrollPane.addMouseMotionListener(this);
		scrollPane.addMouseListener(this);
		addMouseMotionListener(this);
		addMouseListener(this);
		resize();
	}
	
	public void resize(){
		int l = modele.getLargeur()*caseLargeur;
		int h = modele.getHauteur()*caseLargeur;
		
		int sl = (int)modele.getDimension().getWidth();
		int sh = (int)modele.getDimension().getHeight();
		if(sl>l+45){
			sl = l+40;
		}
		if(sh>h+105){
			sh = h+105;
		}
		panelVue.setPreferredSize(new Dimension(l, h));
		System.out.println(sl+" "+ sh);
		scrollPane.setPreferredSize(new Dimension(sl-20, sh-100));
	}
	
	/**
	 * methode qui reinitialise le plateau sert pour recommencer un labyrinthe
	 */
	public void newPlateau(){
		resize();
		cases = new Case[modele.getLargeur()][modele.getHauteur()];
		panelVue.removeAll();
		panelVue.setLayout(new GridLayout(modele.getHauteur(), modele.getLargeur()));
		for(int i = 0; i < modele.getHauteur(); i++){
			for(int j = 0; j < modele.getLargeur(); j++){
				cases[j][i] = new Case(j,i,modele.getTypePourCase(j, i));
				cases[j][i].addMouseMotionListener(this);
				cases[j][i].addMouseListener(this);
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
		repaint();
		panelVue.revalidate();
		panelVue.repaint();
		scrollPane.revalidate();
		scrollPane.repaint();
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

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Stub de la méthode généré automatiquement

		//plus c'est proche de 0 plus c'est sensible
		int sensi = 2;
		int dep = 5;
		boolean gauche = xOri>arg0.getXOnScreen() && Math.abs(x-arg0.getXOnScreen())>sensi;
		boolean droite = xOri<arg0.getXOnScreen() && Math.abs(x-arg0.getXOnScreen())>sensi;
		boolean haut = yOri>arg0.getYOnScreen() && Math.abs(y-arg0.getYOnScreen())>sensi;
		boolean bas = yOri<arg0.getYOnScreen() && Math.abs(y-arg0.getYOnScreen())>sensi;
		x = arg0.getXOnScreen();
		y = arg0.getYOnScreen();
//		System.out.println("gauche : "+gauche);
//		System.out.println("droite : "+droite);
//		System.out.println("haut : "+haut);
//		System.out.println("bas : "+bas);
		JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
		JScrollBar horizontalScrollBar = scrollPane.getHorizontalScrollBar();
		if(bas){
			verticalScrollBar.setValue(verticalScrollBar.getValue()-dep);
		}
		if(haut){
			verticalScrollBar.setValue(verticalScrollBar.getValue()+dep);
		}
		if(droite){
			horizontalScrollBar.setValue(horizontalScrollBar.getValue()-dep);
		}
		if(gauche){
			horizontalScrollBar.setValue(horizontalScrollBar.getValue()+dep);
		}
		//Rectangle r = new Rectangle(arg0.getX()+intX, arg0.getY(), 1, 1);
        //scrollPane.scrollRectToVisible(r);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Stub de la méthode généré automatiquement
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Stub de la méthode généré automatiquement

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Stub de la méthode généré automatiquement
		xOri = arg0.getXOnScreen();
		yOri = arg0.getYOnScreen();
		x = arg0.getXOnScreen();
		y = arg0.getYOnScreen();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Stub de la méthode généré automatiquement
		
	}
}
