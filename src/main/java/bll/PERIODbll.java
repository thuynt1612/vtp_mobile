package bll;

import dal.PERIODdal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PERIODbll {
    public static String getdate(String idPeriod,String column) {
        String date = "";
        PERIODdal dal = new PERIODdal();
        try {
            ResultSet result = dal.getdate(idPeriod);
            if (result != null && result.next()) {
                date = result.getString(column);
            }
            result.close();
            dal.closeConnection();
            return date;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
