import java.util.List;

public interface I_plateau {
    void init_plateau();
    void capturer(int index_case, Joueur joueur_courrant);
    int semer(String coup);
    boolean coup_possible(String coup);
    List<String> liste_coup_possible(Joueur joueur_current);
    boolean affamer(Joueur joueur_current, Joueur joueur_precedent);
    void jouer(Joueur joueur_current, Joueur joueur_precedent);
    void ordinateur(Joueur joueur_current, Joueur joueur_precedent);
    void actualisation_graine_restante(int nombre_graine_capture);
    int graines_restantes();
    boolean partie_non_finie(int score_J1, int score_J2);
    void connaitre_vainqueur(Joueur j1, Joueur j2);
    void afficher(Joueur[] joueurs);
}
