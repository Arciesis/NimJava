public class Board {

    /**
     * Champ representant l'etat courant du jeu
     */
    private GameState plateau;

    /**
     * Champ reprensentant le premier joueur
     */
    private HumanPlayer j1;

    /**
     * Champ representant le 2nd joueur lorsque la partie est en mode Humain vs
     * Humain
     */
    private HumanPlayer j2;

    /**
     * Champ representant l'ia lorsque la partie est en mode Humain vs IA Naive ou
     * Humain vs IA Strategie gagnante
     */
    private Ia ia;



    /**
     * Constructeur de la classe initialisant tous les champs à null
     */
    public Board() {
        plateau = null;
        j1 = null;
        j2 = null;
        ia = null;
    }

    /**
     * Constructeur de la classe
     *
     * @param GameState
     *            board - Tableau representant le plateau de jeu
     * @param HumanPlayer
     *            J1 - Represente le premier joueur
     * @param HumanPlayer
     *            J2 - Represente le second joueur
     */
    public Board(HumanPlayer j1, Ia ia) {
        this.plateau = null;
        this.j1 = j1;
        this.ia = ia;
    }

    /**
     * Constructeur de la classe
     *
     * @param GameState
     *            board - Tableau representant le plateau de jeu
     * @param HumanPlayer
     *            J1 - Represente le premier joueur
     * @param HumanPlayer
     *            J2 - Represente le second joueur
     */
    public Board(GameState board, HumanPlayer J1, HumanPlayer J2) {
        this.plateau = board;
        this.j1 = J1;
        this.j2 = J2;
    }

    /**
     * Constructeur de la classe
     *
     * @param HumanPlayer
     *            J1 - Represente le premier joueur
     * @param HumanPlayer
     *            J2 - Represente le second joueur
     */
    public Board(HumanPlayer J1, HumanPlayer J2) {
        this.plateau = null;
        this.j1 = J1;
        this.j2 = J2;
    }



    /**
     * @return the board
     */
    public GameState getPlateau() {
        return plateau;
    }

    /**
     * @return the joueur
     */
    public HumanPlayer getJ1() {
        return j1;
    }

    /**
     * @return the joueur
     */
    public HumanPlayer getJ2() {
        return j2;
    }

    /**
     * @return the Ia
     */
    public Ia getIa() {
        return ia;
    }



    /**
     * @param joueuer
     *            the j2 to set
     */
    public void setJ2(HumanPlayer joueuer) {
        this.j2 = joueuer;
    }

    /**
     * @param ia
     *            the ia to set
     */
    public void setIa(Ia ia) {
        this.ia = ia;
    }

    /**
     * @param board
     *            the board to set
     */
    public void setPlateau(GameState board) {
        this.plateau = board;
    }

    /**
     * @param joueur
     *            the joueur to set
     */
    public void setJ1(HumanPlayer joueur) {
        this.j1 = joueur;
    }

    /**
     * Methode permettant de Nommer l'ia selon le style de jeu voulus
     *
     */
    public void initNameIa() {
        if (plateau.getVersion() == 2) {
            ia.setName("IA Naive");
        } else {
            ia.setName("IA Strategie Gagnante");
        }
    }

}
