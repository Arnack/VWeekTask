package ru.third.inno.task.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.third.inno.task.common.exception.SubjectDaoException;
import ru.third.inno.task.models.dao.SubjectDao;
import ru.third.inno.task.models.pojo.Subject;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by bot on 23.02.17.
 */

@Service
public class SubjectService implements iSubjectService {
    private static Logger logger = Logger.getLogger(SubjectService.class);

    private SubjectDao subjectDao;

    @Autowired
    public void setSubjectDao(SubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Override
    public boolean create(String name, String description, String sphere) {
        return subjectDao.createSubject(name, description, sphere);
    }

    @Override
    public List<Subject> getAllSubjects() {
        try {
            return subjectDao.getAllSubjects();
        } catch (SubjectDaoException e) {
            logger.error("Cant get all subjects in SubjectServlet");
        }
        return null;
    }

    @Override
    public List<Subject> getAllUserSubjects(int id) {
        try {
            return subjectDao.getAllUserSubjects(id);
        } catch (SubjectDaoException e) {
            logger.error("Cant get all subjects in SubjectServlet");
        } catch (SQLException e) {
            logger.error("sql error while getting user subjects");
        }
        return null;
    }

    @Override
    public List<Subject> getAllNotUserSubjects(int id) {
        try {
            return subjectDao.getAllNotUserSubjects(id);
        } catch (SubjectDaoException e) {
            logger.error("Cant get all subjects in SubjectServlet");
        }
        return null;
    }


    @Override
    public Subject getSubjectById(int id) throws SubjectDaoException {
        return  subjectDao.getSubjectById(id);
    }

    @Override
    public boolean updateSubject(String id, String name, String description, String sphere){
        return subjectDao.updateSubject(id, name, description, sphere);
    }

}
