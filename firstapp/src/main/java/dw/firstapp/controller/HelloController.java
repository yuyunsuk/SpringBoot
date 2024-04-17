package dw.firstapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 톰켓 서버랑 매칭 시킴
public class HelloController {
    @GetMapping("/sayhello") // 톰켓이 읽으면 실행
    public String hello() {
        return "Hello World!"; // 문자열 리턴
    }
}
