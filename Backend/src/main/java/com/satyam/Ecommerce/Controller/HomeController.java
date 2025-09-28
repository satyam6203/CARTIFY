package com.satyam.Ecommerce.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.satyam.Ecommerce.Domain.Home;
import com.satyam.Ecommerce.Domain.HomeCategory;
import com.satyam.Ecommerce.Service.HomeCategoryService;
import com.satyam.Ecommerce.Service.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/home")
public class HomeController {

    private final HomeCategoryService homeCategoryService;
    private final HomeService homeService;
    
    @PostMapping("/home/category")
    public ResponseEntity<Home> createHomeCategory(
        @RequestBody List<HomeCategory> homeCategories)
    {
        List<HomeCategory> categories=homeCategoryService.createCategories(homeCategories);
        Home home=homeService.createHomePageData(categories);
        return new ResponseEntity<>(home,HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/home-category")
    public ResponseEntity<List<HomeCategory>> getHomeCategory(){
        List<HomeCategory> categories=homeCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PatchMapping("/admin/home-category/{id}")
    public ResponseEntity<HomeCategory> updateHomeCategory(
        @PathVariable Long id,
        @RequestBody HomeCategory homeCategory
    ) throws Exception{
        HomeCategory updatedCategory=homeCategoryService.updateHomeCategory(homeCategory, id);
        return ResponseEntity.ok(updatedCategory);
    }


}
