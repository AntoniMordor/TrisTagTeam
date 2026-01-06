package entity;

import entity.Player;
import java.util.List;
import java.util.ArrayList;

public class Team {

     private int idTeam;
     private String nameTeam;
     private List<Player> players = new ArrayList<>();
     private int teamScore = 0; // punteggio del team totale sommando i punteggi dei giocatori
     private char symbol; // simbolo del team (X o O)

     public Team() {
     }

     public Team(String name) {
          setNameTeam(name);
     }

     public int getIdTeam() {
          return idTeam;
     }

     public void setIdTeam(int idTeam) {
          this.idTeam = idTeam;
     }

     public String getNameTeam() {
          return nameTeam;
     }

     public void setNameTeam(String nameTeam) {
          this.nameTeam = nameTeam;
     }

     public List<Player> getPlayers() { // ritorna la lista dei giocatori del team
          return players;
     }

     public void setPlayers(Player player) { // aggiunge un giocatore alla lista dei giocatori del team
          this.players.add(player);
     }

     public int getTeamScore() {
          return teamScore;
     }

     public void setTeamScore(int bonusMalusPunteggio) {

          int totalScore = 0;
          for (Player p : players) {
               totalScore += p.getScore();
          }
          this.teamScore = totalScore + bonusMalusPunteggio;
     }

     public void resetTeamScore() {
          this.teamScore = 0;
     }

     public char getSymbol() {
          return symbol;
     }

     public void setSymbol(char symbol) {
          this.symbol = symbol;
     }

     public String toString() {
          return "########################\nTeam" + idTeam + ":" + "\nNome Team:" + "'' " + nameTeam + " ''" +
                    "\nSimbolo Team: " + symbol +
                    "\nPunteggio Team: " + teamScore +
                    "\nGiocatori del Team: " + players.toString();
     }

}
