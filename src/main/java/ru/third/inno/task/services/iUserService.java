package ru.third.inno.task.services;

import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.models.pojo.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yy on 03.03.17.
 */
public interface iUserService {
    boolean login(String login, String password) throws UserDaoException;

    boolean registration(String login, String password) throws SQLException, NamingException;

    List<User> getAllUsers() throws ClassNotFoundException, IllegalAccessException, InstantiationException;

    int getUserIdByLoginAndPassword(String login, String psw);

    User getUserByLoginAndPassword(String login, String password);

    User getUserById(String id) throws UserDaoException;

    boolean updateUser(String id, String login, String password, String description, String role);

    boolean updateUserDescription(String id, String description, String pass);
}
