import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public interface I_plateau {
    /** Initialisation du plateau de jeu */
    void init_plateau();
    void init_plateau(Plateau p);
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

    String minCoup(Map<String, Integer> tab);

    int minInArray(ArrayList<Integer> tab);

    String maxCoup(Map<String, Integer> tab);

    int maxInArray(ArrayList<Integer> tab);

    int evaluation(Plateau e, Joueur[] J);

    int evaluationRapide(Plateau e, Joueur[] J);

    int evaluationLent(Plateau e, Joueur[] J);

    int MinMaxValue(Plateau e, Joueur[] J, Joueur Jcurrent, boolean isMax, int pmax);

    int MinMaxValueRapide(Plateau e, Joueur[] J, Joueur Jcurrent, boolean isMax, int pmax);

    int MinMaxValueLent(Plateau e, Joueur[] J, Joueur Jcurrent, boolean isMax, int pmax);

    int AlphaBetaValue(Plateau e, Joueur[] J, Joueur Jcurrent, int alpha, int beta, boolean isMax, int pmax);

    int AlphaBetaValueRapide(Plateau e, Joueur[] J, Joueur Jcurrent, int alpha, int beta, boolean isMax, int pmax);

    int AlphaBetaValueLent(Plateau e, Joueur[] J, Joueur Jcurrent, int alpha, int beta, boolean isMax, int pmax);

    String DecisionMinMax (Plateau e, Joueur[] J, Joueur Jcurrent, int pmax) ;

    String DecisionMinMaxRapide (Plateau e, Joueur[] J, Joueur Jcurrent, int pmax) ;

    String DecisionMinMaxLent (Plateau e, Joueur[] J, Joueur Jcurrent, int pmax) ;

    String DecisionAlphaBeta (Plateau e, Joueur[] J, Joueur Jcurrent, int pmax) ;

    String DecisionAlphaBetaRapide (Plateau e, Joueur[] J, Joueur Jcurrent, int pmax) ;

    String DecisionAlphaBetaLent (Plateau e, Joueur[] J, Joueur Jcurrent, int pmax) ;

    void ordinateurMinMax0(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax1(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax2(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax3(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax4(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax5(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax6(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax7(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax8(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax9(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax10(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMaxAdaptatif(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax0Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax1Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax2Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax3Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax4Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax5Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax6Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax7Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax8Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax9Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax10Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMaxAdaptatifRapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax0Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax1Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax2Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax3Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax4Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax5Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax6Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax7Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax8Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax9Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMax10Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurMinMaxAdaptatifLent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;


    void ordinateurAlphaBeta5(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta6(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta7(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta8(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta9(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta10(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta11(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta12(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta13(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta14(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta15(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBetaAdaptatif(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta5Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta6Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta7Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta8Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta9Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta10Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta11Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta12Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta13Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta14Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta15Rapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBetaAdaptatifRapide(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta5Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta6Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta7Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta8Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta9Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent);

    void ordinateurAlphaBeta10Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta11Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta12Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta13Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) ;

    void ordinateurAlphaBeta14Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent);

    void ordinateurAlphaBeta15Lent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent);

    void ordinateurAlphaBetaAdaptatifLent(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent);











    void afficher(Joueur[] joueurs);
}
