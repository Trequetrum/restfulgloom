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

    public ca.flearning.restfulgloom.entities.Item getItem() { return item; }
    public void setItem(ca.flearning.restfulgloom.entities.Item item) { this.item = item; }
    public boolean isLost() { return lost; }
    public void setLost(boolean lost) { this.lost = lost; }
    public boolean isUsed() { return used; }
    public void setUsed(boolean used) { this.used = used; }
}
