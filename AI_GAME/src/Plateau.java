import java.util.ArrayList;
import java.util.List;

public class Plateau implements I_plateau{
    Case[] cases;
    boolean partie_en_cours;
    int nombres_graines_plateau;
    int nombres_graines_restante;
    int taille_plateau;

    public Plateau(int size){
        this.partie_en_cours = true;
        this.cases = new Case[size];
        this.nombres_graines_plateau = size * 4;
        this.nombres_graines_restante = size * 4;
        this.taille_plateau = size;
    }

    public void init_plateau(){
        for(int i=0; i<this.taille_plateau; i++){
            this.cases[i] = new Case(2,2);
        }
    }

    public void capturer(int index_case, Joueur joueur_courrant){
        while(this.cases[index_case].nombre_graines() == 3 || this.cases[index_case].nombre_graines() == 2){
            joueur_courrant.score += this.cases[index_case].nombre_graines();
            actualisation_graine_restante(this.cases[index_case].nombre_graines());
            this.cases[index_case].retirer_toutes_graines();
            if(index_case == 0){
                index_case = 15;
            }
            else {
                index_case--;
            }
        }
    }

    public int semer(String coup){
        int numero_case;
        char couleur;
        int nombre_graines;
        int derniere_semance = 0;

        if(coup.length() == 3){
            numero_case = Integer.parseInt(coup.substring(0,2));
            couleur = coup.charAt(2);
        }
        else{
            numero_case = Integer.parseInt(coup.substring(0,1));
            couleur = coup.charAt(1);
        }

        if(couleur == 'B'){ //Opponent holes
            nombre_graines = this.cases[numero_case-1].nombre_graines_bleus();
            this.cases[numero_case-1].retirer_graines(nombre_graines,0);

            for(int i=1; i<nombre_graines*2+1; i=i+2) {
                this.cases[(numero_case-1 + i) % this.taille_plateau].ajouter_graines(1, 0);
            }
            derniere_semance = (numero_case-1 + (nombre_graines-1)*2+1) % this.taille_plateau;

        }
        else if (couleur == 'R'){ // All holes
            nombre_graines = this.cases[numero_case-1].nombre_graines_rouges();
            this.cases[numero_case-1].retirer_graines(0,nombre_graines);
            int nombre_tour = 0;

            for(int i=0; i<nombre_graines; i++){
                if((numero_case-1 + i + nombre_tour) % this.taille_plateau == numero_case-1) {
                    nombre_tour++;
                }

                this.cases[(numero_case-1 + i + nombre_tour) % this.taille_plateau].ajouter_graines(0,1);
            }

            derniere_semance = (numero_case-1 + nombre_graines-1 + nombre_tour) % this.taille_plateau;
        }

        return derniere_semance;
    }

