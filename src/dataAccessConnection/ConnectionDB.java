package dataAccessConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB {
    private static final Logger LOGGER = Logger.getLogger(ConnectionDB.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/tema3";
    private static final String USER = "root";
    private static final String PASS = "diana";

    private static ConnectionDB singleInstance = new ConnectionDB();

    private ConnectionDB() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connectionDb = null;
        try {
            connectionDb = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Eroare la logare!");
            e.printStackTrace();
        }
        return connectionDb;
    }

    /**
     * face conexiunea cu baza de date
     *
     * @return
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }

    /**
     * inchide conexiunea cu baza de date
     *
     * @param connectionDb
     */
    public static void close(Connection connectionDb) {
        if (connectionDb != null) {
            try {
                connectionDb.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Eroare inchidere conexiune!");
            }
        }
    }

    /**
     * inchide conexiunea cu un statement
     *
     * @param statementDb
     */
    public static void close(Statement statementDb) {
        if (statementDb != null) {
            try {
                statementDb.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Eroare inchidere interogare!");
            }
        }
    }

    /**
     * inchide conexiunea cu un ResultSet
     *
     * @param resultSetDb
     */
    public static void close(ResultSet resultSetDb) {
        if (resultSetDb != null) {
            try {
                resultSetDb.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "Eroare inchidere rezultat interogare!");
            }
        }
    }
}
