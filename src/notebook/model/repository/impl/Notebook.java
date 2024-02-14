package notebook.model.repository.impl;

import notebook.model.User;

import java.util.Iterator;
import java.util.List;

public class Notebook implements Iterable{
    private List<User> users;

    private int index = 0;

    public Notebook(String filePath){
        UserRepository repos = new UserRepository(filePath);
        users = repos.findAll();
    }
    public Notebook(List<User> users){
        this.users = users;
    }

    public void setUsers(List<User> users){
        this.users =users;
    }

    public List<User> getUsers(){
        return users;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {
                return index < users.size();
            }

            @Override
            public Object next() {
                return users.get(index++);
            }
        };
    }
}
