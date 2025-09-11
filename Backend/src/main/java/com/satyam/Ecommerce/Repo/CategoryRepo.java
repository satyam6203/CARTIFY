package com.satyam.Ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Domain.Category;
@Repository
public interface CategoryRepo extends JpaRepository<Category,Long>{
    Category findByCategoryId(String categoryId);
}
