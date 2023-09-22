package dal;

import helpers.ActionFile;

import java.sql.*;
import java.util.Map;

public class BaseMariaseviceDal {
    protected Connection conn;
    protected PreparedStatement preStatement;
    protected Statement statement;

    protected BaseMariaseviceDal() {
        try {
            Map<String, String> configProperties = ActionFile.getDataPropertiesFile("src/main/resources/config.properties");
            String hostName = configProperties.get("mariaHostname");
            String port = configProperties.get("mariaPort");
//            String DB_URL = "jdbc:mariadb://171.244.0.181:3306/db_prs_dl";
            String username = configProperties.get("mariaUserName");
            String password = configProperties.get("mariaPassword");
            String connectionString = "jdbc:mariadb://" + hostName + ":" + port + "/" + "salary_kcq";
            Class.forName("org.mariadb.jdbc.Driver");
            this.conn = DriverManager.getConnection(connectionString, username, password);
            this.statement = this.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void closeConnection() {
        try {
            preStatement.close();
            conn.close();
        } catch (Exception ex) {
        }
    }
}
