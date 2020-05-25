package ca.flearning.restfulgloom.rest.boartstate;

import ca.flearning.restfulgloom.entities.game.Game;
import ca.flearning.restfulgloom.entities.game.Monster;
import ca.flearning.restfulgloom.entities.game.PlayerCharacter;
import ca.flearning.restfulgloom.rest.errors.InconsistentBoardstateException;
import ca.flearning.restfulgloom.rest.errors.MalformedRequestException;
import ca.flearning.restfulgloom.rest.jsonwrappers.BoardstateWrapper;
import ca.flearning.restfulgloom.rest.jsonwrappers.MonsterWrapper;
import ca.flearning.restfulgloom.rest.jsonwrappers.PlayerCharacterWrapper;
import ca.flearning.restfulgloom.rest.jsonwrappers.SummonWrapper;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/boardstate")
public class BoardstateController {

    /**
     * Return an empty boardstate object to be passed in to other boardstate APIs.
     * @return an empty boardstate object
     */
    @GetMapping("/")
    public BoardstateWrapper getBoardstate() {
        return new BoardstateWrapper(new Game());
    }

    @PostMapping(value="/", produces="application/json")
    public BoardstateWrapper postBoardstate(@RequestBody BoardstateWrapper wrapper)
    {
        // parse the provided boardstate
        Game game = getBoardstate(wrapper);

        // resolve any state stuff
        try {
            game.checkConsistency();
            return new BoardstateWrapper(game);
        } catch (InconsistentBoardstateException ex) {
            throw new MalformedRequestException("Boardstate is not internally consistent: "+ ex.getMessage());
        }
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
    public BoardstateWrapper addPlayerCharacter(@RequestBody PlayerCharacterWrapper wrapper,
                                          HttpServletRequest request, HttpServletResponse response) {

        Game game = getBoardstate(wrapper);
        PlayerCharacter player = wrapper.getPlayer();
        if(player == null) {
            throw new HttpMessageNotReadableException("'player' missing from POST body.");
        }

        game.addPlayer(player);
        return new BoardstateWrapper(game);
    }

    @PostMapping(value="/addSummon/{playerId}", consumes="application/json", produces="application/json")
    @ResponseBody
    public BoardstateWrapper addSummon(@RequestBody SummonWrapper wrapper,
                                       @PathVariable("playerId") int playerId) {

        Game game = getBoardstate(wrapper);
        Monster summon = wrapper.getSummon();
        if(summon == null) {
            throw new HttpMessageNotReadableException("'summon' missing from POST body.");
        }

        summon.setSummon(true);

        // add this summon to the referenced player
        for(PlayerCharacter player : game.getPlayers()) {
            if(player.getId() == playerId) {
                player.addSummon(summon);
                return new BoardstateWrapper(game);
            }
        }

        // fail
        throw new HttpMessageNotReadableException("Boardstate does not contain a player with id "+playerId);
    }
    
    @PostMapping(value="/addMonster", consumes="application/json", produces="application/json")
    @ResponseBody
    public BoardstateWrapper addMonster(@RequestBody MonsterWrapper wrapper) {

        Game game = getBoardstate(wrapper);
        Monster monster = wrapper.getMonster();
        if(monster == null) {
            throw new HttpMessageNotReadableException("'monster' missing from POST body.");
        }

        game.addMontster(monster);
        return new BoardstateWrapper(game);
    }

    @PostMapping(value="/endturn", consumes="application/json", produces="application/json")
    @ResponseBody
    public BoardstateWrapper processEndOfTurn(@RequestBody BoardstateWrapper boardstateWrapper) {
        Game game = boardstateWrapper.getGame();
        if(game == null) {
            game = new Game();
        }

        // TODO foreach: PlayerCharacter { process long rest }

        // foreach: Character { remove initiative }
        for(PlayerCharacter player : game.getPlayers()) {
            player.setInitiative(0.0);
        }

        game.setTurn(game.getTurn()+1);

        return new BoardstateWrapper(game);
    }

    public static Game getBoardstate(BoardstateWrapper wrapper) {
        Game game = wrapper.getGame();
        if(game == null) {
            game = new Game();
        }

        return game;
    }
}
