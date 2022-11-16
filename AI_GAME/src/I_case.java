public interface I_case {
    boolean est_vide();
    int nombre_graines_bleus();
    int nombre_graines_rouges();
    int nombre_graines();
    void ajouter_graines(int graines_bleus, int graines_rouges);
    void retirer_graines(int graines_bleus, int graines_rouges);
    void retirer_toutes_graines();
}
