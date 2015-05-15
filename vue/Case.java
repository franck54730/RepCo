package vue;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import modele.Constantes;
import modele.Labyrinthe.TypeCase;

public class Case extends JButton {
	
	private int x;
	private int y;
	private TypeCase type;
	
	public Case(int x, int y, TypeCase t){
		this.x = x;
		this.y = y;
		this.type = t;
		majType();
	}
	
	/**
	 * methode qui modifie le type de la case et met a jour son image en fonction
	 * @param t
	 */
	public void setTypeCase(TypeCase t){
		type = t;
		majType();
	}
	
	/**
	 * methode qui met a jour l'image en fonction du type
	 */
	private void majType(){
		switch (type) {
		case MUR:
			this.setIcon(Constantes.ICON_MUR);
			break;
		case PASSAGE:
			this.setIcon(Constantes.ICON_PASSAGE);
			break;
		case FINISH:
			this.setIcon(Constantes.ICON_FINISH);
			break;
		case JOUEUR:
			this.setIcon(Constantes.ICON_JOUEUR);
			break;
		}
	}
	
}
