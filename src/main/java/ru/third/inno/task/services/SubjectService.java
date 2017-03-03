package ru.third.inno.task.services;

import org.apache.log4j.Logger;
import ru.third.inno.task.common.exception.SubjectDaoException;
import ru.third.inno.task.models.dao.SubjectDao;
import ru.third.inno.task.models.pojo.Subject;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by bot on 23.02.17.
 */

public class SubjectService {
    private static Logger logger = Logger.getLogger(SubjectService.class);

    public static boolean create(String name, String description, String sphere) {
        return SubjectDao.createSubject(name, description, sphere);
    }

    public static List<Subject> getAllSubjects() {
        try {
            return SubjectDao.getAllSubjects();
        } catch (SubjectDaoException e) {
            logger.error("Cant get all subjects in SubjectServlet");
        }
        return null;
    }

    public static List<Subject> getAllUserSubjects(int id) {
        try {
            return SubjectDao.getAllUserSubjects(id);
        } catch (SubjectDaoException e) {
            logger.error("Cant get all subjects in SubjectServlet");
        } catch (SQLException e) {
            logger.error("sql error while getting user subjects");
        }
        return null;
    }

    public static List<Subject> getAllNotUserSubjects(int id) {
        try {
            return SubjectDao.getAllNotUserSubjects(id);
        } catch (SubjectDaoException e) {
            logger.error("Cant get all subjects in SubjectServlet");
        }
        return null;
    }


    public static Subject getSubjectById(int id) throws SubjectDaoException {
        return  SubjectDao.getSubjectById(id);
    }

    public  static boolean updateSubject(String id, String name, String description, String sphere){
        return SubjectDao.updateSubject(id, name, description, sphere);
    }

}
