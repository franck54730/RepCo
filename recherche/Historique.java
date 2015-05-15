package recherche;

import java.util.ArrayList;
import java.util.Arrays;

public class Historique {
	
	private ArrayList<IJeu> historique;
	
	public Historique(){
		historique = new ArrayList<IJeu>();
	}
	
	public IJeu getValue(int a){
		return this.historique.get(a);
	}
	
	public int size(){
		return historique.size();
	}
	
	public boolean contain(IJeu a){
		boolean res = false;
		for (int i = 0; i <historique.size(); i++) {
			if(Arrays.deepEquals(historique.get(i).getTab(),a.getTab())){
				res = true;
			}
		}
		return res;
	}
	
	public void ajouterHistorique(IJeu a){
		this.historique.add(a);
	}
}
