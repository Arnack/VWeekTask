package ru.third.inno.task.models.connector;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

/**
 * This is a custom connection pool
 *
 */

public class ConnectionPool {

    Logger logger = Logger.getLogger(ConnectionPool.class);

    private Vector<Connection> availableConns = new Vector<Connection>();
    private Vector<Connection> usedConns = new Vector<Connection>();
    private String url = "jdbc:mysql://localhost:3306/HW2" +
            "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String password = "hminnopolis";
    int initConnCnt = 8;


    public ConnectionPool() {
        try {
            Class.forName(driver);
            logger.trace(" Class.forName(driver); in conn pool: " +  Class.forName(driver));

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < initConnCnt; i++) {
            availableConns.addElement(getConnection());
        }
    }

    /**
     * gets connection. this method is used in others methods
     * @return connection
     */
    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url,userName,password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("connection gettet (before sending) ");
        return conn;
    }

    /**
     * Invokes instance of connection
     * @return connection from the pool
     * @throws SQLException
     */
    public synchronized Connection retrieve() throws SQLException {
        Connection newConn = null;
        logger.debug("retrieving connection");

        if (availableConns.size() == 0) {

            logger.debug("try to retriew conn ");

            newConn = getConnection();
        } else {
            newConn = (Connection) availableConns.lastElement();
            availableConns.removeElement(newConn);
        }
        usedConns.addElement(newConn);

        return newConn;
    }

    /**
     * puts the connection back to the pool
     * @param c - connection to putback
     * @throws NullPointerException
     */
    public synchronized void putback(Connection c) throws NullPointerException {
        if (c != null) {
            if (usedConns.removeElement(c)) {
                availableConns.addElement(c);
            } else {
                throw new NullPointerException("Connection not in the usedConns array");
            }
        }
    }

    /**
     * @return count of available connections
     */
    public int getAvailableConnsCnt() {
        return availableConns.size();
    }
}