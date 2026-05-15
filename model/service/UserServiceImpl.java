package MVC.studentmanagement.model.service;

import MVC.studentmanagement.mapper.UserMapper;
import MVC.studentmanagement.model.User;
import MVC.studentmanagement.model.UserDao;
import MVC.studentmanagement.model.UserDatabase;
import MVC.studentmanagement.model.dto.CreateUserDto;
import MVC.studentmanagement.model.dto.UpdateRequestDto;
import MVC.studentmanagement.model.dto.UserResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class UserServiceImpl implements UserService{
    private final UserDao userDao = new UserDao();
    private final UserMapper userMapper= new UserMapper();

//    private List<UserResponseDto> users = new ArrayList<>();

    @Override
    public UserResponseDto createUser(CreateUserDto createUserDto) {

        // create user
        // map from CreateUserDto to User4
        User user = userMapper.fromCreateUserDataToUser(createUserDto);
//        User user = new User(new Random().nextInt(9999999),
//                UUID.randomUUID().toString(),
//                createUserDto.username(), createUserDto.email(),
//                createUserDto.password(),"https://st.depositphotos.com/2218212/2938/i/450/depositphotos_29387653-stock-photo-facebook-profile.jpg");
        userDao.save(user);// save user to database
        // map from User to UserResponseDto
        UserResponseDto userResponseDto = userMapper.fromUserToUserResponseDto(user);
//        UserResponseDto userResponseDto =
//                new UserResponseDto(user.getUuid(),
//                        user.getName(),
//                        user.getEmail(),
//                        user.getProfile());

        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
//        List<User> users = userDao.findAll();


        return userDao.findAll().stream()
                .map(userMapper:: fromUserToUserResponseDto).toList();
    }

    @Override
    public UserResponseDto getUserByUuid(String uuid) {
        User user = userDao.findAll()
                .stream().filter(u->u.getUuid().equals(uuid))
                .findFirst().get();
        return userMapper.fromUserToUserResponseDto(user);
    }

//    @Override
//    public UserResponseDto updateUserByUuid(String uuid, UpdateRequestDto updateRequestDto) {
//        return null;
//    }
@Override
public UserResponseDto updateUserByUuid(String uuid, UpdateRequestDto dto) {

    User user = userDao.findAll()
            .stream()
            .filter(u -> u.getUuid().equals(uuid))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("User not found"));

    // ✅ update only these fields
    user.setName(dto.name());
    user.setEmail(dto.email());
    user.setPassword(dto.password());
    user.setProfile(dto.profile());

    return userMapper.fromUserToUserResponseDto(user);
}

    @Override
    public int deleteUserByUuid(String uuid) {
        User user = userDao.findAll()
                .stream().filter(u->u.getUuid().equals(uuid))
                .findFirst()
                .get();
        userDao.remove(user);

        return 1;
    }

//    @Override
//    public List<UserResponseDto> searchUserByName(String name) {
//        return List.of();
//    }
@Override
public List<UserResponseDto> searchUserByName(String name) {

    return userDao.findAll()
            .stream()
            .filter(u -> u.getName().toLowerCase().contains(name.toLowerCase()))
            .map(userMapper::fromUserToUserResponseDto)
            .toList();
}
}




//package MVC.studentmanagement.model.service;
//
//import MVC.studentmanagement.model.User;
//import MVC.studentmanagement.model.UserDao;
//import MVC.studentmanagement.model.dto.CreateUserDto;
//import MVC.studentmanagement.model.dto.UpdateRequestDto;
//import MVC.studentmanagement.model.dto.UserResponseDto;
//
//import java.util.List;
//import java.util.Random;
//import java.util.UUID;
//import java.util.stream.Collectors;
//
//public class UserServiceImpl implements UserService {
//
//    private final UserDao userDao = new UserDao();
//
//    @Override
//    public UserResponseDto createUser(CreateUserDto dto) {
//
//        User user = new User(
//                new Random().nextInt(999999),
//                UUID.randomUUID().toString(),
//                dto.username(),
//                dto.email(),
//                dto.password(),
//                "default-profile.png"
//        );
//
//        userDao.save(user);
//
//        return mapToDto(user);
//    }
//
//    @Override
//    public List<UserResponseDto> getAllUsers() {
//        return userDao.findAll()
//                .stream()
//                .map(this::mapToDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public UserResponseDto getUserByUuid(String uuid) {
//        return userDao.findAll()
//                .stream()
//                .filter(u -> u.getUuid().equals(uuid))
//                .map(this::mapToDto)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("User not found"));
//    }
//
//    @Override
//    public UserResponseDto updateUserByUuid(String uuid, UpdateRequestDto dto) {
//
//        User user = userDao.findAll()
//                .stream()
//                .filter(u -> u.getUuid().equals(uuid))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        user.setName(dto.name());
//        user.setEmail(dto.email());
//        user.setPassword(dto.password());
//        user.setProfile(dto.profile());
//
//        return mapToDto(user);
//    }
//
//    @Override
//    public int deleteUserByUuid(String uuid) {
//
//        User user = userDao.findAll()
//                .stream()
//                .filter(u -> u.getUuid().equals(uuid))
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        return userDao.remove(user);
//    }
//
//    @Override
//    public List<UserResponseDto> searchUserByName(String name) {
//        return userDao.findAll()
//                .stream()
//                .filter(u -> u.getName().toLowerCase().contains(name.toLowerCase()))
//                .map(this::mapToDto)
//                .collect(Collectors.toList());
//    }
//
//    private UserResponseDto mapToDto(User user) {
//        return new UserResponseDto(
//                user.getId(),
//                user.getUuid(),
//                user.getName(),
//                user.getEmail(),
//                user.getProfile()
//        );
//    }
//}