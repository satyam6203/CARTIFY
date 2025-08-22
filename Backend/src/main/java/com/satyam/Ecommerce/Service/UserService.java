package com.satyam.Ecommerce.Service;

import com.satyam.Ecommerce.Entity.User;

public interface UserService {
    User findUserByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;
}
