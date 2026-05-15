package MVC.studentmanagement.model.dto;

public record CreateUserDto(
        String username,
        String email,
        String password
) {
}
