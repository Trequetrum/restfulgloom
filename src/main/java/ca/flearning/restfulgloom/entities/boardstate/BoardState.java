package ca.flearning.restfulgloom.entities.boardstate;


import java.util.ArrayList;

/**
 * I want this entity to be exchanged back and forth with the client over REST,
 * but not persisted to the DB.
 */
public class BoardState {

    private int turn = 0;

    private Map map;

    private ArrayList<Character> monsters;
    private ArrayList<Character> players;

    public int getTurn() { return turn; }
    public void setTurn(int turn) { this.turn = turn; }
    public Map getMap() { return map; }
    public void setMap(Map map) { this.map = map; }
    public ArrayList<Character> getMonsters() { return monsters; }
    public void setMonsters(ArrayList<Character> monsters) { this.monsters = monsters; }
    public ArrayList<Character> getPlayers() { return players; }
    public void setPlayers(ArrayList<Character> players) { this.players = players; }
}
