package ca.flearning.restfulgloom.entities.boardstate;

public class MapTile {

    /**
     * "A1a", "J2b", etc.
     * Should be data-driven off the map tiles in the db. ... should probably be the db key (int or wtv)
     */
    private String tileNum;

    // TODO some way to list other tiles it's connected to.

    // TODO some way to map obstacles, doors, traps, treasure, coins, etc.


    public String getTileNum() { return tileNum; }
    public void setTileNum(String tileNum) { this.tileNum = tileNum; }
}
