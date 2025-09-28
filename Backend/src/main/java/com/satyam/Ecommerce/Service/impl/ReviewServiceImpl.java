package com.satyam.Ecommerce.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Domain.Product;
import com.satyam.Ecommerce.Domain.Review;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Repo.ReviewRepo;
import com.satyam.Ecommerce.Request.CreateReviewRequest;
import com.satyam.Ecommerce.Service.ReviewService;
import com.satyam.Ecommerce.Service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepo;
    private final UserService userService;

    @Override
    public Review createReview(CreateReviewRequest req, User user, Product product) {
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setReviewText(req.getReviewText());
        review.setRating(req.getReviewReating());
        review.setProductImage(req.getProductImage());

        product.getReviews().add(review);
        return reviewRepo.save(review);
    }

    @Override
    public List<Review> getReviewByProductId(Long productId) {
        return reviewRepo.findByProductId(productId);
    }

    @Override
    public Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception {
        Review review=getReviewById(reviewId);
        if(review.getUser().getId().equals(userId)){
           review.setReviewText(reviewText);
           review.setRating(rating);
           return reviewRepo.save(review);
        }
        throw new Exception("you can't update the review");
    }

    @Override
    public void deleteReview(Long reviewId, Long userId) throws Exception {
        Review review=getReviewById(reviewId);
        if(review.getUser().getId().equals(userId)){
            throw new  Exception("you can't delete this review");
        }
        else{
            reviewRepo.delete(review);
        }
    }

    @Override
    public Review getReviewById(Long reviewId) throws Exception {
        return reviewRepo.findById(reviewId).orElseThrow(() -> 
            new Exception("Not found with this index."));
    }
}
