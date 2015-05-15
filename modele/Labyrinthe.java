package modele;

import modele.Labyrinthe.TypeCase;

public class Labyrinthe {

	public enum TypeCase{MUR, PASSAGE, JOUEUR, FINISH, JOUEUR_FINISH};
	
	private TypeCase[][] plateau;
	private int hauteur;
	private int largeur;
	
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
	
	/**
	 * initialise le plateau avec des cases de type MUR
	 */
	public void initLabyrinthe(){
		for(int i = 0; i < largeur; i++){
			for(int j = 0; j < hauteur; j++){
				plateau[i][j] = TypeCase.MUR;
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
	
}
