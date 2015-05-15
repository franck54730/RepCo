package recherche;

import modele.Labyrinthe.TypeCase;

public interface IJeu extends Iterable<IJeu> {
    public boolean estFinal();
    public TypeCase[][] getTab();
}


