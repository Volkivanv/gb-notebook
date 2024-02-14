package notebook;

import notebook.controller.UserController;

import notebook.model.repository.GBRepository;
import notebook.model.repository.impl.UserRepository;
import notebook.view.UserView;
import notebook.view.ViewRunner;

import static notebook.util.DBConnector.DB_PATH;
import static notebook.util.DBConnector.createDB;

public class Main {
    public static void main(String[] args) {
        ViewRunner viewRunner = new ViewRunner();
        viewRunner.run();

    }
}
