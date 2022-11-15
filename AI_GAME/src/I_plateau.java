public interface I_plateau {
    void init_plateau();
    void capturer();
    void semer();
    boolean coup_possible();
    void jouer();
    int graines_restantes();
    boolean partie_terminee();
    void afficher();
}
