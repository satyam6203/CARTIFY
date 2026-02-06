package com.satyam.Ecommerce.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.satyam.Ecommerce.Model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {
	PasswordResetToken findByToken(String token);
}

