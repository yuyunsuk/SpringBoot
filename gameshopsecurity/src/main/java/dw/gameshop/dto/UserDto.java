package dw.gameshop.dto;

import dw.gameshop.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

//    private String userId;
//    private String password;
//    private String userName;
//    private String userEmail;

    @NotBlank(message = "User ID is mandatory")
    private String userId;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 4, message = "Password must be at least 4 characters long") // 4자 이상 입력
    private String password;

    @NotBlank(message = "User name is mandatory")
    private String userNameKor;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String userEmail;

    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getUserId(),
                null,
                user.getUsername(),
                user.getEmail()
        );
    }
}
