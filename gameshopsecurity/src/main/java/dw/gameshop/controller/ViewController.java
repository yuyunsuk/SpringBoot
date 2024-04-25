package dw.gameshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // View 를 Controll
public class ViewController { // 디자인 패턴, MVC(Model[정보] View(=>Front 로 넘어감) Controll[로직(Controll+Service)]) 디자인 패턴
    @GetMapping("/login")
    public String login() {
        return "login";
    } // login.html (.html 생략)

    @GetMapping("/articles")
    public String article() {
        return "article";
    } // article.html (.html 생략)
}
