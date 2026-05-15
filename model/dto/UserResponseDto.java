//package MVC.studentmanagement.model.dto;
//
//public record UserResponseDto(
//        String name,
//        String email,
//        String password,
//        String profile
//
//) {
//}

package MVC.studentmanagement.model.dto;

public record UserResponseDto(
        Integer id,
        String uuid,
        String name,
        String email,
        String profile
) {

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getProfile() {
        return profile;
    }

    public Integer getId() {
        return id;
    }
}
