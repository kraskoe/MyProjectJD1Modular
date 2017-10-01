package com.pvt.app.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Class DataSource
 *
 * Created by ykrasko on 16/08/2017.
 */
public class DataSource {
    private static volatile DataSource INSTANCE = null;
    private ComboPooledDataSource pooledDatasource;

    private final String URL;
    private final String DRIVER;
    private final String USER;
    private final String PASSWORD;

    {
        ResourceBundle rb = ResourceBundle.getBundle("resources/db");
        if (rb == null) {
            URL = "UNDEFINED";
            USER = "UNDEFINED";
            PASSWORD = "UNDEFINED";
            DRIVER = "com.mysql.jdbc.Driver";
            System.out.println("Бандл для db не был инициализирован");
        } else {
            URL = rb.getString("url");
            USER = rb.getString("user");
            PASSWORD = rb.getString("password");
            DRIVER = rb.getString("driver");
        }
    }

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        pooledDatasource = new ComboPooledDataSource();
        pooledDatasource.setDriverClass(DRIVER); //loads the jdbc driver
        pooledDatasource.setJdbcUrl(URL);
        pooledDatasource.setUser(USER);
        pooledDatasource.setPassword(PASSWORD);

        // the settings below are optional -- c3p0 can work with defaults
        pooledDatasource.setMinPoolSize(10);
        pooledDatasource.setAcquireIncrement(5);
        pooledDatasource.setMaxPoolSize(20);
        pooledDatasource.setMaxStatements(180);

    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        DataSource datasource = INSTANCE;
        if (datasource == null) {
            synchronized (DataSource.class) {
                datasource = INSTANCE;
                if (datasource == null) {
                    INSTANCE = datasource = new DataSource();
                }
            }
        }
        return datasource;
    }

    public Connection getConnection() throws SQLException {
        return pooledDatasource.getConnection();
    }

//    Заглушка datasource
//    DataSource() throws IOException, SQLException, PropertyVetoException, Exception {
//        try {
//            Class.forName(DRIVER).newInstance();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//
//    public Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(URL,USER,PASSWORD);
//    }

}
