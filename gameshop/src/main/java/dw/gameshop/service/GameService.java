package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.model.User;
import dw.gameshop.repository.GameRepository;
import dw.gameshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLData;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class GameService {

    GameRepository gameRepository;
    UserRepository userRepository;

    // 생성자 주입시에는 생략 가능 @Autowired
    public GameService(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(Long id) {
        Optional<Game> game = gameRepository.findById(id); // Id 로 Game 조회 없으면 예외사항 처리, 있으면 조회결과 리턴;
        if (game.isEmpty()) {
            //return null;
            throw new ResourceNotFoundException("Game", "ID", id); // 예외사항 객체 리턴
        } else {
            return game.get();
        }
    }

    public Game updateGameById(Long id, Game game) {
        Optional<Game> game1 = gameRepository.findById(id); // Id 로 Game 조회 있으면 Update, 없으면 예외사항 처리
        if (game1.isPresent()) {
            game1.get().setGenre(game.getGenre()); // 입력받은 값을 세팅
            game1.get().setImage(game.getImage()); // 입력받은 값을 세팅
            game1.get().setText(game.getText());   // 입력받은 값을 세팅
            game1.get().setPrice(game.getPrice()); // 입력받은 값을 세팅
            game1.get().setTitle(game.getTitle()); // 입력받은 값을 세팅
            gameRepository.save(game1.get()); // 실제 Update 작업
            return game1.get();
        } else {
            //return null;
            throw new ResourceNotFoundException("Game", "ID", id); // 예외사항 객체 리턴
        }

    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // 제일 비싼 게임의 정보
    public Game getInfoMostExpensiveGame() {
        List<Game> gameList = gameRepository.findAll();

//        int maxPrice = 0;
//        int maxIndex = 0;
//        int gamePrice = 0;
//        for (int i=0; i<gameList.size(); i++) {
//            gamePrice = gameList.get(i).getPrice();
//            if (gamePrice > maxPrice) {
//                maxPrice = gamePrice;
//                maxIndex = i;
//            }
//        }
//        return gameList.get(maxIndex);

//        // 람다식이 아닌 일반 자바코드 사용 예 (선생님 코드)
//        List<Game> games = gameRepository.findAll();
//
//        if (games.size() <= 0) {
//            throw new ResourceNotFoundException("Max Price","GameService", " ");
//        }
//
//        Game max = games.get(0);
//        for (int i=0; i<games.size()-1; i++) {
//            if (max.getPrice() < games.get(i+1).getPrice()) {
//                max = games.get(i+1);
//            }
//        }
//        return max;

//        gameList.sort(Comparator.comparingInt(Game::getPrice).reversed());
//        return gameList.getFirst();

//        // 람다식 사용 예
//        return gameList.stream().sorted(Comparator.comparingInt((Game g)->g.getPrice())
//                        .reversed()).findFirst()
//                .orElseThrow(()->new ResourceNotFoundException("Max Price","GameService", " "));

//        // 람다식 사용 예
//        return gameList.stream()
//                .sorted(Comparator.comparingInt(Game::getPrice)
//                .reversed())
//                .findFirst()
//                .orElseThrow(()->new ResourceNotFoundException("Max Price","GameService", " "));

        // GameRepository 에 JPQL 사용
        return gameRepository.getGameWithMaxPrice();

    }

    // 제일 비싼 게임 Top 3
    public List<Game> getInfoTop3ExpensiveGame() {
        List<Game> gameList = gameRepository.findAll();

//        int firstIndex = 0;
//        int secondIndex = 0;
//        int thirdIndex = 0;
//        int gamePrice = 0;
//        int maxPrice;
//
//        maxPrice = 0;
//        for (int i=0; i<gameList.size(); i++) {
//            gamePrice = gameList.get(i).getPrice();
//            if (gamePrice > maxPrice) {
//                maxPrice = gamePrice;
//                firstIndex = i;
//            }
//        }
//
//        maxPrice = 0;
//        for (int i=0; i<gameList.size(); i++) {
//            gamePrice = gameList.get(i).getPrice();
//            if (i == firstIndex) continue;
//            if (gamePrice > maxPrice) {
//                maxPrice = gamePrice;
//                secondIndex = i;
//            }
//        }
//
//        maxPrice = 0;
//        for (int i=0; i<gameList.size(); i++) {
//            gamePrice = gameList.get(i).getPrice();
//            if (i == firstIndex || i == secondIndex) continue;
//            if (gamePrice > maxPrice) {
//                maxPrice = gamePrice;
//                thirdIndex = i;
//            }
//        }
//
//        List<Game> newGameList = new ArrayList<>();
//        newGameList.add(gameList.get(firstIndex));
//        newGameList.add(gameList.get(secondIndex));
//        newGameList.add(gameList.get(thirdIndex));
//        return newGameList;

//        // 선생님 코드
//        List<Game> gameList = gameRepository.findAll();
//        //gameList.sort(Comparator.comparingInt((Game g)->g.getPrice()).reversed()); // g는 변수
//        gameList.sort(Comparator.comparingInt(Game::getPrice).reversed()); // g는 변수
//
//        List<Game> newGameList = new ArrayList<>();
//        newGameList.add(gameList.get(0));
//        newGameList.add(gameList.get(1));
//        newGameList.add(gameList.get(2));
//        return newGameList;

//        // 람다식 표현
//        return gameList.stream()
//                .sorted(Comparator.comparingInt(Game::getPrice).reversed())
//                .limit(3)
//                .collect(Collectors.toList());

        // GameRepository 에 JPQL 사용
        return gameRepository.getGameWithMaxPriceTop3()
                .stream().limit(3)
                .collect(Collectors.toList());
    }

}