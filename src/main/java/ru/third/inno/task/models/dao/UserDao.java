package ru.third.inno.task.models.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.models.connector.VDBconn;
import ru.third.inno.task.models.pojo.User;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bot on 23.02.17.
 */
@Repository
public class UserDao implements iUserDao {

   private static Logger logger = Logger.getLogger(UserDao.class);
    private static final String SQL_FIND_USER =
            "SELECT * FROM person WHERE person.name=? AND person.password=?;";
    private static final String SQL_GET_ALL_USERS =
            "SELECT * FROM person;";
    private static final String SQL_CREATE_USER =
            "INSERT INTO person(`name`, `password`) " +
                    "VALUES(?, ?)";
    private static final String SQL_UPDATE_USER =
            "UPDATE person SET name=?, password=?, description=?, role=? WHERE id=?";

    private static final String SQL_UPDATE_USER_DESCRIPTION =
            "UPDATE person SET description=? WHERE id=?";

    private static final String SQL_UPDATE_USER_DESCRIPTION_AND_PASS =
            "UPDATE person SET description=?, password=? WHERE id=?";

    private static String SQL_GET_ID_BY_NAME = "SELECT id FROM person WHERE name=?";
    private static String SQL_GET_EADRESS_BY_NAME = "SELECT description FROM person WHERE name=?";
    private static String SQL_FIND_USER_ON_ID = "SELECT * FROM person WHERE id=?";
    private static String SQL_DELETE_USER_BY_ID = "DELETE FROM person WHERE id=?";

    @Override
    public boolean getUserByName(String name) throws UserDaoException {
        Connection connection = null;
        try {
            connection = VDBconn.getConn();
        } catch (NamingException e) {
            logger.error("naming exeption");
        } catch (SQLException e) {
            logger.error("sql error");
        }
        User user = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ID_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               logger.trace("already have this user");
               return false;
            }else{
                logger.debug("name " + name + " not found");
                return true;
            }
        } catch (SQLException e) {
            logger.error(" try connection(?) falture " + e);
            throw new UserDaoException();
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("cant close pool");
                }
            }
            connection = null;
        }
    }




    @Override
    public boolean updateUser(String id, String login, String password, String description, String role){

        Connection connection = null;
        try {
            connection = VDBconn.getConn();
        } catch (NamingException e) {
            logger.error("naming exeption");
        } catch (SQLException e) {
            logger.error("sql error");
        }

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, description);
            preparedStatement.setString(4, role);
            preparedStatement.setString(5, id);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug(id + " " + login + " " + password + " not inserted");
            }
        } catch (SQLException e) {
            logger.error(e);
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("cant close pool");
                }
            }
            connection = null;
        }
        return false;
    }

    @Override
    public boolean updateUserDescription(String id, String description, String pass){

        Connection connection = null;
        try {
            connection = VDBconn.getConn();
        } catch (NamingException e) {
            logger.error("naming exeption");
        } catch (SQLException e) {
            logger.error("sql error");
        }

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_DESCRIPTION_AND_PASS);
            preparedStatement.setString(1, description);
            preparedStatement.setString(2, pass);
            preparedStatement.setString(3, id);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug(id +  " not updated");
            }
        } catch (SQLException e) {
            logger.error(e);
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("cant close pool");
                }
            }
            connection = null;
        }
        return false;
    }

    @Override
    public boolean deleteUserById(String id){

        Connection connection = null;
        try {
            connection = VDBconn.getConn();
        } catch (NamingException e) {
            logger.error("naming exeption");
        } catch (SQLException e) {
            logger.error("sql error");
        }

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            preparedStatement.setString(1, id);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("deleted " + count);
                return true;
            }else{
                logger.debug(id + " failure fo delete");
            }
        } catch (SQLException e) {
            logger.error(e);
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("cant close pool");
                }
            }
            connection = null;
        }
        return false;
    }

    @Override
    public User getUserById(String id) throws UserDaoException {

        User user = null;

        Connection connection = null;
        try {
            connection = VDBconn.getConn();
        } catch (NamingException e) {
            logger.error("naming exeption");
        } catch (SQLException e) {
            logger.error("sql error");
        }


        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_ON_ID);
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                logger.debug("find (on result set)");
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getString("description"),
                        resultSet.getString("role")
                );
            }else{
                logger.debug("id" + id + " not found");
            }
        } catch (SQLException e) {
            logger.error(" try connection(?) falture " + e);
            throw new UserDaoException();
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("cant close pool");
                }
            }
            connection = null;
        }
        return user;
    }


    @Override
    public String getEadressByName(String name) throws UserDaoException {

        String eadress = null;

        Connection connection = null;
        try {
            connection = VDBconn.getConn();
        } catch (NamingException e) {
            logger.error("naming exeption");
        } catch (SQLException e) {
            logger.error("sql error");
        }


        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_EADRESS_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                logger.debug("find (on result set)");

                eadress = resultSet.getString("description");

                logger.debug(eadress);
            }else{
                logger.debug("name" + name + " not found");
            }
        } catch (SQLException e) {
            logger.error(" try connection(?) falture " + e);
            throw new UserDaoException();
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error("cant close pool");
                }
            }
            connection = null;
        }
        return eadress;

    }



    @Override
    public User getUserByLoginAndPassword(String login, String password) throws UserDaoException {

        User user = null;

        Connection connection = null;
        try {
            connection = VDBconn.getConn();
        } catch (NamingException e) {
            logger.error("naming exeption");
        } catch (SQLException e) {
            logger.error("sql error");
        }

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
               logger.debug("find");
                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                );
            }else{
                logger.debug(login + " " + password + " not found");
            }
        } catch (SQLException e) {
            throw new UserDaoException();
        }

        return user;
    }

    @Override
    public boolean registerUser(String login, String password) throws SQLException, NamingException {
        Connection connection = VDBconn.getConn();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_USER);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
             logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug(login + " " + password + " not inserted");
            }
        } catch (SQLException e) {
            logger.error(e);
        }

        return false;
    }

    @Override
    public List<User> getAllUsers() throws UserDaoException {
        User user = null;

        Connection conpool = null;
        try {
            conpool = VDBconn.getConn();
        } catch (NamingException e) {
            logger.error("naming exeption");
        } catch (SQLException e) {
            logger.error("sql error");
        }


        List<User> users = new ArrayList<>();
        try  {

            PreparedStatement preparedStatement = conpool.prepareStatement(SQL_GET_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){


                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getString("description"),
                        resultSet.getString("role")
                );
                users.add(user);


            }
        } catch (SQLException e) {
           logger.error(e);
            throw new UserDaoException();
        }finally {
            if (conpool != null){
                try {
                    conpool.close();
                } catch (SQLException e) {
                    logger.error("cant close pool");
                }
            }
            conpool = null;
        }

        return users;
    }




    @Override
    public List<User> getAlUsers() throws UserDaoException, SQLException, NamingException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        User user = null;

        Connection conpool = VDBconn.getConn();

        logger.trace("Hi after get conn");
        List<User> users = new ArrayList<>();
        try {

            PreparedStatement preparedStatement = conpool.prepareStatement(SQL_GET_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){


                user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getString("description"),
                        resultSet.getString("role")
                );
                users.add(user);
            }


        } catch (SQLException e) {
            logger.error(e);
            throw new UserDaoException();
        } finally {
           // connectionPool.putback(conpool);

            if (conpool != null){
                conpool.close();
            }
            conpool = null;
            logger.trace("Hi from finally block");
        }

        return users;
    }

}
