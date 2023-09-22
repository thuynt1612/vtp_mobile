package dal;

import net.serenitybdd.core.Serenity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WORKING_PROCESSdal  extends BaseMariaseviceDal{
    public ResultSet getWorkingInfo(String empid,String period) {
        String query = "SELECT * FROM working_process WHERE employee_id = ? AND period_id = ?";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,empid);
            super.preStatement.setString(2,period);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET WORKING INFO ERROR").andContents(e.getMessage());
            return null;
        }
    }

    public ResultSet getWorkingByEmpMutiContract(String empid,String period,int limit,int offset) {
        String query = "SELECT * FROM working_process WHERE employee_id = ? AND period_id = ? LIMIT 1 OFFSET 1";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,empid);
            super.preStatement.setString(2,period);
            super.preStatement.setInt(3,limit);
            super.preStatement.setInt(4,offset);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET WORKING INFO ERROR").andContents(e.getMessage());
            return null;
        }
    }
}
