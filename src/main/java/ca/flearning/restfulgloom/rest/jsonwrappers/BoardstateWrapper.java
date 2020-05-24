package ca.flearning.restfulgloom.rest.jsonwrappers;

import ca.flearning.restfulgloom.entities.boardstate.Boardstate;

public class BoardstateWrapper {

    private Boardstate boardstate;

    public BoardstateWrapper() { }

    public BoardstateWrapper(Boardstate boardState) {
        this.boardstate = boardState;
    }

    public Boardstate getBoardstate() { return boardstate; }
    public void setBoardstate(Boardstate boardstate) { this.boardstate = boardstate; }

    @Override
    public String toString() {
        return "BoardstateWrapper{" +
                "boardState:" + boardstate +
                '}';
    }
}
