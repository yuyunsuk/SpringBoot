package dw.gameshop.controller;

import dw.gameshop.dto.ReviewDto;
import dw.gameshop.model.Game;
import dw.gameshop.model.Review;
import dw.gameshop.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> saveReview(@RequestBody Review review){
        return new ResponseEntity<>(reviewService.saveReview(review), HttpStatus.OK);
    }

//    @RequestBody => JSON 형태
//    {
//        "point":"5",
//        "reviewText":"Game Review Test",
//        "game":{"id":"2"},
//        "user":{"userId":"steve12"}
//    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(){
        return new ResponseEntity<>(reviewService.getAllReviews(), HttpStatus.OK);
    }

    @GetMapping("/reviews/dto")
    public ResponseEntity<List<ReviewDto>> getAllReviewsByDto(){
        return new ResponseEntity<>(reviewService.getReviewByDto(), HttpStatus.OK);
    }

}
