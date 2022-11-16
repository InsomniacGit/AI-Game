public class Case implements I_case{
    int graines_bleus;
    int graines_rouges;

    public Case(int graines_B, int graines_R){
        this.graines_bleus = graines_B;
        this.graines_rouges = graines_R;
    }

    public boolean est_vide(){
        if(this.graines_bleus == 0 && this.graines_rouges == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int nombre_graines_bleus(){
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
        this.graines_bleus -= graines_bleus;
        this.graines_rouges -= graines_rouges;
    }

    public void retirer_toutes_graines(){
        this.graines_bleus = 0;
        this.graines_rouges = 0;
    }
}
