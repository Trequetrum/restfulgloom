package ca.flearning.restfulgloom.entities.boardstate;

public class Monster extends Character {

    private int standeeNumber;

    /**
     * Should be data-driven off the map tiles in the db. ... should probably be the db key (int or wtv)
     */
    private String monsterType;

    private int move;
    private int atk;
    private int range;
    private Action action;
}
