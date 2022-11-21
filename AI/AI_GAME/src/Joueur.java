public class Joueur implements I_joueur{
    public int score;
    private final String nom;
    private final int id;

    public Joueur(String n, int s, int id){
        this.nom = n;
        this.score = s;
        this.id = id;
    }

    public int consulter_score(){
        return this.score;
    }

    public String consulter_nom(){
        return this.nom;
    }

    public int consulter_id(){
        return this.id;
    }
}
