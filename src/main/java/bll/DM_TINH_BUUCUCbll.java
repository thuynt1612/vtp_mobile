package bll;

import dal.DM_TINH_BUUCUCdal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class DM_TINH_BUUCUCbll {
    public static String getTinhByBuucuc(String buucuc) {
        String tinh = "";
        DM_TINH_BUUCUCdal dal = new DM_TINH_BUUCUCdal();
        try {
            ResultSet result = dal.getTinhByBuucuc(buucuc);
            if (result != null && result.next()) {
                tinh = result.getString("MA_TINH");
            }
            result.close();
            dal.closeConnection();
            return tinh;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
