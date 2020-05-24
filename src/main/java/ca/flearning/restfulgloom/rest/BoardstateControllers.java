package ca.flearning.restfulgloom.rest;

import ca.flearning.restfulgloom.entities.boardstate.Boardstate;
import ca.flearning.restfulgloom.entities.boardstate.Monster;
import ca.flearning.restfulgloom.entities.boardstate.PlayerCharacter;
import ca.flearning.restfulgloom.rest.jsonwrappers.BoardstateWrapper;
import ca.flearning.restfulgloom.rest.jsonwrappers.PlayerCharacterWrapper;
import ca.flearning.restfulgloom.rest.jsonwrappers.SummonWrapper;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/boardstate")
public class BoardstateControllers {

    /**
     * Return an empty boardstate object to be passed in to other boardstate APIs.
     * @return an empty boardstate object
     */
    @GetMapping("/")
    public BoardstateWrapper getBoardstate() {
        return new BoardstateWrapper(new Boardstate());
    }

    /**
     * Requires json-encoded objects:
     *   - Character
     *   - Boardstate
     *
     * @return the updated boardstate
     */
    @PostMapping(value="/addPlayer", consumes="application/json", produces="application/json")
    @ResponseBody
    public BoardstateWrapper addPlayerCharacter(@RequestBody PlayerCharacterWrapper playerCharacterWrapper,
                                          HttpServletRequest request, HttpServletResponse response) {

        Boardstate boardstate = getBoardstate(playerCharacterWrapper);
        PlayerCharacter player = playerCharacterWrapper.getPlayer();
        if(player == null) {
            throw new HttpMessageNotReadableException("'player' missing from POST body.");
        }

        boardstate.addPlayer(player);
        return new BoardstateWrapper(boardstate);
    }

    @PostMapping(value="/addSummon/{playerId}", consumes="application/json", produces="application/json")
    @ResponseBody
    public BoardstateWrapper addSummon(@RequestBody SummonWrapper summonWrapper,
                                       @PathVariable("playerId") int playerId) {

        Boardstate boardstate = getBoardstate(summonWrapper);
        Monster summon = summonWrapper.getSummon();
        if(summon == null) {
            throw new HttpMessageNotReadableException("'summon' missing from POST body.");
        }

        summon.setSummon(true);

        // add this summon to the referenced player
        for(PlayerCharacter player : boardstate.getPlayers()) {
            if(player.getId() == playerId) {
                player.getSummons().add(summon);
                return new BoardstateWrapper(boardstate);
            }
        }

        // fail
        throw new HttpMessageNotReadableException("Boardstate does not contain a player with id "+playerId);
    }

    @PostMapping(value="/endturn", consumes="application/json", produces="application/json")
    @ResponseBody
    public BoardstateWrapper processEndOfTurn(@RequestBody BoardstateWrapper boardstateWrapper) {
        Boardstate boardstate = boardstateWrapper.getBoardstate();
        if(boardstate == null) {
            boardstate = new Boardstate();
        }

        boardstate.setTurn(boardstate.getTurn()+1);

        // TODO foreach: PlayerCharacter { process long rest }

        // TODO foreach: Character { remove initiative }

        return new BoardstateWrapper(boardstate);
    }


    private Boardstate getBoardstate(BoardstateWrapper wrapper) {
        Boardstate boardstate = wrapper.getBoardstate();
        if(boardstate == null) {
            boardstate = new Boardstate();
        }

        return boardstate;
    }
}
