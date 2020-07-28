public class Graph {

    /**
     * Champ represenatnt l'ensemble des configurations du jeu de Nim contenant une
     * liste de liste d'entier, la position dans la liste d'un maillon represente le
     * nombre d'allumette associé et la liste d'entier contenu dans ce maillon est
     * l'ensemble des noeuds acceessible lorsque qu'on se trouve dans cette
     * configuration. L'orsque un entier se trouvant dans la liste de liste d'entier
     * est negatif, c'est que le noeud associé ne possede pas de successeur.
     *
     */
    private LC<LC<Integer>> listeNoeud;

    /**
     * Champ representant l'ensemble des noeuds du noyau. Tout noeuds étant dans
     * cette liste est marqué comme faisant patie du noyau, à contrario l'ensemble
     * des noeuds n'étant pas dans cette liste sont marqués comme non membre du
     * noyau par l'asence de presence dans cette liste
     */
    private LC<Integer> noyau;

    /**
     * Constructeur de la class permettant d'instancié les listes des champs
     * associés
     *
     */
    public Graph() {
        listeNoeud = new LC<LC<Integer>>();
        noyau = new LC<Integer>();
    }

    /**
     * @return the graph
     */
    public LC<LC<Integer>> getListeNoeud() {
        return listeNoeud;
    }

    /**
     * @return the noyau
     */
    public LC<Integer> getNoyau() {
        return noyau;
    }

    /**
     * @param graph
     *            the graph to set
     */
    public void setListeNoeud(LC<LC<Integer>> graph) {
        this.listeNoeud = graph;
    }

    /**
     * @param noyau
     *            the noyau to set
     */
    public void setNoyau(LC<Integer> noyau) {
        this.noyau = noyau;
    }

    /**
     * Permet d'initialiser la liste de liste de noeud representant le graph
     *
     * @param gs
     *            l'etat courant du jeu
     */
    public void initListeNoeud(GameState gs) {

        for (int i = 1; i <= gs.totAllu(); i++) {

            if (i == 1) {
                LC<Integer> l = new LC<Integer>();
                l.ajouterEntete(-1);
                listeNoeud.ajouterEnFin(l);

            } else if (i == 2) {
                LC<Integer> l = new LC<Integer>();
                l.ajouterEntete(i - 1);
                listeNoeud.ajouterEnFin(l);

            } else if (i == 3) {

                LC<Integer> l = new LC<Integer>();
                l.ajouterEntete(i - 1);

                l.ajouterEntete(i - 2);
                listeNoeud.ajouterEnFin(l);
            } else {
                LC<Integer> l = new LC<Integer>();

                // j = 1, 2 ou 3 represente le nombre de noeud accessible
                // et crée autant de maillon dans la sous liste du graph que necéssaire
                for (int j = gs.getAlluMax(); j >= gs.getAlluMin(); j--) {

                    l.ajouterEnFin(i - j);

                }
                listeNoeud.ajouterEnFin(l);
            }
        }

    }

    /**
     * Methode appelée par la methode intitNoyau uniquement, permettant de trouver
     * un noyau en connaissant le noyau precedent
     *
     * @param m
     *            le Maillon de listeNoeud
     * @param noyau
     *            le dernier noeud faisant partie du noyau
     * @return faux si aucun succeseur du maillon correspondant au noeud tester
     *         n'est egale au precedent noeud de noyau, renvoie vrai sinon
     */
    public boolean trouveNoyau(Maillon<LC<Integer>> m, int noyau) {
        // msl = maillon sous liste
        Maillon<Integer> msl = m.getValeur().getHead();

        while (msl != null) {
            if (msl.getValeur() == noyau) {
                return false;
            }
            msl = msl.getNext();
        }
        return true;
    }

    /**
     * Initialise la liste des noeuds faisant partie du noyau à partir de la liste
     * des noeuds du graoh
     */
    public void initNoyau() {

        Maillon<LC<Integer>> m = listeNoeud.getHead();
        int cpt = 1, elementNoyauPrecedent = 0;

        while (m != null) {

            if (m.getValeur().getHead().getValeur() == -1) {
                // definit le premier noeud (1) comme le noeud sans successeur

                noyau.ajouterEnFin(cpt);
                elementNoyauPrecedent = cpt;

            } else {
                // On cherche un noeud dont aucun successeur
                // n'est egale au noeud prededent faisant partie du noyau

                if (trouveNoyau(m, elementNoyauPrecedent)) {
                    noyau.ajouterEnFin(cpt);
                    elementNoyauPrecedent = cpt;
                }
            }

            m = m.getNext();
            cpt++;

        }
    }

}
