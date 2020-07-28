public class HumanPlayer {

    /**
     * Champ representant le nom du joueur
     */
    private String name;

    /**
     * Champ representant les parties gagnés par le joueur
     * chaque maillon contiendra le numero de la partie gagné par le joueur
     */
    private LC<Integer> win;

    /**
     * @param nom nom - le nom associé au joueur
     */
    public HumanPlayer(String nom) {
        name=nom;
        win= new LC<Integer>();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the win
     */
    public LC<Integer> getWin(){
        return win;
    }


    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param l the win to set
     */
    public void setWin(LC<Integer> l) {
        win = l;
    }

}

