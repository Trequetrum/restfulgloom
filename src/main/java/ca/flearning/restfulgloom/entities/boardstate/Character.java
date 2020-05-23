package ca.flearning.restfulgloom.entities.boardstate;

import java.util.ArrayList;

public class Character {
    private int health;
    private int healthMax;
    private double initiative;  // Thought: to avoid ties make this a double and do 21.0, 21.01, etc
    private String name;
    private ArrayList<Status> status;
    private Location location;

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public int getHealthMax() { return healthMax; }
    public void setHealthMax(int healthMax) { this.healthMax = healthMax; }
    public double getInitiative() { return initiative; }
    public void setInitiative(double initiative) { this.initiative = initiative; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ArrayList<Status> getStatus() { return status; }
    public void setStatus(ArrayList<Status> status) { this.status = status; }
    public Location getLocation() { return location; }
    public void setLocation(Location location) { this.location = location; }
}
