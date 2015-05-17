package modele;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

import recherche.IJeu;
import modele.Labyrinthe.TypeCase;

public class Labyrinthe implements IJeu{

	public enum TypeCase{MUR, PASSAGE, JOUEUR, FINISH, CHEMIN, HISTORIQUE};
	
	private TypeCase[][] plateau;
	private Labyrinthe pere;
	private int hauteur;
	private int largeur;
	private int xJoueur = -1;
	private int yJoueur = -1;
	private int xFinish = -1;
	private int yFinish = -1;
	
	public int getXJoueur(){
		return xJoueur;
	}
	
	public int getYJoueur(){
		return yJoueur;
	}
	
	public void setJoueur(int x, int y){
		xJoueur = x;
		yJoueur = y;
	}
	
	public void setFinish(int x, int y){
		xFinish = x;
		yFinish = y;
	}
	
	/**
	 * constructeur initialise le plateau avec des cases de type MUR
	 * @param l
	 * @param h
	 */
	public Labyrinthe(int l, int h){
		largeur = l;
		hauteur = h;
		plateau = new TypeCase[l][h];
		initLabyrinthe();
	}
	
	public Labyrinthe(Labyrinthe labyrinthe) {
		// TODO Stub du constructeur généré automatiquement
		largeur = labyrinthe.getLargeur();
		hauteur = labyrinthe.getHauteur();
		plateau = new TypeCase[largeur][hauteur];
		xJoueur = labyrinthe.xJoueur;
		yJoueur = labyrinthe.yJoueur;
		xFinish = labyrinthe.xFinish;
		yFinish = labyrinthe.yFinish;
		for(int i = 0; i < largeur; i++){
			for(int j = 0; j < hauteur; j++){
				plateau[i][j] = labyrinthe.getTypePourCase(i, j);
			}
		}
	}

	/**
	 * initialise le plateau avec des cases de type MUR
	 */
	public void initLabyrinthe(){
		for(int i = 0; i < largeur; i++){
			for(int j = 0; j < hauteur; j++){
				plateau[i][j] = TypeCase.PASSAGE;
			}
		}
	}
	
	/**
	 * methode qui retourne le type de la case correspondant au couple x,y 
	 * @param x indice de l'absisse
	 * @param y indice de l'ordonnée
	 * @return
	 */
	public TypeCase getTypePourCase(int x, int y){
		return plateau[x][y];
	}
	
	/**
	 * methode qui affecte le type de la case correspondant au couple x,y 
	 * @param x indice de l'absisse
	 * @param y indice de l'ordonnée
	 * @param t nouveau type de la case
	 * @return
	 */
	public void setTypePourCase(int x, int y, TypeCase t) {
		plateau[x][y] = t;
	}

	public int getHauteur() {
		return hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	@Override
	public Iterator<IJeu> iterator() {
		ArrayList<IJeu> rep = new ArrayList<IJeu>();
		if(xFinish != -1 && yFinish != -1 && xJoueur != -1 && yJoueur != -1){
			boolean surBordDroit = xJoueur == largeur-1;
			boolean droitePossible = !surBordDroit?(plateau[xJoueur+1][yJoueur]!=TypeCase.MUR):false; 
			boolean surBordGauche = xJoueur == 0;
			boolean gauchePossible = !surBordGauche?(plateau[xJoueur-1][yJoueur]!=TypeCase.MUR):false; 
			boolean surBordBas = yJoueur == hauteur-1;
			boolean basPossible = !surBordBas?(plateau[xJoueur][yJoueur+1]!=TypeCase.MUR):false; 
			boolean surBordHaut = yJoueur == 0;
			boolean hautPossible = !surBordHaut?(plateau[xJoueur][yJoueur-1]!=TypeCase.MUR):false; 
			
//			System.out.println("droitePossible : "+droitePossible);
//			System.out.println("bord gauche : "+gauchePossible);
//			System.out.println("bord bas : "+basPossible);
//			System.out.println("bord haut : "+hautPossible);
			if(gauchePossible)
				rep.add(DeplacerGauche());
			if(droitePossible)
				rep.add(DeplacerDroite());
			if(hautPossible)
				rep.add(DeplacerHaut());
			if(basPossible)
				rep.add(DeplacerBas());
		}
		return rep.iterator();
	}

	@Override
	public boolean estFinal() {
		// TODO Stub de la méthode généré automatiquement
		return xJoueur == xFinish && yJoueur == yFinish;
	}
	
	@Override
	public TypeCase[][] getTab() {
		// TODO Stub de la méthode généré automatiquement
		return plateau;
	}
	
	public void setPere(Labyrinthe l){
		pere = l;
	}
	
	public Labyrinthe getPere(){
		return pere;
	}
	
	public Labyrinthe DeplacerHaut(){
		Labyrinthe rep = new Labyrinthe(this);
		rep.goToCase(xJoueur, yJoueur-1);
		rep.setPere(this);
		return rep;
	}
	
	public Labyrinthe DeplacerBas(){
		Labyrinthe rep = new Labyrinthe(this);
		rep.goToCase(xJoueur, yJoueur+1);
		rep.setPere(this);
		return rep;
	}
	
	public Labyrinthe DeplacerDroite(){
		Labyrinthe rep = new Labyrinthe(this);
		rep.goToCase(xJoueur+1, yJoueur);
		rep.setPere(this);
		return rep;
	}
	
	public Labyrinthe DeplacerGauche(){
		Labyrinthe rep = new Labyrinthe(this);
		rep.goToCase(xJoueur-1, yJoueur);
		rep.setPere(this);
		return rep;
	}
	
	/**
	 * 
	 * @param posX1 x du joueur
	 * @param posY1 y du joueur
	 * @param posX2 x de la nouvelle position
	 * @param posY2 y de la nouvelle position
	 */
	public void goToCase(int posX, int posY){
		plateau[xJoueur][yJoueur] = TypeCase.PASSAGE;
		plateau[posX][posY] = TypeCase.JOUEUR;
		xJoueur = posX;
		yJoueur = posY;
	}
	
	public String toString(){
		return " h : "+hauteur+" l : "+largeur+" finish : ("+xFinish+","+yFinish+") joueur : ("+xJoueur+","+yJoueur+")";
	}
	


    @Override
    public boolean equals(Object o){
        try{
        	Labyrinthe other = (Labyrinthe) o;
        	boolean equal = true;
        	if(other.hauteur != this.hauteur)
        		equal = false;
        	else if(other.largeur != this.largeur)
        		equal = false;
        	else if(other.xFinish != this.xFinish)
        		equal = false;
        	else if(other.xJoueur != this.xJoueur)
        		equal = false;
        	else if(other.yFinish != this.yFinish)
        		equal = false;
        	else if(other.yJoueur != this.yJoueur)
        		equal = false;
        	else {
        		for(int i = 0; i < plateau.length; i++){
        			for (int j = 0; j < plateau[0].length; j++) {
						if(plateau[i][j] != other.plateau[i][j]){
							equal = false;
							break;
						}
					}
        		}
        	}
        	return equal;
        		
        }catch(Exception e){
        	return false;
        }
    }
}
