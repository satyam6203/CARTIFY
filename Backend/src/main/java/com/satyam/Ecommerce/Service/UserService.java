package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Exceptions.UserException;
import com.satyam.Ecommerce.Model.User;

public interface UserService {

	public User findUserProfileByJwt(String jwt) throws UserException;
	
	public User findUserByEmail(String email) throws UserException;
}
