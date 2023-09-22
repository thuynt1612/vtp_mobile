package bll;

import dal.SYSTEM_PARAMETERdal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SYSTEM_PARAMETERbll {
    public static String getConfigByName(String name) {
        String valueConfig = "";
        SYSTEM_PARAMETERdal dal = new SYSTEM_PARAMETERdal();
        try {
            ResultSet result = dal.getConfigByName(name);
            if (result != null && result.next()) {
                valueConfig = result.getString("CONFIG_VALUE");
            }
            result.close();
            dal.closeConnection();
            return valueConfig;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
