package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
        // реализуйте алгоритм здесь
        public static void main(String[] args) {
          UserService userService = new UserServiceImpl();
           userService.createUsersTable();

          userService.saveUser("Dmitry", "Migal", (byte) 25);
          userService.saveUser("Anna", "Migal", (byte) 23);
            userService.saveUser("Vadim", "Dzagoev", (byte) 26);
            userService.saveUser("Alina", "Mitrofanova", (byte) 21);
           System.out.println(userService.getAllUsers());

         userService.cleanUsersTable();
         userService.dropUsersTable();
        }
    }
