package notebook.model.repository.impl;


import notebook.model.repository.Operation;

import notebook.util.mapper.impl.UserMapper;
import notebook.model.User;
import notebook.model.repository.GBRepository;

import java.io.*;
import java.util.*;

import static notebook.util.DBConnector.DB_PATH;

public class UserRepository implements GBRepository, Operation {
    private final UserMapper mapper;

    private final String filePath;

    private HashMap<Long, User> notebook = new HashMap<>();

    public UserRepository(String filePath) {
        this.filePath = filePath;
        this.mapper = new UserMapper();
        List<User> users = findAll();
        for(User u: users){
            notebook.put(u.getId(), u);
        }

    }

    @Override
    public List<User> findAll() {
        List<String> lines = readAll();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.toOutput(line));
        }
        return users;
    }

    @Override
    public List<User> getAll() {

        List<User> users = new ArrayList<>();
        for(User u: notebook.values()){
            users.add(u);
        }
        return users;
    }

    @Override
    public List<String> readAll() {
        List<String> lines = new ArrayList<>();
        try {
            File file = new File(filePath);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            if (line != null) {
                lines.add(line);
            }
            while (line != null) {
                // считываем остальные строки в цикле
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            }
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void saveAll(List<String> data) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (String line : data) {
                // запись всей строки
                writer.write(line);
                // запись по символам
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public User create(User user) {
        long max = 0L;
        for (User u : notebook.values()) {
            long id = u.getId();
            if (max < id){
                max = id;
            }
        }
        long next = max + 1;
        user.setId(next);
        notebook.put(user.getId(), user);
        return user;
    }


    @Override
    public Optional<User> findById(Long id) {
        //return Optional.empty();
        return Optional.ofNullable(notebook.get(id));
    }

    @Override
    public Optional<User> update(Long userId, User update) {
        try {
            User editUser = notebook.get(userId);
            if(!update.getFirstName().isEmpty()) {
                editUser.setFirstName(update.getFirstName());
            }
            if(!update.getLastName().isEmpty()) {
                editUser.setLastName(update.getLastName());
            }
            if(!update.getPhone().isEmpty()) {
                editUser.setPhone(update.getPhone());
            }
            notebook.replace(userId, editUser);


        } catch (NullPointerException e){
            System.out.println("User not found");
        }

        return Optional.of(update);
    }

    @Override
    public boolean delete(Long id) {
        User deletedUser = notebook.getOrDefault(id, null);
        if (deletedUser == null) return false;
        notebook.remove(id);
        return true;
    }

    private void write(List<User> users) {
        List<String> lines = new ArrayList<>();
        for (User u: users) {
            lines.add(mapper.toInput(u));
        }
        saveAll(lines);
    }

    public void write() {
        List<String> lines = new ArrayList<>();
        for (User u: notebook.values()) {
            lines.add(mapper.toInput(u));
        }
        saveAll(lines);
    }

}
