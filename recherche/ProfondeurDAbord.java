package recherche;
import java.util.*;

public class ProfondeurDAbord implements IRecherche {

    public ProfondeurDAbord(){
	
	
    }
    
    public IJeu existeChemin(IJeu i,Historique h){
    	IJeu res = null;
		if(i.estFinal()){
		    res = i;
		}else{
		    Iterator<IJeu> j = i.iterator();
		    while(j.hasNext() && !res.estFinal()){
				IJeu tmp = j.next();
				h.ajouterHistorique(tmp);
				res= existeChemin(tmp,h);
		    }
		}
		return res;
    }
}