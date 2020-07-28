public class Maillon<T> {

    private Maillon<T> prev;
    private T valeur;
    private Maillon<T> next;

    public Maillon(T m, Maillon<T> pre, Maillon<T> nex) {
        prev=pre;
        valeur=m;
        next=nex;
    }

    public Maillon(T valeur, Maillon<T> suivant) {
        this.valeur = valeur;
        this.next = suivant;
    }

    public Maillon(Maillon<T> precedent, T mv) {
        valeur=mv;
        prev=precedent;
        next=null;
    }



    public Maillon(T valeur) {
        prev=null;
        next=null;
        this.valeur = valeur;
    }

    public Maillon() {
        prev=null;
        next=null;
        valeur=null;
    }

    /**
     * @return the prev
     */
    public Maillon<T> getPrev() {
        return prev;
    }



    /**
     * @param prev the prev to set
     */
    public void setPrev(Maillon<T> prev) {
        this.prev = prev;
    }

    public void setPrevNull() {
        prev = null;
    }

    /**
     * @return the valeur
     */
    public T getValeur() {
        return valeur;
    }

    /**
     * @return the suivant
     */
    public Maillon<T> getNext() {
        return next;
    }

    /**
     * @param valeur the valeur to set
     */
    public void setValeur(T valeur) {
        this.valeur = valeur;
    }

    /**
     * @param suivant the suivant to set
     */
    public void setNext(Maillon<T> suivant) {
        this.next = suivant;
    }

    public void setNextNull() {
        next=null;
    }


}
