package com.satyam.Ecommerce.Service;

import java.util.List;

import com.satyam.Ecommerce.Domain.HomeCategory;

public interface HomeCategoryService {
    HomeCategory createHomeCategory(HomeCategory homeCategory);
    List<HomeCategory> createCategories(List<HomeCategory> homeCategories);
    HomeCategory updateHomeCategory(HomeCategory homeCategory,Long id)throws Exception;
    List<HomeCategory> getAllCategories();
}
