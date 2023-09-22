package dal;

import net.serenitybdd.core.Serenity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class DM_QUANHUYEN_BUUCUCdal extends BaseMicroserviceDal{
    public ResultSet getqh(String userId) throws ParseException {
        String query = "select ma_quanhuyen from dm_quanhuyen_buucuc where ma_buucuc in (SELECT ma_buucuc FROM VTP.SUSERS where userid = ?)";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,userId);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET QUAN HUYEN ERROR").andContents(e.getMessage());
            return null;
        }
    }

    public ResultSet getQuanhuyenByBuucuc(String buucuc)  {
        String query = "select ma_quanhuyen from dm_quanhuyen_buucuc where ma_buucuc in (?)";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,buucuc);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET QUAN HUYEN ERROR").andContents(e.getMessage());
            return null;
        }
    }
}
