package MVC.studentmanagement.view;

import MVC.studentmanagement.controller.UserController;
import MVC.studentmanagement.model.dto.CreateUserDto;
import MVC.studentmanagement.model.dto.UpdateRequestDto;
import MVC.studentmanagement.model.dto.UserResponseDto;
import MVC.studentmanagement.utils.APIResponseTemplate;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import java.util.List;
import java.util.Scanner;

public class UI {

    private static final Scanner scanner = new Scanner(System.in);
    private final static UserController userController = new UserController();
    private  static  void  thumbnail(){
        System.out.println(
                """
                        =================User Management System=========
                        1. Create User 
                        2. Search User By UUID
                        3. Search User By Name
                        4. Delete User By UUID
                        5. Update User By UUID
                        6. List All Users
                        0. Exit
                        
                        """
        );
    }
    private static void printUserTable(List<UserResponseDto> users) {

        Table table = new Table(5, BorderStyle.UNICODE_BOX, ShownBorders.ALL);
        CellStyle center = new CellStyle(CellStyle.HorizontalAlign.center);

        table.addCell("ID",center);
        table.addCell("UUID",center);
        table.addCell("NAME",center);
        table.addCell("EMAIL",center);
        table.addCell("PROFILE",center);




        for (UserResponseDto user : users) {

            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getUuid());
            table.addCell(user.getName());
            table.addCell(user.getEmail());
            table.addCell(user.getProfile() == null ? "-" : user.getProfile());
        }

        System.out.println(table.render());
    }
    private static int insertOption(){
        System.out.print("[+] Insert your option: ");
        return new Scanner(System.in).nextInt();
    }
    public static void getRendered(){
        while (true){
            thumbnail();
            System.out.println("--");
            switch (insertOption()){
                case 1->{

                    System.out.println("😡 Create user");
                    System.out.print("[+] Insert name: ");
                    String name = new Scanner(System.in).nextLine();
                    System.out.print("[+] Insert email: ");
                    String email = new Scanner(System.in).nextLine();
                    System.out.print("[+] Insert password: ");
                    String password = new Scanner(System.in).nextLine();
                    //
                    CreateUserDto createUserDto = new CreateUserDto(name,email,password);
                    APIResponseTemplate<UserResponseDto> createdUser = userController.createUser(createUserDto);
                    System.out.println(createdUser);
                }
                case 2 -> {
                    System.out.println("🔍 Get user By UUID");
                    System.out.print("[+] Input UUID: ");
                    String uuid = scanner.nextLine();

                    try {
                        System.out.println(userController.getUserByUuid(uuid));
                    } catch (Exception e) {
                        System.out.println("❌ User not found!");
                    }
                }
                case 3 -> {
                    System.out.println("🙋‍♂️ Get user By Name");
                    System.out.print("[+] Input name: ");
                    String name = scanner.nextLine();

                    try {
                        APIResponseTemplate<List<UserResponseDto>> response =
                                userController.searchUserByName(name);

                        List<UserResponseDto> users = response.getData();

                        if (users == null || users.isEmpty()) {
                            System.out.println("❌ No user found");
                        } else {
                            printUserTable(users);
                        }

                    } catch (Exception e) {
                        System.out.println("❌ Error while searching user");
                    }
                }
                case 4 -> {
                    // delete user by uuid if delete give message to ask user want to delete or not
                    System.out.println("🗑️ Delete user By UUID");
                    System.out.print("[+] Input UUID: ");
                    String uuid = scanner.nextLine();

                    System.out.print("Are you sure? (y/n): ");
                    String confirm = scanner.nextLine();

                    if(confirm.equalsIgnoreCase("y")){
                        try {
                            userController.deleteUserByUuid(uuid);
                            System.out.println("✅ Deleted successfully");
                        } catch (Exception e) {
                            System.out.println("❌ User not found");
                        }
                    }


                }
                case 5-> {
                    System.out.println("🧸 Update user By UUID");
                    System.out.print("[+] Input UUID: ");
                    String uuid = scanner.nextLine();

                    try {

                        APIResponseTemplate<UserResponseDto> response =
                                userController.getUserByUuid(uuid);

                        UserResponseDto oldUser = response.getData();

                        System.out.println("Press ENTER to keep old value");

                        System.out.print("[+] New name (" + oldUser.getName() + "): ");
                        String name = scanner.nextLine();
                        if (name.isBlank()) {
                            name = oldUser.getName();
                        }

                        System.out.print("[+] New email (" + oldUser.getEmail() + "): ");
                        String email = scanner.nextLine();
                        if (email.isBlank()) {
                            email = oldUser.getEmail();
                        }

                        System.out.print("[+] New password (hidden): ");
                        String password = scanner.nextLine();
                        password = password.isBlank() ? "" : password;

                        System.out.print("[+] New profile (" + oldUser.getProfile() + "): ");
                        String profile = scanner.nextLine();
                        if (profile.isBlank()) {
                            profile = oldUser.getProfile();
                        }

                        UpdateRequestDto dto = new UpdateRequestDto(name, email, password, profile);

                        System.out.println(userController.updateUserByUuid(uuid, dto));

                    } catch (Exception e) {
                        System.out.println("❌ User not found");
                    }
                }



                case 6->{
                    try {
                        System.out.println("👨‍👩‍👧‍👦 List All user");
                        APIResponseTemplate<List<UserResponseDto>> response =
                                userController.getAllUsers();

                        List<UserResponseDto> users = response.getData();

                        if (users == null || users.isEmpty()) {
                            System.out.println("❌ No users available");
                        } else {
                            printUserTable(users);
                        }

                    } catch (Exception e) {
                        System.out.println("❌ Error while fetching users");
                    }
                }
                case 0->{
                    System.out.println("System closed...");
                    try{
                        Thread.sleep(100);
                    }catch (Exception ignore){}
                }
                default -> System.out.println("No Invalid option");
            }
        }
    }
}
