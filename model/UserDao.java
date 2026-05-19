package MVC.studentmanagement.model;


import MVC.studentmanagement.utils.DataConnectionConfigure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// repository or DAO - Data access object
public class UserDao {
    public List<User> findAll() {
        String sql = "SELECT * FROM users ORDER BY id DESC";
        List<User> users = new ArrayList<>();

        try (Connection connection = DataConnectionConfigure.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("uuid"),
                        rs.getString("user_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("profile")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException("FindAll failed: " + e.getMessage());
        }

        return users;
    }


//    public List<User>  findAll(){
//        String sql = "SELECT * FROM users";
//        List<User> users = new ArrayList<>();
//        try(Connection connection = DataConnectionConfigure.getConnection()){
//            Statement statement = connection.createStatement();
//            boolean isExecuted = statement.execute(sql);
//            ResultSet resultSet = statement.getResultSet();
//            while (resultSet.next()){;
//                int id = resultSet.getInt("id");
//                String uuid = resultSet.getString("uuid");
//                String userName = resultSet.getString("user_name");
//                String email  =resultSet.getString("email");
//                String password = resultSet.getString("password");
//                String profile  = resultSet.getString("profile");
//                User user = new User(id,uuid,userName,email,password,profile);
//                // add user object to list
//                users.add(user);
//            }
//        }catch (Exception exception){
//            System.out.println("Connection failed");
//        }
//        return users;
//    }

    public boolean remove(User user) {
        String sql = "DELETE FROM users WHERE uuid = ?";

        try (Connection connection = DataConnectionConfigure.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getUuid());

            int rows = ps.executeUpdate();

            if (rows == 0) {
                throw new RuntimeException("User not found with UUID: " + user.getUuid());
            }

            return true;

        } catch (Exception e) {
            throw new RuntimeException("Delete failed: " + e.getMessage());
        }
    }

//    public User update(User uu) {
//        String sql = """
//        UPDATE users
//        SET user_name = ?, email = ?, password = ?, profile = ?
//        WHERE TRIM(uuid) = TRIM(?)
//    """;
//
//        try (Connection connection = DataConnectionConfigure.getConnection();
//             PreparedStatement ps = connection.prepareStatement(sql)) {
//
//            String uuid = uu.getUuid();
//
//            System.out.println("🔥 RAW UUID = [" + uuid + "]");
//            System.out.println("🔥 LENGTH = " + (uuid == null ? 0 : uuid.length()));
//
//            if (uuid == null || uuid.isBlank()) {
//                throw new RuntimeException("UUID is EMPTY from input");
//            }
//
//            ps.setString(1, uu.getName());
//            ps.setString(2, uu.getEmail());
//            ps.setString(3, uu.getPassword());
//            ps.setString(4, uu.getProfile());
//            ps.setString(5, uuid.trim());
//
//            int rows = ps.executeUpdate();
//
//            System.out.println("🔥 ROWS UPDATED = " + rows);
//
//            if (rows == 0) {
//                throw new RuntimeException("NO MATCH FOUND in DB for UUID: " + uuid);
//            }
//
//            return uu;
//
//        } catch (Exception e) {
//            throw new RuntimeException("Update failed: " + e.getMessage());
//        }
//    }

    public User update(User uu) {

        String sql = """
            UPDATE users
            SET user_name = ?, email = ?, password = ?, profile = ?
            WHERE TRIM(uuid) = TRIM(?)
        """;

        try (Connection connection = DataConnectionConfigure.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // 🔥 SAFETY CHECK
            if (uu.getUuid() == null || uu.getUuid().isBlank()) {
                throw new RuntimeException("UUID is NULL or EMPTY (check Service/Controller)");
            }

            String uuid = uu.getUuid().trim();

            System.out.println("DEBUG UUID = [" + uuid + "]");

            ps.setString(1, uu.getName());
            ps.setString(2, uu.getEmail());
            ps.setString(3, uu.getPassword());
            ps.setString(4, uu.getProfile());
            ps.setString(5, uuid);

            int rows = ps.executeUpdate();

            System.out.println("DEBUG UPDATE ROWS = " + rows);

            if (rows == 0) {
                throw new RuntimeException("User not found in DB with UUID: " + uuid);
            }

            return uu;

        } catch (Exception e) {
            throw new RuntimeException("Update failed: " + e.getMessage());
        }
    }



    public User save(User user){
        String sql = """
                INSERT  INTO users(uuid, user_name, email, password, profile)
                VALUES (?,?,?,?,?)
                """;
        try(Connection connection = DataConnectionConfigure.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // set value for replace ? symbol in SQL Statement
            preparedStatement.setString(1,user.getUuid());
            preparedStatement.setString(2,user.getName());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getProfile());
            //
            int rowAffected = preparedStatement.executeUpdate();
            if(rowAffected<=0){
                throw new RuntimeException("Failed to insert new data into table users");
            }
            return user;
        }catch (Exception exception){
            System.out.println("Error during insert new user");
        }
        return null;
    }
    public List<User> searchUserByName(String name) {

        String sql = "SELECT * FROM users WHERE user_name LIKE ?";
        List<User> users = new ArrayList<>();

        try (Connection connection = DataConnectionConfigure.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("uuid"),
                        rs.getString("user_name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("profile")
                ));
            }

        } catch (Exception e) {
            throw new RuntimeException("Search failed: " + e.getMessage());
        }

        return users;
    }


    }

