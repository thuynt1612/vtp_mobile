package dal;

import net.serenitybdd.core.Serenity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class EMPLOYEEdal extends BaseMariaseviceDal{
    public ResultSet getEmpInfo(String empid) {
        String query = "SELECT * FROM employee WHERE id = ?";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,empid);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET EMPLOYEE INFO ERROR").andContents(e.getMessage());
            return null;
        }
    }
}
