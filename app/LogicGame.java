package app;

import entity.Player;
import entity.Team;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class LogicGame {

    public static List<Team> teams = new ArrayList<>();
    public static List<Player> players1 = new ArrayList<>(); // lista dei giocatori del team 1
    public static List<Player> players2 = new ArrayList<>(); // lista dei giocatori del team 2
    public static char winnerSymbol; // simbolo del team vincitore

    // metodo per settare personaggi e team nella logica di gioco
    public static void setupGame(Team team1, Team team2) {
        // Svuota le liste statiche prima di aggiungere nuovi giocatori
        players1.clear();
        players2.clear();

        teams.clear(); // Anche teams potrebbe accumulare, meglio svuotarlo
        teams.add(team1);
        teams.add(team2);

        players1.addAll(team1.getPlayers());
        players2.addAll(team2.getPlayers());
    }

    public static char board[][] = new char[3][3]; // griglia di gioco 3x3

    public static void resetBoard() { // inizializza la griglia di gioco o la resetta
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public static void printBoard() {
        for (int i = 0; i < 3; i++) { // riga
            System.out.println();
            if (i > 0) {
                System.out.println("----+-----");
            }
            for (int j = 0; j < 3; j++) { // colonna
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
        }
    }

    // metodo per vedere se il range è valido e se la posizione è occupata
    public static boolean isValidPosition(int row, int col) {
        // Controlla se le coordinate sono valide (tra 0 e 2)
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        return true;
    }

    // metodo per vedere se una posizione è già occupata
    public static boolean isPositionOccupied(int row, int col) {

        // Controlla prima se le coordinate sono valide (tra 0 e 2)
        if (!isValidPosition(row, col)) {
            return true; // Consideriamo "occupata" o non valida una posizione fuori range
        }

        return board[row][col] != '-';
    }

    public static void updateBoard(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') { // la board non è piena
                    return false;
                }
            }
        }
        return true; // la board è piena
    }

    public static void insertInBoard(char symbol) {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        System.out.println("\n\n\nInserisci la riga (0, 1, 2): ");
        row = scanner.nextInt();
        System.out.println("Inserisci la colonna (0, 1, 2): ");
        col = scanner.nextInt();
        // controlla se la posizione è valida
        while (isPositionOccupied(row, col)) {

            System.out.println("\nPosizione non valida o già occupata. Riprova.\n");
            System.out.println("Inserisci la riga (0, 1, 2): ");
            row = scanner.nextInt();
            System.out.println("Inserisci la colonna (0, 1, 2): ");
            col = scanner.nextInt();
        }

        // aggiorna la board
        updateBoard(row, col, symbol);

    }

    public static void startGame(boolean esci) {

        Random random = new Random();

        int counter = random.nextInt(10) + 1;
        String currentPlayer = new String();

        while (!esci) { // ciclo per più partite finché l'utente non sceglie di uscire quindi usciamo
                        // dal ciclo con esci = true
            resetBoard();

            // Escere dal ciclo quando la board è piena
            while (!isBoardFull()) {
                printBoard();
                // if casuale su quale turno team

                if (counter % 2 == 0) {
                    currentPlayer = players1.get(random.nextInt(players1.size())).getName(); // cosi sappiamo il nome
                                                                                             // del
                                                                                             // giocatore che ha fatto
                                                                                             // la
                                                                                             // mossa vincente
                    System.out
                            .print("\n\nTurno TEAM " + teams.get(0).getNameTeam() + " ' " + teams.get(0).getSymbol()
                                    + " ' ");

                    System.out.print(" Giocatore: " + currentPlayer + "\n");

                    insertInBoard(teams.get(0).getSymbol());

                } else {
                    currentPlayer = players2.get(random.nextInt(players2.size())).getName(); // cosi sappiamo il nome
                                                                                             // del
                                                                                             // giocatore che ha fatto
                                                                                             // la
                                                                                             // mossa vincente
                    System.out
                            .print("\n\nTurno TEAM " + teams.get(1).getNameTeam() + " ' " + teams.get(1).getSymbol()
                                    + " ' ");
                    System.out.print(" Giocatore: " + currentPlayer + "\n");
                    insertInBoard(teams.get(1).getSymbol());
                }

                clearScreen();

                // Controlla se qualcuno ha vinto
                if (checkWin()) {

                    break; // esce dal ciclo se qualcuno ha vinto
                }
                counter++;

            }

            if (checkWin()) {

                if (currentPlayer != null) {
                    // Aggiorna il punteggio del giocatore che ha fatto la mossa vincente
                    for (Team team : teams) {
                        for (Player player : team.getPlayers()) {
                            if (player.getName().equals(currentPlayer)) {
                                player.setScore(player.getScore() + 1); // Aggiunge 1 punto al giocatore
                            }
                        }
                    }
                    // Aggiorna il punteggio del team vincitore
                    for (Team team : teams) {
                        if (team.getSymbol() == winnerSymbol) {
                            bonusMalusPunteggio(); // Ricalcola il punteggio del team
                        }
                    }
                }
                if (teams.get(0).getSymbol() == winnerSymbol) {
                    System.out.println(
                            "\n\nComplimenti! Ha vinto il TEAM " + teams.get(0).getNameTeam() + " con simbolo '"
                                    + teams.get(0).getSymbol() + "' !" + " Grazie al giocatore: " + currentPlayer);

                } else {
                    System.out.println(
                            "\n\nComplimenti! Ha vinto il TEAM " + teams.get(1).getNameTeam() + " con simbolo '"
                                    + teams.get(1).getSymbol() + "' !" + " Grazie al giocatore: " + currentPlayer);
                }
            } else {
                System.out.println("\n\nLa partita è finita in pareggio!");
            }

            System.out.println("\nStato Punteggi dopo la partita:\n");
            for (Team team : teams) {
                System.out.println(team.getNameTeam() + " - Punteggio Team: " + team.getTeamScore());
                for (Player player : team.getPlayers()) {
                    System.out.println("   " + player.toString());
                }
            }

            System.out.println("\nVuoi giocare un'altra partita? (s/n): ");
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine().trim().toLowerCase();
            if (response.equals("n")) {
                esci = true; // esce dal ciclo principale
            }
            clearScreen();

            // Mostra la classifica finale se si esce e c'è un vincitore
            if (esci == true && checkWin() == true) {
                // TODO: DA AGGIUNGERE LA CLASSIFICA FINALE DEI PERSONAGGI E DEL TEAM VINCENTE
                // DELLA PARTITA UNA VOLTA CHE SI ESCE DALLA PARTITA.

                int punteggioMassimoTeam = 0;
                int punteggioMassimoGiocatore = 0;
                String nomeGiocatoreVincente = "";
                String nomeTeamVincente = "";

                System.out.println("Classifica Finale:\n");
                for (Team team : teams) {

                    // Controlla il team con il punteggio più alto e di conseguenza vincente
                    if (team.getTeamScore() > punteggioMassimoTeam) {
                        punteggioMassimoTeam = team.getTeamScore();
                        nomeTeamVincente = team.getNameTeam();
                    }

                    System.out.println("Team: " + team.getNameTeam() + " - Punteggio Team: " + team.getTeamScore());

                    // Controlla il giocatore con il punteggio più alto all'interno del team
                    for (Player player : team.getPlayers()) {

                        if (player.getScore() > punteggioMassimoGiocatore) {
                            punteggioMassimoGiocatore = player.getScore();
                            nomeGiocatoreVincente = player.getName();
                        }
                        System.out.println("   " + player.toString());

                    }
                }

                System.out.println("\nComplimenti al TEAM VINCITORE: " + nomeTeamVincente + " con punteggio: "
                        + punteggioMassimoTeam);
                System.out.println("Complimenti al GIOCATORE VINCITORE: " + nomeGiocatoreVincente + " con punteggio: "
                        + punteggioMassimoGiocatore);

                System.out.println("\n premi un tasto per continuare...");
                scanner.nextLine();

            } else if (esci == true && checkWin() == false) { // in caso di pareggio finale
                System.out.println("Pareggio! Nessun vincitore questa volta.");
                System.out.println("\n premi un tasto per continuare...");
                scanner.nextLine();
            }

        }

        clearScreen();

        // TODO: CONTINUARE A TESTARE IL GIOCO E SISTEMARE I BUG

        // TODO: AGGIUNGERE BONUS E MALUS PER I GIOCATORI E TEAM

    }

    public static boolean checkWin() {
        // 1. Controlla righe
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                winnerSymbol = board[i][0];
                return true;
            }
        }

        // 2. Controlla colonne
        for (int i = 0; i < 3; i++) {
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                winnerSymbol = board[0][i];
                return true;
            }
        }

        // 3. Controlla diagonali
        if (board[1][1] != '-') { // Se il centro è vuoto, nessuna diagonale può essere vincente
            if ((board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
                    (board[0][2] == board[1][1] && board[1][1] == board[2][0])) {
                winnerSymbol = board[1][1];
                return true;
            }
        }

        return false;
    }

    // Metodo per applicare bonus e malus ai punteggi dei team in base al nome del
    // team
    public static void bonusMalusPunteggio() {

        for (Team team : teams) {

            switch (team.getNameTeam().toLowerCase()) {

                // CASISTICA BONUS E MALUS:
                case "sayan":
                    team.setTeamScore(team.getTeamScore() * 2); // Aggiungi il doppio del punteggio come bonus
                    break;

                case "gino":
                    team.setTeamScore(team.getTeamScore() - 2); // Sottrai 2 punti come malus
                    break;
                default:
                    // CASISTICA NESSUN CAMBIAMENTO:
                    team.setTeamScore(0); // Nessun cambiamento
                    break;
            }

        }
    }

    public static void clearScreen() { // metodo per pulire la console sia su Windows che su Linux/macOS
        try {
            // Rileva il sistema operativo
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("windows")) {
                // Per Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Per Linux / macOS
                // Prova a usare il comando 'clear'
                if (System.console() != null) {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                } else {
                    // Se non c'è una console reale (IDE, Eclipse, IntelliJ, ecc.)
                    // usa i codici ANSI
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                }
            }

        } catch (IOException | InterruptedException e) {
            // In caso di errore, fallback ANSI
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }
    }

}
