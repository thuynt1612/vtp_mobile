package bll;

import dal.BG_BANGTINH_LINEdal;
import dal.BaseMicroserviceDal;
import model.TinhCuocModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class BG_BANGTINH_LINEbll{
    public static ArrayList<TinhCuocModel> getGiaCuoc(String maDv, String vung_di_h, String vung_den_h, String hanghoa) {
        ArrayList<TinhCuocModel> list = new ArrayList<>();
        BG_BANGTINH_LINEdal dal = new BG_BANGTINH_LINEdal();
        try {
            ResultSet result = dal.getGiaCuoc(maDv,vung_di_h,vung_den_h,hanghoa);
            while (result.next()) {
                TinhCuocModel model = new TinhCuocModel.Builder()
                        .setmucdau(result.getInt("MUC_DAU"))
                        .setmuccuoi(result.getInt("MUC_CUOI"))
                        .setgiacuoc(result.getInt("GIA_CUOC"))
                        .setluy_ke(result.getString("LUY_KE"))
                        .build();
                list.add(model);
            }
            result.close();
            dal.closeConnection();
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
