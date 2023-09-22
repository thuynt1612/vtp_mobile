package bll;

import dal.WORKING_PROCESSdal;
import model.EmployeeModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WORKING_PROCESSbll {
    public static EmployeeModel getWorkingInfo(String empid,String period) {
        WORKING_PROCESSdal dal = new WORKING_PROCESSdal();
        try {
            ResultSet result = dal.getWorkingInfo(empid,period);
            EmployeeModel model = null;
            if (result != null && result.next()) {
                model = new EmployeeModel.Builder()
                        .setposition(result.getString("POSITION"))
                        .setposition_dec(result.getString("POSITION_DESCRIPTION"))
                        .setcontractType(result.getString("CONTRACT_TYPE"))
                        .setbase_salary(result.getDouble("BASE_SALARY"))
                        .setinsurance_salary(result.getDouble("INSURANCE_SALARY"))
                        .setactual_workday(result.getDouble("actual_workday"))
                        .setholidays_absence(result.getDouble("holidays_absence"))
                        .setholidays_workday(result.getDouble("holidays_workday"))
                        .setpaid_leave_absence(result.getDouble("paid_leave_absence"))
                        .settotal_workday(result.getDouble("total_workday"))
                        .build();
            }
            result.close();
            dal.closeConnection();
            return model;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static EmployeeModel getWorkingByEmpMutiContract(String empid,String period,int limit,int offset) {
        WORKING_PROCESSdal dal = new WORKING_PROCESSdal();
        try {
            ResultSet result = dal.getWorkingByEmpMutiContract(empid,period,limit,offset);
            EmployeeModel model = null;
            if (result != null && result.next()) {
                model = new EmployeeModel.Builder()
                        .setposition(result.getString("POSITION"))
                        .setposition_dec(result.getString("POSITION_DESCRIPTION"))
                        .setcontractType(result.getString("CONTRACT_TYPE"))
                        .setbase_salary(result.getDouble("BASE_SALARY"))
                        .setinsurance_salary(result.getDouble("INSURANCE_SALARY"))
                        .build();
            }
            result.close();
            dal.closeConnection();
            return model;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
