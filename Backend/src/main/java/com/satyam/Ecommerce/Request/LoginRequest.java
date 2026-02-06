package com.satyam.Ecommerce.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	
	private String email;
	private String password;
	private String otp;
	

}