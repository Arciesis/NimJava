
public class Ia {

    /**
     * Champ repredsentant les coups possibles pour l'ia chaque maillon contient un
     * Move qui est realisable
     */
    private LC<Move> listeCoup;

    /**
     * Champ representant le niveau de l'ia Prendra automatiquement la vauleur "IA
     * Naive" si c'est le premier niveau et "IA Strategie gagnate" sinon
     */
    private String name;

    /**
     * Champ representant les parties gagnés par l'ia chaque maillon contiendra le
     * numero de la partie gagné par l'ia
     */
    private LC<Integer> win;

    /**
     * champ representant les noeuds de noyau de l'ia chaque entier contenu dans la
     * liste est ainsi dans le noyau
     *
     */
    private Graph graph;

    /**
     * @return the noeuds
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @param noeuds
     *            the noeuds to set
     */
    public void setGraph(Graph g) {
        this.graph = g;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param s
     *            the name to set
     */
    public void setName(String s) {
        name = s;
    }

    /**
     * Constructeur de la class
     *
     */
    public Ia() {

        listeCoup = new LC<Move>();
        win = new LC<Integer>();
        name = "";
        graph = new Graph();
    }

    /**
     *
     * @return the win
     */
    public LC<Integer> getWin() {
        return win;
    }

    /**
     * @return the liste
     */
    public LC<Move> getListeCoup() {
        return listeCoup;
    }

    /**
     * @param liste
     *            the liste to set
     */
    public void setListeCoup(LC<Move> liste) {
        this.listeCoup = liste;
    }

    /**
     * Methode permettant de reset la liste des coups de l'ia si le joueur décide de
     * passer du style IA Naive à un autre
     */
    public void resetIaN() {
        setListeCoup(null);
    }

    /**
     * Methode permettant de choisir un Move aléatoirement
     *
     * @return Move - le Move choisit aléatoirement
     */
    public Move randMove() {
        // Donne un entier entre 1 et le nombre total de Move possible
        int cpt, r = (int) ((Math.random()) * listeCoup.getNbMaillon() + 1);

        // Maillon permettant de parcourir la liste
        Maillon<Move> m = null;

        // Instanciation d"un Move pcq le JRE ne compile pas si on ne lui en renvoie pas
        // un dans tous les cas bref ca sert à rien mais en attendant de savoir comment
        // faire plus proprement ca restera la

        if (listeCoup.getNbMaillon() == 1) {
            return (Move) listeCoup.getHead().getValeur();
        } else if (r < listeCoup.getNbMaillon() / 2) {

            // Permet de chercher dans un sens ou dans l'autre pour economiser la memoire
            cpt = 1;
            m = listeCoup.getHead();
            // Permet de trouver le Move associé à l'entier
            while (cpt <= r) {
                if (cpt == r) {
                    return ((Move) m.getValeur());
                }
                m = m.getNext();
                cpt++;
            }
        } else {

            cpt = listeCoup.getNbMaillon();
            m = listeCoup.getEnd();
            while (cpt >= r) {
                if (cpt == r) {
                    return ((Move) m.getValeur());
                }
                m = m.getPrev();
                cpt--;
            }
        }
        return new Move(0, 0);
    }

    /**
     * Permet de connaitre la presence ou non d'un Move dans une liste
     *
     * @param mv
     *            l'objet dont on souhaite conaitre la presence
     * @param b
     *            - L'objet contenant toute les informations du jeu
     * @return vrai si le move est present dans la liste et faux sinon
     */
    public boolean estMovePresent(Move mv, GameState b) {
        Maillon<Move> m = new Maillon<Move>();

        if (listeCoup.getHead() == null) {
            return false;
        } else if (mv.getLigne() > listeCoup.optiListe(b) / 2) {
            // Permet de parcourir la liste dans un sens ou l'autre pour economiser de la
            // memoire
            // Cela economise de la memoire seulement si l'objet est present dans la liste
            // Sinon dans un cas comme dans l'autre la methode parcourera toute la liste
            // pour renvoyer faux au final

            m = listeCoup.getEnd();
            while (m != null) { // si = null alors la liste a été parcourus entierement donc on s'arrete
                if (((Move) m.getValeur()).equals(mv)) {
                    return true;
                } else {
                    m = m.getPrev();
                }
            }

        } else {
            m = listeCoup.getHead();
            while (m != null) { // si = null alors la liste a été parcourus entierement donc on s'arrete
                if (m.getValeur().equals(mv)) {
                    return true;
                } else {
                    m = m.getNext();
                }
            }
        }
        return false;
    }


    /**
     * Methode permettant d'initialiser la liste des mMove possible
     *
     * @param GameState
     *            b - Le plateau de jeu représentant l'etat courant, permet d'avoir
     *            le nombre de ligne
     */
    public void initListe(GameState b) {
        if (!listeCoup.estListeVide()) {
            listeCoup.setHead(null);
            listeCoup.setEnd(null);
            listeCoup.setNbMaillon(0);
        }

        // Creation du premier move possible qui est toujours present
        listeCoup.ajouterEnFin(new Move(0, 1));

        // À partir de ligne=2, il y a toujours 3 Move possible pour chaque ligne
        // Creation puis ajout en tete à la liste
        for (int i = 1; i < b.getBoard().length; i++) {
            listeCoup.ajouterEnFin(new Move(i, 1));
            listeCoup.ajouterEnFin(new Move(i, 2));
            listeCoup.ajouterEnFin(new Move(i, 3));
        }

    }


    /**
     * Methode permettant d'actualiser la liste des coups de l'ia
     *
     * @param mv
     *            Le dernier Move à avoir été joué
     * @param gs
     *            l'objet contenant le plateau courant du jeu
     */
    public void refresh(Move mv, GameState gs) {
        // La lste ne peut pas etre vide mais on sait jamais
        assert !(listeCoup.estListeVide()) : "Error - liste vide -> refresh()";
        boolean supp = false, stop = false, senseHasBeenUsed = false, sense;
        // sens = vrai => m=head, sinon m=end
        int cpt = 0;
        Maillon<Move> m = new Maillon<Move>();

        if (mv.getLigne() == 0) {
            listeCoup.supprimer(listeCoup.getHead());
        }

        // Test l'utilité de la methode optiListe pour savoir si on l'utilise
        if (!(gs.getBoard().length > 100)) {
            // On utilise pas Sense car Liste trop courte pour que cela ait une utilié
            m = listeCoup.getHead();
            senseHasBeenUsed = true;
            sense = false;
        } else {
            sense = mv.getLigne() > listeCoup.optiListe(gs) / 2;
        }

        do {
            // permet de definir le sens de lecture de la liste
            // Puis de savoir vers où s'orienter
            if (!senseHasBeenUsed) {
                if (!sense) {
                    m = listeCoup.getHead();
                } else {
                    m = listeCoup.getEnd();
                }
                senseHasBeenUsed = true;
            }

            // Si le maillon et le Move ont la meme ligne et que le nombre d'allumette du
            // Move est superieur à celui du plateau de jeu alors on supprime le maillon
            if ((mv.equalsLigne(m.getValeur()))
                    && (m.getValeur().getAllumette() > gs.getBoard(m.getValeur().getLigne()))) {
                supp = listeCoup.supprimer(m);
                cpt++;
            }

            if (senseHasBeenUsed) {
                if (!sense) {
                    m = m.getNext();
                } else {
                    m = m.getPrev();
                }
            }

            // Condition pour les crans d'arret de la boucle
            if (m == null) {
                stop = true;
            } else if (supp && (m.getValeur()).getLigne() != mv.getLigne()) {
                stop = true;
            } else if (cpt >= 3) {
                stop = true;
            }
        } while (!stop);
    }

    /**
     * Initialise le graphe de l'ia strategie gagnante
     *
     * @param gs
     *            l'etat courant du jeu
     */
    public void initGraph(GameState gs) {
        graph.initListeNoeud(gs);
        graph.initNoyau();
    }

    /**
     * Permet de connaitre la position du nombre d'allumette encore sur le plateau
     * de jeu
     *
     * @param gs
     *            l'etat ciurant du jeu
     * @return vrai si le nombre d'allumette appartient au noyau et faux sinon
     */
    public boolean isAlluBelongsToNoyau(GameState gs) {
        boolean isAlluBelongsToNoyau = false;
        Maillon<Integer> m = graph.getNoyau().getHead();

        while (m != null) {
            if (gs.totAllu() == graph.getNoyau().getHead().getValeur()) {
                isAlluBelongsToNoyau = true;
                // return 0
            }
            m = m.getNext();
        }
        return isAlluBelongsToNoyau;

    }

    /**
     * Permet de trouver la ligne d'un move d'attente
     *
     * @param gs
     *            l'etat courant du jeu
     * @return -1 si aucune ligne ne comporte au moins une alumette et la ligne
     *         contenant plus d'une allumette sinon
     */
    public int searchLineForBadMove(GameState gs) {
        for (int i = 0; i < gs.getBoard().length; i++) {
            if (gs.getBoard(i) >= gs.getAlluMin() && gs.totAllu() > 1) {
                // Si le nombre d'allumette sur la ligne est superieur ou egale au nombre
                // d'allumette minimal d'un coup on retourne cette ligne
                return i;
            }
        }
        return 0;
    }

    /**
     * Cherche le nombre d'allumette qu'il faut jouer pour un coup strategique,
     * selon le nombre d'allumette sur le plateau et le noyau suivant atteignable
     *
     * @param gs
     *            l'etat courant du jeu
     * @return le nombre d'allumette à jouer pour atteindre le noeud de noyau
     *         suivant
     */
    public int SearchAlluForGoodMove(GameState gs) {
        Maillon<Integer> m = graph.getNoyau().getHead();

        while (m != null) {
            if (gs.totAllu() > m.getValeur() && (gs.totAllu() - m.getValeur()) <= gs.getAlluMax()) {
                /*
                 * Si le nombre d'alluemtte total est superieur (pour verifier que le noeud de
                 * noyau est bien accessible et n'a pas encore et atteint) Et que soustraction
                 * du nombre d'allumette sur le plateau par le nombre d'allumette du noeud de
                 * noyau suivant est inferieur au nombre d'allumette maximum d'un coup
                 */
                return gs.totAllu() - m.getValeur();
            }
            m = m.getNext();
        }
        return 0;
        // La methode ne renverra jamais 0 mais pour que ca compile il est nécéssaire
    }

    /**
     * Cherche une ligne contenant suffisemment d'allumette pour jouer un coup
     * strategique
     *
     * @param gs
     *            l'etat courant du jeu
     * @param allu
     *            le nombre d'allumette à jouer pour atteindre le prochain noued de
     *            noyau
     * @return -1 si aucune ligne ne convient à jouer un coup strategique et une
     *         ligne où il y a suffisemment d'allumette pour jouer un coup
     *         strategique
     */
    public int searchLineForGoodMove(GameState gs, int allu) {
        int[] tab = gs.getBoard();

        for (int i = 0; i < tab.length; i++) {
            if (tab[i] >= allu) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Cherche un Move jouable et optimiser pour l'ia Strategie gagnante
     *
     * @param gs
     *            l'etat courant du jeu
     * @return Un move joubal et optimiser de facon à donner le plus grand taux de
     *         victoire possible à m'ia strategie gagnante
     */
    public Move tourIA(GameState gs) {
        if (isAlluBelongsToNoyau(gs)) {
            // Joue un coup d'attente car un noeud ne noyau des pas atteignable
            return new Move(searchLineForBadMove(gs), 1);

        } else {

            int allu = SearchAlluForGoodMove(gs);

            if (allu != 0) {
                // allu ne sera jamais égal à 0 mais le cas d'erreur est tout de meme gere

                int line = searchLineForGoodMove(gs, allu);
                if (line == -1) {
                    // Si aucune ligne ne contient suffisemment d'allumette alors l'ia joue un coup
                    // d'attente

                    return new Move(searchLineForBadMove(gs), 1);

                } else {
                    // Sinon elle joue un coup strategique pour atteindre un noeud de noyau

                    return new Move(line, allu);
                }
            } else {
                // L'ia joue un coup d'attente si il y a eu une erreur

                return new Move(searchLineForBadMove(gs), 1);
            }
        }
    }

}
