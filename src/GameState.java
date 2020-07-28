public class GameState {
    /**
     * Champ represetant le plateau de jeu avec les allumettes où i represente la
     * ligne et Board[i] represente le nombre d'allumette sur la ligne i
     */
    private int[] board;

    /**
     * Champ representant le tour auquel on se trouve dans la partie
     */
    private int tour;

    /**
     * Champ represantant le numero de la partie en cours de jeu
     * et aisni permet de connaitre la quantité de partie joué
     */
    private int numPartie;

    /**
     * Champ representant la version en cours prends la valeur 0 avant le choix du
     * style puis 1 pour Humain vs Humain puis 2 pour Humain vs IA Naive puis 3 pour
     * Humain vs IA Strategie Gagnante
     */
    private int version;

    /**
     * Represente le nombre maximum d'alumette retirable par coup
     */
    private final int alluMax = 3;

    /**
     * Representen le nombre minimum d'allumette retirable par coup
     */
    private final int alluMin = 1;

    /**
     * @return the alluMax
     */
    public int getAlluMax() {
        return alluMax;
    }

    /**
     * @return the alluMin
     */
    public int getAlluMin() {
        return alluMin;
    }

    /**
     * @return the numPartie
     */
    public int getNumPartie() {
        return numPartie;
    }

    /**
     * @param numPartie the numPartie to set
     */
    public void setNumPartie(int numPartie) {
        this.numPartie = numPartie;
    }

    /**
     * @return the version
     */
    public int getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(int version) {
        this.version = version;
    }


    /**
     * @param int a - La taille du tableau du champ board
     */
    public GameState(int numLigne, int numPartie, int version) {
        board = new int[numLigne];
        tour = 1;
        this.numPartie = numPartie;
        this.version = version;
    }

    /**
     * Constructeur de la class
     */
    public GameState() {
        board = null;
        tour = 1;
        numPartie = 1;
        version = 0;
    }

    /**
     * @return the board
     */
    public int[] getBoard() {
        return board;
    }

    /**
     * @param i la valeur de la case à retrouner
     * @return l'etier de la case i
     */
    public int getBoard(int i) {
        return board[i];
    }

    /**
     * @return the tour
     */
    public int getTour() {
        return tour;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(int[] board) {
        this.board = board;
    }

    /**
     * @param t the tour to set
     */
    public void setTour(int t) {
        this.tour = t;
    }

    /**
     * Methode d'initialisation d'un tableau à une dimension
     */
    public void initTab() {
        for (int i = 0; i < board.length; i++) {
            board[i] = (2 * i + 1); // Formule permettant de savoir combien d'allumette doit contenir une ligne
        }
    }

    /**
     * Soustrait un move au tableau de jeu courant
     *
     * @param mv Le move à appliquer au tableau courant
     */
    public void applyMove(Move mv) {
        board[mv.getLigne()] -= mv.getAllumette();
    }

    /**
     * Methode permettant d'obtenir le nombre total d'allumette encore en cours de
     * jeu
     *
     * @return int le nombre d'allumette
     */
    public int totAllu() {
        int cpt = 0;

        for (int i = 0; i < getBoard().length; i++) {
            cpt += getBoard(i);
        }
        return cpt;
    }

    /**
     * Augmente le nombre de tour joué
     */
    public void incTour() {
        tour++;
    }

    /**
     * Augmente le nombre de partie joué
     */
    public void incPartie() {
        numPartie++;
    }

}