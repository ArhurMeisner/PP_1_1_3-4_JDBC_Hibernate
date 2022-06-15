package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Кирилл", "Лалка", (byte) 69);
        userService.saveUser("Александрдрдрдр", "Есть", (byte) 42);
        userService.saveUser("Супа", "Кат", (byte) 8);
        userService.saveUser("Виктор", "Бухера", (byte) 27);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
