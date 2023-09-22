package dal;

import net.serenitybdd.core.Serenity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class VTP_KETQUA_KHOAN_LINEdal extends BaseMicroserviceDal{
//    public int updateChannelByWalletId(String channel, String walletId) {
//        String query = "update EWALLET_Customer set SOURCE_APP_ID = ? where wallet_ID = ?";
//        try {
//            super.preStatement = super.conn.prepareStatement(query);
//            super.preStatement.setString(1, channel);
//            super.preStatement.setString(2, walletId);
//            return super.preStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            Serenity.recordReportData().withTitle("UPDATE CHANNEL ERROR").andContents(e.getMessage());
//            return 0;
//        }
//    }
//    public ResultSet countBillSuccess() {
//        String query = "SELECT count(*) as COUNT FROM VTP_KETQUA_KHOAN_LINE WHERE TO_CHAR(NGAY_THANG) BETWEEN '01-MAR-23' AND '14-MAR-23'\n" +
//                "GROUP_TYPE = 'GIAO' AND TYPE = 'PHAT' AND MA_BUUCUC_PHAT = 'TN2'\n" +
//                "AND THU_HO != 0\n" +
//                "AND TRONG_NUOC = 1 \n" +
//                "AND ID_ZONE in\n" +
//                "          (select ID_ZONE from LG_ZONE where zone_name like '%1')\n" +
//                "AND ma_dv_viettel not in ('VLC','VLF','VLK')";
//        try {
//            super.preStatement = super.conn.prepareStatement(query);
////            super.preStatement.setString(1, buu_cuc);
////            super.preStatement.setString(2, maVung);
//            return super.preStatement.executeQuery();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            Serenity.recordReportData().withTitle("GET PARAM FOR CREAT NEW WALLET ERROR").andContents(e.getMessage());
//            return null;
//        }
//    }


    public ResultSet countBillCod(String startDate,String endDate,String buuCuc,String maVung) throws ParseException {
        String query = "SELECT count(*) as COUNT FROM VTP_KETQUA_KHOAN_LINE WHERE NGAY_THANG BETWEEN TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American') AND TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American')  +1 - 0.00001 \n" +
                "AND GROUP_TYPE = 'GIAO' AND TYPE = 'PHAT' \n" +
                "AND MA_BUUCUC_PHAT = ?\n" +
                "AND THU_HO > 0\n" +
                "AND TRONG_NUOC = 1 \n" +
                "AND ID_PHUONGXA in (SELECT ID_PHUONGXA FROM ERP_HR.HR_KHOAN_ZONE_NEW where ZONE_GIAO = ? AND MA_BUUCUC = ?)\n" +
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
        String query = "SELECT count(*)*1/3 as COUNT FROM VTP_KETQUA_KHOAN_LINE WHERE NGAY_THANG BETWEEN TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American') AND TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American')  +1 - 0.00001 \n" +
                "AND GROUP_TYPE = 'GIAO' AND TYPE = 'PHAT' \n" +
                "AND MA_BUUCUC_PHAT = ?\n" +
                "AND THU_HO = 0\n" +
                "AND TRONG_NUOC = 1 \n" +
                "AND ID_PHUONGXA in (SELECT ID_PHUONGXA FROM ERP_HR.HR_KHOAN_ZONE_NEW where ZONE_GIAO = ? AND MA_BUUCUC = ?)\n" +
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
        String query = "SELECT count(*)*3 as COUNT FROM VTP_KETQUA_KHOAN_LINE WHERE NGAY_THANG BETWEEN TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American') AND TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American')  +1 - 0.00001 \n" +
                "AND GROUP_TYPE = 'GIAO' AND TYPE = 'PHAT' \n" +
                "AND MA_BUUCUC_PHAT = ?\n" +
                "AND TRONG_NUOC = 0 \n" +
                "AND ID_PHUONGXA in (SELECT ID_PHUONGXA FROM ERP_HR.HR_KHOAN_ZONE_NEW where ZONE_GIAO = ? AND MA_BUUCUC = ?)\n" +
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
        String query = "SELECT count(*) as COUNT FROM VTP_KETQUA_KHOAN_LINE WHERE NGAY_THANG BETWEEN TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American') AND TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American')  +1 - 0.00001 \n" +
                "AND GROUP_TYPE = 'GIAO' AND TYPE = 'PHAT' \n" +
                "AND MA_BUUCUC_PHAT = ?\n" +
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
        String query = "SELECT count(*)*1/3 as COUNT FROM VTP_KETQUA_KHOAN_LINE WHERE NGAY_THANG BETWEEN TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American') AND TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American')  +1 - 0.00001 \n" +
                "AND GROUP_TYPE = 'GIAO' AND TYPE = 'PHAT' \n" +
                "AND MA_BUUCUC_PHAT = ?\n" +
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
        String query = "SELECT count(*)*3 as COUNT FROM VTP_KETQUA_KHOAN_LINE WHERE NGAY_THANG BETWEEN TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American') AND TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American')  +1 - 0.00001 \n" +
                "AND GROUP_TYPE = 'GIAO' AND TYPE = 'PHAT' \n" +
                "AND MA_BUUCUC_PHAT = ?\n" +
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
        String query = "SELECT count(*) as COUNT FROM VTP_KETQUA_KHOAN_LINE WHERE NGAY_THANG BETWEEN TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American') AND TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American')  +1 - 0.00001 \n" +
                "AND GROUP_TYPE = 'GIAO' AND TYPE = 'PHAT' \n" +
                "AND MA_BUUCUC_PHAT = ?\n" +
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
    public ResultSet countBillGreater5Kg(String startDate,String endDate,String buuCuc) throws ParseException {
        String query = "SELECT sum(TRONG_LUONG-5000) as COUNT FROM VTP_KETQUA_KHOAN_LINE WHERE NGAY_THANG BETWEEN TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American') AND TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American')  +1 - 0.00001 \n" +
                "AND GROUP_TYPE = 'GIAO' AND TYPE = 'PHAT' \n" +
                "AND MA_BUUCUC_PHAT = ?\n" +
                "AND TRONG_LUONG > 5000\n" +
                "AND ma_dv_viettel not in ('VLC','VLF','VLK')\n" +
                "group by MA_BUUCUC_PHAT";
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
        String query = "SELECT sum(TRONG_LUONG-5000) as COUNT FROM VTP_KETQUA_KHOAN_LINE WHERE NGAY_THANG BETWEEN TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American') AND TO_DATE(?, 'DD-MM-YYYY','NLS_DATE_LANGUAGE = American')  +1 - 0.00001 \n" +
                "AND GROUP_TYPE = 'GIAO' AND TYPE = 'PHAT' \n" +
                "AND MA_BUUCUC_PHAT = ?\n" +
                "AND TRONG_LUONG > 5000\n" +
                "AND ID_PHUONGXA in (SELECT ID_PHUONGXA FROM ERP_HR.HR_KHOAN_ZONE_NEW where ZONE_GIAO = ? AND MA_BUUCUC = ?)\n" +
                "AND ma_dv_viettel not in ('VLC','VLF','VLK')\n" +
                "group by MA_BUUCUC_PHAT";
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
