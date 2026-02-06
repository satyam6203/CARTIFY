package com.satyam.Ecommerce.mapper;

import com.satyam.Ecommerce.Model.User;
import com.satyam.Ecommerce.dto.UserDto;

public class UserMapper {

    public static UserDto toUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
