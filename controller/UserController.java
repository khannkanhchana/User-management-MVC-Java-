
package MVC.studentmanagement.controller;

import MVC.studentmanagement.model.dto.CreateUserDto;
import MVC.studentmanagement.model.dto.UpdateRequestDto;
import MVC.studentmanagement.model.dto.UserResponseDto;
import MVC.studentmanagement.model.service.UserService;
import MVC.studentmanagement.model.service.UserServiceImpl;
import MVC.studentmanagement.utils.APIResponseTemplate;

import java.time.LocalDate;
import java.util.List;

public class UserController {

    private final UserService userService = new UserServiceImpl();
    public APIResponseTemplate<UserResponseDto> createUser(CreateUserDto createUserDto){
        return APIResponseTemplate.<UserResponseDto>builder()
                .status(200)
                .message("Created user successfully✅")
                .data(userService.createUser(createUserDto))
                .timeStamp(LocalDate.now())
                .build();
    }
    public APIResponseTemplate<List<UserResponseDto>> getAllUsers(){
        return new APIResponseTemplate<>(
                200,
                "Get all user successfully✅",
                LocalDate.now(),
                userService.getAllUsers()
        );
    }
    public APIResponseTemplate<UserResponseDto> getUserByUuid(String uuid){
        return APIResponseTemplate.<UserResponseDto>builder()
                .status(200)
                .message("User found✅")
                .data(userService.getUserByUuid(uuid))
                .timeStamp(LocalDate.now())
                .build();
    }

    public APIResponseTemplate<List<UserResponseDto>> searchUserByName(String name){
        return APIResponseTemplate.<List<UserResponseDto>>builder()
                .status(200)
                .message("Search result successfully✅")
                .data(userService.searchUserByName(name))
                .timeStamp(LocalDate.now())
                .build();
    }

    public APIResponseTemplate<Integer> deleteUserByUuid(String uuid){
        return APIResponseTemplate.<Integer>builder()
                .status(200)
                .message("Deleted successfully✅")
                .data(userService.deleteUserByUuid(uuid))
                .timeStamp(LocalDate.now())
                .build();
    }

    public APIResponseTemplate<UserResponseDto> updateUserByUuid(String uuid, UpdateRequestDto dto){
        return APIResponseTemplate.<UserResponseDto>builder()
                .status(200)
                .message("Updated successfully✅")
                .data(userService.updateUserByUuid(uuid, dto))
                .timeStamp(LocalDate.now())
                .build();
    }

}
