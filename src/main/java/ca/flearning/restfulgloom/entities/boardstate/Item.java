package ca.flearning.restfulgloom.entities.boardstate;

public class Item {

    /**
     * Reference to the item type.
     */
    private ca.flearning.restfulgloom.entities.Item item;

    /**
     * Permanently lost for the game
     */
    private boolean lost;

    /**
     * Used and will come back on long rest.
     */
    private boolean used;
}
