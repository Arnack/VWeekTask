package ru.third.inno.task.models.connector;

/**
 * Created by yy on 23.02.17.
 */
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {


    private static final String USER = "root";//Логин пользователя
    private static final String PASSWORD = "hminnopolis";//Пароль пользователя
    private static final String URL = "jdbc:mysql://localhost/HW2"
           + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";//URL адрес
    private static final String driver = "com.mysql.jdbc.Driver";//Имя драйвера


    /**
     * @return Connection
     * @throws SQLException
     */
    public static synchronized Connection getConnection() throws SQLException {
        try {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {


            e.printStackTrace();
        }
        return null;
    }



    public static synchronized Connection getConn() throws NamingException, SQLException {
        InitialContext initContext= new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/HW2");
        Connection conn = ds.getConnection();

        return ds.getConnection();
    }

}
