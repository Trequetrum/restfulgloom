package ca.flearning.restfulgloom.entities.boardstate;

public abstract class BoardEntity {
    /**
     * Global counter to make sure each entity in the ephemeral boardstate gets a unique ID.
     */
    private static int uniqueIDCounter = 0;
    private int id;
    private Location location;

    public BoardEntity() {
        this.id = getUniqueID();
    }

    private synchronized int getUniqueID() {
        return uniqueIDCounter++;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }

    @Override
    public String toString() {
        return "BoardstateEntity{" +
                "id=" + id +
                '}';
    }
}
