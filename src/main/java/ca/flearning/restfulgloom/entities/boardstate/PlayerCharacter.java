package ca.flearning.restfulgloom.entities.boardstate;

import java.util.ArrayList;

public class PlayerCharacter extends Character {

    /**
     * Should be data-driven off the map tiles in the db. ... should probably be the db key (int or wtv)
     */
    private String characterClass;

    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Monster> summons = new ArrayList<>();
    private boolean longResting;

    public String getCharacterClass() { return characterClass; }
    public void setCharacterClass(String characterClass) { this.characterClass = characterClass; }
    public ArrayList<Item> getItems() { return items; }
    public void setItems(ArrayList<Item> items) { this.items = items; }
    public ArrayList<Monster> getSummons() { return summons; }
    public void setSummons(ArrayList<Monster> summons) { this.summons = summons; }
    public boolean isLongResting() { return longResting; }
    public void setLongResting(boolean longResting) { this.longResting = longResting; }

    @Override
    public String toString() {
        return "PlayerCharacter{" +
                "characterClass='" + characterClass + '\'' +
                ", items=" + items +
                ", summons=" + summons +
                ", longResting=" + longResting +
                "} " + super.toString();
    }
}
