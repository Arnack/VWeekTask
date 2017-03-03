package ru.third.inno.task.models.dao;

import ru.third.inno.task.models.connector.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by yy on 03.03.17.
 */
public interface iBoardDao {
    boolean deleteBoard (String person_id, String subject_id);

    boolean addBoard(String person_id, String subject_id);
}
