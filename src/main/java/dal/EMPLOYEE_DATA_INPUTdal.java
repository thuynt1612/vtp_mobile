package dal;

import net.serenitybdd.core.Serenity;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EMPLOYEE_DATA_INPUTdal extends BaseMariaseviceDal{
    public ResultSet getEmpInputInfo(String empid,String periodId) {
        String query = "SELECT * FROM employee_data_input WHERE employee_id = ? and period_id = ? AND is_success = 1;";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,empid);
            super.preStatement.setString(2,periodId);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET EMPLOYEE INPUT INFO ERROR").andContents(e.getMessage());
            return null;
        }
    }
}
