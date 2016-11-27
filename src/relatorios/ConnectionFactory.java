package relatorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import views.Mensagens;

public class ConnectionFactory {

    private static final ConnectionFactory instance = new ConnectionFactory();
    public static final String URL = "jdbc:mysql://localhost:3306/appcarhibernate?autoReconnect=true&useSSL=false";
    public static final String USER = "root";
    public static final String PASSWORD = "";
    public static final String DRIVER_CLASS = "org.gjt.mm.mysql.Driver";

    private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            Mensagens.erroBD(e.toString());
            System.exit(0);
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }

}
