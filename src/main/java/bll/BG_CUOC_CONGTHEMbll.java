package bll;

import dal.BG_CUOC_CONGTHEMdal;
import model.TinhDvctModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class BG_CUOC_CONGTHEMbll {
    public static ArrayList<TinhDvctModel> getGiaDvct(String maDv, String vung_di_h, String vung_den_h, String hanghoa) {
        ArrayList<TinhDvctModel> list = new ArrayList<>();
        BG_CUOC_CONGTHEMdal dal = new BG_CUOC_CONGTHEMdal();
        try {
            ResultSet result = dal.getGiaDvct(maDv, vung_di_h, vung_den_h, hanghoa);
            while (result.next()) {
                TinhDvctModel model = new TinhDvctModel.Builder()
                        .setcachtinh(result.getString("CACH_TINH"))
                        .setgiatri(result.getString("GIA_TRI"))
                        .setdvcongthem(result.getString("MA_DV_CONGTHEM"))
                        .setmucdau(result.getString("MUC_DAU"))
                        .setmuccuoi(result.getString("MUC_CUOI"))
                        .setmin(result.getString("GIA_TRI_MIN"))
                        .setmax(result.getString("GIA_TRI_MAX"))
                        .setid(result.getString("ID_CUOC_CONGTHEM"))
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

    public static ArrayList<TinhDvctModel> getDvCOD(String maDv, String vung_di_h, String vung_den_h, String hanghoa) {
        ArrayList<TinhDvctModel> list = new ArrayList<>();
        BG_CUOC_CONGTHEMdal dal = new BG_CUOC_CONGTHEMdal();
        try {
            ResultSet result = dal.getDvCOD(maDv, vung_di_h, vung_den_h, hanghoa);
            while (result.next()) {
                TinhDvctModel model = new TinhDvctModel.Builder()
                        .setcachtinh(result.getString("CACH_TINH"))
                        .setgiatri(result.getString("GIA_TRI"))
                        .setdvcongthem(result.getString("MA_DV_CONGTHEM"))
                        .setmucdau(result.getString("MUC_DAU"))
                        .setmuccuoi(result.getString("MUC_CUOI"))
                        .setmin(result.getString("GIA_TRI_MIN"))
                        .setmax(result.getString("GIA_TRI_MAX"))
                        .setid(result.getString("ID_CUOC_CONGTHEM"))
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

    public static int updateInfoById(String cachtinh, String mucdau, String muccuoi, String giatri, String min, String max,String idref, String id) {
        BG_CUOC_CONGTHEMdal dal = new BG_CUOC_CONGTHEMdal();
        int update = dal.updateInfoById(cachtinh, mucdau, muccuoi, giatri, min, max,idref, id);
        dal.closeConnection();
        return update;
    }

    public static int insertMadv(String UUID,String cachtinh,String madv,String id) {
        BG_CUOC_CONGTHEMdal dal = new BG_CUOC_CONGTHEMdal();
        int insert = dal.insertMadv(UUID,cachtinh,madv,id);
        dal.closeConnection();
        return insert;
    }
}
