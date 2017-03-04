package ru.third.inno.task.models.dao;

import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.models.pojo.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by yy on 03.03.17.
 */
public interface iUserDao {
    boolean getUserByName(String name) throws UserDaoException;

    boolean updateUser(String id, String login, String password, String description, String role);

    boolean updateUserDescription(String id, String description, String pass);

    boolean deleteUserById(String id);

    User getUserById(String id) throws UserDaoException;

    String getEadressByName(String name) throws UserDaoException;

    User getUserByLoginAndPassword(String login, String password) throws UserDaoException;

    boolean registerUser(String login, String password) throws SQLException, NamingException;

    List<User> getAllUsers() throws UserDaoException;

    List<User> getAlUsers() throws UserDaoException, SQLException, NamingException, ClassNotFoundException, InstantiationException, IllegalAccessException;
}