    public boolean coup_possible(String coup){
        int numero_case;
        char couleur;

        if(coup.length() == 3){
            numero_case = Integer.parseInt(coup.substring(0,2));
            couleur = coup.charAt(2);
        }
        else{
            numero_case = Integer.parseInt(coup.substring(0,1));
            couleur = coup.charAt(1);
        }

        if(couleur == 'B' && this.cases[numero_case-1].nombre_graines_bleus() != 0){
            return true;
        }
        else if (couleur == 'R' && this.cases[numero_case-1].nombre_graines_rouges() != 0){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean affamer(Joueur joueur_current, Joueur joueur_precedent){
        List<String> liste_coup_possible = new ArrayList<String>();
        if(joueur_current.consulter_id() == 1){
            if(coup_possible("1B")){
                liste_coup_possible.add("1B");
            }
            if(coup_possible("1R")){
                liste_coup_possible.add("1R");
            }
            if(coup_possible("3B")){
                liste_coup_possible.add("3B");
            }
            if(coup_possible("3R")){
                liste_coup_possible.add("3R");
            }
            if(coup_possible("5B")){
                liste_coup_possible.add("5B");
            }
            if(coup_possible("5R")){
                liste_coup_possible.add("5R");
            }
            if(coup_possible("7B")){
                liste_coup_possible.add("7B");
            }
            if(coup_possible("7R")){
                liste_coup_possible.add("7R");
            }
            if(coup_possible("9B")){
                liste_coup_possible.add("9B");
            }
            if(coup_possible("9R")){
                liste_coup_possible.add("9R");
            }
            if(coup_possible("11B")){
                liste_coup_possible.add("11B");
            }
            if(coup_possible("11R")){
                liste_coup_possible.add("11R");
            }
            if(coup_possible("13B")){
                liste_coup_possible.add("13B");
            }
            if(coup_possible("13R")){
                liste_coup_possible.add("13R");
            }
            if(coup_possible("15B")){
                liste_coup_possible.add("15B");
            }
            if(coup_possible("15R")){
                liste_coup_possible.add("15R");
            }

            if (liste_coup_possible.size() == 0){
                joueur_precedent.score += graines_restantes();
                actualisation_graine_restante(graines_restantes());
                for(int i=0; i<this.taille_plateau; i++){
                    this.cases[i].retirer_toutes_graines();
                }
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(coup_possible("2B")){
                liste_coup_possible.add("2B");
            }
            if(coup_possible("2R")){
                liste_coup_possible.add("2R");
            }
            if(coup_possible("4B")){
                liste_coup_possible.add("4B");
            }
            if(coup_possible("4R")){
                liste_coup_possible.add("4R");
            }
            if(coup_possible("6B")){
                liste_coup_possible.add("6B");
            }
            if(coup_possible("6R")){
                liste_coup_possible.add("6R");
            }
            if(coup_possible("8B")){
                liste_coup_possible.add("8B");
            }
            if(coup_possible("8R")){
                liste_coup_possible.add("8R");
            }
            if(coup_possible("10B")){
                liste_coup_possible.add("10B");
            }
            if(coup_possible("10R")){
                liste_coup_possible.add("10R");
            }
            if(coup_possible("12B")){
                liste_coup_possible.add("12B");
            }
            if(coup_possible("12R")){
                liste_coup_possible.add("12R");
            }
            if(coup_possible("14B")){
                liste_coup_possible.add("14B");
            }
            if(coup_possible("14R")){
                liste_coup_possible.add("14R");
            }
            if(coup_possible("16B")){
                liste_coup_possible.add("16B");
            }
            if(coup_possible("16R")){
                liste_coup_possible.add("16R");
            }

            if (liste_coup_possible.size() == 0){
                joueur_precedent.score += graines_restantes();
                actualisation_graine_restante(graines_restantes());
                for(int i=0; i<this.taille_plateau; i++){
                    this.cases[i].retirer_toutes_graines();
                }
                return true;
            }
            else {
                return false;
            }
        }
    }

    public void jouer(Joueur joueur_current, Joueur joueur_precedent) {
        java.util.Scanner entree = new java.util.Scanner(System.in);
        String coup = "";
        boolean coup_possible = false;

        if (!affamer(joueur_current, joueur_precedent)) {

            while (!coup_possible) {
                if (joueur_current.consulter_id() == 1) {
                    String regex = "^1[135][BR]$|^[13579][BR]$";

                    System.out.print(joueur_current.consulter_nom() + " - Ecris ton coup! (Par exemple : '1B' ou '15R') : ");
                    coup = entree.next();

                    while (!coup.matches(regex)) {
                        System.out.print(joueur_current.consulter_nom() + " - Il faut choisir une case impaire (entre 1 et 15) puis une couleur (B ou R) : ");
                        coup = entree.next();
                    }
                } else {
                    String regex = "^1[0246][BR]$|^[2468][BR]$";

                    System.out.print(joueur_current.consulter_nom() + " - Ecris ton coup! (Par exemple : '2B', '16R') : ");
                    coup = entree.next();

                    while (!coup.matches(regex)) {
                        System.out.print(joueur_current.consulter_nom() + " - Il faut choisir une case (entre 2 et 16) puis une couleur (B ou R) : ");
                        coup = entree.next();
                    }
                }

                System.out.print(joueur_current.consulter_nom() + " - Tu as choisis de joué : " + coup + "\n");

                coup_possible = coup_possible(coup);

                if (!coup_possible) {
                    System.out.print(joueur_current.consulter_nom() + " - Ton coup n'est pas valide, choisis-en un autre!\n");
                }
            }
            int derniere_semance;
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void actualisation_graine_restante(int nombre_graine_capture){
        this.nombres_graines_restante -= nombre_graine_capture;
    }
    public int graines_restantes(){
        return this.nombres_graines_restante;
    }

    public boolean partie_non_finie(int score_J1, int score_J2){
        if(score_J1 > 32 || score_J2 > 32 || this.graines_restantes() < 8){
            return false;
        }
        else {
            return true;
        }
    }

    public void connaitre_vainqueur(Joueur j1, Joueur j2){
        if(j1.consulter_score() > j2.consulter_score()){
            System.out.printf("Le vainqueur est " + j1.consulter_nom() + " avec un score de %d contre %d", j1.consulter_score(), j2.consulter_score());
        } else if (j1.consulter_score() < j2.consulter_score()) {
            System.out.printf("Le vainqueur est " + j2.consulter_nom() + " avec un score de %d contre %d", j2.consulter_score(), j1.consulter_score());
        } else {
            System.out.printf("Egalité, les joueurs " + j1.consulter_nom() + " et " + j2.consulter_nom() + " finissent la partie avec un score de %d", j1.consulter_score());
        }
    }

    public void afficher(Joueur[] joueurs){
        System.out.print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

        for(int i=0; i<this.taille_plateau/2; i++) {
            if(i >= 10) {
                System.out.print("|    " + (i+1) + "     ");
            }
            else {
                System.out.print("|     " + (i+1) + "     ");
            }
        }
        System.out.print("| Score J"+ joueurs[0].consulter_id() + "  |\n");

        for(int i=0; i<this.taille_plateau/2; i++) {
            if(this.cases[i].graines_bleus >= 10) {
                System.out.print("| " + this.cases[i].graines_bleus + "B ");
            }
            else {
                System.out.print("|  " + this.cases[i].graines_bleus + "B ");
            }
            if(this.cases[i].graines_rouges >= 10) {
                System.out.print(" " + this.cases[i].graines_rouges + "R  ");
            }
            else{
                System.out.print("  " + this.cases[i].graines_rouges + "R  ");
            }
        }
        if(joueurs[0].consulter_score() >= 10){
            System.out.print("|    "+ joueurs[0].consulter_score() + "     |\n");
        }
        else{
            System.out.print("|     "+ joueurs[0].consulter_score() + "     |\n");
        }

        System.out.print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

        for(int i=this.taille_plateau; i>this.taille_plateau/2; i--) {
            if(i >= 10) {
                System.out.print("|    " + (i) + "     ");
            }
            else {
                System.out.print("|     " + (i) + "     ");
            }
        }
        System.out.print("| Score J"+ joueurs[1].consulter_id() + "  |\n");

        for(int i=this.taille_plateau; i>this.taille_plateau/2; i--) {
            if(this.cases[i-1].graines_bleus >= 10) {
                System.out.print("| " + this.cases[i-1].graines_bleus + "B ");
            }
            else {
                System.out.print("|  " + this.cases[i-1].graines_bleus + "B ");
            }
            if(this.cases[i-1].graines_rouges >= 10) {
                System.out.print(" " + this.cases[i-1].graines_rouges + "R  ");
            }
            else {
                System.out.print("  " + this.cases[i - 1].graines_rouges + "R  ");
            }
        }
        if(joueurs[1].consulter_score() >= 10){
            System.out.print("|    "+ joueurs[1].consulter_score() + "     |\n");
        }
        else{
            System.out.print("|     "+ joueurs[1].consulter_score() + "     |\n");
        }

        System.out.print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
    }
}
