package ru.third.inno.task.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.third.inno.task.common.exception.SubjectDaoException;
import ru.third.inno.task.models.dao.SubjectDao;
import ru.third.inno.task.models.pojo.Subject;

import java.util.List;

/**
 * Created by yy on 03.03.17.
 */
public interface iSubjectService {


    boolean create(String name, String description, String sphere);

    List<Subject> getAllSubjects();

    List<Subject> getAllUserSubjects(int id);

    List<Subject> getAllNotUserSubjects(int id);

    Subject getSubjectById(int id) throws SubjectDaoException;

    boolean updateSubject(String id, String name, String description, String sphere);
}
