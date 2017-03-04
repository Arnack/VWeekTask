package ru.third.inno.task.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.models.dao.UserDao;
import ru.third.inno.task.models.dao.iUserDao;
import ru.third.inno.task.models.pojo.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by bot on 23.02.17.
 */
@Service
public class UserService implements iUserService {
    private static Logger logger = Logger.getLogger(UserService.class);

    iUserDao userDao;

    @Autowired
    public void setUserDao(iUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean login(String login, String password) throws UserDaoException {
        if (userDao.getUserByLoginAndPassword(login, password) != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean registration(String login, String password) throws SQLException, NamingException {
        return userDao.registerUser(login, password);
    }

    @Override
    public List<User> getAllUsers() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        try {
            return userDao.getAlUsers();
        } catch (UserDaoException e) {
            logger.error("Cant get all users in UserServlet");
        } catch (SQLException e) {
            logger.trace("cpe sql: " + e);
        } catch (NamingException e) {
            logger.error("cpe naming: " + e);
        }
        return null;
    }


    @Override
    public int getUserIdByLoginAndPassword(String login, String psw){
        User user = null;
        try {
            user = userDao.getUserByLoginAndPassword(login, psw);
        } catch (UserDaoException e) {
            logger.error("Cant get 1 user in UService");
        }

        return user.getId();
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password){
        try {
            return userDao.getUserByLoginAndPassword(login, password);
        } catch (UserDaoException e) {
            logger.error("Error on getting user by pass and login ");
        }
        return null;
    }

    @Override
    public User getUserById(String id) throws UserDaoException {
        return  userDao.getUserById(id);
    }

    @Override
    public boolean updateUser(String id, String login, String password, String description, String role){
        return userDao.updateUser(id, login, password, description, role);
    }

    @Override
    public boolean updateUserDescription(String id, String description, String pass){
        return userDao.updateUserDescription(id, description, pass);
    }

}
