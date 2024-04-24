package dw.gameshop.service;

import dw.gameshop.dto.ReviewDto;
import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Review;
import dw.gameshop.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
// @Transactional은 주로 여러 쿼리문이 있을때 사용
// 데이터베이스의 데이터를 수정하는 도중에 예외가 발생된다면 어떻게 해야 할까? DB의 데이터들은 수정이 되기 전의 상태로 다시 되돌아가져야 하고, 다시 수정 작업이 진행되어야 할 것이다.
// 이렇듯 여러 작업을 진행하다가 문제가 생겼을 경우 이전 상태로 롤백하기 위해 사용되는 것이 트랜잭션(Transaction)
public class ReviewService {

    ReviewRepository reviewRepository;

    // 생성자 주입 @Autowired 생략
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review saveReview(Review review){
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

//    public List<ReviewDto> getReviewByDto(){
//        List<Review> reviewList = reviewRepository.findAll(); // 전체 받아옴
//        List<ReviewDto> reviewDtoList = new ArrayList<>(); // 변환될 DTO 객체 리스트
//        for (int i=0;i<reviewList.size();i++) {
//            ReviewDto reviewDto = new ReviewDto(); // 변환될 DTO 객체 생성
//
//            reviewDto.setReviewPoint(reviewList.get(i).getPoint());
//            reviewDto.setReviewText(reviewList.get(i).getReviewText());
//            reviewDto.setGameId(reviewList.get(i).getGame().getId());
//            reviewDto.setGameName(reviewList.get(i).getGame().getTitle());
//            reviewDto.setUserId(reviewList.get(i).getUser().getUserId());
//
//            reviewDtoList.add(reviewDto); // 변환될 DTO 객체 리스트에 추가
//        }
//        return reviewDtoList;
//    }

    public List<ReviewDto> getReviewByDto(){
        List<Review> reviewList = reviewRepository.findAll(); // 전체 받아옴
        List<ReviewDto> reviewDtoList = new ArrayList<>(); // 변환될 DTO 객체 리스트

        for (int i=0;i<reviewList.size();i++) {
            ReviewDto reviewDto = new ReviewDto(); // 변환될 DTO 객체 생성
            reviewDtoList.add(reviewDto.toReviewDtoFromReview(reviewList.get(i))); // 변환될 DTO 객체 리스트에 추가, 사용자함수 이용
        }
        return reviewDtoList;
    }

    // @Transactional 의 Transaction Test Code, 있는 경우 에러 발생시 Rollback 발생, 없는 경우 그냥 Save 됨.
    public List<ReviewDto> getReviewByDtoTt(){
        List<Review> reviewList = reviewRepository.findAll(); // 전체 받아옴
        reviewList.getFirst().setPoint(100);
        reviewRepository.save(reviewList.getFirst());
        List<ReviewDto> reviewDtoList = new ArrayList<>(); // 변환될 DTO 객체 리스트

        for (int i=0;i<reviewList.size();i++) {
            ReviewDto reviewDto = new ReviewDto(); // 변환될 DTO 객체 생성
            reviewDtoList.add(reviewDto.toReviewDtoFromReview(reviewList.get(i))); // 변환될 DTO 객체 리스트에 추가, 사용자함수 이용
            if (i==1) {
                throw new ResourceNotFoundException("Error", "", "");
            }
        }
        return reviewDtoList;
    }

}
