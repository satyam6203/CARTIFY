package com.satyam.Ecommerce.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {

	private String message;
	private boolean status;
	private String otp;

	public ApiResponse(String message, boolean status) {
		this.message = message;
		this.status = status;
	}
}
