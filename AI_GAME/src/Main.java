public class Main {
    static final int NB_JOUEURS = 2;
    static final int NB_CASES = 16;
    static final boolean DEBUG = true;

    public static void main(String[] args) {
        // Initialisation des joueurs
        Joueur[] joueurs = new Joueur[NB_JOUEURS];
        for(int k = 0; k < NB_JOUEURS; k++){
            Joueur j = new Joueur("player"+String.valueOf(k+1));
            joueurs[k] = j;
        }

        // Initialisation du plateau
        Plateau plateau = new Plateau(NB_CASES);
        plateau.init_plateau();

        if(DEBUG) plateau.afficher();

        // Début de la partie
        while(plateau.partie_en_cours){
            // TO DO
        }

        // Afficher vainqueur
        System.out.printf("Le vainqueur est %s - %d graines à %d graines\n", connaitre_vainqueur(joueurs[0], joueurs[1]), joueurs[0].consulter_score(), joueurs[1].consulter_score());
    }
    static String connaitre_vainqueur(Joueur j1, Joueur j2){
        if(j1.consulter_score() > j2.consulter_score()){
            return j1.consulter_nom();
        } else if (j1.consulter_score() < j2.consulter_score()) {
            return j2.consulter_nom();
        } else {
            return  j1.consulter_nom() + " & " + j2.consulter_nom();
        }
    }
}