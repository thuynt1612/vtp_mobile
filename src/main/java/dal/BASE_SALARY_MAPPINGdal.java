package dal;

import net.serenitybdd.core.Serenity;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BASE_SALARY_MAPPINGdal extends BaseMariaseviceDal{
    public ResultSet getBaseSalaryInfo(String gradeId,String stepId,String tableId) {
        String query = "SELECT * FROM base_salary_mapping WHERE hrl_mapping = ? AND step_mapping = ? AND region_mapping = ? ";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,gradeId);
            super.preStatement.setString(2,stepId);
            super.preStatement.setString(3,tableId);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET BASE SALARY INFO ERROR").andContents(e.getMessage());
            return null;
        }
    }
}
