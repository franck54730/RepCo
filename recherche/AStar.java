package recherche;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import modele.Labyrinthe;

public class AStar implements IRecherche {

	public AStar() {

	}
	
	public IJeu existeChemin(IJeu i, Historique h) {
		ArrayList<IJeu> listOuverte = new ArrayList<IJeu>();
		listOuverte.add(i);
		ArrayList<IJeu> listFermer = new ArrayList<IJeu>();
		while(!listOuverte.isEmpty()){
			//on extrait de la liste celui qui a le f() le plus petit.
			Labyrinthe fMinimum = (Labyrinthe) min(listOuverte);
			listFermer.add(fMinimum);
			if(fMinimum.estFinal()){
				return fMinimum;
			}else{
				Iterator<IJeu> iter = fMinimum.iterator();
				while (iter.hasNext()) {
					Labyrinthe fils = (Labyrinthe) iter.next();
					boolean dansFermerOuOuvert = ( listFermer.contains(fils) || listOuverte.contains(fils) );
					if(!dansFermerOuOuvert){
						h.ajouterHistorique(fils);
						fils.setPere((Labyrinthe) fMinimum);
						listOuverte.add(fils);
					}
				}
			}
		}
		for (IJeu iJeu : listFermer) {
			System.out.println(iJeu);
		}
		return null;		
	}

	/**
	 * recupere le minimum (en f) le renvoie et le retire de la liste
	 * @param al
	 * @return
	 */
	public IJeu min(ArrayList<IJeu> al) {
		Labyrinthe min = (Labyrinthe)al.get(0);
		int indiceMin = 0;
		for (int i = 1; i < al.size(); i++) {
			Labyrinthe current = (Labyrinthe)al.get(i);
			if (min.getF() > current.getF()) {
				min = current;
				indiceMin = i;
			}
		}
		al.remove(indiceMin);
		return min;
	}
}
