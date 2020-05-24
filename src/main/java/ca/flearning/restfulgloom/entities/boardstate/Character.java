package ca.flearning.restfulgloom.entities.boardstate;

import java.util.ArrayList;

public class Character extends BoardEntity {
    private int health;
    private int healthMax;
    private double initiative;  // Thought: to avoid ties make this a double and do 21.0, 21.01, etc
    private ArrayList<StatusAilments> statusAilments;

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public int getHealthMax() { return healthMax; }
    public void setHealthMax(int healthMax) { this.healthMax = healthMax; }
    public double getInitiative() { return initiative; }
    public void setInitiative(double initiative) { this.initiative = initiative; }
    public ArrayList<StatusAilments> getStatusAilments() { return statusAilments; }
    public void setStatusAilments(ArrayList<StatusAilments> statusAilments) { this.statusAilments = statusAilments; }
}
