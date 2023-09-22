package bll;

import dal.ERPHR_KHOAN_PHIEU_GUI_KTdal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class ERPHR_KHOAN_PHIEU_GUI_KTbll {
    public static String countBillCod(String startDate, String endDate, String buuCuc, String maVung) {
        String count = "";
        ERPHR_KHOAN_PHIEU_GUI_KTdal dal = new ERPHR_KHOAN_PHIEU_GUI_KTdal();
        try {
            ResultSet result = dal.countBillCod(startDate,endDate,buuCuc,maVung);
            if (result != null && result.next()) {
                count = result.getString("COUNT");
            }
            result.close();
            dal.closeConnection();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String countBillNCod(String startDate, String endDate, String buuCuc, String maVung) {
        String count = "";
        ERPHR_KHOAN_PHIEU_GUI_KTdal dal = new ERPHR_KHOAN_PHIEU_GUI_KTdal();
        try {
            ResultSet result = dal.countBillNCod(startDate,endDate,buuCuc,maVung);
            if (result != null && result.next()) {
                count = result.getString("COUNT");
            }
            result.close();
            dal.closeConnection();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String countBillqte(String startDate, String endDate, String buuCuc, String maVung) {
        String count = "";
        ERPHR_KHOAN_PHIEU_GUI_KTdal dal = new ERPHR_KHOAN_PHIEU_GUI_KTdal();
        try {
            ResultSet result = dal.countBillqte(startDate,endDate,buuCuc,maVung);
            if (result != null && result.next()) {
                count = result.getString("COUNT");
            }
            result.close();
            dal.closeConnection();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String countCod(String startDate, String endDate, String buuCuc) {
        String count = "";
        ERPHR_KHOAN_PHIEU_GUI_KTdal dal = new ERPHR_KHOAN_PHIEU_GUI_KTdal();
        try {
            ResultSet result = dal.countCod(startDate,endDate,buuCuc);
            if (result != null && result.next()) {
                count = result.getString("COUNT");
            }
            result.close();
            dal.closeConnection();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String countNCod(String startDate, String endDate, String buuCuc) {
        String count = "";
        ERPHR_KHOAN_PHIEU_GUI_KTdal dal = new ERPHR_KHOAN_PHIEU_GUI_KTdal();
        try {
            ResultSet result = dal.countNCod(startDate,endDate,buuCuc);
            if (result != null && result.next()) {
                count = result.getString("COUNT");
            }
            result.close();
            dal.closeConnection();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String countQte(String startDate, String endDate, String buuCuc) {
        String count = "";
        ERPHR_KHOAN_PHIEU_GUI_KTdal dal = new ERPHR_KHOAN_PHIEU_GUI_KTdal();
        try {
            ResultSet result = dal.countQte(startDate,endDate,buuCuc);
            if (result != null && result.next()) {
                count = result.getString("COUNT");
            }
            result.close();
            dal.closeConnection();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String countG00(String startDate, String endDate, String buuCuc) {
        String count = "";
        ERPHR_KHOAN_PHIEU_GUI_KTdal dal = new ERPHR_KHOAN_PHIEU_GUI_KTdal();
        try {
            ResultSet result = dal.countG00(startDate,endDate,buuCuc);
            if (result != null && result.next()) {
                count = result.getString("COUNT");
            }
            result.close();
            dal.closeConnection();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static String countBillGreater5Kg(String startDate, String endDate, String buuCuc) {
        String count = "";
        ERPHR_KHOAN_PHIEU_GUI_KTdal dal = new ERPHR_KHOAN_PHIEU_GUI_KTdal();
        try {
            ResultSet result = dal.countBillGreater5Kg(startDate,endDate,buuCuc);
            if (result != null && result.next()) {
                count = result.getString("COUNT");
            }
            result.close();
            dal.closeConnection();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public static String countBillGreater5KgReg(String startDate, String endDate, String buuCuc, String maVung) {
        String count = "";
        ERPHR_KHOAN_PHIEU_GUI_KTdal dal = new ERPHR_KHOAN_PHIEU_GUI_KTdal();
        try {
            ResultSet result = dal.countBillGreater5KgReg(startDate,endDate,buuCuc,maVung);
            if (result != null && result.next()) {
                count = result.getString("COUNT");
            }
            result.close();
            dal.closeConnection();
            return count;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
