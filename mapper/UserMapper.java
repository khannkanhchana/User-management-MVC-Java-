package MVC.studentmanagement.mapper;

import MVC.studentmanagement.model.User;
import MVC.studentmanagement.model.dto.CreateUserDto;
import MVC.studentmanagement.model.dto.UserResponseDto;

import java.util.Random;
import java.util.UUID;

public class UserMapper {
    public User fromCreateUserDataToUser(CreateUserDto createUserDto){
        return new User(new Random().nextInt(99999999),
                UUID.randomUUID().toString(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKaiKiPcLJj7ufrj6M2KaPwyCT4lDSFA5oog&s"

                );
    }
    public UserResponseDto fromUserToUserResponseDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getUuid(),
                user.getName(),
                user.getEmail(),
                user.getProfile()
        );
    }

}
