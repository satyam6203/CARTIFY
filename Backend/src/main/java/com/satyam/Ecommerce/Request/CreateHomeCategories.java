package com.satyam.Ecommerce.Request;

import lombok.Data;

@Data
public class CreateHomeCategories {
    private String categoryId;
    private String name;
    private String image;
}