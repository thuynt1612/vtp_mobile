package dal;

import net.serenitybdd.core.Serenity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class PHIEUGUIdal extends BaseMicroserviceDal{
    public ResultSet getInfoByBuucucMadvAndDate(String starDate, String endDate,String buucuc) {
        String query = "SELECT (TONG_TIEN - TONG_VAT) as tong,A.* FROM PHIEU_GUI A\n" +
                "WHERE NGAY_GUI_BP BETWEEN TRUNC(TO_DATE(?),'MONTH') AND TRUNC(TO_DATE(?)) + 1 -0.00001\n" +
                "AND MA_DV_VIETTEL IN ('VCBO','VCBA','VBK')\n" +
                "AND ma_buucuc_goc = ?";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,starDate);
            super.preStatement.setString(2,endDate);
            super.preStatement.setString(3,buucuc);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET INFO PHIEU GUI ERROR").andContents(e.getMessage());
            return null;
        }
    }

    public ResultSet getInfoByNotMadvAndDate(String starDate, String endDate, String madv) {
//        String query = "SELECT (TONG_TIEN - TONG_VAT) as TONG,A.* FROM PHIEU_GUI A\n" +
//                "WHERE NGAY_GUI_BP BETWEEN TRUNC(TO_DATE(?),'MONTH') AND TRUNC(TO_DATE(?)) + 1 -0.00001\n" +
//                "AND MA_DV_VIETTEL NOT IN ('VCBO','VCBA','VBK')\n" +
//                "AND ma_buucuc_goc = ?";
        String query = "SELECT sum(TONG_TIEN - TONG_VAT) as TONG FROM PHIEU_GUI A\n" +
                "WHERE NGAY_GUI_BP  BETWEEN TRUNC(TO_DATE(?),'MONTH') and TRUNC(TO_DATE(?)) + 1-0.00001\n" +
                "AND MA_DV_VIETTEL NOT IN ('VCBO','VCBA','VBK')\n" +
                "and ma_buucuc_goc=?\n" +
                "group by ma_buucuc_goc";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,starDate);
            super.preStatement.setString(2,endDate);
            super.preStatement.setString(3,madv);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET INFO PHIEU GUI ERROR").andContents(e.getMessage());
            return null;
        }
    }
}
