package bll;

import dal.USERSdal;
import dal.VTP_KETQUA_KHOAN_LINEdal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class USERSbll {
    public static String getUserid(String username, String buucuc) {
        String userid = "";
        USERSdal dal = new USERSdal();
        try {
            ResultSet result = dal.getUserid(username,buucuc);
            if (result != null && result.next()) {
                userid = result.getString("USERID");
            }
            result.close();
            dal.closeConnection();
            return userid;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
