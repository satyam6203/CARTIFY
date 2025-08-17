package com.satyam.Ecommerce.Repo;

import com.satyam.Ecommerce.Domain.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepo extends JpaRepository<VerificationCode,Long> {
    VerificationCode findByEmail(String email);
}
