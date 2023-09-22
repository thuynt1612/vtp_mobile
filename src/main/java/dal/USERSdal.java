package dal;

import net.serenitybdd.core.Serenity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class USERSdal extends BaseMicroserviceDal{
    public ResultSet getUserid(String username, String buucuc) throws ParseException {
        String query = "SELECT * FROM VTP.SUSERS WHERE username = ? and ma_buucuc = ?";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,username);
            super.preStatement.setString(2,buucuc);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET USER INFO ERROR").andContents(e.getMessage());
            return null;
        }
    }
}
