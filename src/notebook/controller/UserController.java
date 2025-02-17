package notebook.controller;

import notebook.model.User;
import notebook.model.repository.GBRepository;
import notebook.util.Scanner;
import notebook.util.UserValidator;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static notebook.util.Scanner.prompt;

public class UserController {
    private final GBRepository repository;


    public UserController(GBRepository repository) {
        this.repository = repository;

    }

    public void saveUser(User user) {
        repository.create(user);
    }

    public User readUser(Long userId) throws Exception {
//        List<User> users = repository.findAll();
//        for (User user : users) {
//            if (Objects.equals(user.getId(), userId)) {
//                return user;
//            }
//        }
        User u = repository.findById(userId).get();
        return u;
    }

    public List<User> readAll(){
        return repository.getAll();
    }

    public void updateUser(String userId, User update) {
        update.setId(Long.parseLong(userId));
        repository.update(Long.parseLong(userId), update);
    }

    public void deleteUser(String userId) {
       if(repository.delete(Long.parseLong(userId))){
           System.out.println("User "+userId + " is deleted");
       } else {
           System.out.println("User "+userId + " is NOT FOUND");
       }
    }

    public User createUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        UserValidator validator = new UserValidator();
        return validator.validate(new User(firstName, lastName, phone));
    }

    public void saveUsers(){
        repository.write();
    }


}
