package ru.third.inno.task.models.dao;

import ru.third.inno.task.common.exception.TaskDaoException;
import ru.third.inno.task.models.pojo.Task;

import java.util.List;

/**
 * Created by yy on 03.03.17.
 */
public interface iTaskDao {
    boolean updateTaskReadyness(String userId, String taskId, String isdone);

    boolean deleteTaskById(String userId, String id);

    boolean newTask(String name, String description, String id);

    List<Task> getAllTasks(String id) throws TaskDaoException;

    boolean editTask(String id, String person_id, String name, String description);
}
