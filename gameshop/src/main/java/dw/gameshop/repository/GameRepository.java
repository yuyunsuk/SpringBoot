package dw.gameshop.repository;

import dw.gameshop.model.Game;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

//    public  class Game
//    private long id;
//    private String title;
//    private String genre;
//    private int price;
//    private String image;
//    private String text;

    // JPQL 사용 예, Table 을 Class Name 으로 해야 됨, alias 가 select 절에 나와야 됨
    @Query("select g1 from Game g1 where g1.price = ( select max(g2.price) from Game g2 )")
    public Game getGameWithMaxPrice();

    // JPQL 사용 예, Table 을 Class Name 으로 해야 됨, alias 가 select 절에 나와야 됨
    @Query("select g1 from Game g1 order by price desc")
    public List<Game> getGameWithMaxPriceTop3();

}
