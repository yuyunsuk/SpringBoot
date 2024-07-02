package dw.gameshop.controller;

import dw.gameshop.dto.BaseResponse;
import dw.gameshop.enumstatus.ResultCode;
import dw.gameshop.model.Game;
import dw.gameshop.model.User;
import dw.gameshop.service.GameShopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
/* @CrossOrigin(origins="*", allowedHeaders = "*") */
/* @CrossOrigin(origins="http://127.0.0.1:5500/") => origin 사이트로 허용함 */
/* WebConfig.java 에 allowedOrigins 부분에 추가 */
public class GameShopController {
    GameShopService gameShopService;

    public GameShopController(GameShopService gameShopService) {
        this.gameShopService = gameShopService;
    }

//    @GetMapping("/products") // Gameshop products 12개 자료 모두 조회
//    public ResponseEntity<List<Game>> getAllGames() {
//        return new ResponseEntity<>(gameShopService.getAllGames(),
//                HttpStatus.OK);
//    }

    @GetMapping("/products") // Gameshop products 12개 자료 모두 조회
        public ResponseEntity<BaseResponse<List<Game>>> getAllGames() {
        return new ResponseEntity<>(
                new BaseResponse(ResultCode.SUCCESS.name(),
                        gameShopService.getAllGames(),
                        ResultCode.SUCCESS.getMsg())
                , HttpStatus.OK);
    }

    @GetMapping("/products/{id}") // Gameshop product ID 를 가지고 1개의 데이터를 조회
    public ResponseEntity<Game> getGameById(@PathVariable long id) {
        return new ResponseEntity<>(gameShopService.getGameById(id),
                HttpStatus.OK);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Game> updateGameById(@PathVariable long id,
                               @RequestBody Game game) {
        return new ResponseEntity<>(gameShopService.updateGameById(id, game),
                HttpStatus.OK);
    }

    @PostMapping("/products/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(gameShopService.saveUser(user),
                HttpStatus.OK);
    }

    @GetMapping("/products/maxprice")
    public ResponseEntity<Game> getGameWithMaxPrice() {
        return new ResponseEntity<>(gameShopService.getGameWithMaxPrice(),
                HttpStatus.OK);
    }

    @GetMapping("/products/maxpricetop3")
    public ResponseEntity<List<Game>> getGameWithMaxPriceTop3() {
        return new ResponseEntity<>(gameShopService.getGameWithMaxPriceTop3(),
                HttpStatus.OK);
    }
}





