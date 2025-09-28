package com.satyam.Ecommerce.Service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.satyam.Ecommerce.Domain.HomeCategory;
import com.satyam.Ecommerce.Repo.HomeCategoryRepo;
import com.satyam.Ecommerce.Service.HomeCategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeCategoriesServiceImpl implements HomeCategoryService{

    private final HomeCategoryRepo homeCategoryRepo;

    @Override
    public HomeCategory createHomeCategory(HomeCategory homeCategory) {
        return homeCategoryRepo.save(homeCategory);
    }

    @Override
    public List<HomeCategory> createCategories(List<HomeCategory> homeCategories) {
        if(homeCategoryRepo.findAll().isEmpty()){
            return homeCategoryRepo.saveAll(homeCategories);
        }
        return homeCategoryRepo.findAll();
    }

    @Override
    public HomeCategory updateHomeCategory(HomeCategory category, Long id) throws Exception {
        HomeCategory existingCategory=homeCategoryRepo.findById(id).orElseThrow(()->
            new Exception("Category not found")
        );
        if(category.getImage() != null){
            existingCategory.setImage(category.getImage());
        }
        if(category.getCategoryId() != null){
            existingCategory.setCategoryId(category.getCategoryId());
        }
        return homeCategoryRepo.save(existingCategory);
    }

    @Override
    public List<HomeCategory> getAllCategories() {
        return homeCategoryRepo.findAll();
    }
    
}
