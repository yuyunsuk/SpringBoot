package dw.gameshop.controller;

import dw.gameshop.model.Game;
import dw.gameshop.model.User;
import dw.gameshop.repository.GameRepository;
import dw.gameshop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {
    //@Autowired => 필드 주입에서 생성사 주입으로 수정
    GameService gameService;

    @Autowired //=> 의존객체 생성자 주입(권장하는 방법)
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Game>> getAllGames()
    {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable long id) {
        return new ResponseEntity<>(gameService.getGameById(id), HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Game> updateGameById(@PathVariable long id,
                               @RequestBody Game game) {
        return new ResponseEntity<>(gameService.updateGameById(id, game), HttpStatus.OK);
    }

    @PostMapping("/products/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<User>(gameService.saveUser(user), HttpStatus.OK);
    }

    @GetMapping("/products/maxprice")
    public ResponseEntity<Game> getInfoMostExpensiveGame() {
        return new ResponseEntity<>(gameService.getInfoMostExpensiveGame(), HttpStatus.OK);
    }

    @GetMapping("/products/maxpriceTop3")
    public ResponseEntity<List<Game>> getInfoTop3ExpensiveGame() {
        return new ResponseEntity<>(gameService.getInfoTop3ExpensiveGame(), HttpStatus.OK);
    }

}
