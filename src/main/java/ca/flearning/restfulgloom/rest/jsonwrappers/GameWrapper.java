package ca.flearning.restfulgloom.rest.jsonwrappers;

import ca.flearning.restfulgloom.entities.game.Game;

public class BoardstateWrapper {

    private Game game;

    public BoardstateWrapper() { }

    public BoardstateWrapper(Game boardState) {
        this.game = boardState;
    }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }

    @Override
    public String toString() {
        return "BoardstateWrapper{" +
                "boardState:" + game +
                '}';
    }
}
