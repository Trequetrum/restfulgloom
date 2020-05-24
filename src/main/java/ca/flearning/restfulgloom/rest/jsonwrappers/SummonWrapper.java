package ca.flearning.restfulgloom.rest.jsonwrappers;

import ca.flearning.restfulgloom.entities.boardstate.Monster;

public class SummonWrapper extends BoardstateWrapper {

    private Monster summon;

    public Monster getSummon() {
        return summon;
    }

    public void setSummon(Monster summon) {
        this.summon = summon;
    }
}
