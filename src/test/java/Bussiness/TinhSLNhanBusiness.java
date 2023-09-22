package Bussiness;

import bll.ERPHR_KHOAN_PHIEU_GUI_KTbll;
import common.Logger;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TinhSLNhanBusiness {
    public static String tinhSlGcod(String startDate, String endDate, String buuCuc, String maVung) {
        String g00 = ERPHR_KHOAN_PHIEU_GUI_KTbll.countBillCod(startDate, endDate, buuCuc, maVung);
//        Logger.info("Sản lượng COD của bưu cục " + buuCuc + " theo vùng " + maVung.substring(1) + " là: " + g00);
        return g00;
    }

    public static String tinhSlGncod(String startDate, String endDate, String buuCuc, String maVung) {
        String g00 = ERPHR_KHOAN_PHIEU_GUI_KTbll.countBillNCod(startDate, endDate, buuCuc, maVung);

        if (g00.contains(".")) {
            int k = g00.indexOf(".");
            String a = g00.substring(0, k + 4);
            DecimalFormat df = new DecimalFormat("###.##");
            String i = df.format(Float.parseFloat(a)).replace(",", ".");
//            Logger.info("Sản lượng NCOD của bưu cục " + buuCuc + " theo vùng " + maVung.substring(1) + " là: " + i);
            return i;
        } else {
            float i = Float.parseFloat(g00);
//            Logger.info("Sản lượng NCOD của bưu cục " + buuCuc + " theo vùng " + maVung.substring(1) + " là: " + g00);
            return g00;
        }
    }

    public static String tinhSlGqte(String startDate, String endDate, String buuCuc, String maVung) {
        String g00 = ERPHR_KHOAN_PHIEU_GUI_KTbll.countBillqte(startDate, endDate, buuCuc, maVung);
//        Logger.info("Sản lượng QTE của bưu cục " + buuCuc + " theo vùng " + maVung.substring(1) + " là: " + g00);
        return g00;
    }

    public static float sl1vung(ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3, float heSo) {
        float g01 = 0.0F;
        ArrayList<Float> sl = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++) {
            sl.add((Float.parseFloat(list1.get(i)) + Float.parseFloat(list2.get(i)) + Float.parseFloat(list3.get(i))) * heSo);
        }
        for (int i = 0; i < list1.size(); i++) {
            g01 += sl.get(i);
        }
        return g01;
    }

    public static String tinhCod(String startDate, String endDate, String buuCuc) {
        String g00 = ERPHR_KHOAN_PHIEU_GUI_KTbll.countCod(startDate, endDate, buuCuc);
        Logger.info("Sản lượng COD của bưu cục " + buuCuc + " là: " + g00);
        return g00;
    }

    public static String tinhNCod(String startDate, String endDate, String buuCuc) {
        String g00 = ERPHR_KHOAN_PHIEU_GUI_KTbll.countNCod(startDate, endDate, buuCuc);

        if (g00.contains(".")) {
            int k = g00.indexOf(".");
            String a = g00.substring(0, k + 4);
            DecimalFormat df = new DecimalFormat("###.##");
            String i = df.format(Float.parseFloat(a)).replace(",", ".");
            Logger.info("Sản lượng COD của bưu cục " + buuCuc + " là: " + i);
            return i;
        } else {
            float i = Float.parseFloat(g00);
            Logger.info("Sản lượng COD của bưu cục " + buuCuc + " là: " + g00);
            return g00;
        }
    }

    public static String tinhQte(String startDate, String endDate, String buuCuc) {
        String g00 = ERPHR_KHOAN_PHIEU_GUI_KTbll.countQte(startDate, endDate, buuCuc);
        Logger.info("Sản lượng COD của bưu cục " + buuCuc + " là: " + g00);
        return g00;
    }

    public static float g02(float cod, float ncod, float qte) {
        Logger.info("Sản lượng G02 là: " + (cod + ncod + qte));
        return (cod + ncod + qte);
    }

    public static String tinhG00(String startDate, String endDate, String buuCuc) {
        String g00 = ERPHR_KHOAN_PHIEU_GUI_KTbll.countG00(startDate, endDate, buuCuc);
        Logger.info("Sản lượng G00 của bưu cục " + buuCuc + " là: " + g00);
        return g00;
    }
    public static String tinhG03(String startDate, String endDate, String buuCuc) {
        String g00 = ERPHR_KHOAN_PHIEU_GUI_KTbll.countBillGreater5Kg(startDate, endDate, buuCuc);
        Logger.info("Sản lượng G03 của bưu cục " + buuCuc + " là: " + g00);
        return g00;
    }

    public static String tinhG04V(String startDate, String endDate, String buuCuc,String maVung) {
        String g00 = ERPHR_KHOAN_PHIEU_GUI_KTbll.countBillGreater5KgReg(startDate, endDate, buuCuc,maVung);

        if (g00.length() >0) {
            Logger.info("Sản lượng G04 của vùng " + maVung.substring(1) + " là: " + g00);
            return g00;
        } else {
            Logger.info("Sản lượng G04 của vùng " + maVung.substring(1) + " là: " + "0");
            return "0";
        }
    }

    public static float tinhG04(ArrayList<String> list,float heSo) {
        float sl = 0.0F;
        for (int i = 0; i < list.size(); i++) {
            sl += Float.parseFloat(list.get(i)) * heSo;
        }
        return sl;

    }
}
