package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;

import modele.Modele;
 
class MainClass extends JFrame{
	
	public MainClass(){
		super("Projet RepCo - Résolution de labyrinthe");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Modele m = new Modele();
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
	}
	
	public static void main(String[] args) {
        new MainClass() ;
    }
	
}