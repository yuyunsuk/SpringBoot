package dw.gameshop.service;

import dw.gameshop.dto.UserDto;
import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Authority;
import dw.gameshop.model.Purchase;
import dw.gameshop.model.User;
import dw.gameshop.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String saveUser(UserDto userDto) { // userDto 형태로 변경
        Optional <User> userOptional = userRepository.findByUserId(userDto.getUserId()); // 기본적으로 Optional 로 가져옴
        if (userOptional.isPresent()) {
            return "이미 등록된 ID 입니다."; // 나중에 Status 실패로 보내서 클라이언트에서 처리 예정
        }

        /* 권한 관련 추가, 회원 가입시 무조건 user 권한, 나중에 Admin 이 권한을 바꿈 */
        Authority authority = new Authority();
        authority.setAuthorityName("ROLE_USER");

        User user = new User(userDto.getUserId(),
                userDto.getUserNameKor(),
                userDto.getUserEmail(),
                bCryptPasswordEncoder.encode(userDto.getPassword()), // 암호화 하여 적용
                authority,
                LocalDateTime.now());
        return userRepository.save(user).getUserId(); // 없으면 Insert, 있으면 Update
    }

    public User getUserByUserId(String userId) {
        // 유저아이디로 유저객체 찾기
        Optional<User> userOptional = userRepository.findByUserId(userId);
        if (userOptional.isEmpty()) {
            // throw new ResourceNotFoundException("User", "ID", userId);
            throw new ResourceNotFoundException("ResourceNotFoundException User ID: " + userId);
        }
        return userOptional.get();
    }

}
