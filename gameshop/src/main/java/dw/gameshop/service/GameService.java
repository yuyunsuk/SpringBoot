package dw.gameshop.service;

import dw.gameshop.model.Game;
import dw.gameshop.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isEmpty()) {
            return null;
        } else {
            return game.get();
        }
    }

    public Game updateGameById(Long id, Game game) {
        Optional<Game> game1 = gameRepository.findById(id);
        if (game1.isPresent()) {
            game1.get().setGenre(game.getGenre()); // 입력받은 값을 세팅
            game1.get().setImage(game.getImage()); // 입력받은 값을 세팅
            game1.get().setText(game.getText());   // 입력받은 값을 세팅
            game1.get().setPrice(game.getPrice()); // 입력받은 값을 세팅
            game1.get().setTitle(game.getTitle()); // 입력받은 값을 세팅
            gameRepository.save(game1.get()); // 실제 Update 작업
            return game1.get();
        } else {
            return null;
        }

    }

}
