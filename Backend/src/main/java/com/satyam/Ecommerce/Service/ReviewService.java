package com.satyam.Ecommerce.Service;

import java.util.List;

import com.satyam.Ecommerce.Exceptions.ReviewNotFoundException;
import com.satyam.Ecommerce.Model.Product;
import com.satyam.Ecommerce.Model.Review;
import com.satyam.Ecommerce.Model.User;
import com.satyam.Ecommerce.Request.CreateReviewRequest;
import com.stripe.exception.AuthenticationException;

public interface ReviewService {

    Review createReview(CreateReviewRequest req,
                        User user,
                        Product product);

    List<Review> getReviewsByProductId(Long productId);

    Review updateReview(Long reviewId,
                        String reviewText,
                        double rating,
                        Long userId) throws ReviewNotFoundException, AuthenticationException;


    void deleteReview(Long reviewId, Long userId) throws ReviewNotFoundException, AuthenticationException;

}
