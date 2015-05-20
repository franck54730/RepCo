package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import modele.Modele;
 
class MainClass extends JFrame implements ComponentListener {
	
	private boolean clicked = false;
	
	private Modele m;
	
	public MainClass(){
		super("Projet RepCo - Résolution de labyrinthe");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    m = new Modele();
	    setPreferredSize(new Dimension(500, 500));
	    VueGraphique vg = new VueGraphique(m);
	    add(vg, BorderLayout.CENTER);
	    //vg.newPlateau();
        //
        ToolBar toolBar = new ToolBar(m);
        add(toolBar, BorderLayout.PAGE_START);
	    MenuBar menu = new MenuBar(m);
	    setJMenuBar(menu);
        m.miseAJour();
	    pack() ;
        setVisible(true);
        addComponentListener(this);
	}
	
	public static void main(String[] args) {
        new MainClass() ;
    }

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		// TODO Stub de la méthode généré automatiquement
		m.setDimension(this.getSize());
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Stub de la méthode généré automatiquement
		
	}

	
	
}