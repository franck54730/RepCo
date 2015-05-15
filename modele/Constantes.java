package modele;

import javax.swing.ImageIcon;

import vue.Case;

public class Constantes {

	public static final ImageIcon ICON_MUR = new ImageIcon(Case.class.getResource("/image/mur.png"));
	public static final ImageIcon ICON_PASSAGE = new ImageIcon(Case.class.getResource("/image/passage.png"));
	public static final ImageIcon ICON_JOUEUR = new ImageIcon(Case.class.getResource("/image/joueur.png"));
	public static final ImageIcon ICON_FINISH = new ImageIcon(Case.class.getResource("/image/finish.png"));
	public static final String[] LIST_TYPE_CASE_STRING = {"Mur","Passage","Joueur","Arrivé"};
	
	public static String[] getListTypeCaseString(){
		String[] rep = {"Mur","Passage","Joueur","Arrivé"};
		return rep;
	}
}
