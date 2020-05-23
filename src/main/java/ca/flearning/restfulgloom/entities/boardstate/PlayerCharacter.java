package ca.flearning.restfulgloom.entities.boardstate;

import java.util.ArrayList;

public class PlayerCharacter extends Character {

    /**
     * Should be data-driven off the map tiles in the db. ... should probably be the db key (int or wtv)
     */
    private String characterClass;

    private ArrayList<Item> items;
    private ArrayList<Monster> summons;
}
