package DatabaseConnector;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;

public class DatabaseConnector {
    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.sqlite");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
