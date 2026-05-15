package MVC.studentmanagement.model;


import java.util.List;

// repository or DAO - Data access object
public class UserDao {
    public List<User> findAll(){
        return UserDatabase .users;
    }
    public int remove(User user){
        UserDatabase.users.remove(user);
        return 1;
    }

    public User update(User uu){
        User user = UserDatabase.users.stream()
                .filter(u->u.getId().equals(uu.getId()))
                .findFirst().get();
        if (user==null){
            throw new RuntimeException("User is not found");
        }
        UserDatabase.users.remove(user);
        // update
        user.setName((uu.getName()));
        user.setEmail((uu.getEmail()));
        user.setPassword((uu.getPassword()));
        user.setProfile((uu.getProfile()));
        UserDatabase.users.add(user);
        return user;


    }
    public User save(User user){
        UserDatabase.users.add(user);
        return user;
    }

}
