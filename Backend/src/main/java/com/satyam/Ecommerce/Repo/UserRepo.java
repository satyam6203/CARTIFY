package com.satyam.Ecommerce.Repo;

import com.satyam.Ecommerce.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {//here long is the primary key id
    User findByEmail(String email);
}
