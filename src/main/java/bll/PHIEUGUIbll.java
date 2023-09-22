package bll;

import dal.PHIEUGUIdal;
import model.PhieuGuiModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PHIEUGUIbll {
    public static ArrayList<PhieuGuiModel> getInfoByBuucucMadvAndDate(String starDate, String endDate,String buucuc) {
        PHIEUGUIdal dal = new PHIEUGUIdal();
        ArrayList<PhieuGuiModel> list = new ArrayList<>();
        try {
            ResultSet result = dal.getInfoByBuucucMadvAndDate(starDate, endDate,buucuc);
            while (result.next()) {
                PhieuGuiModel model = new PhieuGuiModel.Builder()
                        .setbuucucgoc(result.getString("MA_BUUCUC_GOC"))
                        .setbuucucgphat(result.getString("MA_BUUCUC_PHAT"))
                        .setloaihh(result.getString("MA_LOAI_HANGHOA"))
                        .setmadv(result.getString("MA_DV_VIETTEL"))
                        .setmadvct(result.getString("PHU_PHI_COMMENT"))
                        .setthuho(result.getInt("THU_HO"))
                        .settienkg(result.getInt("KHAI_GIA"))
                        .settongtien(result.getLong("TONG"))
                        .settrongluong(result.getLong("TRONG_LUONG"))
                        .setvung_phat(result.getString("MA_VUNG_PHAT"))
                        .setvung_phat_h(result.getString("MA_QUANHUYEN"))
                        .setloaihh(result.getString("MA_LOAI_HANGHOA"))
                        .build();
                list.add(model);
            }
            result.close();
            dal.closeConnection();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<PhieuGuiModel> getInfoByNotMadvAndDate(String starDate, String endDate,String buucuc) {
        PHIEUGUIdal dal = new PHIEUGUIdal();
        ArrayList<PhieuGuiModel> list = new ArrayList<>();
        try {
            ResultSet result = dal.getInfoByNotMadvAndDate(starDate, endDate,buucuc);
            while (result.next()) {
                PhieuGuiModel model = new PhieuGuiModel.Builder()
//                        .setbuucucgoc(result.getString("MA_BUUCUC_GOC"))
//                        .setbuucucgphat(result.getString("MA_BUUCUC_PHAT"))
//                        .setloaihh(result.getString("MA_LOAI_HANGHOA"))
//                        .setmadv(result.getString("MA_DV_VIETTEL"))
//                        .setmadvct(result.getString("PHU_PHI_COMMENT"))
//                        .setthuho(result.getInt("THU_HO"))
//                        .settienkg(result.getInt("KHAI_GIA"))
                        .settongtien(result.getLong("TONG"))
//                        .settrongluong(result.getFloat("TRONG_LUONG"))
//                        .setvung_phat(result.getString("MA_VUNG_PHAT"))
//                        .setvung_phat_h(result.getString("MA_QUANHUYEN"))
//                        .setloaihh(result.getString("MA_LOAI_HANGHOA"))
                        .build();
                list.add(model);
            }
            result.close();
            dal.closeConnection();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
