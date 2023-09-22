package bll;

import dal.Data_Input_Processdal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class Data_Input_Processbll {
    public static String getEmployeeInfo() {
        String manv = "";
        Data_Input_Processdal dal = new Data_Input_Processdal();
        try {
            ResultSet result = dal.getEmployeeInfo();
            if (result != null && result.next()) {
                manv = result.getString("period_id");
            }
            result.close();
            dal.closeConnection();
            return manv;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
