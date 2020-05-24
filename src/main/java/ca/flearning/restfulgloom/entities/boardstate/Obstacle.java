package ca.flearning.restfulgloom.entities.boardstate;

public class Obstacle extends BoardEntity {

    // TODO: maybe this is better as a pointer to a DB object
    enum ObstacleType {
        DOOR_CLOSED,
        DOOR_OPEN,
        LAVA_1_HEX,
        LAVA_2_HEX,
        LAVA_3_HEX,
    }

    private ObstacleType type;

    public ObstacleType getType() { return type; }
    public void setType(ObstacleType type) { this.type = type; }
}
