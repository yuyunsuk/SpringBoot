package dw.gameshop.controller;

import dw.gameshop.model.Game;
import dw.gameshop.repository.GameRepository;
import dw.gameshop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {
    @Autowired
    GameService gameService;

    @GetMapping("/api/game")
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }

    @GetMapping("/api/game/{id}")
    public Game getGameById(@PathVariable long id) {
        return gameService.getGameById(id);
    }

    @PutMapping("/api/game/{id}")
    public Game updateGameById(@PathVariable long id,
                               @RequestBody Game game) {
        return gameService.updateGameById(id, game);
    }

}
