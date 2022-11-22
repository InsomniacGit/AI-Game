public class Main {
    public static final int NB_JOUEURS = 2;
    public static final int NB_CASES = 16;
    public static final boolean DEBUG = true;

    public static void main(String[] args) {
        // Initialisation des joueurs
        Joueur[] joueurs = new Joueur[NB_JOUEURS];
        for(int k = 0; k < NB_JOUEURS; k++){
            Joueur j = new Joueur("player" + (k+1), 0, k+1);
            joueurs[k] = j;
        }

        // Initialisation du plateau
        Plateau plateau = new Plateau(NB_CASES);
        plateau.init_plateau();

        if(DEBUG) plateau.afficher(joueurs);

        // Début de la partie
        int numCoup = 0;
        while(plateau.etat_partie(joueurs[0].consulter_score(), joueurs[1].consulter_score())){

            Joueur joueur_current = joueurs[numCoup%2];

            // Mode 1v1
            /**plateau.jouer(joueur_current, joueurs[(numCoup+1)%2]);*/

            // Mode Ordinateur vs Ordinateur
            /**plateau.ordinateur(joueur_current, joueurs[(numCoup+1)%2]);*/

            // Mode 1 contre Ordinateur
            if(joueur_current.consulter_id() == 1){
                plateau.jouer(joueur_current, joueurs[(numCoup+1)%2]);
            }
            else{
                plateau.ordinateur(joueur_current, joueurs[(numCoup+1)%2]);
            }


            // Mode Ordinateur contre 1
            /**
            if(joueur_current.consulter_id() == 2){
                plateau.jouer(joueur_current, joueurs[(numCoup+1)%2]);
            }
            else{
                plateau.ordinateur(joueur_current, joueurs[(numCoup+1)%2]);
            }
             */

            if(DEBUG) plateau.afficher(joueurs);

            numCoup++;
        }

        // Afficher vainqueur
        plateau.connaitre_vainqueur(joueurs[0], joueurs[1]);
    }
}