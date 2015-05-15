package recherche;

import java.util.ArrayList;
import java.util.Iterator;

public class LargeurDAbord implements IRecherche{

	public LargeurDAbord(){
		
	}
	
	public boolean existeChemin(IJeu i, Historique h) {
		boolean res = false;
		ArrayList<IJeu> noeuds = new ArrayList<IJeu>();
		noeuds.add(i);
		for (int j2 = 0; j2 < noeuds.size(); j2++) {
			Iterator<IJeu> j = noeuds.get(j2).iterator();
			while(j.hasNext()){
				IJeu tmp = j.next();
				noeuds.add(tmp);
				h.ajouterHistorique(tmp);
				if(noeuds.get(j2).estFinal()){
					return true;
				}
			}
		}
		return res;
	}
		
		
		
		
		
	}
