package dw.gameshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

// 아래 4개는 Lombok 이용 Annotation 사용
// 단점 => 자동화 테스트시 에러가 많이 생김
// 경우에 따라서는 사용하지 않는 회사도 많음
// 개발 단계에서는 Lombok 사용, 테스트 시에는 Lombok 제거 사용
// 기본생성자를 사용하지 않으면 값이 입력 되지 않음
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@Entity
@Table(name="purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne // 게임 3개 구입시 하나당 구매 데이터 3개 생김
    @JoinColumn(name="game_id") // 한글 사용 가능
    private Game game; // 객체 변수에는 한글을 사용하지 말것

    @ManyToOne
    @JoinColumn(name="user_id") // 한글 사용 가능
    private User user; // 객체 변수에는 한글을 사용하지 말것

    @Column(name="purchase_time")
    private LocalDateTime purchaseTime;
}
