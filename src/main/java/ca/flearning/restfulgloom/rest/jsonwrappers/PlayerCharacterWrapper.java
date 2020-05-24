package ca.flearning.restfulgloom.rest.jsonwrappers;

import ca.flearning.restfulgloom.entities.boardstate.Boardstate;
import ca.flearning.restfulgloom.entities.boardstate.Character;
import ca.flearning.restfulgloom.entities.boardstate.PlayerCharacter;

public class PlayerCharacterWrapper {
    PlayerCharacter player;
    Boardstate boardstate;

    public PlayerCharacter getPlayer() {
        return player;
    }

    public void setPlayer(PlayerCharacter player) {
        this.player = player;
    }

    public Boardstate getBoardstate() {
        return boardstate;
    }

    public void setBoardstate(Boardstate boardstate) {
        this.boardstate = boardstate;
    }
}
