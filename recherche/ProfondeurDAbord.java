package recherche;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import modele.Constantes;
import modele.Labyrinthe;
import modele.Modele;
import modele.Constantes.Vitesse;


public class ProfondeurDAbord implements IRecherche {
	
	protected Modele modele;
	
	public ProfondeurDAbord(Modele m){
		modele = m;
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
	
	
	public IJeu existeChemin(IJeu i, Historique h){
		h.ajouterHistorique(i);
		try {
			if(modele.getVitesse() != Vitesse.DIRECT)
				Thread.sleep(Constantes.getVitesseProfondeur(modele));
		} catch (InterruptedException e) {
			// TODO Bloc catch généré automatiquement
			e.printStackTrace();
		}
		modele.miseAJour();
		boolean fini = false;
		IJeu res = null;
		if(i.estFinal()){
			res = i;
		}else{
			Iterator<IJeu> lesFils = i.iterator();
			while(lesFils.hasNext() && !fini){
				IJeu unFils = lesFils.next();
				if(!h.contain(unFils) && !fini){
					res = existeChemin(unFils, h);
					fini = res != null;
				}
			}
		}
		return res;
	}

	@Override
	public void run() {
	    long time = System.currentTimeMillis();
		IJeu chemin = this.existeChemin(modele.getLabyrinthe(), modele.getHistorique());
		modele.recupereChemin((Labyrinthe) chemin);
		modele.miseAJour();
		System.out.println(System.currentTimeMillis()-time);
	}

	@Override
	public void existeChemin() {
	}
}