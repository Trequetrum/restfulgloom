package ca.flearning.restfulgloom.entities.boardstate;


import com.sun.istack.NotNull;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * I want this entity to be exchanged back and forth with the client over REST,
 * but not persisted to the DB.
 */
public class Boardstate {

    private int turn = 1;

    private Map map = new Map();

    private double initiativeProcessed;  // to track how far through the initiative order we are.
    private ArrayList<Monster> monsters = new ArrayList<>();
    private ArrayList<PlayerCharacter> players = new ArrayList<>();
    private ArrayList<Coin> coins = new ArrayList<>();
    private ArrayList<TreasureChest> treasureChests = new ArrayList<>();
    private ArrayList<Obstacle> obstacles = new ArrayList<>();
    private ArrayList<Trap> traps = new ArrayList<>();
    private Elements elements = new Elements();


    /**
     *
     * @param playerCharacter
     * @throws Exception if the chactare's id is not unique in the array being inserted into.
     */
    public void addPlayer(@NotNull PlayerCharacter playerCharacter) {
        checkIdIsUnique(playerCharacter, players);
        players.add(playerCharacter);
    }

    /**
     *
     * @param monster
     * @throws Exception if the chactare's id is not unique in the array being inserted into.
     */
    public void addMontster(@NotNull Monster monster) {
        checkIdIsUnique(monster, monsters);
        monsters.add(monster);
    }

    public static <T extends BoardEntity> void checkIdIsUnique(T entity, ArrayList<T> entities) {
        int id = entity.getId();
        for(T e: entities) {
            if(e.getId() == id) {
                throw new HttpMessageNotReadableException("'id' not unique.");
            }
        }
    }

    /**
     * Perform state resolutions that happen globally at end of turn:
     *   - Kill things whose health = 0
     *   - Create elements
     */
    public void resolveEndOfTurn() {
        // TODO
    }

    /**
     * Perform state resolutions that happen at end of turn for a player or monster:
     *   - players pick up coins they are starting on
     *   - Process status ailments
     *
     * @param characterId
     */
    public void resolveEndOfTurn(int characterId) {
        resolveEndOfTurn();
    }


    /* ~~~ Getters and Setters ~~~ */

    public int getTurn() { return turn; }
    public void setTurn(int turn) { this.turn = turn; }
    public Map getMap() { return map; }
    public void setMap(Map map) { this.map = map; }
    public ArrayList<Monster> getMonsters() { return monsters; }
    public void setMonsters(ArrayList<Monster> monsters) { this.monsters = monsters; }
    public ArrayList<PlayerCharacter> getPlayers() { return players; }
    public void setPlayers(ArrayList<PlayerCharacter> players) { this.players = players; }
    public ArrayList<Coin> getCoins() { return coins; }
    public void setCoins(ArrayList<Coin> coins) { this.coins = coins; }
    public ArrayList<TreasureChest> getTreasureChests() { return treasureChests; }
    public void setTreasureChests(ArrayList<TreasureChest> treasureChests) {
        this.treasureChests = treasureChests;
    }
    public ArrayList<Obstacle> getObstacles() { return obstacles; }
    public void setObstacles(ArrayList<Obstacle> obstacles) { this.obstacles = obstacles; }
    public ArrayList<Trap> getTraps() { return traps; }
    public void setTraps(ArrayList<Trap> traps) { this.traps = traps; }
    public double getInitiativeProcessed() { return initiativeProcessed; }
    public void setInitiativeProcessed(double initiativeProcessed) {
        this.initiativeProcessed = initiativeProcessed;
    }
    public Elements getElements() { return elements; }
    public void setElements(Elements elements) { this.elements = elements; }

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
