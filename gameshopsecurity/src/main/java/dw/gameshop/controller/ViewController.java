package dw.gameshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // View 를 Controll
public class ViewController { // 디자인 패턴, MVC(Model[정보] View(=>Front 로 넘어감) Controll[로직(Controll+Service)]) 디자인 패턴
    @GetMapping("/login_form")
    public String login_form() {
        return "login_form";
    } // login.html (.html 생략)

    @GetMapping("/articles")
    public String article() {
        return "article";
    } // article.html (.html 생략)

    @GetMapping("/gameshop/index.html")
    public String index() {
        return "index";
    }

    @GetMapping("/gameshop/singleProduct.html")
    public String singleProduct() {
        return "singleProduct";
    }

    @GetMapping("/gameshop/login.html")
    public String login() {
        return "login";
    }

    @GetMapping("/gameshop/cart.html")
    public String cart() {
        return "cart";
    }

    @GetMapping("/gameshop/mypage.html")
    public String mypage() {
        return "mypage";
    }



}
