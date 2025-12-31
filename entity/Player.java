package entity;

public class Player {

    private int idPlayer;
    private String name; // nome del giocatore/username del player
    private int score = 0; // punteggio del giocatore

    public Player() {
    }

    public Player(int idPlayer) {
        setIdPlayer(idPlayer);
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // Nuovo metodo per resettare il punteggio
    public void resetScore() {
        this.score = 0;
    }

    public String toString() {
        return "Giocatore" + idPlayer + ":" + " Nome='" + name + '\'' + ", Punteggio Personale=" + score;
    }

}
