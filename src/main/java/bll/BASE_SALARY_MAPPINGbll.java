package bll;

import dal.BASE_SALARY_MAPPINGdal;
import dal.PERIODdal;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BASE_SALARY_MAPPINGbll {
    public static String getBaseSalaryInfo(String gradeId,String stepId,String tableId) {
        String value = "";
        BASE_SALARY_MAPPINGdal dal = new BASE_SALARY_MAPPINGdal();
        try {
            ResultSet result = dal.getBaseSalaryInfo(gradeId,stepId,tableId);
            if (result != null && result.next()) {
                value = result.getString("VALUE");
            }
            result.close();
            dal.closeConnection();
            return value;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
