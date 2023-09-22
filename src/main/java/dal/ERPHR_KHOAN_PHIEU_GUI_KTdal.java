package dal;

import net.serenitybdd.core.Serenity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class ERPHR_KHOAN_PHIEU_GUI_KTdal extends BaseMicroserviceDal{
    public ResultSet countBillCod(String startDate, String endDate, String buuCuc, String maVung) throws ParseException {
        String query = "SELECT count(*) as COUNT from ERP_HR.KHOAN_PHIEU_GUI_KT where ngay_khoan BETWEEN TO_DATE(?) AND TO_DATE(?)  +1 - 0.00001\n" +
                "AND MA_BUUCUC = ? and loai_khoan = 0\n" +
                "AND THU_HO > 0\n" +
                "AND ID_PHUONGXA in (SELECT ID_PHUONGXA FROM ERP_HR.HR_KHOAN_ZONE_NEW where ZONE_NHAN = ? AND MA_BUUCUC =?)\n" +
                "AND TRONG_NUOC = 1 \n" +
                "AND ma_dv_viettel not in ('VLC','VLF','VLK')";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1, startDate);
            super.preStatement.setString(2,endDate );
            super.preStatement.setString(3,buuCuc);
            super.preStatement.setString(4,maVung);
            super.preStatement.setString(5,buuCuc);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET PARAM FOR CREAT NEW WALLET ERROR").andContents(e.getMessage());
            return null;
        }
    }


    public ResultSet countBillNCod(String startDate,String endDate,String buuCuc,String maVung) throws ParseException {
        String query = "SELECT count(*)*1/3 as COUNT from ERP_HR.KHOAN_PHIEU_GUI_KT where ngay_khoan BETWEEN TO_DATE(?) AND TO_DATE(?)  +1 - 0.00001\n" +
                "AND MA_BUUCUC = ? and loai_khoan = 0\n" +
                "AND THU_HO = 0\n" +
                "AND ID_PHUONGXA in (SELECT ID_PHUONGXA FROM ERP_HR.HR_KHOAN_ZONE_NEW where ZONE_NHAN = ? AND MA_BUUCUC =?)\n" +
                "AND TRONG_NUOC = 1 \n" +
                "AND ma_dv_viettel not in ('VLC','VLF','VLK')";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1, startDate);
            super.preStatement.setString(2,endDate );
            super.preStatement.setString(3,buuCuc);
            super.preStatement.setString(4,maVung);
            super.preStatement.setString(5,buuCuc);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET PARAM FOR CREAT NEW WALLET ERROR").andContents(e.getMessage());
            return null;
        }
    }

    public ResultSet countBillqte(String startDate,String endDate,String buuCuc,String maVung) throws ParseException {
        String query = "SELECT count(*)*3 as COUNT from ERP_HR.KHOAN_PHIEU_GUI_KT where ngay_khoan BETWEEN TO_DATE(?) AND TO_DATE(?)  +1 - 0.00001\n" +
                "AND MA_BUUCUC = ? and loai_khoan = 0\n" +
                "AND THU_HO > 0\n" +
                "AND ID_PHUONGXA in (SELECT ID_PHUONGXA FROM ERP_HR.HR_KHOAN_ZONE_NEW where ZONE_NHAN = ? AND MA_BUUCUC =?)\n" +
                "AND TRONG_NUOC = 1 \n" +
                "AND ma_dv_viettel not in ('VLC','VLF','VLK')";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1, startDate);
            super.preStatement.setString(2,endDate );
            super.preStatement.setString(3,buuCuc);
            super.preStatement.setString(4,maVung);
            super.preStatement.setString(5,buuCuc);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET PARAM FOR CREAT NEW WALLET ERROR").andContents(e.getMessage());
            return null;
        }
    }

    public ResultSet countCod(String startDate,String endDate,String buuCuc) throws ParseException {
        String query = "SELECT count(*) as COUNT from ERP_HR.KHOAN_PHIEU_GUI_KT where ngay_khoan BETWEEN TO_DATE(?) AND TO_DATE(?)  +1 - 0.00001\n" +
                "AND MA_BUUCUC = ? and loai_khoan = 0\n" +
                "AND THU_HO > 0\n" +
                "AND TRONG_NUOC = 1 \n" +
                "AND ma_dv_viettel not in ('VLC','VLF','VLK')";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1, startDate);
            super.preStatement.setString(2,endDate );
            super.preStatement.setString(3,buuCuc);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET PARAM FOR CREAT NEW WALLET ERROR").andContents(e.getMessage());
            return null;
        }
    }

    public ResultSet countNCod(String startDate,String endDate,String buuCuc) throws ParseException {
        String query = "SELECT count(*)*1/3 as COUNT from ERP_HR.KHOAN_PHIEU_GUI_KT where ngay_khoan BETWEEN TO_DATE(?) AND TO_DATE(?)  +1 - 0.00001\n" +
                "AND MA_BUUCUC = ? and loai_khoan = 0\n" +
                "AND THU_HO = 0\n" +
                "AND TRONG_NUOC = 1 \n" +
                "AND ma_dv_viettel not in ('VLC','VLF','VLK')";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1, startDate);
            super.preStatement.setString(2,endDate );
            super.preStatement.setString(3,buuCuc);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET PARAM FOR CREAT NEW WALLET ERROR").andContents(e.getMessage());
            return null;
        }
    }

    public ResultSet countQte(String startDate,String endDate,String buuCuc) throws ParseException {
        String query = "SELECT count(*)*3 as COUNT from ERP_HR.KHOAN_PHIEU_GUI_KT where ngay_khoan BETWEEN TO_DATE(?) AND TO_DATE(?)  +1 - 0.00001\n" +
                "AND MA_BUUCUC = ? and loai_khoan = 0\n" +
                "AND TRONG_NUOC = 0 \n" +
                "AND ma_dv_viettel not in ('VLC','VLF','VLK')";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1, startDate);
            super.preStatement.setString(2,endDate );
            super.preStatement.setString(3,buuCuc);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET PARAM FOR CREAT NEW WALLET ERROR").andContents(e.getMessage());
            return null;
        }
    }

    public ResultSet countG00(String startDate,String endDate,String buuCuc) throws ParseException {
        String query = "SELECT count(*) as COUNT from ERP_HR.KHOAN_PHIEU_GUI_KT where NGAY_KHOAN BETWEEN TO_DATE(?) AND TO_DATE(?)  +1 - 0.00001\n" +
                "AND MA_BUUCUC = ? \n" +
                "AND LOAI_KHOAN = 0\n" +
                "AND MA_DV_VIETTEL not in ('VLC','VLF','VLK')";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1, startDate);
            super.preStatement.setString(2,endDate );
            super.preStatement.setString(3,buuCuc);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET PARAM FOR CREAT NEW WALLET ERROR").andContents(e.getMessage());
            return null;
        }
    }
    public ResultSet countBillGreater5Kg(String startDate,String endDate,String buuCuc) throws ParseException {
        String query = "SELECT sum(TRONG_LUONG-5000) as COUNT FROM ERP_HR.KHOAN_PHIEU_GUI_KT WHERE ngay_khoan BETWEEN TO_DATE(?) AND TO_DATE(?)  + 1 - 0.00001 \n" +
                "AND MA_BUUCUC = ? and loai_khoan = 0\n" +
                "AND TRONG_LUONG > 5000\n" +
                "AND ma_dv_viettel not in ('VLC','VLF','VLK')\n" +
                "group by MA_BUUCUC";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1, startDate);
            super.preStatement.setString(2,endDate );
            super.preStatement.setString(3,buuCuc);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET PARAM FOR CREAT NEW WALLET ERROR").andContents(e.getMessage());
            return null;
        }
    }

    public ResultSet countBillGreater5KgReg(String startDate,String endDate,String buuCuc,String maVung) throws ParseException {
        String query = "SELECT sum(TRONG_LUONG-5000) as COUNT FROM ERP_HR.KHOAN_PHIEU_GUI_KT WHERE ngay_khoan BETWEEN TO_DATE(?) AND TO_DATE(?)  +1 - 0.00001 \n" +
                "AND MA_BUUCUC = ? and loai_khoan = 0\n" +
                "AND TRONG_LUONG > 5000\n" +
                "AND ID_PHUONGXA in (SELECT ID_PHUONGXA FROM ERP_HR.HR_KHOAN_ZONE_NEW where ZONE_NHAN = ? AND MA_BUUCUC = ?)\n" +
                "AND ma_dv_viettel not in ('VLC','VLF','VLK')\n" +
                "group by MA_BUUCUC";
        try {
            super.preStatement = super.conn.prepareStatement(query);
            super.preStatement.setString(1,startDate);
            super.preStatement.setString(2,endDate );
            super.preStatement.setString(3,buuCuc);
            super.preStatement.setString(4,maVung);
            super.preStatement.setString(5,buuCuc);
            return super.preStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            Serenity.recordReportData().withTitle("GET PARAM FOR CREAT NEW WALLET ERROR").andContents(e.getMessage());
            return null;
        }
    }
}
