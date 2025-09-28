package com.satyam.Ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Domain.HomeCategory;

@Repository
public interface HomeCategoryRepo extends JpaRepository<HomeCategory,Long>{
    
}
