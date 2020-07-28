import java.util.*;

/**
 * @author mathis
 *
 */
public class Console {

    public static Scanner sc = new Scanner(System.in);

    /**
     * @return int size
     *
     *         La taille du tableau Board de la class GameState
     */
    public static int getSize() {
        return validInteger("Donnez la taille du tableau");
    }

    /**
     * Methode qui permet de réaliser un move
     * @param name le nom du joueur qui est entrainde jouer
     *
     * @return m, le move
     */
    public static Move getMove(String name) {
        System.out.println("Quel est votre coup " + name + " ?");
        return new Move(validInteger("ligne n°: ") - 1, validInteger("Nbr d'allumette: "));
    }

    /**
     * Methode d'affichage du plateau de jeu
     *
     * @param b
     *            - L'objet contenant toutes les informations du jeu
     *
     *
     */
    public static void showBoard(Board b) {
        System.out.println("");

        // digit pour la format String
        // 8 = nbr char de "ligne n°
        // String.valueOf pour convertir un entier un chaine de charactere
        // le length() pour avoir la taille de cette chaine
        String myFormat = "%" + (8 + String.valueOf(b.getPlateau().getBoard().length).length()) + "s:";
        // Parcours du tableau
        for (int k = 0; k < b.getPlateau().getBoard().length; k++) {

            // Formule trouvé pour determiner le nombre d'espace avant une ligne pour former
            // Une pyramide
            // Afficher avec une fomat string pour ne pas avoir de decallage au passage à
            // une dizaine superieur
            System.out.printf(myFormat, "ligne n°" + (k + 1));
            for (int i = 0; i < b.getPlateau().getBoard().length - k; i++) {
                System.out.print(" ");
            }

            // affichage des "allumettes" celon leurs presence sur une ligne determiné
            if (b.getPlateau().getBoard(k) == (2 * k + 1)) {
                for (int j = 0; j < (2 * k + 1); j++) {
                    System.out.print("|");
                }
            } else {
                for (int l = 0; l < b.getPlateau().getBoard(k); l++) {
                    System.out.print("|");
                }
            }

            System.out.println("");
        }
        System.out.println("");
    }

    /**
     * Affiche le vainqueur d'une partie
     *
     * @param string
     *            nom du jouueur qui gagne la partie
     */
    public static void showWinner(String name) {
        System.out.println("===========================================\n");
        System.out
                .println("	Bien joué " + name + " gagne la partie \n\n===========================================\n ");
    }

    /**
     * Affiche Le nombre de partie gagné par joueur apres la victoire de l'un d'eux
     *
     * @param b
     *            - L'objet contenant toutes les informatiosn du jeu
     */
    public static void showState(Board b) {
        System.out.println(b.getJ1().getName() + " a gagné " + b.getJ1().getWin().getNbMaillon() + " partie(s)");
        if (b.getPlateau().getVersion() == 1) {
            System.out.println(b.getJ2().getName() + " a gagné " + b.getJ2().getWin().getNbMaillon() + " partie(s)\n");
        } else {
            System.out.println(b.getIa().getName() + " a gagné " + b.getIa().getWin().getNbMaillon() + " partie(s)\n");
        }
    }

    /**
     * Methode d'affichage des stats apres toute(s) les partie(s) jouée(s)
     *
     * @param b
     *            - L'objet contenant toutes les informatiosn du jeu
     */
    public static void showUltimState(Board b) {

        if (b.getPlateau().getVersion() == 1) {
            showUltimJ1(b);
            showUltimJ2(b);
        } else {
            showUltimJ1(b);
            showUltimIa(b);
        }
        System.out.println("");
    }

    /**
     * Affiche les parties gagnés du joueur 1 quand les joueurs veulent arreter de
     * jouer
     *
     * @param b
     *            - L'objet contenant toutes les informatiosn du jeu
     */
    public static void showUltimJ1(Board b) {
        System.out.println();
        StringTokenizer str = new StringTokenizer(b.getJ1().getWin().toString(), " ");
        System.out.print(b.getJ1().getName() + " a gagné(e) la(es) partie(s) ");

        // Boucle pour obtenir le numero des parties gagnée(s)
        while (str.hasMoreTokens()) {
            System.out.print("n°" + str.nextToken());
            if (str.hasMoreTokens()) {
                System.out.print(", ");
            } else {
                System.out.print(".");
            }
        }

        System.out.println("");
    }

