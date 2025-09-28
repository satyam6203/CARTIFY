package com.satyam.Ecommerce.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.Ecommerce.Domain.Product;
import com.satyam.Ecommerce.Domain.Review;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Request.CreateReviewRequest;
import com.satyam.Ecommerce.Response.ApiResponse;
import com.satyam.Ecommerce.Service.ProductSerice;
import com.satyam.Ecommerce.Service.ReviewService;
import com.satyam.Ecommerce.Service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    
    private final ReviewService reviewService;
    private final UserService userService;
    private final ProductSerice productService;

    @GetMapping("/products/{productId}/reviews")
    public ResponseEntity<List<Review>> getReviewsByProductId(
        @PathVariable Long productId
    ){
        List<Review> reviews=reviewService.getReviewByProductId(productId);
        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/products/{productId}/reviews")
    public ResponseEntity<Review> writeReview(
        @RequestBody CreateReviewRequest req,
        @PathVariable Long productId,
        @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Product product=productService.findProductById(productId);

        Review review=reviewService.createReview(req, user, product);
        return ResponseEntity.ok(review);
    }

    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateREview(
        @RequestBody CreateReviewRequest req,
        @PathVariable Long reviewId,
        @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user=userService.findUserByJwtToken(jwt);
        Review review=reviewService.updateReview(
            reviewId,
            req.getReviewText(),
            req.getReviewReating(),
            user.getId()
        );
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<ApiResponse> deleteReviews(
        @PathVariable Long reviewId,
        @RequestHeader("Authorization") String jwt
    ) throws Exception{
        User user=userService.findUserByEmail(jwt);
        reviewService.deleteReview(reviewId, reviewId);
        ApiResponse res=new ApiResponse();
        res.setMessage("Review is deleted SuccessFully");

        return ResponseEntity.ofNullable(res);
    }
}
