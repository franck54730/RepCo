package recherche;
import java.util.*;

public class ProfondeurDAbord implements IRecherche {

    public ProfondeurDAbord(){
	
	
    }
    
    public boolean existeChemin(IJeu i,Historique h){
		boolean res =false;
		if(i.estFinal()){
		    res = true;
		}else{
		    Iterator<IJeu> j = i.iterator();
		    while(j.hasNext() && !res){
				IJeu tmp = j.next();
				h.ajouterHistorique(tmp);
				res= existeChemin(tmp,h);
		    }
		}
		return res;
    }
}