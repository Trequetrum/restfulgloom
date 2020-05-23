package ca.flearning.restfulgloom.entities.boardstate;


import javax.persistence.Entity;
import java.util.ArrayList;

/**
 * I want this entity to be exchanged back and forth with the client over REST,
 * but not persisted to the DB.
 */
@Entity
public class BoardState {

    private int turnNum = 0;

    private Map map;

    private ArrayList<Character> monsters;
    private ArrayList<Character> players;


}