    /**
     * Affiche les parties gagnés du joueur 2 quand les joueurs veulent arreter de
     * jouer
     *
     * @param b
     *            - L'objet contenant toutes les informatiosn du jeu
     */
    public static void showUltimJ2(Board b) {
        StringTokenizer str = new StringTokenizer(b.getJ2().getWin().toString() + " ");
        System.out.print(b.getJ2().getName() + " a gagné(e) la(es) partie(s)");

        // Boucle pour obtenir le numero des parties gagné(es)
        while (str.hasMoreTokens()) {
            System.out.print("n°" + str.nextToken());
            if (str.hasMoreTokens()) {
                System.out.print(", ");
            } else {
                System.out.print(".");
            }
        }
        System.out.println("");
    }

    /**
     * Affiche les parties gagnés de l'IA quand les joueurs veulent arreter de jouer
     *
     * @param b
     *            - L'objet contenant toutes les informatiosn du jeu
     */
    public static void showUltimIa(Board b) {
        StringTokenizer str = new StringTokenizer(b.getIa().getWin().toString() + " ");
        System.out.print(b.getIa().getName() + " a gagné(e) la(es) partie(s)");

        // Boucle pour obtenir le numero des parties gagné(es)
        while (str.hasMoreTokens()) {
            System.out.print("n°" + str.nextToken());
            if (str.hasMoreTokens()) {
                System.out.print(", ");
            } else {
                System.out.print(".");
            }
        }
        System.out.println("");
    }

    /**
     * Methode permettant de determiner le vanqueur de la partie en cours
     *
     * @param b
     *            - L'objet contenant toutes les informatiosn du jeu
     */
    public static void determineVanqueur(Board b) {
        if (b.getPlateau().getVersion() == 1) {
            detHvH(b);
        } else {
            detIa(b);
        }

    }

    /**
     * Determine le vainquer lorsque la partie en cours est humain contre humain
     *
     * @param b
     *            - L'objet contenant toutes les informatiosn du jeu
     */
    public static void detHvH(Board b) {
        if (b.getPlateau().totAllu() == 1) {
            if (b.getPlateau().getTour() % 2 == 1) {
                b.getJ2().getWin().incWin(b.getPlateau().getNumPartie());
                Console.showWinner(b.getJ2().getName());
            } else {
                b.getJ1().getWin().incWin(b.getPlateau().getNumPartie());
                Console.showWinner(b.getJ1().getName());
            }
        } else {
            if (b.getPlateau().getTour() % 2 == 1) {
                b.getJ1().getWin().incWin(b.getPlateau().getNumPartie());
                Console.showWinner(b.getJ1().getName());
            } else {
                b.getJ2().getWin().incWin(b.getPlateau().getNumPartie());
                Console.showWinner(b.getJ2().getName());
            }
        }
    }

    /**
     * Determine le vainquer lorsque la partie en cours est humain contre IA
     *
     * @param b
     *            - L'objet contenant toutes les informatiosn du jeu
     */
    public static void detIa(Board b) {
        System.out.println();
        if (b.getPlateau().totAllu() == 1) {
            if (b.getPlateau().getTour() % 2 == 1) {
                b.getIa().getWin().incWin(b.getPlateau().getNumPartie());
                Console.showWinner(b.getIa().getName());
            } else {
                b.getJ1().getWin().incWin(b.getPlateau().getNumPartie());
                Console.showWinner(b.getJ1().getName());
            }
        } else {
            if (b.getPlateau().getTour() % 2 == 1) {
                b.getJ1().getWin().incWin(b.getPlateau().getNumPartie());
                Console.showWinner(b.getJ1().getName());
            } else {
                b.getIa().getWin().incWin(b.getPlateau().getNumPartie());
                Console.showWinner(b.getIa().getName());
            }
        }
        System.out.println();
    }

    /**
     * Affiche un message d'erreur de move invalide
     *
     * @param Move
     *            m
     *
     *            L'objet Move qui n'est pas valide
     */
    public static void invaldMove(Move m) {
        System.out.println("");
        System.out.println("le move (" + (m.getLigne() + 1) + "," + m.getAllumette() + ") n'est pas valide !");
    }

    /**
     * Initialisation du nom du premier joueur
     *
     * @return HumanPlayer joueur1
     *
     *         Le nom du premier joueur
     */
    public static HumanPlayer initJ1() {
        String str = null;
        do {
            System.out.println("Entrer nom J1");
            str = sc.nextLine();
        } while (str.trim().equalsIgnoreCase("") || str.trim().equalsIgnoreCase(" "));
        return new HumanPlayer(str);
    }

    /**
     * Initialisation du nom du sencond joueur
     *
     * @return HumanPlayer joueur1
     *
     *         Le nom du second joueur
     */
    public static HumanPlayer initJ2() {
        String str = null;
        do {
            System.out.println("Entrer nom J2");
            str = sc.nextLine();
        } while (str.trim().equalsIgnoreCase("") || str.trim().equalsIgnoreCase(" "));
        return new HumanPlayer(str);
    }

