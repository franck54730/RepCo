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
		for (int j2 = 0; j2 < noeuds.size(); j2++) {
			Iterator<IJeu> j = noeuds.get(j2).iterator();
			while(j.hasNext()){
				IJeu tmp = j.next();
				if(!noeuds.contains(tmp))
					noeuds.add(tmp);
				h.ajouterHistorique(tmp);
				if(noeuds.get(j2).estFinal()){
					res = noeuds.get(j2);
				}
			}
		}
		return res;
	}
}
