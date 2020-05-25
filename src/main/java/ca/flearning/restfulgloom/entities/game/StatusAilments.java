package ca.flearning.restfulgloom.entities.boardstate;

public class StatusAilments {

    public enum Statuses {
        POISON,
        INVISIBLE,
        SHIELD,
        RETALIATE
        // TODO: fill in
    };

    private Statuses status;

    /**
     * Some statuses, like shield or retaliate, have one or more associated values.
     */
    private int value1;
    private int value2;

    public Statuses getStatus() { return status; }
    public void setStatus(Statuses status) { this.status = status; }
    public int getValue1() { return value1; }
    public void setValue1(int value1) { this.value1 = value1; }
    public int getValue2() { return value2; }
    public void setValue2(int value2) { this.value2 = value2; }
}
