package ru.third.inno.task.services;

import org.apache.log4j.Logger;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.models.dao.UserDao;
import ru.third.inno.task.models.pojo.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by bot on 23.02.17.
 */
public class UserService {
    private static Logger logger = Logger.getLogger(UserService.class);

    public static boolean login(String login, String password) throws UserDaoException {
        if (UserDao.getUserByLoginAndPassword(login, password) != null) {
            return true;
        }
        return false;
    }

    public static boolean registration(String login, String password) throws SQLException, NamingException {
        return UserDao.registerUser(login, password);
    }

    public static List<User> getAllUsers() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            return UserDao.getAlUsers();
        } catch (UserDaoException e) {
            logger.error("Cant get all users in UserServlet");
        } catch (SQLException e) {
            logger.trace("cpe sql: " + e);
        } catch (NamingException e) {
            logger.error("cpe naming: " + e);
        }
        return null;
    }


    public static int getUserIdByLoginAndPassword(String login, String psw){
        User user = null;
        try {
            user = UserDao.getUserByLoginAndPassword(login, psw);
        } catch (UserDaoException e) {
            logger.error("Cant get 1 user in UService");
        }

        return user.getId();
    }

    public static User getUserByLoginAndPassword(String login, String password){
        try {
            return UserDao.getUserByLoginAndPassword(login, password);
        } catch (UserDaoException e) {
            logger.error("Error on getting user by pass and login ");
        }
        return null;
    }

    public static User getUserById(String id) throws UserDaoException {
        return  UserDao.getUserById(id);
    }

    public  static boolean updateUser(String id, String login, String password, String description, String role){
        return UserDao.updateUser(id, login, password, description, role);
    }

    public  static boolean updateUserDescription(String id,String description, String pass){
        return UserDao.updateUserDescription(id, description, pass);
    }

}
