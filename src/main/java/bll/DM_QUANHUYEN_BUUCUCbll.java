package bll;

import dal.BG_BANGTINH_LINEdal;
import dal.DM_QUANHUYEN_BUUCUCdal;
import model.TinhCuocModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class DM_QUANHUYEN_BUUCUCbll {
    public static String getqh(String userId) {
        String dm_quan_huyen = null;
        DM_QUANHUYEN_BUUCUCdal dal = new DM_QUANHUYEN_BUUCUCdal();
        try {
            ResultSet result = dal.getqh(userId);
            if (result != null && result.next()) {
                dm_quan_huyen = result.getString("ma_quanhuyen");
            }
            result.close();
            dal.closeConnection();
            return dm_quan_huyen;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public static String getQuanhuyenByBuucuc(String buucuc) {
        String dm_quan_huyen = null;
        DM_QUANHUYEN_BUUCUCdal dal = new DM_QUANHUYEN_BUUCUCdal();
        try {
            ResultSet result = dal.getQuanhuyenByBuucuc(buucuc);
            if (result != null && result.next()) {
                dm_quan_huyen = result.getString("ma_quanhuyen");
            }
            result.close();
            dal.closeConnection();
            return dm_quan_huyen;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
