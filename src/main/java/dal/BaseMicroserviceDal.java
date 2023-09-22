package dal;

import helpers.ActionFile;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseMicroserviceDal {
    protected Connection conn;
    protected PreparedStatement preStatement;
    protected Statement statement;

    protected BaseMicroserviceDal() {
        try {
            Map<String, String> configProperties = ActionFile.getDataPropertiesFile("src/main/resources/config.properties");
            String hostName = configProperties.get("microServiceHostname");
            String port = configProperties.get("microServicePort");
            String schema = configProperties.get("microServiceSerName");
            String username = configProperties.get("microServiceUserName");
            String password = configProperties.get("microServicePassword");
            String connectionString = "jdbc:oracle:thin:@" + hostName + ":" + port + ":" + schema;
            this.conn = DriverManager.getConnection(connectionString, username, password);
            this.statement = this.conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> getDataColumns(List<String> columns, String queryString) {
        try {
            ResultSet rs = this.statement.executeQuery(queryString);
            HashMap dataLst = new HashMap<String, String>();
            while (rs.next()) {
                for (String item : columns) {
                    dataLst.put(item, rs.getString(item));
                }
            }
            return dataLst;
        } catch (Exception ex) {
        }
        return null;
    }

//    public List<Map> getMultiRowsData(List<String> columns, String queryString) {
//        List dataLst = []
//        try {
//            ResultSet rs = this.statement.executeQuery(queryString);
//            while (rs.next()) {
//                HashMap dataMap = new HashMap<String, String>();
//                for (String item : columns) {
//                    //println item+": "+ rs.getString(item)
//                    dataMap.put(item, rs.getString(item));
//                }
//                dataLst.add(dataMap);
//            }
//        } catch (Exception ex) {
////            KeywordUtil.logInfo(ex.getMessage());
//        }
//        return dataLst;
//    }

//    public void insertRowData(String tableName, List<String> lstColumns =[], List<String> lstValues) {
//        try {
//            String executeStr;
//            if (lstColumns.size() == 0) {
//                executeStr = "insert into " + tableName + " values ('" + String.join("','", lstValues) + "')";
//            } else {
//                executeStr = "insert into " + tableName + " columns (" + String.join(",", lstColumns) + ") values ('" + String.join("','", lstValues) + "')";
//            }
//            this.statement.executeQuery(executeStr);
//        } catch (Exception ex) {
////            KeywordUtil.logInfo(ex.getMessage());
//        }

    public void deleteData(String queryString) {
        try {
            this.statement.executeQuery(queryString);
        } catch (Exception ex) {
        }
    }

    protected ResultSet getDataTable(String querySQL) {
        try {
            return this.statement.executeQuery(querySQL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
