package ca.flearning.restfulgloom.entities.boardstate;

import java.util.ArrayList;

public class Map {
    private ArrayList<MapTile> tiles;

    public ArrayList<MapTile> getTiles() { return tiles; }
    public void setTiles(ArrayList<MapTile> tiles) { this.tiles = tiles; }
}
