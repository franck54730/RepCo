package recherche;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;


public class ProfondeurDAbord implements IRecherche {

	public ProfondeurDAbord() {
		
	}

//	public IJeu existeChemin(IJeu i, Historique h) {
//		IJeu res = null;
//		Stack<IJeu> noeuds = new Stack<IJeu>();
//		ArrayList<IJeu> marquer = new ArrayList<IJeu>();
//		noeuds.push(i);
//		marquer.add(i);
//		boolean isFinal = false;
//		while(!noeuds.empty() && !isFinal) {
//			IJeu courant = noeuds.pop();
//			Iterator<IJeu> j = courant.iterator();
//			if (courant.estFinal()) {
//				res = courant;
//				isFinal = true;
//			}
//			if (!isFinal) {
//				while (j.hasNext()) {
//					IJeu tmp = j.next();
//					if (!(marquer.contains(tmp))) {
//						noeuds.push(tmp);
//						h.ajouterHistorique(tmp);
//						marquer.add(tmp);
//					}
//				}
//			}
//		}
//		return res;
//	}
	
	
	public IJeu existeChemin(IJeu i,Historique h){
		IJeu res = null;
		boolean fini = false;
		if(i.estFinal()){
			fini = true;
			res = i;
		}else{
			Iterator<IJeu> j = i.iterator();
			while(j.hasNext() && !fini){
				IJeu tmp = j.next();
				h.ajouterHistorique(tmp);
				res= existeChemin(tmp,h);
				fini = res != null;
			}
		}
		return res;
	}
}