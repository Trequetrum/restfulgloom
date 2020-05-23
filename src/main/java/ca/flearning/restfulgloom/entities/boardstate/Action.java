package ca.flearning.restfulgloom.entities.boardstate;

public class Action {
    private int move;
    private int atk;
    private int range;
    private String descr = "default action";

    public int getMove() { return move; }
    public void setMove(int move) { this.move = move; }
    public int getAtk() { return atk; }
    public void setAtk(int atk) { this.atk = atk; }
    public int getRange() { return range; }
    public void setRange(int range) { this.range = range; }
    public String getDescr() { return descr; }
    public void setDescr(String descr) { this.descr = descr; }
}
