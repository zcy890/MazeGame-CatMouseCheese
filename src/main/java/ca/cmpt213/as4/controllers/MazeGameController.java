package ca.cmpt213.as4.controllers;

import ca.cmpt213.as4.model.Direction;
import ca.cmpt213.as4.model.GameManager;
import ca.cmpt213.as4.model.MazeGameException;
import ca.cmpt213.as4.restapi.ApiBoardWrapper;
import ca.cmpt213.as4.restapi.ApiGameWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class is the controller, its main purpose is taking in endpoints and transfer data to the backend
 */

@RestController
public class MazeGameController {
    private List<GameManager> games = new ArrayList<>();

    private AtomicLong nextId = new AtomicLong();

    @GetMapping("/api/about")
    public String getMyName() {
        return "Zecheng Yan";
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/api/games")
    public ApiGameWrapper createNewGame() {

        // initialize the game with the manager
        GameManager manager = new GameManager();
        manager.setId(nextId.incrementAndGet());
        games.add(manager);
        return ApiGameWrapper.makeFromGame(manager, manager.getId());
    }

    @GetMapping("/api/games")
    public List<ApiGameWrapper> getAllGames() {
        List<ApiGameWrapper> listOfWrappers = new ArrayList<>();

        for (GameManager game : games) {
            listOfWrappers.add(ApiGameWrapper.makeFromGame(game, game.getId()));
        }
        return listOfWrappers;
    }

    @GetMapping("/api/games/{id}")
    public ApiGameWrapper getOneGame(@PathVariable("id") long gameID) {
        List<ApiGameWrapper> listOfWrappers = new ArrayList<>();

        for (GameManager game : games) {
            listOfWrappers.add(ApiGameWrapper.makeFromGame(game, game.getId()));
        }

        for (ApiGameWrapper wrapper : listOfWrappers) {
            if (wrapper.getGameNumber() == gameID) {
                return wrapper;
            }
        }
        throw new notFound();
    }

    @GetMapping("/api/games/{id}/board")
    public ApiBoardWrapper getBoardFromGameID(@PathVariable("id") long gameID) {

        for (GameManager manager : games) {
            if (manager.getId() == gameID) {
                return ApiBoardWrapper.makeFromGame(manager);
            }
        }
        throw new notFound();
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PostMapping("/api/games/{id}/moves")
    public void makeAMove(@PathVariable("id") long gameID, @RequestBody String move) throws MazeGameException {

        for (GameManager manager : games) {
            if (manager.getId() == gameID) {
                if (!move.equals("MOVE_UP")
                        && !move.equals("MOVE_LEFT")
                        && !move.equals("MOVE_RIGHT")
                        && !move.equals("MOVE_DOWN")
                        && !move.equals("MOVE_CATS")) {
                    throw new IllegalArgumentException();
                }
                switch (move) {
                    case "MOVE_UP":
                        manager.moveMouse(Direction.UP);
                        break;
                    case "MOVE_DOWN":
                        manager.moveMouse(Direction.DOWN);
                        break;
                    case "MOVE_LEFT":
                        manager.moveMouse(Direction.LEFT);
                        break;
                    case "MOVE_RIGHT":
                        manager.moveMouse(Direction.RIGHT);
                        break;
                    case "MOVE_CATS":
                        manager.moveCats();
                        break;
                    default:
                        throw new MazeGameException();
                }
            }
            else{
                throw new notFound();
            }
        }
    }

    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @PostMapping("/api/games/{id}/cheatstate")
    public void cheatState(@PathVariable("id") long gameID, @RequestBody String state) {

        for (GameManager manager : games) {
            if (manager.getId() == gameID) {
                if (!state.equals("1_CHEESE") && !state.equals("SHOW_ALL")) {
                    throw new IllegalArgumentException();
                }
                manager.activateCheatState(state);
                return;
            }
        }
        throw new notFound();
    }

    // Create Exception Handle
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Request")
    @ExceptionHandler(IllegalArgumentException.class)
    public void badIdExceptionHandler() {
        // Nothing to do
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
    public static class notFound extends RuntimeException {
        public notFound() {
            super();
        }
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Request")
    @ExceptionHandler(MazeGameException.class)
    public void wallBadIdExceptionHandler() {
        // Nothing to do
    }
}
