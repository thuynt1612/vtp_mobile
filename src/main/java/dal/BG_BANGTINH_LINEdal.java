package dal;

import net.serenitybdd.core.Serenity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class BG_BANGTINH_LINEdal extends BaseMicroserviceDal{
    public ResultSet getGiaCuoc(String maDv, String vung_di_h, String vung_den_h, String hanghoa) throws ParseException {
        String query = "select A.*,C.ID_BANGTINH from BG_BANGTINH_LINE A inner join BG_BANGTINH_ZONE B on A.ID_BT_ZONE = B.ID_BT_ZONE\n" +
                "    inner join BG_BANGTINH C on C.ID_BANGTINH = B.ID_BANGTINH\n" +
                "    inner join BG_BANGTINH_DICHVU D on D.ID_BANGTINH_DICHVU = C.ID_BANGTINH_DICHVU\n" +
                "    where  D.DS_DICHVU like ?\n" +
                "    and B.ID_ZONE in (select ID_ZONE from BG_ZONE_LINE where DS_VUNG_DI like ? and DS_VUNG_DEN like ?)\n" +
                "    and C.DUNG_CHUNG = 1\n" +
                "    and C.TRANG_THAI = 1\n" +
                "    and C.LOAIHANGHOA in ('ALL',?)";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,maDv);
            super.preStatement.setString(2,"%"+vung_di_h +"%");
            super.preStatement.setString(3,"%"+ vung_den_h + "%");
            super.preStatement.setString(4,hanghoa);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET PARAM FOR CREAT NEW WALLET ERROR").andContents(e.getMessage());
            return null;
        }
    }
}
