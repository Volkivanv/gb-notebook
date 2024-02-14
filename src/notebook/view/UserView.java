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
            String command = prompt("Введите команду (список доступных команд - HELP): ").toUpperCase();
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) {
                userController.saveUsers();
                return;
            }
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
                case SAVE:
                    userController.saveUsers();
                    break;
                case HELP:
                    System.out.println("Сохранить изменения: SAVE");
                    System.out.println("Прочитать пользователя по id: READ");
                    System.out.println("Добавить пользователя: CREATE");
                    System.out.println("Обновить данные пользователя: UPDATE");
                    System.out.println("Вывести список пользователей: LIST");
                    System.out.println("Удалить пользователя по id: DELETE");
                    System.out.println("Вызов справки: HELP");
                    System.out.println("Выход: EXIT");
                    break;
            }
        }
    }




}
