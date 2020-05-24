package ca.flearning.restfulgloom.entities.boardstate;

public abstract class BoardstateEntity {
    /**
     * Global counter to make sure each entity in the ephemeral boardstate gets a unique ID.
     */
    private static int uniqueIDCounter = 0;

    private int id;

    public BoardstateEntity() {
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

    @Override
    public String toString() {
        return "BoardstateEntity{" +
                "id=" + id +
                '}';
    }
}
