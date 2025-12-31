package app;

import app.LogicGame;
import entity.Team;
import entity.Player;
import java.util.Scanner;
import java.io.IOException;
import java.util.Random;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        Team team1 = new Team(); // team 1
        Team team2 = new Team(); // team 2

        // creazione giocatori
        Player player1 = new Player(1);
        Player player2 = new Player(2);
        Player player3 = new Player(3);
        Player player4 = new Player(4);

        team1.setIdTeam(1);
        team2.setIdTeam(2);

        // Sceglie casualmente se assegnare X/O (0 = X/O, 1 = O/X)
        if (random.nextInt(2) == 0) { // se uguale a 0
            team1.setSymbol('X');
            team2.setSymbol('O');
        } else { // se uguale a 1
            team1.setSymbol('O');
            team2.setSymbol('X');
        }

        String namesTeam[] = { "Implacabili", "Gladiatori", "Campioni", "Leggende", "Titani", "Eroi", "Predatori",
                "Distruttori", "Cannibali", "Assassini", "Conquistatori", "Vincitori", "Sayan", "Gino" }; // array con i
                                                                                                          // nomi
        // dei team.

        int choice = 0;

        while (choice != 2) {
            int randomIndex1 = random.nextInt(namesTeam.length); // indice random da 0 a 4 per il per i nomi del team
                                                                 // scelto
            // casualmente.

            int randomIndex2 = random.nextInt(namesTeam.length);

            // Reset dei punteggi dei giocatori prima di ogni nuova partita
            player1.resetScore();
            player2.resetScore();
            player3.resetScore();
            player4.resetScore();

            // Reset del punteggio dei team prima di ogni nuova partita
            team1.resetTeamScore();
            team2.resetTeamScore();

            GraphicInterface();

            System.out.println("\n\nBENVENUTO IN TRIS TAG TEAM GAME!");
            System.out.println("1)Gioca");
            System.out.println("2)Esci");
            System.out.print("-> ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consumare il newline
            LogicGame.clearScreen();
            if (choice == 1) {

                System.out.println("I nomi dei due team scelti sono i seguenti:");
                boolean flag = false;
                team1.setNameTeam(namesTeam[randomIndex1]);
                do {
                    team2.setNameTeam(namesTeam[randomIndex2]);
                    if (team1.getNameTeam().equals(team2.getNameTeam())) {
                        flag = true;
                        randomIndex2 = random.nextInt(namesTeam.length); // rigenera l'indice del secondo team se i nomi
                                                                         // sono
                                                                         // uguali.
                    }

                } while (flag == true); // se i nomi sono uguali, rigenera il nome del secondo team.

                System.out.println("Team 1: " + team1.getNameTeam());
                System.out.println("Team 2: " + team2.getNameTeam());
                System.out.println("ORA INSERISCI I NOMI DEI GIOCATORI DEI DUE TEAM:");

                System.out.println("Player 1: ");
                String namePlayer1 = scanner.nextLine();
                player1.setName(namePlayer1);
                System.out.println("Player 2: ");
                String namePlayer2 = scanner.nextLine();
                player2.setName(namePlayer2);
                System.out.println("Player 3: ");
                String namePlayer3 = scanner.nextLine();
                player3.setName(namePlayer3);
                System.out.println("Player 4: ");
                String namePlayer4 = scanner.nextLine();
                player4.setName(namePlayer4);

                // ASSEGNAMENTO GIOCATORI AI TEAM IN MANIERA CASUALE:
                // Aggiungi queste righe per svuotare le liste prima di assegnare nuovi
                // giocatori
                team1.getPlayers().clear();
                team2.getPlayers().clear();

                int number = random.nextInt(10) + 1; // numero casuale da 1 a 10 per l'assegnamento dei giocatori ai
                                                     // team.
                switch (number) {
                    case 1:
                        team1.setPlayers(player1);
                        team1.setPlayers(player2);
                        team2.setPlayers(player3);
                        team2.setPlayers(player4);
                        break;
                    case 2:
                        team1.setPlayers(player1);
                        team1.setPlayers(player3);
                        team2.setPlayers(player2);
                        team2.setPlayers(player4);
                        break;
                    case 3:
                        team1.setPlayers(player1);
                        team1.setPlayers(player4);
                        team2.setPlayers(player2);
                        team2.setPlayers(player3);
                        break;
                    case 4:
                        team1.setPlayers(player2);
                        team1.setPlayers(player3);
                        team2.setPlayers(player1);
                        team2.setPlayers(player4);
                        break;
                    case 5:
                        team1.setPlayers(player2);
                        team1.setPlayers(player4);
                        team2.setPlayers(player1);
                        team2.setPlayers(player3);
                        break;
                    case 6:
                        team1.setPlayers(player3);
                        team1.setPlayers(player4);
                        team2.setPlayers(player1);
                        team2.setPlayers(player2);
                        break;

                    case 7:
                        team1.setPlayers(player3);
                        team1.setPlayers(player1);
                        team2.setPlayers(player2);
                        team2.setPlayers(player4);
                        break;
                    case 8:
                        team1.setPlayers(player4);
                        team1.setPlayers(player1);
                        team2.setPlayers(player2);
                        team2.setPlayers(player3);
                        break;
                    case 9:
                        team1.setPlayers(player4);
                        team1.setPlayers(player2);
                        team2.setPlayers(player1);
                        team2.setPlayers(player3);
                        break;
                    case 10:
                        team1.setPlayers(player4);
                        team1.setPlayers(player3);
                        team2.setPlayers(player1);
                        team2.setPlayers(player2);
                        break;
                }

                LogicGame.setupGame(team1, team2);

                LogicGame.clearScreen();

                System.out.println("\n\nI TEAM SONO I SEGUENTI:");
                System.out.println(team1.toString());
                System.out.println(team2.toString());
                System.out.println("\nPremi INVIO per Iniziare la Partita...");
                scanner.nextLine(); // attendi che l'utente prema INVIO
                LogicGame.clearScreen();
                LogicGame.startGame(false); // avvia la logica di gioco

            } else if (choice == 2) {
                break;
            } else {
                continue;
            }

        }

        System.out.println("Grazie per aver giocato a TRIS TAG TEAM GAME! Arrivederci!");
        System.out.println("\nCreated by Antonio Izzo");
        System.exit(0);

        // TODO: CONTINUARE A TESTARE IL GIOCO E SISTEMARE I BUG

    }

    public static void GraphicInterface() {
        // Codice per Reset (Riporta il colore al default del terminale)
        String RESET = "\u001B[0m";

        // Codici Colore del TESTO (Foreground)
        String ROSSO = "\u001B[31m";
        String VERDE = "\u001B[32m";
        String GIALLO = "\u001B[33m";
        String BLU = "\u001B[34m";

        // Stile Aggiuntivo
        String GRASSETTO = "\u001B[1m";

        String separatore = VERDE + GRASSETTO + " | " + RESET; // Separatore Verde/Grassetto

        System.out.println(
                GIALLO + "--------------------------------------------------------------------------------" + RESET);
        System.out.println(BLU + "                         TRIS" + separatore + "TAG" + separatore + "TEAM" + RESET);
        System.out.println(
                GIALLO + "--------------------------------------------------------------------------------" + RESET);

        // --- Righe di testo affiancate ---

        // Per ogni riga, usiamo un colore diverso per ogni parola:
        // TRIS (ROSSO) | TAG (VERDE) | TEAM (BLU)

        // Riga 1:
        System.out.println(
                ROSSO + "TTTTT RRRR  IIIII SSSSS " + RESET + separatore +
                        VERDE + "TTTTT  AAA   GGGG " + RESET + separatore +
                        BLU + "TTTTT EEEEE  AAA  M   M" + RESET);

        // Riga 2:
        System.out.println(
                ROSSO + "  T   R   R   I   S     " + RESET + separatore +
                        VERDE + "  T   A   A G     " + RESET + separatore +
                        BLU + "  T   E     A   A MM MM" + RESET);

        // Riga 3:
        System.out.println(
                ROSSO + "  T   RRRR    I    SSSSS" + RESET + separatore +
                        VERDE + "  T   AAAAA G GGG " + RESET + separatore +
                        BLU + "  T   EEE   AAAAA M M M" + RESET);

        // Riga 4:
        System.out.println(
                ROSSO + "  T   R R     I        S" + RESET + separatore +
                        VERDE + "  T   A   A G   G " + RESET + separatore +
                        BLU + "  T   E     A   A M   M" + RESET);

        // Riga 5:
        System.out.println(
                ROSSO + "  T   R  RR IIIII SSSSS " + RESET + separatore +
                        VERDE + "  T   A   A  GGGG " + RESET + separatore +
                        BLU + "  T   EEEEE A   A M   M" + RESET);

        System.out.println(
                GIALLO + "--------------------------------------------------------------------------------" + RESET);

    }
}