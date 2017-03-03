package ru.third.inno.task.models.dao;

import org.apache.log4j.Logger;
import ru.third.inno.task.models.connector.ConnectionPool;
import ru.third.inno.task.models.dao.interfaces.IutilsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by yy on 27.02.17.
 */

public class UtilsDao implements IutilsDao{
    private static Logger logger = Logger.getLogger(UtilsDao.class);

    private static final String SQL_GET_VALUE_BY_NAME =
            "SELECT value FROM utils WHERE name=?;";

    private static final String SET_VALUE_BY_NAME =
            "UPDATE utils SET value = ? WHERE name=?;";

    public static String getValueByName(String name){
        String  value = null;
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            Connection conpool = connectionPool.retrieve();
            PreparedStatement preparedStatement = conpool.prepareStatement(SQL_GET_VALUE_BY_NAME);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                logger.debug("find");

                value = resultSet.getString("value");
            }else{
                logger.debug(name + " not found");
            }

            connectionPool.putback(conpool);
        } catch (SQLException e) {
            logger.error("error while getting utils value " + e);
        }
    return value;
    }

    public static boolean setValueByName(String value, String name){
        try {
            ConnectionPool connectionPool = new ConnectionPool();
            Connection conpool = connectionPool.retrieve();
            PreparedStatement preparedStatement = conpool.prepareStatement(SET_VALUE_BY_NAME);
            preparedStatement.setString(1, value);
            preparedStatement.setString(2, name);

            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug(name +  " not updated");
            }

            connectionPool.putback(conpool);
        } catch (SQLException e) {
            logger.error("error while getting utils value " + e);
        }finally {

        }
        return false;
    }
}
