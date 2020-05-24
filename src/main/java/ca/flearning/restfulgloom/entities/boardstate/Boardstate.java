package ca.flearning.restfulgloom.entities.boardstate;


import com.sun.istack.NotNull;

import java.util.ArrayList;

/**
 * I want this entity to be exchanged back and forth with the client over REST,
 * but not persisted to the DB.
 */
public class Boardstate {

    private int turn = 1;

    private Map map = new Map();

    private ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<PlayerCharacter> players = new ArrayList<>();


    public void addPlayer(@NotNull PlayerCharacter playerCharacter) {
        players.add(playerCharacter);
    }
    public void addMontster(@NotNull Monster monster) { monsters.add(monster); }

    /* ~~~ Getters and Setters ~~~ */

    public int getTurn() { return turn; }
    public void setTurn(int turn) { this.turn = turn; }
    public Map getMap() { return map; }
    public void setMap(Map map) { this.map = map; }
    public ArrayList<Monster> getMonsters() { return monsters; }
    public void setMonsters(ArrayList<Monster> monsters) { this.monsters = monsters; }
    public ArrayList<PlayerCharacter> getPlayers() { return players; }
    public void setPlayers(ArrayList<PlayerCharacter> players) { this.players = players; }

    @Override
    public String toString() {
        return "BoardState{" +
                "turn=" + turn +
                ", map=" + map +
                ", monsters=" + monsters +
                ", players=" + players +
                '}';
    }
}
