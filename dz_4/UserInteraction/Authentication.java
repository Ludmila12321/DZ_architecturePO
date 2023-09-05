package dz_4.UserInteraction;

import dz_4.Core.UserProvider;
import dz_4.Models.User;

public class Authentication {
    public static User authentication(UserProvider userProvider, String login, int passHash) {
        var client = userProvider.getClientByName(login);
        if (client.getHashPassword() == passHash) {
            return client;
        } else {
            throw new RuntimeException("Ошибка аутентификации! Проверьте введённые данные!");
        }
    }
}
