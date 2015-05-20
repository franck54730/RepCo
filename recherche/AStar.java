package recherche;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import modele.Constantes;
import modele.Labyrinthe;
import modele.Modele;
import modele.Constantes.Vitesse;

public class AStar implements IRecherche {

	protected Modele modele;
	
	public AStar(Modele m){
		modele = m;
	}
	
	public void existeChemin() {
		ArrayList<IJeu> listOuverte = new ArrayList<IJeu>();
		listOuverte.add(modele.getLabyrinthe());
		ArrayList<IJeu> listFermer = new ArrayList<IJeu>();
		Labyrinthe chemin = null;
		boolean fini = false;
		while(!listOuverte.isEmpty() && !fini){
			try {
				if(modele.getVitesse() != Vitesse.DIRECT)
					Thread.sleep(Constantes.getVitesseAStar(modele));
			} catch (InterruptedException e) {
				// TODO Bloc catch généré automatiquement
				e.printStackTrace();
			}
			//on extrait de la liste celui qui a le f() le plus petit.
			Labyrinthe fMinimum = (Labyrinthe) min(listOuverte);
			listFermer.add(fMinimum);
			if(fMinimum.estFinal()){
				chemin = fMinimum;
				fini = true;
			}else{
				Iterator<IJeu> iter = fMinimum.iterator();
				while (iter.hasNext()) {
					Labyrinthe fils = (Labyrinthe) iter.next();
					boolean dansFermerOuOuvert = ( listFermer.contains(fils) || listOuverte.contains(fils) );
					if(!dansFermerOuOuvert){
						modele.getHistorique().ajouterHistorique(fils);
						modele.miseAJour();
						fils.setPere((Labyrinthe) fMinimum);
						listOuverte.add(fils);
					}
				}
			}
		}
		for (IJeu iJeu : listFermer) {
			System.out.println(iJeu);
		}
		modele.recupereChemin((Labyrinthe) chemin);
		modele.miseAJour();
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

	@Override
	public void run() {
		// TODO Stub de la méthode généré automatiquement
	    long time = System.currentTimeMillis();
		this.existeChemin();
		System.out.println(System.currentTimeMillis()-time);
	}
}
