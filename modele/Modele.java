package modele;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

import recherche.Historique;

import vue.VueGraphique;

import modele.Constantes.Vitesse;
import modele.Labyrinthe.TypeCase;

public class Modele extends Observable{

	private Labyrinthe labyrinthe;
	private TypeCase typeSelectionner = TypeCase.PASSAGE;
	private VueGraphique vueGraphique;
	private boolean joueurPlacer = false;
	private boolean finishPlacer = false;
	private Historique historique;
	private ArrayList<Point> chemin;
	private Vitesse vitesse = Vitesse.NORMAL;
	private Dimension dimension = new Dimension(500, 500);
	
	public Modele(){
		historique = new Historique();
		chemin = new ArrayList<Point>();
		//initLabyrinthe(5, 5);
	}
	
	/**
	 * methode qui initialise un nouveau labyrinthe de largeur et hauteur donnée 
	 * avec des cases de type MUR par defaut
	 * @param largeur
	 * @param hauteur
	 */
	public void initLabyrinthe(int largeur, int hauteur){
		labyrinthe = new Labyrinthe(largeur, hauteur);
		joueurPlacer = false;
		finishPlacer = false;
	}
	
	/**
	 * methode qui retourne le type de la case correspondant au couple x,y 
	 * @param x indice de l'absisse
	 * @param y indice de l'ordonnée
	 * @return
	 */
	public TypeCase getTypePourCase(int x, int y){
		TypeCase rep = null;
		if(labyrinthe != null)
			rep = labyrinthe.getTypePourCase(x, y);
		return rep;
	}
	
	/**
	 * methode qui affecte le type de la case correspondant au couple x,y 
	 * @param x indice de l'absisse
	 * @param y indice de l'ordonnée
	 * @param t nouveau type de la case
	 * @return
	 */
	public void setTypePourCase(int x, int y, TypeCase t){
		labyrinthe.setTypePourCase(x, y, t);
		miseAJour();
	}

	/**
	 * methode qui met a jour les observer du model
	 */
	public void miseAJour() {
		setChanged();
		notifyObservers();
	}
	
	public int getHauteur(){
		return labyrinthe!=null?labyrinthe.getHauteur():0;
	}
	
	public int getLargeur(){
		return labyrinthe!=null?labyrinthe.getLargeur():0;
	}

	public void setTypeSelectionner(TypeCase typeCase) {
		typeSelectionner = typeCase;
	}

	public TypeCase getTypeSelectionner() {
		return typeSelectionner;
	}

	public void setVueGraphique(VueGraphique vueGraphique) {
		this.vueGraphique = vueGraphique;
	}
	
	public void newVueGraphiquePlateau(){
		vueGraphique.newPlateau();
	}
	
	public boolean isJoueurPlacer(){
		return joueurPlacer;
	}
	
	public boolean isFinishPlacer(){
		return finishPlacer;
	}
	
	public void changeJoueurPlacer(){
		joueurPlacer = !joueurPlacer;
	}
	
	public void changeFinishPlacer(){
		finishPlacer = !finishPlacer;
	}

	public void setJoueur(int x, int y) {
		labyrinthe.setJoueur(x, y);
	}

	public void setFinish(int x, int y) {
		labyrinthe.setFinish(x, y);
	}
	
	public Historique getHistorique(){
		return historique;
	}
	
	public Labyrinthe getLabyrinthe(){
		return labyrinthe;
	}
	
	public void recupereChemin(Labyrinthe l){
		chemin.clear();
		while(l != null){
			chemin.add(0,new Point(l.getXJoueur(), l.getYJoueur()));
			l = l.getPere();
		}
	}

	public ArrayList<Point> getChemin() {
		return chemin;
	}

	public void reinitialiser() {
		chemin.clear();
		historique.clear();
	}

	/**
	 * vide le chemin et l'historique pour ne plus les afficher
	 */
	public void reinitVue() {
		chemin.clear();
		historique.clear();
	}

	public Vitesse getVitesse() {
		// TODO Stub de la méthode généré automatiquement
		return vitesse;
	}

	public void setVitesse(Vitesse v) {
		vitesse = v;
	}

	public Dimension getDimension() {
		// TODO Stub de la méthode généré automatiquement
		return dimension;
	}
	
	public void setDimension(Dimension d){
		dimension = d;
		vueGraphique.resize();
	}
}
