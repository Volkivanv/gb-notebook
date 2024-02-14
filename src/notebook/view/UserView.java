package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;
import notebook.util.UserValidator;

import java.util.Scanner;

import static notebook.util.Scanner.prompt;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {

        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = prompt("Введите команду: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    User u = userController.createUser();
                    userController.saveUser(u);
                    break;
                case READ:
                    String id = prompt("Идентификатор пользователя: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    System.out.println(userController.readAll());

                    break;
                case UPDATE:
                    String userId = prompt("Enter user id: ");
                    try {
                        User user = userController.readUser(Long.parseLong(userId));
                        System.out.println("Имеющиеся данные пользователя: "+user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Ваша редакция: ");
                    userController.updateUser(userId, userController.createUser());
                    break;
                case DELETE:

                    String userDelId = prompt("Enter user id: ");
                    try {
                        User user = userController.readUser(Long.parseLong(userDelId));
                        System.out.println("Подтвердите удаление: " + user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    String localCommand = prompt("Введите Y для подтверждение или N для отмены: ");
                    if(localCommand.equals("Y")) {
                        userController.deleteUser(userDelId);
                    }
                    break;
            }
        }
    }




}
