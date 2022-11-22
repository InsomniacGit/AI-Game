public class Case implements I_case{
    public int graines_bleus;
    public int graines_rouges;

    public Case(int graines_B, int graines_R){
        this.graines_bleus = graines_B;
        this.graines_rouges = graines_R;
    }

    public boolean est_vide(){
        return this.graines_bleus == 0 && this.graines_rouges == 0;
    }

    public int nombre_graines_bleues(){
        return this.graines_bleus;
    }

    public int nombre_graines_rouges(){
        return this.graines_rouges;
    }

    public int nombre_graines(){
        return this.graines_bleus + this.graines_rouges;
    }

    public void ajouter_graines(int graines_bleus, int graines_rouges){
        this.graines_bleus += graines_bleus;
        this.graines_rouges += graines_rouges;
    }

    public void retirer_graines(int graines_bleus, int graines_rouges){
        ajouter_graines(-1 * graines_bleus,-1 * graines_rouges);
    }

    public void retirer_toutes_graines(){
        retirer_graines(this.nombre_graines_bleues(), this.nombre_graines_rouges());
    }
}
