package recherche;

import java.util.ArrayList;
import java.util.Iterator;

import modele.Constantes;
import modele.Constantes.Vitesse;
import modele.Labyrinthe;
import modele.Modele;

public class LargeurDAbord implements IRecherche{

	protected Modele modele;
	
	public LargeurDAbord(Modele m){
		modele = m;
	}
	
	/**
	 * retourne le tout dernier labyrinthe (utiliser le pere pour remonter jusque l'origine)
	 */
	public void existeChemin() {
		System.out.println("algo : "+modele.getVitesse()+" int vitesse : "+Constantes.getVitesseLargeur(modele) );
		IJeu res = null;
		ArrayList<IJeu> noeuds = new ArrayList<IJeu>();
		noeuds.add(modele.getLabyrinthe());
		boolean isFinal = false;
		for (int j2 = 0; j2 < noeuds.size() && !isFinal; j2++) {
			try {
				if(modele.getVitesse() != Vitesse.DIRECT)
					Thread.sleep(Constantes.getVitesseLargeur(modele));
			} catch (InterruptedException e) {
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			}
			IJeu courant = noeuds.get(j2);
			Iterator<IJeu> j = courant.iterator();
			if(courant.estFinal()){
				res = courant;
				isFinal = true;
			}
			if(!isFinal){
				while(j.hasNext()){
					IJeu tmp = j.next();
					if(!noeuds.contains(tmp)){
						noeuds.add(tmp);
						modele.getHistorique().ajouterHistorique(tmp);
						modele.miseAJour();
					}
				}
			}
		}
		modele.recupereChemin((Labyrinthe) res);
		modele.miseAJour();
	}

	@Override
	public void run() {
	    long time = System.currentTimeMillis();
		this.existeChemin();
		System.out.println(System.currentTimeMillis()-time);
	}
}
