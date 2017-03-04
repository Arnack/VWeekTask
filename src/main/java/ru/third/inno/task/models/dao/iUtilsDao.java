package ru.third.inno.task.models.dao;

/**
 * Created by yy on 03.03.17.
 */
public interface iUtilsDao {
    String getValueByName(String name);

    boolean setValueByName(String value, String name);
}
