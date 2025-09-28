package com.satyam.Ecommerce.Request;

import java.util.List;
import lombok.Data;

@Data
public class CreateReviewRequest {
    private String reviewText;
    private double reviewReating;
    private List<String> productImage;
}
