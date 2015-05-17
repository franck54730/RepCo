package recherche;

import java.util.ArrayList;
import java.util.Iterator;

import modele.Labyrinthe;

public class LargeurDAbord implements IRecherche{

	public LargeurDAbord(){
		
	}
	
	/**
	 * retourne le tout dernier labyrinthe (utiliser le pere pour remonter jusque l'origine)
	 */
	public IJeu existeChemin(IJeu i, Historique h) {
		IJeu res = null;
		ArrayList<IJeu> noeuds = new ArrayList<IJeu>();
		noeuds.add(i);
		boolean isFinal = false;
		for (int j2 = 0; j2 < noeuds.size() && !isFinal; j2++) {
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
						h.ajouterHistorique(tmp);
					}
				}
			}
		}
		return res;
	}
}
