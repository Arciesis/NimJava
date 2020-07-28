public class Nim {

    public static void main(String[] args) {
        Board b = new Board();
        b.setPlateau(new GameState());


        do {

            switch (Console.choixStyle(b)) {

                case 1: // hvh
                    b.setJ1(Console.initJ1());
                    b.setJ2(Console.initJ2());

                    do {
                        b.setPlateau(new GameState(Console.getSize(), b.getPlateau().getNumPartie(),
                                b.getPlateau().getVersion()));
                        b.getPlateau().initTab();

                        // Boucle qui fait jouer par alternance les deux joueurs
                        Console.alternTour(b);
                        // Determine le vanqueur et l'affiche
                        Console.determineVanqueur(b);
                        // Incremente le nombre de partie joué
                        b.getPlateau().incPartie();
                        // Affiche le vaniqueur selon la derniere personne à avoir joue
                        Console.showState(b);
                    } while (Console.replay());
                    Console.showUltimState(b);

                    break;

                case 2: // IA Naive
                    b.setJ1(Console.initJ1());
                    b.setIa(new Ia());
                    b.initNameIa();

                    do {
                        // Initialisation
                        b.setPlateau(new GameState(Console.getSize(), b.getPlateau().getNumPartie(),
                                b.getPlateau().getVersion()));
                        b.getPlateau().initTab();
                        b.getIa().initListe(b.getPlateau());

                        Console.alternTour(b);
                        Console.determineVanqueur(b);
                        b.getPlateau().incPartie();

                        Console.showState(b);
                    } while (Console.replay());
                    Console.showUltimState(b);
                    break;

                case 3: // IA Strategie Gagnante
                    b.setJ1(Console.initJ1());
                    b.setIa(new Ia());
                    b.initNameIa();

                    do {
                        b.setPlateau(new GameState(Console.getSize(), b.getPlateau().getNumPartie(),
                                b.getPlateau().getVersion()));
                        b.getPlateau().initTab();
                        b.getIa().initGraph(b.getPlateau());

                        Console.alternTour(b);
                        Console.determineVanqueur(b);
                        b.getPlateau().incPartie();

                        Console.showState(b);
                    } while (Console.replay());
                    Console.showUltimState(b);
                    break;

            }
        } while (Console.replayStyle(b));
    }

}
