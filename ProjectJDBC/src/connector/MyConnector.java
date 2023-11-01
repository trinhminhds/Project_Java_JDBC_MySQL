package connector;

import java.sql.*;

public class MyConnector {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/qlsv";

    public Connection connection = null;

    public void initConnector() {
        try {
            connection = DriverManager.getConnection(jdbcUrl, "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
