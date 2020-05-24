package ca.flearning.restfulgloom.entities.boardstate;

public class Monster extends Character {

    private int standeeNumber;

    /**
     * Should be data-driven off the map tiles in the db. ... should probably be the db key (int or wtv)
     */
    private String monsterType;

    private int move=2;
    private int atk=2;
    private int range=0;
    private Action turnAction;
    private boolean isSummon=false;

    public int getStandeeNumber() { return standeeNumber; }
    public void setStandeeNumber(int standeeNumber) { this.standeeNumber = standeeNumber; }
    public String getMonsterType() { return monsterType; }
    public void setMonsterType(String monsterType) { this.monsterType = monsterType; }
    public int getMove() { return move; }
    public void setMove(int move) { this.move = move; }
    public int getAtk() { return atk; }
    public void setAtk(int atk) { this.atk = atk; }
    public int getRange() { return range; }
    public void setRange(int range) { this.range = range; }
    public Action getTurnAction() { return turnAction; }
    public void setTurnAction(Action turnAction) { this.turnAction = turnAction; }
    public boolean isSummon() { return isSummon; }
    public void setSummon(boolean summon) { isSummon = summon; }
}
