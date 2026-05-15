package MVC.studentmanagement.model;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {
    private Integer id;
    private String uuid;
    private String name;
    private String email;
    private String password;
    private String profile;
}
