package com.satyam.Ecommerce.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Model.Category;


@Repository
public interface CategoryRepo extends JpaRepository<Category,Long>{
    Category findByCategoryId(String categoryId);
    List<Category>findByLevel(Integer level);
}
