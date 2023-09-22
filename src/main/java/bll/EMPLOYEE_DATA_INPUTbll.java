package bll;

import dal.EMPLOYEE_DATA_INPUTdal;
import model.EmployeeModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EMPLOYEE_DATA_INPUTbll {
    public static EmployeeModel getEmpInputInfo(String empid,String periodId) {
        EMPLOYEE_DATA_INPUTdal dal = new EMPLOYEE_DATA_INPUTdal();
        try {
            ResultSet result = dal.getEmpInputInfo(empid,periodId);
            EmployeeModel model = null;
            if (result != null && result.next()) {
                model = new EmployeeModel.Builder()
                        .setki(result.getDouble("KI"))
                        .setkpi(result.getDouble("KPI"))
                        .setluongbosung(result.getDouble("additional_salary"))
                        .setpcqlkd(result.getDouble("business_management_allowance"))
                        .setthuonghtkhkdl(result.getDouble("plan_completion_bonus"))
                        .settruythubhxh(result.getDouble("bhxh_arrears"))
                        .setthuquydenon(result.getDouble("gratitude_fund"))
                        .settruythuthue(result.getDouble("tax_arrears"))
                        .settruythuccongdoan(result.getDouble("union_arrears"))
                        .setkhautru(result.getDouble("deductible_debt"))
                        .setgiuluong(result.getDouble("hold_salary"))
                        .setluongbaohiem(result.getDouble("insurance_salary"))
                        .sethesodangdoan(result.getDouble("party_factor"))
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
