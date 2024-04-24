package dw.gameshop.repository;

import dw.gameshop.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    // JPQL 사용 예, Table 을 Class Name 으로 해야 됨, alias 가 select 절에 나와야 됨
    @Query("select g1 from Game g1 where g1.price = ( select max(g2.price) from Game g2 )")
    public Game getGameWithMaxPrice();

    // JPQL 사용 예, Table 을 Class Name 으로 해야 됨, alias 가 select 절에 나와야 됨
    @Query("select g1 from Game g1 order by price desc")
    public List<Game> getGameWithMaxPriceTop3();

}
