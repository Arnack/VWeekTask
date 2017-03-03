package ru.third.inno.task.models.dao;

import org.apache.log4j.Logger;
import ru.third.inno.task.common.exception.TaskDaoException;
import ru.third.inno.task.models.connector.Connector;
import ru.third.inno.task.models.pojo.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bot on 23.02.17.
 */
public class TaskDao {

    private static Logger logger = Logger.getLogger(TaskDao.class);
    private static final String SQL_GET_ALL_TASKS =
            "SELECT * FROM task WHERE person_id = ";
    private static final String SQL_CREATE_TASK =
            "INSERT INTO task (`name`, `description`, `person_id`) " +
                    "VALUES(?, ?, ?) ";

    private static final String SQL_UPDATE_TASK_READYNESS =
            "UPDATE task SET isdone=? WHERE id=? AND person_id=?";

    private static String SQL_DELETE_TASK_BY_USERID_AND_ID = "DELETE FROM task WHERE person_id=? AND id=?";


    private Statement statement;
    private Connection conn;

    public static boolean updateTaskReadyness(String userId, String taskId, String isdone){
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

    public static boolean deleteTaskById(String userId, String id){
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

    public static boolean newTask(String name, String description, String id){
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

    public static List<Task> getAllTasks(String id) throws TaskDaoException {
        System.out.println("");
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
}