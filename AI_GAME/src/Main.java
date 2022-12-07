public class Main {
    public static final int NB_JOUEURS = 2;
    public static final int NB_CASES = 16;
    public static final boolean DEBUG = true;

    public static void main(String[] args) {

        //while(true) {

        // Initialisation des joueurs
        Joueur[] joueurs = new Joueur[NB_JOUEURS];
        for (int k = 0; k < NB_JOUEURS; k++) {
            Joueur j = new Joueur("player" + (k + 1), 0, k + 1);
            joueurs[k] = j;
        }

        // Initialisation du plateau
        Plateau plateau = new Plateau(NB_CASES);
        plateau.init_plateau();

        if (DEBUG) plateau.afficher(joueurs);

        // Début de la partie
        int numCoup = 0;

        while (plateau.etat_enCours(joueurs[0].consulter_score(), joueurs[1].consulter_score())) {

            Joueur joueur_current = joueurs[numCoup % 2];

            // Mode 1v1
            /**plateau.jouer(joueur_current, joueurs[(numCoup+1)%2]);*/

            // Mode OrdinateurNaif vs OrdinateurNaif
            /**plateau.ordinateurNaif(joueur_current, joueurs[(numCoup+1)%2]);*/

            // Mode 1 contre OrdinateurNaif
                /*if(joueur_current.consulter_id() == 1){      // 1 pour jouer en premier, 2 en deuxième
                    plateau.jouer(joueur_current, joueurs[(numCoup+1)%2]);
                }
                else{
                    long start = System.currentTimeMillis();
                    plateau.ordinateurMinMaxAdaptatif(joueur_current, joueurs, joueurs[(numCoup+1)%2]);
                    System.out.println("Run time : " + (System.currentTimeMillis() - start));
                }*/

            // Mode OrdinateurMinMax2 vs OrdinateurNaif
            /**if(joueur_current.consulter_id() == 2){ // Si ==2, alors joueur2, sinon joueur1
             plateau.ordinateurMinMax2(joueur_current, joueurs, joueurs[(numCoup+1)%2]);
             }
             else{
             plateau.ordinateurNaif(joueur_current, joueurs[(numCoup+1)%2]);
             }*/

            // Mode OrdinateurMinMax1 vs OrdinateurMinMax3
                /*if(joueur_current.consulter_id() == 2){ // Si ==2, alors joueur2, sinon joueur1
                 plateau.ordinateurMinMax5(joueur_current, joueurs, joueurs[(numCoup+1)%2]);
                 }
                 else{
                 plateau.ordinateurMinMaxAdaptatif(joueur_current, joueurs, joueurs[(numCoup+1)%2]);
                 }*/

            // Mode OrdinateurMinMaxAdaptatif vs OrdinateurMinMax5
            /**if (joueur_current.consulter_id() == 2) { // Si ==2, alors joueur2, sinon joueur1
             long start = System.currentTimeMillis();
             plateau.ordinateurMinMaxAdaptatif(joueur_current, joueurs, joueurs[(numCoup + 1) % 2]);
             System.out.println("Run time : " + (System.currentTimeMillis() - start));
             } else {
             plateau.ordinateurMinMax5(joueur_current, joueurs, joueurs[(numCoup + 1) % 2]);
             }*/

            // Mode OrdinateurMinMaxAdaptatif vs OrdinateurMinMaxAdaptatif
            /**long start = System.currentTimeMillis();
             plateau.ordinateurMinMaxAdaptatif(joueur_current, joueurs, joueurs[(numCoup + 1) % 2]);
             System.out.println("Run time : " + (System.currentTimeMillis() - start));*/

            // Mode OrdinateurMinMaxAdaptatif vs OrdinateurAlphaBetaAdaptatif
            if (joueur_current.consulter_id() == 2) { // Si ==2, alors joueur2, sinon joueur1
                long start = System.currentTimeMillis();
                plateau.ordinateurAlphaBetaAdaptatif(joueur_current, joueurs, joueurs[(numCoup + 1) % 2]);
                System.out.println("Run time : " + (System.currentTimeMillis() - start));
            } else {
                long start = System.currentTimeMillis();
                plateau.ordinateurMinMax5(joueur_current, joueurs, joueurs[(numCoup + 1) % 2]);
                System.out.println("Run time : " + (System.currentTimeMillis() - start));
            }


            if (DEBUG) plateau.afficher(joueurs);

            numCoup++;
        }


        // Afficher vainqueur
        plateau.connaitre_vainqueur(joueurs[0], joueurs[1]);
        //}
    }
}