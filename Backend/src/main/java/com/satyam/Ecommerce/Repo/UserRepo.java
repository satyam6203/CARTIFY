package com.satyam.Ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.satyam.Ecommerce.Model.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {//here long is the primary key id
    User findByEmail(String email);
}
