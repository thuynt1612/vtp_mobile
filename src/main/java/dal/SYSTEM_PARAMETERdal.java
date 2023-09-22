package dal;

import net.serenitybdd.core.Serenity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class SYSTEM_PARAMETERdal extends BaseMariaseviceDal{
    public ResultSet getConfigByName(String name) {
        String query = "SELECT * FROM system_parameter WHERE NAME LIKE ?";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,name);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET CONFIG ERROR").andContents(e.getMessage());
            return null;
        }
    }
}
