package dw.gameshop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto { // Login Dto 2개(userId, password) 필요, Sign-up Dto 4개 필요
    private String userId;
    private String password;
    private String userName;
    private String userEmail;
}
