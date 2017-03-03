package ru.third.inno.task.models.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.third.inno.task.common.exception.SubjectDaoException;
import ru.third.inno.task.models.connector.ConnectionPool;
import ru.third.inno.task.models.connector.Connector;
import ru.third.inno.task.models.pojo.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bot on 23.02.17.
 */
@Repository
public class SubjectDao {

    private static Logger logger = Logger.getLogger(SubjectDao.class);
    private static final String SQL_GET_ALL_SUBJECTS =
            "SELECT * FROM subject;";

    private static final String SQL_GET_ALL_NOT_MY_SUBJECTS =
            "SELECT * FROM subject WHERE id NOT IN (SELECT subject.id FROM board LEFT JOIN subject ON subject.id = board.subject_id WHERE board.person_id = ";

    private static final String SQL_CREATE_SUBJECT =
            "INSERT INTO subject(`name`, `description`, `sphere`) " +
                    "VALUES(?, ?, ?)";
    private static final String SQL_UPDATE_SUBJECT =
            "UPDATE subject SET name=?, description=?, sphere=? WHERE id=?";

    private static String SQL_FIND_SUBJECT_ON_ID = "SELECT * FROM subject WHERE id=?";
    private static String SQL_DELETE_SUBJECT_BY_ID = "DELETE FROM subject WHERE id=?";
    private static String SQL_GET_ALL_SUBJECTS_BY_USER_ID = "SELECT * FROM board LEFT JOIN subject ON subject.id = board.subject_id WHERE board.person_id = ";

    public static boolean updateSubject(String id, String name, String description, String sphere){
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_SUBJECT)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, sphere);
            preparedStatement.setString(4, id);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug(id + "  not inserted");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    public static boolean deleteSubjectById(String id){
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_SUBJECT_BY_ID)) {
            preparedStatement.setString(1, id);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("deleted " + count);
                return true;
            }else{
                logger.debug(id + " failure fo delete this");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    public static Subject getSubjectById(int id) throws SubjectDaoException {
        logger.debug(id + " right?");
        Subject subject = null;
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_SUBJECT_ON_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                logger.debug("find (on result set)");
                subject = new Subject(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("sphere")
                );
            }else{
                logger.debug("id" + id + " not found");
            }
        } catch (SQLException e) {
            logger.error(" try connection(?) falture " + e);
            throw new SubjectDaoException();
        }
        return subject;

    }



    public static boolean createSubject(String name, String description, String sphere){
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_SUBJECT)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setString(3, sphere);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug(name + " not inserted");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    public static List<Subject> getAllSubjects() throws SubjectDaoException {
        System.out.println("");
        Subject subject;

        List<Subject> subjects = new ArrayList<>();
        try(Connection connection = Connector.getConnection();
            Statement statement = connection.createStatement()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_SUBJECTS);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                logger.debug("Select All subjects");

                subject = new Subject(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("sphere")
                );
                subjects.add(subject);


            }
        } catch (SQLException e) {
            logger.error(e);
            throw new SubjectDaoException();
        }

        return subjects;
    }

    public static List<Subject> getAllUserSubjects(int id) throws SubjectDaoException, SQLException {

        Subject subject;

        List<Subject> subjects = new ArrayList<>();

            logger.trace("new con pool");
            ConnectionPool connectionPool = new ConnectionPool();
            Connection conpool = connectionPool.retrieve();

            PreparedStatement preparedStatement = conpool.prepareStatement(SQL_GET_ALL_SUBJECTS_BY_USER_ID + id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                logger.debug("Select All subjects");

                subject = new Subject(
                        resultSet.getInt("subject_id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("sphere")
                );
                subjects.add(subject);

            }


        return subjects;
    }

    public static List<Subject> getAllNotUserSubjects(int id) throws SubjectDaoException {
        Subject subject;

        List<Subject> subjects = new ArrayList<>();
        try(Connection connection = Connector.getConnection();
            Statement statement = connection.createStatement()) {

            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_NOT_MY_SUBJECTS + id + ");");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                logger.debug("Select All not user subjects");

                subject = new Subject(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("sphere")
                );
                subjects.add(subject);


            }
        } catch (SQLException e) {
            logger.error(e);
            throw new SubjectDaoException();
        }

        return subjects;
    }
}