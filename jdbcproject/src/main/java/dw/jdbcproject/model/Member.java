package dw.jdbcproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

//@Entity 는 JPA 가 아니기 때문에 사용할 수 없음, Table 직접 SQL 문으로 Create
public class Member {
    private long id;
    private String name;
}
