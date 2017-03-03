package ru.third.inno.task.models.dao;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import ru.third.inno.task.models.connector.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by yy on 26.02.17.
 */
@Repository
public class BoardDao implements iBoardDao {
    private static Logger logger = Logger.getLogger(BoardDao.class);

    private static final String SQL_DELETE_BOARD_BY_USER_AND_SUBJECT_ID =
            "DELETE FROM board WHERE person_id=? AND subject_id=?;";

    private static final String ADD_BOARD_BY_USER_AND_SUBJECT_ID =
            "INSERT INTO board (person_id, subject_id) VALUES (?, ?);";

    private static final String SQL_GET_BOARD_BY_USER_AND_SUBJECT_ID =
            "SELECT * FROM board WHERE person_id=? AND subject_id=?;";


    @Override
    public boolean addBoard(String person_id, String subject_id){
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOARD_BY_USER_AND_SUBJECT_ID)) {
            preparedStatement.setString(1, person_id);
            preparedStatement.setString(2, subject_id);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                logger.debug("inserted " + count);
                return true;
            }else{
                logger.debug("person_id: " + person_id + "  not inserted");
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return false;
    }

    @Override
    public boolean deleteBoard(String person_id, String subject_id){
        try(Connection connection = Connector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(BoardDao.SQL_DELETE_BOARD_BY_USER_AND_SUBJECT_ID)) {
            preparedStatement.setString(1, person_id);
            preparedStatement.setString(2, subject_id);
            int count = preparedStatement.executeUpdate();
            if(count > 0){
                BoardDao.logger.debug("inserted " + count);
                return true;
            }else{
                BoardDao.logger.debug(person_id + "  not deleted");
            }
        } catch (SQLException e) {
            BoardDao.logger.error(e);
        }
        return false;
    }

}
