package ru.third.inno.task.models.dao;

import ru.third.inno.task.common.exception.SubjectDaoException;
import ru.third.inno.task.models.pojo.Subject;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by yy on 03.03.17.
 */
public interface iSubjectDao {
    boolean updateSubject(String id, String name, String description, String sphere);

    boolean deleteSubjectById(String id);

    Subject getSubjectById(int id) throws SubjectDaoException;

    boolean createSubject(String name, String description, String sphere);

    List<Subject> getAllSubjects() throws SubjectDaoException;

    List<Subject> getAllUserSubjects(int id) throws SubjectDaoException, SQLException;

    List<Subject> getAllNotUserSubjects(int id) throws SubjectDaoException;
}
