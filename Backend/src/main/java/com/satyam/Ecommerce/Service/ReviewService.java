package com.satyam.Ecommerce.Service;

import java.util.List;

import com.satyam.Ecommerce.Domain.Product;
import com.satyam.Ecommerce.Domain.Review;
import com.satyam.Ecommerce.Entity.User;
import com.satyam.Ecommerce.Request.CreateReviewRequest;

public interface ReviewService {

    Review createReview(CreateReviewRequest req,
            User user,
            Product product);

    List<Review> getReviewByProductId(Long productId);
    Review updateReview(Long reviewId,String reviewText,double rating,Long userId)throws Exception;
    void deleteReview(Long reviewId,Long userId)throws Exception;
    Review getReviewById(Long reviewId)throws Exception;
}
