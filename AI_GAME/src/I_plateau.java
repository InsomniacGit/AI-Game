import java.util.List;

public interface I_plateau {
    /** Initialisation du plateau de jeu */
    void init_plateau();
    /** Ramasser les graines de la case courante si possible, si oui, tant que c'est possible, faire la même chose pour la case précédente  */
    void capturer(int index_case, Joueur joueur_courant);
    /** Seme les graines en fonction de l'instruction donnée */
    int semer(String coup);
    /** Verifier si le coup est possible et accepté */
    boolean coup_possible(String coup);
    /** Récupérer la liste de coup possible du joueur à un instant donné, sur l'ensemble de ses coups possibles */
    List<String> liste_coup_possible(Joueur joueur_current);
    /** Si le joueur courant est affamé, le joueur précédent récupère le reste des graines */
    boolean est_affame(Joueur joueur_current, Joueur joueur_precedent);
    /** Tour de jeu du joueur courant */
    void jouer(Joueur joueur_current, Joueur joueur_precedent);
    /** Tour de jeu du joueur courant s'il s'agit d'un bot */
    void ordinateurNaif(Joueur joueur_current, Joueur joueur_precedent);
    /** Mise à jour des graines restantes sur le plateau */
    void actualisation_graines_restantes(int nombre_graine_capture);
    /** Connaitre le nombre de graines encore en jeu */
    int connaitre_graines_restantes();
    /** Connaitre l'état de la partie */
    boolean etat_enCours(int score_J1, int score_J2);
    /** Connaitre résultat de la partie */
    void connaitre_vainqueur(Joueur j1, Joueur j2);
    /** Afficher le plateau de jeu */
    void afficher(Joueur[] joueurs);
}
