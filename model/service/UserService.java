package MVC.studentmanagement.model.service;

import MVC.studentmanagement.model.dto.CreateUserDto;
import MVC.studentmanagement.model.dto.UpdateRequestDto;
import MVC.studentmanagement.model.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(CreateUserDto createUserDto);
    List<UserResponseDto> getAllUsers();
    UserResponseDto getUserByUuid(String uuid);
    UserResponseDto updateUserByUuid(String uuid ,
                                     UpdateRequestDto updateRequestDto);
    int deleteUserByUuid(String uuid);
    List<UserResponseDto> searchUserByName(String name);
}
