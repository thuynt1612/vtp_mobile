package dal;

import net.serenitybdd.core.Serenity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DM_TINH_BUUCUCdal extends BaseMicroserviceDal{
    public ResultSet getTinhByBuucuc(String buucuc) {
        String query = "select * from dm_tinh_buucuc where ma_buucuc = ?";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,buucuc);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET INFO TINH BUU CUC ERROR").andContents(e.getMessage());
            return null;
        }
    }
}
