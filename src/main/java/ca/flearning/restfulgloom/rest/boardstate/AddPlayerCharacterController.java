package ca.flearning.restfulgloom.rest.boardstate;

import ca.flearning.restfulgloom.entities.boardstate.BoardState;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/boardstate")
public class AddPlayerCharacterController {

    public class BoardstateWrapper {
        private BoardState boardState;

        public BoardstateWrapper(BoardState boardState) {
            this.boardState = boardState;
        }

        public BoardState getBoardState() { return boardState; }
        public void setBoardState(BoardState boardState) { this.boardState = boardState; }
    }

    /**
     * Requires json-encoded objects:
     *   - Character
     *   - Boardstate
     *
     * @return the updated boardstate
     */
    @PostMapping("/addcharacter")
    public BoardstateWrapper devLogin()
    {
        return new BoardstateWrapper(new BoardState());
    }

}
