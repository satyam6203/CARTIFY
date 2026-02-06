package com.satyam.Ecommerce.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Exceptions.ReviewNotFoundException;
import com.satyam.Ecommerce.Exceptions.UnauthorizedException;
import com.satyam.Ecommerce.Model.Product;
import com.satyam.Ecommerce.Model.Review;
import com.satyam.Ecommerce.Model.User;
import com.satyam.Ecommerce.Repo.ReviewRepo;
import com.satyam.Ecommerce.Request.CreateReviewRequest;
import com.satyam.Ecommerce.Service.ReviewService;
import com.satyam.Ecommerce.Service.UserService;
import org.springframework.security.core.AuthenticationException;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepo reviewRepository;


    @Override
    public Review createReview(CreateReviewRequest req,
                               User user,
                               Product product) {
        Review newReview = new Review();

        newReview.setReviewText(req.getReviewText());
        newReview.setRating(req.getReviewRating());
        newReview.setProductImages(req.getProductImages());
        newReview.setUser(user);
        newReview.setProduct(product);

        product.getReviews().add(newReview);

        return reviewRepository.save(newReview);
    }

    @Override
    public List<Review> getReviewsByProductId(Long productId) {
        return reviewRepository.findReviewsByProductId(productId);
    }


    @Override
    public Review updateReview(Long reviewId,
                               String reviewText,
                               double rating,
                               Long userId) throws ReviewNotFoundException, AuthenticationException {
        Review review=reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review Not found"));

        if (!review.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You do not have permission to update this review");
        }

        review.setReviewText(reviewText);
        review.setRating(rating);
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(Long reviewId,Long userId) throws ReviewNotFoundException,
            AuthenticationException {
        Review review=reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review Not found"));
        if (!review.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You do not have permission to update this review");
        }
        reviewRepository.delete(review);
    }

}
