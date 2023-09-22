package dal;

import net.serenitybdd.core.Serenity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class BG_CUOC_CONGTHEMdal extends BaseMicroserviceDal{
    public ResultSet getGiaDvct(String maDv, String vung_di_h, String vung_den_h, String hanghoa) throws ParseException {
        String query = "select C.* from BG_CUOC_CONGTHEM C  inner join BG_BANGTINH B on   B.ID_BANGTINH = C.ID_BANGTINH\n" +
                "    inner join BG_BANGTINH_DICHVU A on A.ID_BANGTINH_DICHVU = B.ID_BANGTINH_DICHVU\n" +
                "    where A.ds_dichvu like ?\n" +
                "    AND B.TRANG_THAI = 1\n" +
                "    AND B.LOAIHANGHOA in ('ALL',?)\n"+
                "    AND C.ID_BANGTINH in (select ID_BANGTINH from BG_BANGTINH_ZONE where ID_ZONE in (select ID_ZONE from BG_ZONE_LINE where DS_VUNG_DI like ? and DS_VUNG_DEN like ?))";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,"%"+maDv +"%");
            super.preStatement.setString(2,hanghoa);
            super.preStatement.setString(3,"%"+vung_di_h +"%");
            super.preStatement.setString(4,"%"+ vung_den_h + "%");
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET PARAM DVCT ERROR").andContents(e.getMessage());
            return null;
        }
    }
    public ResultSet getDvCOD(String maDv, String vung_di_h, String vung_den_h, String hanghoa) throws ParseException {
    String query = "select C.* from BG_CUOC_CONGTHEM C  inner join BG_BANGTINH B on   B.ID_BANGTINH = C.ID_BANGTINH\n" +
            "    inner join BG_BANGTINH_DICHVU A on A.ID_BANGTINH_DICHVU = B.ID_BANGTINH_DICHVU\n" +
            "    where A.ds_dichvu like ?\n" +
            "    AND B.TRANG_THAI = 1\n" +
            "    AND B.LOAIHANGHOA in ('ALL',?)\n"+
            "    AND C.ID_BANGTINH in (select ID_BANGTINH from BG_BANGTINH_ZONE where ID_ZONE in (select ID_ZONE from BG_ZONE_LINE where DS_VUNG_DI like ? and DS_VUNG_DEN like ?))\n" +
            "    AND MA_DV_CONGTHEM = 'COD'";
        try {
        super.preStatement = super.conn.prepareStatement(query);
        super.preStatement.setString(1,"%"+maDv +"%");
        super.preStatement.setString(2,hanghoa);
        super.preStatement.setString(3,"%"+vung_di_h +"%");
        super.preStatement.setString(4,"%"+ vung_den_h + "%");
        return super.preStatement.executeQuery();
    } catch (SQLException e) {
        e.printStackTrace();
        Serenity.recordReportData().withTitle("GET PARAM DVCT ERROR").andContents(e.getMessage());
        return null;
    }
}

        public int updateInfoById(String cachtinh,String mucdau,String muccuoi,String giatri,String min,String max,String idref,String id) {
        String query = "update BG_CUOC_CONGTHEM set CACH_TINH = ?,MUC_DAU = ?,MUC_CUOI = ?,GIA_TRI = ?,GIA_TRI_MIN = ?,GIA_TRI_MAX = ?,ID_BANGTINH_REF = ? where ID_CUOC_CONGTHEM = ?";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1, cachtinh);
            super.preStatement.setString(2, mucdau);
            super.preStatement.setString(3, muccuoi);
            super.preStatement.setString(4, giatri);
            super.preStatement.setString(5, min);
            super.preStatement.setString(6, max);
            super.preStatement.setString(7, idref);
            super.preStatement.setString(8, id);
            return super.preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("UPDATE CHANNEL ERROR").andContents(e.getMessage());
            return 0;
        }
    }

    public int insertMadv(String UUID,String cachtinh,String madv,String id) {
        String query = "INSERT INTO BG_CUOC_CONGTHEM(ID_CUOC_CONGTHEM,ID_BANGTINH,CACH_TINH,GIA_TRI,GIA_TRI_MIN,GIA_TRI_MAX,MA_DV_CONGTHEM) \n" +
                "SELECT ?,ID_BANGTINH,?,GIA_TRI,GIA_TRI_MIN,GIA_TRI_MAX,? from BG_CUOC_CONGTHEM where ID_CUOC_CONGTHEM = ?";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1, UUID);
            super.preStatement.setString(2, cachtinh);
            super.preStatement.setString(3, madv);
            super.preStatement.setString(4, id);
            return super.preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("INSERT MADV ERROR").andContents(e.getMessage());
            return 0;
        }
    }
}
