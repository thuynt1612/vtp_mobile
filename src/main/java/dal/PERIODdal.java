package dal;

import net.serenitybdd.core.Serenity;
import java.sql.ResultSet;
import java.sql.SQLException;


public class PERIODdal extends BaseMariaseviceDal{
    public ResultSet getdate(String idPeriod) {
        String query = "SELECT * FROM period WHERE id = ?";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,idPeriod);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET DATE INFO ERROR").andContents(e.getMessage());
            return null;
        }
    }
}