    /**
     * @param b
     *            - L'objet contenant toutes les informatiosn du jeu
     * @return Move - le Move que le joueur vient de jouer
     */
    public static Move tourJoueur(Board b) {
        Move mv = new Move();
        if (b.getPlateau().getTour() % 2 == 1) {
            do {
                mv = Console.getMove(b.getJ1().getName());

                // Previent le joueur si le move n'est pas valide puis affiche le plateau
                if (!(mv.estMoveValide(b.getPlateau()))) {
                    Console.invaldMove(mv);
                    Console.showBoard(b);
                }

                // Permet d'informer le programme que le joueur 1 a joué
                // Et que le prochain tour sera celui du second joueur
            } while (!mv.estMoveValide(b.getPlateau()));
            return mv;
        } else {
            do {
                mv = Console.getMove(b.getJ2().getName());

                // Previent le joueur si le move n'est pas valide puis affiche le plateau
                if (!(mv.estMoveValide(b.getPlateau()))) {
                    Console.invaldMove(mv);
                    Console.showBoard(b);
                }

            } while (!mv.estMoveValide(b.getPlateau()));
            return mv;

        }
    }

    /**
     * Methode permettant d'alterner entre le tour du joueur 1 et du joueur 2 ou de
     * l'ia
     *
     * @param b
     *            - L'objet contenant toutes les informatiosn du jeu
     */
    public static void alternTour(Board b) {
        Move mv = new Move();
        while (!(b.getPlateau().totAllu() <= 1)) {

            // L'entier tour permet savoir quel joueur doit jouer en suivant
            if (b.getPlateau().getTour() % 2 == 1 & b.getPlateau().totAllu() > 1) { // Si le nbr de tour est impair
                // c'est au premiere joueur de jouer
                // Affiche le plateau
                showBoard(b);

                // Demande le move que le joueur veut effectuer tant que celui n'est pas valide
                mv = tourJoueur(b);

                // Met à jour le plateau de jeu puis l'affiche
                b.getPlateau().applyMove(mv);

                // Incremente le nombre de tour joué
                b.getPlateau().incTour();


                // Idem que pour le joueur 1 mais pour le joueur 2 ou l'ia
            } else if (b.getPlateau().getTour() % 2 == 0 && b.getPlateau().totAllu() > 1) { // Si nbr tour est pair
                // c'est au 2nd joueur de jouer
                if (b.getPlateau().getVersion() == 1) { // HvH

                    showBoard(b);
                    mv = tourJoueur(b);
                    b.getPlateau().applyMove(mv);

                } else if (b.getPlateau().getVersion() == 2) { // IA Naive

                    b.getIa().refresh(mv, b.getPlateau());
                    mv = b.getIa().randMove();
                    b.getPlateau().applyMove(mv);
                    b.getIa().refresh(mv, b.getPlateau());
                    Console.affTourIa(mv, b);

                } else { // IA SG

                    //b.getIa().refresh(mv, b.getPlateau());
                    mv = b.getIa().tourIA(b.getPlateau());
                    b.getPlateau().applyMove(mv);
                    Console.affTourIa(mv, b);
                }
                b.getPlateau().incTour();
            }
        }
    }

    /**
     * Affiche le nom du joueur qui doit jouer en suivant
     *
     * @param t
     *            permet de referencer le joueur dans le main
     *
     * @param b
     *            permet d'obtenir le nom du joueur celon le parametre t
     */
    public static void affTourIa(Move mv, Board b) {
        System.out.println();
        System.out.println("L-ia a joué le coup " + mv.toString());
    }

    /**
     * Test si une String ne comporte seulement des entiers
     *
     * @param str
     *            La String à tester
     * @return Vrai si la String ne comporte que des entiers, faux sinon
     */
    public static boolean isNumericInput(String str) {
        if (str == null || str.trim().equalsIgnoreCase(""))
            return false;

        char c[] = str.toCharArray(); // Construit un tableau de tous les caractere de la String
        for (int i = 0; i < c.length; i++) { // Test si tous les caracteres sont des entiers
            if (c[i] < '0' || c[i] > '9')
                return false;
        }
        return true;
    }

    /**
     * Methode pour demander au joueur si il veut rejouer
     *
     * @return vrai si le joueur le demande et faux sinon
     */
    public static boolean replay() {
        String s = null;
        do {
            System.out.println("Voulez vous rejouer ? [O/n]");
            s = sc.nextLine();
            if (s.trim().equalsIgnoreCase("o") || s.trim().equalsIgnoreCase("oui")) {
                return true;
            } else if (s.trim().equalsIgnoreCase("n") || s.trim().equalsIgnoreCase("non")) {
                return false;
            }
        } while ((!s.trim().equalsIgnoreCase("o") || !s.trim().equalsIgnoreCase("oui")
                || !s.trim().equalsIgnoreCase("n") || !s.trim().equalsIgnoreCase("non")));
        return false;
    }

