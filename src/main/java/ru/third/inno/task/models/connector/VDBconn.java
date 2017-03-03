package ru.third.inno.task.models.connector;

/**
 * Created by yy on 27.02.17.
 */

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class VDBconn {

    public static Connection getConn() throws NamingException, SQLException {
        Context initialContext = new InitialContext();

        /**
         * Get Context object for all environment naming (JNDI), such as
         * Resources configured for this web application.
         */
        Context environmentContext = (Context) initialContext
                .lookup("java:comp/env");
        /**
         * Name of the Resource we want to access.
         */
        String dataResourceName = "jdbc/HW2";
        /**
         * Get the data source for the MySQL to request a connection.
         */
        DataSource dataSource = (DataSource) environmentContext
                .lookup(dataResourceName);
        /**
         * Request a Connection from the pool of connection threads.
         */
        Connection conn = dataSource.getConnection();

        return conn;
    }
}
