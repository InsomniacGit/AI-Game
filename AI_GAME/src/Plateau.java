import java.util.*;

public class Plateau implements I_plateau{
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static final int MAXVAL = 1000;
    public Case[] cases;
    public boolean partie_en_cours;
    public int nombres_graines_plateau;
    public int taille_plateau;
    private int nombres_graines_restante;

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
    public void init_plateau(Plateau p){
        for(int i=0; i<this.taille_plateau; i++){
            this.cases[i] = new Case(p.cases[i].nombre_graines_bleues(), p.cases[i].nombre_graines_rouges());
        }
        this.partie_en_cours = p.partie_en_cours;
        this.nombres_graines_restante = p.connaitre_graines_restantes();
    }

    public void capturer(int index_case, Joueur joueur_courant){
        while(this.cases[index_case].nombre_graines() == 3 || this.cases[index_case].nombre_graines() == 2){ //Tant que le nombre total de graine de la case courante vaut 3 ou 2, on ramasse
            joueur_courant.score += this.cases[index_case].nombre_graines(); // On l'ajoute au score du joueur qui ramasse
            actualisation_graines_restantes(this.cases[index_case].nombre_graines()); // On actualise le nombre de graines restantes
            this.cases[index_case].retirer_toutes_graines(); // On vide la case courante
            // On décrémente la case pour regarder la précédente
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

        if(coup.length() == 3){ // Case visée inférieur à 10
            numero_case = Integer.parseInt(coup.substring(0,2));
            couleur = coup.charAt(2);
        }
        else{ // Case supérieur à 9
            numero_case = Integer.parseInt(coup.substring(0,1));
            couleur = coup.charAt(1);
        }

        if(couleur == 'B'){ // Opponent holes
            // Retirer graines bleues de la case cible
            nombre_graines = this.cases[numero_case-1].nombre_graines_bleues();
            this.cases[numero_case-1].retirer_graines(nombre_graines,0);

            for(int i=1; i<nombre_graines*2+1; i=i+2) { // Incrémenter d'une graine les cases adverses
                this.cases[(numero_case-1 + i) % this.taille_plateau].ajouter_graines(1, 0);
            }
            derniere_semance = (numero_case-1 + (nombre_graines-1)*2+1) % this.taille_plateau; // Position de la dernière graine placé

        }
        else if (couleur == 'R'){ // All holes
            // Retirer graines rouges de la case cible
            nombre_graines = this.cases[numero_case-1].nombre_graines_rouges();
            this.cases[numero_case-1].retirer_graines(0, nombre_graines);
            int nombre_tour = 0;

            for(int i=0; i<nombre_graines; i++){ // Incrémenter d'une graine toutes les cases
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

        if(couleur == 'B' && this.cases[numero_case-1].nombre_graines_bleues() != 0){
            return true;
        }
        else return couleur == 'R' && this.cases[numero_case - 1].nombre_graines_rouges() != 0;
    }

    public List<String> liste_coup_possible(Joueur joueur_current){

        List<String> liste_coup_possible = new ArrayList<>();

        for(int k = joueur_current.consulter_id(); k <= taille_plateau; k += 2){
            if(coup_possible(String.valueOf(k)+'B')) liste_coup_possible.add(String.valueOf(k)+'B');
            if(coup_possible(String.valueOf(k)+'R')) liste_coup_possible.add(String.valueOf(k)+'R');
        }

        return liste_coup_possible;
    }

    public boolean est_affame(Joueur joueur_current, Joueur joueur_precedent){

        List<String> liste_coup_possible = liste_coup_possible(joueur_current);

        if (liste_coup_possible.size() == 0){
            joueur_precedent.score += connaitre_graines_restantes();
            actualisation_graines_restantes(connaitre_graines_restantes());
            for(int i=0; i<this.taille_plateau; i++){
                this.cases[i].retirer_toutes_graines();
            }
            return false;
        }
        return true;
    }

    public void jouer(Joueur joueur_current, Joueur joueur_precedent) {
        java.util.Scanner entree = new java.util.Scanner(System.in);
        String coup = "";
        boolean coup_possible = false;

        if (est_affame(joueur_current, joueur_precedent)) {  // Si aucun joueur n'est affamé

            while (!coup_possible) { // Tant qu'on a pas défini un coup possible
                if (joueur_current.consulter_id() == 1) {  // S'il s'agit de J1, il ne peut jouer des cases impaires
                    String regex = "^1[135][BR]$|^[13579][BR]$";

                    System.out.print(joueur_current.consulter_nom() + " - Ecris ton coup! (Par exemple : " + ANSI_BLUE + "'1B' " + ANSI_RESET + "ou " + ANSI_RED +"'15R'" + ANSI_RESET + ") : ");
                    coup = entree.next();

                    while (!coup.matches(regex)) {
                        System.out.print(joueur_current.consulter_nom() + " - Il faut choisir une case impaire (entre 1 et 15) puis une couleur (B ou R) : ");
                        coup = entree.next();
                    }
                } else { // Si J2 paire
                    String regex = "^1[0246][BR]$|^[2468][BR]$";

                    System.out.print(joueur_current.consulter_nom() + " - Ecris ton coup! (Par exemple : " + ANSI_BLUE + "'2B' " + ANSI_RESET + "ou " + ANSI_RED +"'16R'" + ANSI_RESET + ") : ");
                    coup = entree.next();

                    while (!coup.matches(regex)) {
                        System.out.print(joueur_current.consulter_nom() + " - Il faut choisir une case paire (entre 2 et 16) puis une couleur (B ou R) : ");
                        coup = entree.next();
                    }
                }

                System.out.print(joueur_current.consulter_nom() + " - Tu as choisi de jouer : " + coup + "\n");

                coup_possible = coup_possible(coup);

                if (!coup_possible) {
                    System.out.print(joueur_current.consulter_nom() + " - Ton coup n'est pas valide, choisis-en un autre!\n");
                }
            }

            int derniere_semance;
            derniere_semance = semer(coup); // Semer les graines d'une case en fonction du coup et récupérer la position de la dernière case semée
            capturer(derniere_semance, joueur_current); // Capturer si possible
        }
    }

    public void ordinateurNaif(Joueur joueur_current, Joueur joueur_precedent) {
        String coup;
        List<String> liste_coup_possible;
        int derniere_semance;
        int nombre_coup;
        int nombreAleatoire;
        Random rand;

        if (est_affame(joueur_current, joueur_precedent)) {
            liste_coup_possible = liste_coup_possible(joueur_current);
            nombre_coup = liste_coup_possible.size();

            rand = new Random();
            nombreAleatoire = rand.nextInt(nombre_coup);
            coup = liste_coup_possible.get(nombreAleatoire);

            System.out.print("Naif" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");

            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void actualisation_graines_restantes(int nombre_graines_capturees){
        this.nombres_graines_restante -= nombre_graines_capturees;
    }

    public int connaitre_graines_restantes(){
        return this.nombres_graines_restante;
    }

    public boolean etat_enCours(int score_J1, int score_J2){
        return score_J1 <= 32 && score_J2 <= 32 && this.connaitre_graines_restantes() >= 8;
    }

    public void connaitre_vainqueur(Joueur j1, Joueur j2){
        if(j1.consulter_score() > j2.consulter_score()){
            System.out.printf("Le vainqueur est " + j1.consulter_nom() + " avec un score de %d contre %d", j1.consulter_score(), j2.consulter_score());
        } else if (j1.consulter_score() < j2.consulter_score()) {
            System.out.printf("Le vainqueur est " + j2.consulter_nom() + " avec un score de %d contre %d", j2.consulter_score(), j1.consulter_score());
        } else {
            System.out.printf("Egalité, les joueurs " + j1.consulter_nom() + " et " + j2.consulter_nom() + " finissent la partie avec un score de %d", j1.consulter_score());
        }
        System.out.println("\n\n\n\n\n");
    }

    public String minCoup(Map<String, Integer> tab){
        int val = 500;
        String ind = "";

        for(String m : tab.keySet()){
            if(ind == ""){
                ind = m;
            }
            else {
                if (val > tab.get(m)) {
                    val = tab.get(m);
                    ind = m;
                }
            }
        }

        return ind;
    }

    public int minInArray(ArrayList<Integer> tab){
        int val = Integer.MAX_VALUE;

        for(int m : tab){
            if(val > m){
                val = m;
            }
        }
        return val;
    }

    public String maxCoup(Map<String, Integer> tab){
        int val = -500;
        String ind = "";

        for(String m : tab.keySet()){
            if(ind == ""){
                ind = m;
            }
            else {
                if (val < tab.get(m)) {
                    val = tab.get(m);
                    ind = m;
                }
            }
        }
        return ind;
    }

    public int maxInArray(ArrayList<Integer> tab){
        int val = Integer.MIN_VALUE;

        for(int m : tab){
            if(val < m){
                val = m;
            }
        }
        return val;
    }

    public int evaluation(Plateau e, Joueur[] J){ // h method

        return (J[0].consulter_score() - J[1].consulter_score());
    }

    public int MinMaxValue(Plateau e, Joueur[] J, Joueur Jcurrent, boolean isMax, int pmax){
        int[] scoreState = {J[0].consulter_score(), J[1].consulter_score() };

        Joueur Jopponent;
        if (J[0].consulter_id() == Jcurrent.consulter_id()){
            Jopponent = J[1];
        } else {
            Jopponent = J[0];
        }

        if(!e.etat_enCours(J[0].consulter_score(), J[1].consulter_score())){
            if (J[0].consulter_score() > J[1].consulter_score()) return MAXVAL+pmax;
            if (J[0].consulter_score() < J[1].consulter_score()) return -MAXVAL-pmax;
            if (J[0].consulter_score() == J[1].consulter_score()) return 0;
        }

        if ((pmax == 0) && (e.etat_enCours(J[0].consulter_score(), J[1].consulter_score()))) return evaluation(e, J);

        ArrayList<Integer> vals = new ArrayList<>();

        for(String m : e.liste_coup_possible(Jcurrent)){

            Plateau p = new Plateau(this.taille_plateau);
            p.init_plateau(e); // copie dure du plateau e
            p.capturer(p.semer(m), Jcurrent); // Apply(m, e)
            vals.add(MinMaxValue(p, J, Jopponent, !(isMax), pmax-1));


            // Rétablir le score des joueurs dans ce contexte
            J[0].score = scoreState[0];
            J[1].score = scoreState[1];
            if(Jcurrent.consulter_id() == J[0].consulter_id()){
                Jcurrent.score = J[0].consulter_score();
                Jopponent.score = J[1].consulter_score();
            }
            else {
                Jcurrent.score = J[1].consulter_score();
                Jopponent.score = J[0].consulter_score();
            }
        }

        if(isMax){
            return maxInArray(vals);
        } else {
            return minInArray(vals);
        }
    }

    public int AlphaBetaValue(Plateau e, Joueur[] J, Joueur Jcurrent, int alpha, int beta, boolean isMax, int pmax){
        int[] scoreState = {J[0].consulter_score(), J[1].consulter_score() };

        Joueur Jopponent;
        if (J[0].consulter_id() == Jcurrent.consulter_id()){
            Jopponent = J[1];
        } else {
            Jopponent = J[0];
        }

        if(!e.etat_enCours(J[0].consulter_score(), J[1].consulter_score())){
            if (J[0].consulter_score() > J[1].consulter_score()) return MAXVAL+pmax;
            if (J[0].consulter_score() < J[1].consulter_score()) return -MAXVAL-pmax;
            if (J[0].consulter_score() == J[1].consulter_score()) return 0;
        }

        if ((pmax == 0) && (e.etat_enCours(J[0].consulter_score(), J[1].consulter_score()))) return evaluation(e, J);

        if(isMax){
            for(String m : e.liste_coup_possible(Jcurrent)){

                Plateau p = new Plateau(this.taille_plateau);
                p.init_plateau(e); // copie dure du plateau e
                p.capturer(p.semer(m), Jcurrent); // Apply(m, e)

                alpha = Math.max(alpha, AlphaBetaValue(p, J, Jopponent, alpha, beta, !(isMax), pmax-1));
                if(alpha >= beta) return alpha;

                // Rétablir le score des joueurs dans ce contexte
                J[0].score = scoreState[0];
                J[1].score = scoreState[1];
                if(Jcurrent.consulter_id() == J[0].consulter_id()){
                    Jcurrent.score = J[0].consulter_score();
                    Jopponent.score = J[1].consulter_score();
                }
                else {
                    Jcurrent.score = J[1].consulter_score();
                    Jopponent.score = J[0].consulter_score();
                }
            }
            return alpha;
        } else {
            for(String m : e.liste_coup_possible(Jcurrent)){

                Plateau p = new Plateau(this.taille_plateau);
                p.init_plateau(e); // copie dure du plateau e
                p.capturer(p.semer(m), Jcurrent); // Apply(m, e)

                beta = Math.min(beta, AlphaBetaValue(p, J, Jopponent, alpha, beta, !(isMax), pmax-1));
                if(beta <= alpha) return beta;

                // Rétablir le score des joueurs dans ce contexte
                J[0].score = scoreState[0];
                J[1].score = scoreState[1];
                if(Jcurrent.consulter_id() == J[0].consulter_id()){
                    Jcurrent.score = J[0].consulter_score();
                    Jopponent.score = J[1].consulter_score();
                }
                else {
                    Jcurrent.score = J[1].consulter_score();
                    Jopponent.score = J[0].consulter_score();
                }
            }
            return beta;
        }
    }

    public String DecisionMinMax (Plateau e, Joueur[] J, Joueur Jcurrent, int pmax) {
        Map<String, Integer> value = new Hashtable<>();
        int[] scoreState = {J[0].consulter_score(), J[1].consulter_score() };

        Joueur Jopponent;
        boolean isMax;

        if (J[0].consulter_id() != Jcurrent.consulter_id()){
            // Jcurrent = J[1]
            Jopponent = J[0];
            isMax = false;
        } else {
            // Jcurrent = J[0]
            Jopponent = J[1];
            isMax = true;
        }

        // Decide the best move of J in position e
        System.out.println("nb := " + e.liste_coup_possible(Jcurrent).size() + " \t cp := " + e.liste_coup_possible(Jcurrent));
        if(e.liste_coup_possible(Jcurrent).size() == 1){
            return e.liste_coup_possible(Jcurrent).get(0);
        }
        for(String m : e.liste_coup_possible(Jcurrent)){

            Plateau p = new Plateau(this.taille_plateau);
            p.init_plateau(e); // copie dure du plateau e
            p.capturer(p.semer(m), Jcurrent); // Apply(m, e)
            value.put(m, MinMaxValue(p, J, Jopponent, !(isMax), pmax));
            // System.out.println(m + " " + value); // Affiche les coups possible et l'évaluation lié à ces coups

            // Rétablir le score des joueurs dans ce contexte
            J[0].score = scoreState[0];
            J[1].score = scoreState[1];
            if(Jcurrent.consulter_id() == J[0].consulter_id()){
                Jcurrent.score = J[0].consulter_score();
                Jopponent.score = J[1].consulter_score();
            }
            else {
                Jcurrent.score = J[1].consulter_score();
                Jopponent.score = J[0].consulter_score();
            }
        }

        if(Jcurrent.consulter_id() == 1){
            return maxCoup(value);
        } else {
            return minCoup(value);
        }
    }

    public String DecisionAlphaBeta (Plateau e, Joueur[] J, Joueur Jcurrent, int pmax) {
        int[] scoreState = {J[0].consulter_score(), J[1].consulter_score() };

        Joueur Jopponent;
        boolean isMax;

        if (J[0].consulter_id() != Jcurrent.consulter_id()){
            Jopponent = J[0];
            isMax = false;
        } else {
            Jopponent = J[1];
            isMax = true;
        }

        String coup = e.liste_coup_possible(Jcurrent).get(0);
        int val;
        int alpha = -MAXVAL;
        int beta = MAXVAL;

        // Decide the best move of J in position e
        System.out.println("nb := " + e.liste_coup_possible(Jcurrent).size() + " \t cp := " + e.liste_coup_possible(Jcurrent));
        if(e.liste_coup_possible(Jcurrent).size() == 1){
            return e.liste_coup_possible(Jcurrent).get(0);
        }
        for(String m : e.liste_coup_possible(Jcurrent)){

            Plateau p = new Plateau(this.taille_plateau);
            p.init_plateau(e); // copie dure du plateau e
            p.capturer(p.semer(m), Jcurrent); // Apply(m, e)
            val = AlphaBetaValue(p, J, Jopponent, alpha, beta, !(isMax), pmax);

            // System.out.println(m + " " + value); // Affiche les coups possible et l'évaluation lié à ces coups

            // Rétablir le score des joueurs dans ce contexte
            J[0].score = scoreState[0];
            J[1].score = scoreState[1];

            if(Jcurrent.consulter_id() == J[0].consulter_id()){
                Jcurrent.score = J[0].consulter_score();
                Jopponent.score = J[1].consulter_score();

                if(val > alpha){
                    coup = m;
                    alpha = val;
                }
            }
            else {
                Jcurrent.score = J[1].consulter_score();
                Jopponent.score = J[0].consulter_score();

                if(val < beta){
                    coup = m;
                    beta = val;
                }
            }
        }

        return coup;
    }

    public void ordinateurMinMax0(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionMinMax(this, J, joueur_current, 0);
            System.out.print("MinMax" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurMinMax1(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionMinMax(this, J, joueur_current, 1);
            System.out.print("MinMax" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurMinMax2(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionMinMax(this, J, joueur_current, 2);
            System.out.print("MinMax" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurMinMax3(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionMinMax(this, J, joueur_current, 3);
            System.out.print("MinMax" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurMinMax4(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionMinMax(this, J, joueur_current, 4);
            System.out.print("MinMax" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurMinMax5(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionMinMax(this, J, joueur_current, 5);
            System.out.print("MinMax" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurMinMax6(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionMinMax(this, J, joueur_current, 6);
            System.out.print("MinMax" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurMinMax7(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionMinMax(this, J, joueur_current, 7);
            System.out.print("MinMax" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurMinMax8(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionMinMax(this, J, joueur_current, 8);
            System.out.print("MinMax" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurMinMax9(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionMinMax(this, J, joueur_current, 9);
            System.out.print("MinMax" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurMinMax10(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionMinMax(this, J, joueur_current, 10);
            System.out.print("MinMax" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurMinMaxAdaptatif(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {

        if(this.liste_coup_possible(joueur_current).size() <= 2){
            this.ordinateurMinMax9(joueur_current, J, joueur_precedent);
        }
        else if(this.liste_coup_possible(joueur_current).size() <= 4){
            this.ordinateurMinMax8(joueur_current, J, joueur_precedent);
        }
        else if(this.liste_coup_possible(joueur_current).size() <= 7){
            this.ordinateurMinMax7(joueur_current, J, joueur_precedent);
        }
        else if(this.liste_coup_possible(joueur_current).size() <= 11){
            this.ordinateurMinMax6(joueur_current, J, joueur_precedent);
        }
        else if(this.liste_coup_possible(joueur_current).size() <= 16){
            this.ordinateurMinMax5(joueur_current, J, joueur_precedent);
        }
    }


    public void ordinateurAlphaBeta5(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionAlphaBeta(this, J, joueur_current, 7);
            System.out.print("AlphaBeta" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurAlphaBeta6(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionAlphaBeta(this, J, joueur_current, 8);
            System.out.print("AlphaBeta" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurAlphaBeta7(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionAlphaBeta(this, J, joueur_current, 9);
            System.out.print("AlphaBeta" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurAlphaBeta8(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionAlphaBeta(this, J, joueur_current, 10);
            System.out.print("AlphaBeta" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurAlphaBeta9(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {
        String coup;
        int derniere_semance;

        if (est_affame(joueur_current, joueur_precedent)) {
            coup = DecisionAlphaBeta(this, J, joueur_current, 11);
            System.out.print("AlphaBeta" + joueur_current.consulter_id() + " - Joue le coup : " + coup + "\n");
            derniere_semance = semer(coup);
            capturer(derniere_semance, joueur_current);
        }
    }

    public void ordinateurAlphaBetaAdaptatif(Joueur joueur_current, Joueur[] J, Joueur joueur_precedent) {

        if(this.liste_coup_possible(joueur_current).size() <= 2){
            this.ordinateurAlphaBeta9(joueur_current, J, joueur_precedent);
        }
        else if(this.liste_coup_possible(joueur_current).size() <= 4){
            this.ordinateurAlphaBeta8(joueur_current, J, joueur_precedent);
        }
        else if(this.liste_coup_possible(joueur_current).size() <= 7){
            this.ordinateurAlphaBeta7(joueur_current, J, joueur_precedent);
        }
        else if(this.liste_coup_possible(joueur_current).size() <= 11){
            this.ordinateurAlphaBeta6(joueur_current, J, joueur_precedent);
        }
        else if(this.liste_coup_possible(joueur_current).size() <= 16){
            this.ordinateurAlphaBeta5(joueur_current, J, joueur_precedent);
        }
    }



    public void afficher(Joueur[] joueurs){
        System.out.print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

        for(int i=0; i<this.taille_plateau/2; i++) {
            if(i >= 10) {
                System.out.print("|    " + (i+1) + "     ");
            }
            else {
                System.out.print("|     " + (i+1) + "     ");
            }
        }
        System.out.print("||  Score J"+ joueurs[0].consulter_id() + "  |\n");

        for(int i=0; i<this.taille_plateau/2; i++) {
            if(this.cases[i].graines_bleus >= 10) {
                System.out.print("| " + ANSI_BLUE + this.cases[i].graines_bleus + "B" + ANSI_RESET + " ");
            }
            else {
                System.out.print("|  " + ANSI_BLUE + this.cases[i].graines_bleus + "B" + ANSI_RESET + " ");
            }
            if(this.cases[i].graines_rouges >= 10) {
                System.out.print(" " + ANSI_RED + this.cases[i].graines_rouges + "R" + ANSI_RESET + " ");
            }
            else{
                System.out.print("  " + ANSI_RED + this.cases[i].graines_rouges + "R" + ANSI_RESET + "  ");
            }
        }
        if(joueurs[0].consulter_score() >= 10){
            System.out.print("||     "+ ANSI_PURPLE + joueurs[0].consulter_score() + ANSI_RESET + "     |\n");
        }
        else{
            System.out.print("||      "+ ANSI_PURPLE + joueurs[0].consulter_score() + ANSI_RESET + "     |\n");
        }

        System.out.print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");

        for(int i=this.taille_plateau; i>this.taille_plateau/2; i--) {
            if(i >= 10) {
                System.out.print("|    " + (i) + "     ");
            }
            else {
                System.out.print("|     " + (i) + "     ");
            }
        }
        System.out.print("||  Score J"+ joueurs[1].consulter_id() + "  |\n");

        for(int i=this.taille_plateau; i>this.taille_plateau/2; i--) {
            if(this.cases[i-1].graines_bleus >= 10) {
                System.out.print("| " + ANSI_BLUE + this.cases[i-1].graines_bleus + "B" + ANSI_RESET + " ");
            }
            else {
                System.out.print("|  " + ANSI_BLUE + this.cases[i-1].graines_bleus + "B" + ANSI_RESET + " ");
            }
            if(this.cases[i-1].graines_rouges >= 10) {
                System.out.print(" " + ANSI_RED + this.cases[i-1].graines_rouges + "R" + ANSI_RESET + " ");
            }
            else {
                System.out.print("  " + ANSI_RED + this.cases[i-1].graines_rouges + "R" + ANSI_RESET + "  ");
            }
        }
        if(joueurs[1].consulter_score() >= 10){
            System.out.print("||     "+ ANSI_PURPLE + joueurs[1].consulter_score() + ANSI_RESET + "     |\n");
        }
        else{
            System.out.print("||      "+ ANSI_PURPLE + joueurs[1].consulter_score() + ANSI_RESET + "     |\n");
        }

        System.out.print("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
    }
}
