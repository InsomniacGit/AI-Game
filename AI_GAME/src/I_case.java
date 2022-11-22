public interface I_case {
    /** Savoir si la case est vide */
    boolean est_vide();
    /** Connaitre le nombre de graines bleues dans la case */
    int nombre_graines_bleues();
    /** Connaitre le nombre de graines rouges dans la case */
    int nombre_graines_rouges();
    /** Connaitre le nombre de graines totales dans la case */
    int nombre_graines();
    /** Ajouter n graines bleues et/ou m graines rouges dans la case */
    void ajouter_graines(int graines_bleus, int graines_rouges);
    /** Retirer n graines bleues et/ou m graines rouges dans la case */
    void retirer_graines(int graines_bleus, int graines_rouges);
    /** Retirer toutes les graines dans la case */
    void retirer_toutes_graines();
}
