package ca.flearning.restfulgloom.entities.boardstate;

public class Status {

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
}
