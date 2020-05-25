package ca.flearning.restfulgloom.rest.jsonwrappers;

import ca.flearning.restfulgloom.entities.game.PlayerCharacter;

public class PlayerCharacterWrapper extends BoardstateWrapper {
    PlayerCharacter player;

    public PlayerCharacter getPlayer() {
        return player;
    }

    public void setPlayer(PlayerCharacter player) {
        this.player = player;
    }
}
