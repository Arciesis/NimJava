public class LC<T> {

    /**
     * Champ representant le premier Maillon de la liste
     */
    private Maillon<T> head;

    /**
     * Champ reperesentant le dernier Maillon de la liste
     */
    private Maillon<T> end;

    /**
     * champ representant la taille de la liste
     */
    private int nbMaillon;

    /**
     * Constructeur de la class Instancie à null
     *
     */
    public LC() {
        head = null;
        end = null;
        nbMaillon = 0;
    }

    /**
     * @return the fin
     */
    public Maillon<T> getEnd() {
        return end;
    }

    /**
     * @param fin
     *            the fin to set
     */
    public void setEnd(Maillon<T> fin) {
        this.end = fin;
    }

    /**
     * @return the tete
     */
    public Maillon<T> getHead() {
        return head;
    }

    /**
     * @return the nbMaillon
     */
    public int getNbMaillon() {
        return nbMaillon;
    }

    /**
     * @param tete
     *            the tete to set
     */
    public void setHead(Maillon<T> tete) {
        this.head = tete;
    }

    /**
     * @param nbMaillon
     *            the nbMaillon to set
     */
    public void setNbMaillon(int nbMaillon) {
        this.nbMaillon = nbMaillon;
    }

    /**
     * ajoute à la liste le numero de la partie gagnée par le joueur
     *
     * @param numeroPartieGagne
     *            Le numero de la partie gagnée par le joueur
     */
    public void incWin(T numeroPartieGagne) {
        // verifictaion que la liste ne soit pas vide pour tratier le cas non general
        if (estListeVide()) {
            head = new Maillon<T>(numeroPartieGagne);
            end = head;
            nbMaillon++;
        } else {
            Maillon<T> m = new Maillon<T>(numeroPartieGagne);
            end.setNext(m);
            end = m;
            nbMaillon++;
        }
    }

    /**
     * Methode permettant de savoir si une liste est vide
     *
     * @return Vrai si la liste est vide est faux sinon
     */
    public boolean estListeVide() {
        if (head != null) {
            return false;
        }
        return true;
    }

    /**
     * Methode pemettant d'ajouter un Maillon à une liste
     *
     * @param Move
     *            v - Le move à rajouter en tete
     */
    public boolean ajouterEntete(T v) {
        Maillon<T> m = new Maillon<T>(v);
        // verifictaion que la liste ne soit pas vide pour tratier le cas non general
        if (estListeVide()) {
            head = m;
            end = m;
            nbMaillon++;
            return true;
        } else {
            head.setPrev(m);
            m.setNext(head);
            head = m;
            nbMaillon++;
            return true;
        }
    }

    /**
     * Methode permettant d'ajouter un Maillon en fin de liste
     *
     * @param Move
     *            mv - Le Move à ajouter
     */
    public void ajouterEnFin(T v) {
        // verifictaion que la liste ne soit pas vide pour tratier le cas non general
        if (estListeVide()) {
            head = new Maillon<T>(v);
            end = head;
            nbMaillon++;
        } else {
            Maillon<T> m = new Maillon<T>(end, v);
            end.setNext(m);
            end = m;
            nbMaillon++;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    public String toString() {
        String s = "";
        if (!estListeVide()) { // Si la liste est vide la string renvoyé l'est aussi
            Maillon<T> m = head;

            // boucle jusqu'a ce que plus aucun Maillon soit chainné
            while (m != null) {

                // Ajoute le maillon à la string puis passe au maillon suivant
                s += m.getValeur() + " ";
                m = m.getNext();
            }
            // System.out.println(s);
            return s;
        } else {
            // System.out.println(s);
            return s;
        }
    }



    /**
     * Methode permettant de supprimer un maillon d'une liste
     *
     * @param Maillon
     *            m - Le Maillon à supprimer
     *
     * @return Vrai si le maillon a été supprimé et faux sinon
     */
    public boolean supprimer(Maillon<T> m) {
        // Supprime le Maillon en retablissant les bonnes références
        // Si le maillon est entre deux autres maillon (cas général)
        if (m.getPrev() != null && m.getNext() != null) {

            m = m.getPrev();
            m.setNext(m.getNext().getNext());

            m = m.getNext();
            m.setPrev(m.getPrev().getPrev());

            nbMaillon--;
            return true;
        } else if (m.equals(head) && m.equals(end)) { // Cas particulier: le Maillon est le dernier restant
            end = null;
            head = null;
            nbMaillon--;
        } else if (m.equals(end)) { // Cas particulier: si le Maillon est en fin
            m.getPrev().setNextNull();
            end = m.getPrev();
            nbMaillon--;
            return true;
        } else if (m.equals(head)) { // Cas particulier: si le Maillon est en tete
            m.getNext().setPrev(null);
            head = m.getNext();
            nbMaillon--;
            return true;
        }
        return false;
    }

    /**
     * Permet d'optimiser le parcours de la liste
     *
     * @param b
     *            - L'objet contenant toutes les informations du jeu
     * @return un entier selon la taille de la liste
     */
    public int optiListe(GameState b) {
        // Formule optenue apres visualisation du probleme de l'optimisation
        // Si le nombre de maillon actuel > nbMaillon Max /2 +1
        if (nbMaillon > (((b.getBoard().length - 1) * 3 + 1) / 2 + 1)) {
            // On utilise Lmax *3
            // ex: si le tableau comprte 5 ligne (0,1,2,3,4), on utilise 4*3=12
            return ((b.getBoard().length - 1) * 3);
        } else if (nbMaillon > ((b.getBoard().length - 1) * 3 + 1) / 3) {// Si le nombre de maillon actuel > nbMaillon
            // Max /3
            // On utilise Lmax *2
            return ((b.getBoard().length - 1) * 2);
        } else { // // Si le nombre de maillon actuel <= nbMaillon Max /3
            // On utilise Lmax
            return (b.getBoard().length - 1);
        }

    }



}
