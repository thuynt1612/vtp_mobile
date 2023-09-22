package bll;

import dal.EMPLOYEEdal;
import model.EmployeeModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EMPLOYEEbll {
    public static EmployeeModel getEmployeeInfo(String empid) {
        EMPLOYEEdal dal = new EMPLOYEEdal();
        try {
            ResultSet result = dal.getEmpInfo(empid);
            EmployeeModel model = null;
            if (result != null && result.next()) {
                model = new EmployeeModel.Builder()
                        .setemployeeId(result.getString("ID"))
                        .setfullName(result.getString("FULLNAME"))
                        .setcontractDate(result.getString("SIGNED_DATE"))
                        .sethesopcdt(result.getDouble("PARTY_FACTOR"))
                        .setbankacc(result.getString("BANK_ACCOUNT_NUMBER"))
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