    /**
     * Permet de choisir le style de jeu auquel on veut jouer
     *
     * @param Board
     *            b - Le plateau de jeu
     * @return int - Le mode de jeu choisit
     */
    public static int choixStyle(Board b) {
        if (b.getPlateau().getVersion() == 0) {

            b.getPlateau().setVersion(validInteger("Quel est le jeu auquel vous desirez jouer ?\n"
                    + "1:Humain Vs Humain\n" + "2: Humain Vs IA naïve\n" + "3: Humain Vs IA strategique"));
            return b.getPlateau().getVersion();
        } else {
            return b.getPlateau().getVersion();
        }

    }

    /**
     * Permet de choisir si l'on veut jouer à un autre style de jeu apres une partie
     *
     * @param b
     *            - L'objet contenant toutes les informatiosn du jeu
     * @return Vrai si le joueur veut jouer à un autre style et faux sinon
     */
    public static boolean replayStyle(Board b) {
        String s = null;
        int choixRejouer = 0, choixVersion = 0;
        if (b.getPlateau().getVersion() == 1) {

            do {
                System.out.println("voulez vous jouez à un autre style ? [O/n]");
                s = sc.nextLine();

                if (s.trim().equalsIgnoreCase("oui") || s.trim().equalsIgnoreCase("o")) {



                    do {
                        choixVersion = validInteger("A quelle version voulez vous jouer ?"
                                + "\nTaper 1 pour IA Naive" + "\nTaper 2 pour IA Strategie gagante");

                        if (choixVersion == 1) {
                            b.getPlateau().setVersion(2);
                            return true;
                        } else {
                            b.getPlateau().setVersion(3);
                            return true;
                        }
                    } while (choixVersion != 1 || choixVersion != 2);

                    //} while (choixRejouer != 1 || choixRejouer != 2);

                } else {
                    return false;
                }

            } while (!s.trim().equalsIgnoreCase("Oui") || !s.trim().equalsIgnoreCase("O")
                    || !s.trim().equalsIgnoreCase("non") || !s.trim().equalsIgnoreCase("n"));

        } else {
            if (b.getPlateau().getVersion() == 2) {
                do {
                    System.out.println("voulez vous jouez à un autre style ?");
                    s = sc.nextLine();

                    if (s.trim().equalsIgnoreCase("oui") || s.trim().equalsIgnoreCase("o")) {
                        do {

                            choixVersion = validInteger("A quelle version voulez vous jouer ?" + "\nTaper 1 pour HvH"
                                    + "\nTaper 2 pour IA Strategie gagante");

                            if (choixVersion == 1) {
                                b.setIa(null);
                                b.getPlateau().setVersion(1);
                                return true;
                            } else {
                                b.setIa(null);
                                b.getPlateau().setVersion(3);
                                return true;
                            }
                        } while (choixVersion != 1 || choixVersion != 2);

                    } else {
                        return false;
                    }
                } while (!s.trim().equalsIgnoreCase("Oui") || !s.trim().equalsIgnoreCase("O")
                        || !s.trim().equalsIgnoreCase("non") || !s.trim().equalsIgnoreCase("n"));

            } else {

                do {
                    System.out.println("voulez vous jouez à un autre style ?");
                    s = sc.nextLine();

                    if (s.trim().equalsIgnoreCase("oui") || s.trim().equalsIgnoreCase("o")) {
                        do {

                            choixVersion = validInteger("A quelle version voulez vous jouer ?" + "\nTaper 1 pour HvH"
                                    + "\nTaper 2 pour IA Naive");
                            if (choixVersion == 1) {
                                b.getPlateau().setVersion(1);
                                b.setIa(null);
                                return true;
                            } else {
                                b.setIa(null);
                                b.getPlateau().setVersion(2);
                                return true;
                            }
                        } while (choixVersion != 1 || choixVersion != 2);

                    } else {
                        return false;
                    }
                } while (!s.trim().equalsIgnoreCase("Oui") || !s.trim().equalsIgnoreCase("O")
                        || !s.trim().equalsIgnoreCase("non") || !s.trim().equalsIgnoreCase("n"));

            }
        }
    }

    /**
     * Permet d'obtenir avec certitude un entier lors de la saisie clavier d'un
     * jouer
     *
     * @param ask
     *            - L'information nécéssaire à la saisie de la variable par le
     *            joueur
     * @return L'entier saisie par le joueur
     */
    public static int validInteger(String ask) {
        String str = null;
        do {
            System.out.println(ask);
            str = sc.nextLine();
        } while (!isNumericInput(str));
        return Integer.parseInt(str);
    }

}
