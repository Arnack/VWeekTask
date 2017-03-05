package ru.third.inno.task.models.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.third.inno.task.common.exception.TaskDaoException;
import ru.third.inno.task.common.exception.UserDaoException;
import ru.third.inno.task.models.connector.Connector;
import ru.third.inno.task.models.connector.VDBconn;
import ru.third.inno.task.models.pojo.Task;
import ru.third.inno.task.models.pojo.User;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bot on 23.02.17.
 */
@Repository
public class TaskDao implements iTaskDao {

    private static Logger logger = Logger.getLogger(TaskDao.class);
    private static final String SQL_GET_ALL_TASKS =
            "SELECT * FROM task WHERE person_id = ";
    private static final String SQL_CREATE_TASK =
            "INSERT INTO task (`name`, `description`, `person_id`) " +
                    "VALUES(?, ?, ?) ";

    private static final String SQL_UPDATE_TASK =
            "UPDATE task SET name=?, description=? WHERE id=? AND person_id=?;";

    private static final String SQL_UPDATE_TASK_READYNESS =
            "UPDATE task SET isdone=? WHERE id=? AND person_id=?";

    private static String SQL_DELETE_TASK_BY_USERID_AND_ID = "DELETE FROM task WHERE person_id=? AND id=?";


    private Statement statement;
    private Connection conn;

    @Override
    public boolean updateTaskReadyness(String userId, String taskId, String isdone){
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_TASK_READYNESS)) {
            preparedStatement.setString(1, isdone);
            preparedStatement.setString(2, taskId);
            preparedStatement.setString(3, userId);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug(taskId +  " not updated");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public boolean deleteTaskById(String userId, String id){
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TASK_BY_USERID_AND_ID)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setString(2, id);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("deleted " + count);
                return true;
            }else{
                logger.debug(id + " failure fo delete");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public boolean newTask(String name, String description, String id){
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_TASK)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, id);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug(name + " " + description + " not inserted");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public List<Task> getAllTasks(String id) throws TaskDaoException {
        Task task = null;

        List<Task> tasks = new ArrayList<>();
        try(Connection connection = Connector.getConnection();
            Statement statement = connection.createStatement()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_TASKS + id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){

                task = new Task(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("person_id"),
                        resultSet.getInt("subject_id"),
                        resultSet.getInt("isdone")
                );
                tasks.add(task);
                logger.trace("after adding task: in get all in dao " + task);


            }
        } catch (SQLException e) {
            logger.error(e);
            throw new TaskDaoException();
        }

        return tasks;
    }

    @Override
    public boolean editTask(String id, String person_id, String name, String description) {
        Connection conpool = null;
        try {
            conpool = VDBconn.getConn();
        } catch (NamingException e) {
            logger.error("naming exeption");
        } catch (SQLException e) {
            logger.error("sql error");
        }


        try  {
            PreparedStatement preparedStatement = conpool.prepareStatement(SQL_UPDATE_TASK);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, id);
            preparedStatement.setString(4, person_id);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug(id + " " + name + " " + description + " not inserted");
                return false;
            }
        } catch (SQLException e) {
            logger.error(e);
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

        return false;

    }
}