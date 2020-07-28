public class Move {
    /**
     * Champ Ligne representant la ligne d'un coup
     */
    private int ligne;
    /**
     * Champ allumette reesentant le nombre d'allumette d'un coup à jouer
     */
    private int allumette;

    /**
     * Constructeur de la classe
     * Initialise à 0 par defaut
     */
    public Move(int l, int a) {
        ligne = l;
        allumette = a;
    }

    /**
     * @param Move m - Copie les champ d'un objet Move existant
     */
    public Move(Move m) {
        ligne = m.ligne;
        allumette = m.allumette;
    }

    public Move() {
        ligne=0;
        allumette=0;
    }

    /**
     * @return the ligne
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * @return the allumette
     */
    public int getAllumette() {
        return allumette;
    }

    /**
     * @param ligne
     *            the ligne to set
     */
    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    /**
     * @param allumette
     *            the allumette to set
     */
    public void setAllumette(int allumette) {
        this.allumette = allumette;
    }

    /**
     * Test si une ligne d'un move est valide
     *
     * @param GameState
     *            b - L'etat actuel du jeu
     *
     * @return faux si la ligne n'est pas valide, vrai sinon
     */
    public boolean estLigneValide(GameState b) {
        if (ligne >= 0 && ligne < b.getBoard().length) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Test la validité du nombre d'allumette d'un Move
     *
     * @param GameState
     *            b - L'etat courant du jeu
     *
     * @return vrai si le nombre d'allumette est valid et faux sinon
     */

    public boolean estAllumetteValide(GameState b) {
        // ajouter assert

        // test si la regle du nombre d'allumette est respecter puis si ce nombre est
        // contenus sur la ligne
        if ((allumette >= b.getAlluMin() && allumette <= b.getAlluMax()) && (b.getBoard()[ligne] >= allumette)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param GameState
     *            b - L'etat courant du jeu
     *
     * @return vrai si le move est valide, faux sinon
     */
    public boolean estMoveValide(GameState b) {

        // test si les lignes et les allumettes sont valides
        // puis test si le nombre d'allumette apres validation est superieur ou égale à 1
        if (estLigneValide(b) && (estAllumetteValide(b))) {
            // && b.totAllu() - allumette >= 1

            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        String s = "(" +(ligne+1) + "," + allumette + ")";
        return s;
    }

    public boolean equals(Move m) {
        if (allumette == m.getAllumette() && ligne == m.getAllumette()) {
            return true;
        } else {
            return false;
        }
    }


    public boolean equalsLigne(Move mv) {
        if (ligne == mv.ligne)
            return true;
        return false;
    }
}
