package Bussiness;

import common.Logger;
import helpers.ExcelHelpers;
import model.PhuCapDienThoaiModel;
import model.TinhCuocModel;
import org.json.JSONObject;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class TinhLuongKcqBusiness {
    private String doituong;
    private double ki;
    private double ngaycongtt;
    private double ngaynghicoluong;
    private double ngaytruc;
    private double luongcung;
    private double tylehuong;
    private double ngaycongchedo;
    private double kpi;
    private double namthamnien;
    private double luongCb;
    private double ngaycongtinhluong;
    private String nhomluong;
    private double luongthamnien;
    private double luonglephep;
    private double luongboiduong;
    private double tongluong;
    private double sonamcongtac;
    private double luongbaohiem;
    private double ngaycongpheple;
    private double luongbosung;
    private double phucapqlkd;
    private double thuonghtkhkd;
    private double luongtruylinh;
    private double luongkhm;
    private double ngaycongtrucle;
    private double anca;
    private double dienthoai;
    private double doanthe;
    private double bhxh;
    private double bhyt;
    private double bhtn;
    private double truythu;
    private double noNpt;
    private double thue;
    private double quydenon;
    private double truythuthue;
    private double luongconlai;
    private double hesopcdt;
    private double pcdoanthe;
    private double pcanca;
    private double pcdienthoai;
    private String chucdanh;
    private double tongphucap;
    private double tongthunhap;
    private double congdoan;
    private double truythucongdoan;
    private double khautru;
    private double khautrugiuluong;
    private double sotienconlai;


    public String readDataFromExcel(String fileName, String columnName, int rownum) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileName;

        // Setup đường dẫn của file excel
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(filePath, "Sheet1");
        String value = "";
        value = excel.getCellData(columnName, rownum);
        return value;
    }

    public int getLastRowNo(String fileName) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileName;

        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(filePath, "Sheet1");
        int lastRow = excel.getLastRow();
        return lastRow;
    }

    public void writeDataInExcel(String fileName, String text, int rownum, int colnum) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileName;
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(filePath, "Sheet1");

        //write header
        for (int i = 1; i < rownum; i++) {
            excel.setCellData(text, i, colnum);
        }
    }

    public void clearExcel(String fileName) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileName;
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(filePath, "Sheet1");
        excel.clearExcel();
    }

    public void deletaExcel(String fileName) {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileName;
        ExcelHelpers excel = new ExcelHelpers();
        excel.deleteFile(filePath);
    }

    public ExcelHelpers setExcellFile(String fileName) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileName;
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(filePath, "Sheet1");
        return excel;
    }

    public void writeStaffInfo(String fileInput, String fileOutput) throws Exception {
        ExcelHelpers excel = setExcellFile(fileOutput);
        excel.setCellData("Mã nv", 0, 0);

        for (int i = 1; i < getLastRowNo(fileInput); i++) {
            String manv = readDataFromExcel(fileInput, "Manv", i);
            excel.setCellData(manv, i, 0);
        }
    }


    public void tinhLuongcung(String fileName, String fileOutput) throws Exception {
        ExcelHelpers excel1 = setExcellFile(fileOutput);
        excel1.setCellData("Lương cứng", 0, 1);

        for (int i = 1; i < getLastRowNo(fileName); i++) {
            luongCb = Double.parseDouble(readDataFromExcel(fileName, "Luongcoso", i));
            tylehuong = Double.parseDouble(readDataFromExcel(fileName, "Tylehuong", i));
            ki = Double.parseDouble(readDataFromExcel(fileName, "Ki", i));
            kpi = Double.parseDouble(readDataFromExcel(fileName, "Kpi", i));
            ngaycongtt = Double.parseDouble(readDataFromExcel(fileName, "Congthucte", i));
            ngaynghicoluong = Double.parseDouble(readDataFromExcel(fileName, "Congnghicoluong", i));
            ngaytruc = Double.parseDouble(readDataFromExcel(fileName, "Congtrucle", i));
            ngaycongchedo = Double.parseDouble(readDataFromExcel(fileName, "Congchedo", i));
            nhomluong = readDataFromExcel(fileName, "Nhomluong", i);
            luongcung = luongCung(nhomluong);
            Logger.info("Lương cứng của nv thứ " + i + " là: " + String.valueOf(luongcung));

            //verify luong input voi luong output
            double luonginput = Double.parseDouble(readDataFromExcel(fileName, "Luongcung", i));
            Logger.info("Lương input của nv thứ " + i + " là: " + String.valueOf(luonginput));
            verifyLuong(luonginput, luongcung);

            //ghi thong tin luong cung vao file excel
            excel1.setCellData(String.valueOf(luongcung), i, 1);
        }
    }

    public void tinhLuongthamnien(String fileName, String fileOutput) throws Exception {
        ExcelHelpers excel1 = setExcellFile(fileOutput);
        excel1.setCellData("Lương thâm niên", 0, 2);

        for (int i = 1; i < getLastRowNo(fileName); i++) {
            luongCb = Double.parseDouble(readDataFromExcel(fileName, "Luongcoso", i));
            ngaycongtinhluong = Double.parseDouble(readDataFromExcel(fileName, "Congtinhluong", i));
            ngaycongchedo = Double.parseDouble(readDataFromExcel(fileName, "Congchedo", i));
            sonamcongtac = Double.parseDouble(readDataFromExcel(fileName, "Namthamnien", i));
            ki = Double.parseDouble(readDataFromExcel(fileName, "Ki", i));
            luongthamnien = luongThamNien(sonamcongtac);
            Logger.info("Lương thâm niên tính được của nv thứ " + i + " là: " + String.valueOf(luongthamnien));

            //verify luong input voi luong output
            double luonginput = Double.parseDouble(readDataFromExcel(fileName, "Luongthamnien", i));
            Logger.info("Lương thâm niên input của nv thứ " + i + " là: " + String.valueOf(luonginput));
            verifyLuong(luonginput, luongthamnien);

            //ghi thong tin luong cung vao file excel
            excel1.setCellData(String.valueOf(luongthamnien), i, 2);
        }
    }

    public void tinhLuonglephep(String fileName, String fileOutput) throws Exception {
        ExcelHelpers excel1 = setExcellFile(fileOutput);
        excel1.setCellData("Lương lễ phép", 0, 3);

        for (int i = 1; i < getLastRowNo(fileName); i++) {
            luongCb = Double.parseDouble(readDataFromExcel(fileName, "Luongcoso", i));
            ngaycongpheple = Double.parseDouble(readDataFromExcel(fileName, "Ngaylephep", i));
            ngaycongchedo = Double.parseDouble(readDataFromExcel(fileName, "Congchedo", i));
            luongbaohiem = Double.parseDouble(readDataFromExcel(fileName, "Luongbaohiem", i));
            ki = Double.parseDouble(readDataFromExcel(fileName, "Ki", i));
            nhomluong = readDataFromExcel(fileName, "Nhomluong", i);
            luonglephep = luonglephep(nhomluong);
            Logger.info("Lương lễ phép tính được của nv thứ " + i + " là: " + String.valueOf(luonglephep));

            //verify luong input voi luong output
            double luonginput = Double.parseDouble(readDataFromExcel(fileName, "Luonglephep", i));
            Logger.info("Lương lễ phép input của nv thứ " + i + " là: " + String.valueOf(luonginput));
            verifyLuong(luonginput, luonglephep);

            //ghi thong tin luong cung vao file excel
            excel1.setCellData(String.valueOf(luonglephep), i, 3);
        }
    }

    public void tinhLuongBoiduong(String fileName, String fileOutput) throws Exception {
        ExcelHelpers excel1 = setExcellFile(fileOutput);
        excel1.setCellData("Lương bồi dưỡng trực lễ", 0, 4);

        for (int i = 1; i < getLastRowNo(fileName); i++) {
            ngaycongtrucle = Double.parseDouble(readDataFromExcel(fileName, "Congtrucle", i));
            ngaycongchedo = Double.parseDouble(readDataFromExcel(fileName, "Congchedo", i));
            luongbaohiem = Double.parseDouble(readDataFromExcel(fileName, "Luongbaohiem", i));

            luongboiduong = boiduongtrucle();
            Logger.info("Lương bồi dưỡng trực lễ tính được của nv thứ " + i + " là: " + String.valueOf(luongboiduong));

            //verify luong input voi luong output
            double luonginput = Double.parseDouble(readDataFromExcel(fileName, "Luongboiduong", i));
            Logger.info("Lương bồi dưỡng trực lễ input của nv thứ " + i + " là: " + String.valueOf(luonginput));
            verifyLuong(luonginput, luongboiduong);

            //ghi thong tin luong cung vao file excel
            excel1.setCellData(String.valueOf(luongboiduong), i, 4);
        }
    }

    public void tinhTongLuong(String fileInput, String fileOutput) throws Exception {
        ExcelHelpers excel1 = setExcellFile(fileOutput);
        excel1.setCellData("Tổng lương", 0, 5);

        for (int i = 1; i < getLastRowNo(fileInput); i++) {
            luongcung = Double.parseDouble(readDataFromExcel(fileOutput, "Lương cứng", i));
            luongbosung = Double.parseDouble(readDataFromExcel(fileInput, "LuongBoSung", i));
            phucapqlkd = Double.parseDouble(readDataFromExcel(fileInput, "Phucapkd", i));
            thuonghtkhkd = Double.parseDouble(readDataFromExcel(fileInput, "Thuonghtkh", i));

            luongthamnien = Double.parseDouble(readDataFromExcel(fileOutput, "Lương thâm niên", i));
            luonglephep = Double.parseDouble(readDataFromExcel(fileOutput, "Lương lễ phép", i));
            luongboiduong = Double.parseDouble(readDataFromExcel(fileOutput, "Lương bồi dưỡng trực lễ", i));

            luongtruylinh = Double.parseDouble(readDataFromExcel(fileInput, "Luongtruylinh", i));
            luongkhm = Double.parseDouble(readDataFromExcel(fileInput, "LuongKHM", i));

            tongluong = tongLuong(luongcung, luongbosung, phucapqlkd, thuonghtkhkd, luongthamnien, luonglephep, luongboiduong, luongtruylinh, luongkhm);
            Logger.info("Tổng lương tính được của nv thứ " + i + " là: " + String.valueOf(tongluong));

            //verify luong input voi luong output
            double luonginput = Double.parseDouble(readDataFromExcel(fileInput, "Tongluong", i));
            Logger.info("Tổng lương input của nv thứ " + i + " là: " + String.valueOf(luonginput));
            verifyLuong(luonginput, tongluong);

            //ghi thong tin luong cung vao file excel
            excel1.setCellData(String.valueOf(tongluong), i, 5);
        }
    }

    public void tinhThue(String fileInput, String fileOutput) throws Exception {
        ExcelHelpers excel1 = setExcellFile(fileOutput);
        excel1.setCellData("Thuế TNCN", 0, 6);

        for (int i = 1; i < getLastRowNo(fileInput); i++) {
            tongluong = Double.parseDouble(readDataFromExcel(fileOutput, "Tổng lương", i));
            anca = Double.parseDouble(readDataFromExcel(fileOutput, "Phụ cấp ăn ca", i));
            dienthoai = Double.parseDouble(readDataFromExcel(fileOutput, "Phụ cấp điện thoại", i));
            doanthe = Double.parseDouble(readDataFromExcel(fileOutput, "Phụ cấp đoàn thể", i));
            doituong = readDataFromExcel(fileInput, "Doituong", i);

            bhxh = Double.parseDouble(readDataFromExcel(fileInput, "BHXH", i));
            bhyt = Double.parseDouble(readDataFromExcel(fileInput, "BHYT", i));
            bhtn = Double.parseDouble(readDataFromExcel(fileInput, "BHTN", i));
            truythu = Double.parseDouble(readDataFromExcel(fileInput, "TruythuBhxh", i));
            noNpt = Double.parseDouble(readDataFromExcel(fileInput, "SoNPT", i));

            thue = thue(doituong, tongluong, anca, dienthoai, doanthe, bhxh, bhyt, bhtn, truythu, noNpt);
            Logger.info("Thuế TNCN tính được của nv thứ " + i + " là: " + String.valueOf(thue));

            //verify luong input voi luong output
            double thueinput = Double.parseDouble(readDataFromExcel(fileInput, "THUE", i));
            Logger.info("Thuế TNCN input của nv thứ " + i + " là: " + String.valueOf(thueinput));
            verifyLuong(thueinput, thue);

            //ghi thong tin luong cung vao file excel
            excel1.setCellData(String.valueOf(thue), i, 6);
        }
    }

    public void tinhLuongconlai(String fileInput, String fileOutput) throws Exception {
        ExcelHelpers excel1 = setExcellFile(fileOutput);
        excel1.setCellData("Lương còn lại", 0, 7);

        for (int i = 1; i < getLastRowNo(fileInput); i++) {
            tongluong = Double.parseDouble(readDataFromExcel(fileOutput, "Tổng lương", i));
            thue = Double.parseDouble(readDataFromExcel(fileOutput, "Thuế TNCN", i));
            quydenon = Double.parseDouble(readDataFromExcel(fileInput, "Quydenon", i));
            truythuthue = Double.parseDouble(readDataFromExcel(fileInput, "Truythuthuenamtruoc", i));
            bhxh = Double.parseDouble(readDataFromExcel(fileInput, "BHXH", i));
            bhyt = Double.parseDouble(readDataFromExcel(fileInput, "BHYT", i));
            bhtn = Double.parseDouble(readDataFromExcel(fileInput, "BHTN", i));
            truythu = Double.parseDouble(readDataFromExcel(fileInput, "TruythuBhxh", i));

            luongconlai = luongconlai(tongluong, bhxh, bhyt, bhtn, truythu, thue, quydenon, truythuthue);
            Logger.info("Lương còn lại tính được của nv thứ " + i + " là: " + String.valueOf(luongconlai));

            //verify luong input voi luong output
            double luongconlaiinput = Double.parseDouble(readDataFromExcel(fileInput, "Luongconlai", i));
            Logger.info("Lương còn lại input của nv thứ " + i + " là: " + String.valueOf(luongconlaiinput));
            verifyLuong(luongconlaiinput, luongconlai);

            //ghi thong tin luong cung vao file excel
            excel1.setCellData(String.valueOf(luongconlai), i, 7);
        }
    }

    public void tinhPhucapdoanthe(String fileInput, String fileOutput) throws Exception {
        ExcelHelpers excel1 = setExcellFile(fileOutput);
        excel1.setCellData("Phụ cấp đoàn thể", 0, 8);

        for (int i = 1; i < getLastRowNo(fileInput); i++) {
            hesopcdt = Double.parseDouble(readDataFromExcel(fileInput, "HesoPCDT", i));

            pcdoanthe = phucapdoanthe(hesopcdt);
            Logger.info("Phụ cấp đoàn thể tính được của nv thứ " + i + " là: " + String.valueOf(pcdoanthe));

            //verify luong input voi luong output
            double doantheinput = Double.parseDouble(readDataFromExcel(fileInput, "Doanthe", i));
            Logger.info("Phụ cấp đoàn thể input của nv thứ " + i + " là: " + String.valueOf(doantheinput));
            verifyLuong(doantheinput, pcdoanthe);

            //ghi thong tin luong cung vao file excel
            excel1.setCellData(String.valueOf(pcdoanthe), i, 8);
        }
    }

    public void tinhPhucapAnca(String fileInput, String fileOutput) throws Exception {
        ExcelHelpers excel1 = setExcellFile(fileOutput);
        excel1.setCellData("Phụ cấp ăn ca", 0, 9);

        for (int i = 1; i < getLastRowNo(fileInput); i++) {
            ngaycongtt = Double.parseDouble(readDataFromExcel(fileInput, "Congthucte", i));
            ngaycongchedo = Double.parseDouble(readDataFromExcel(fileInput, "Congchedo", i));

            pcanca = phucapanca(ngaycongtt, ngaycongchedo);
            Logger.info("Phụ cấp ăn ca tính được của nv thứ " + i + " là: " + String.valueOf(pcanca));

            //verify luong input voi luong output
            double ancainput = Double.parseDouble(readDataFromExcel(fileInput, "Anca", i));
            Logger.info("Phụ cấp ăn ca input của nv thứ " + i + " là: " + String.valueOf(ancainput));
            verifyLuong(ancainput, pcanca);

            //ghi thong tin luong cung vao file excel
            excel1.setCellData(String.valueOf(pcanca), i, 9);
        }
    }

    public String tinhMuccapDienthoai(String fileInput, String chucDanh) throws Exception {
        String pc = "";

        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileInput;
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(filePath, "Sheet1");

        List<PhuCapDienThoaiModel> listModel = new ArrayList<>();
        for (int i = 1; i < 44; i++) {
            String chucdanh = readDataFromExcel(fileInput, "Chức danh", i);
            String phuccap = readDataFromExcel(fileInput, "Phụ cấp", i);

            PhuCapDienThoaiModel model = new PhuCapDienThoaiModel.Builder()
                    .setchucdanh(chucdanh)
                    .setphucap(phuccap)
                    .build();
            listModel.add(model);
        }
        List<PhuCapDienThoaiModel> pcmodel = listModel.stream().filter(x -> x.chucdanh.equals(chucDanh)).collect(Collectors.toList());
        if (pcmodel.isEmpty()) {
            pc = "0";
        } else {
            pc = pcmodel.get(0).phucap;
        }
        return pc;
    }

    public void tinhPhucapDienthoai(String fileInput, String fileOutput, String filePhucapdt) throws Exception {
        ExcelHelpers excel1 = setExcellFile(fileOutput);
        excel1.setCellData("Phụ cấp điện thoại", 0, 10);


        for (int i = 1; i < getLastRowNo(fileInput); i++) {
            ngaycongtt = Double.parseDouble(readDataFromExcel(fileInput, "Congthucte", i));
            ngaycongchedo = Double.parseDouble(readDataFromExcel(fileInput, "Congchedo", i));
            chucdanh = readDataFromExcel(fileInput, "Chucdanh", i);

            //get phu cap
            double phucap = Double.parseDouble(tinhMuccapDienthoai(filePhucapdt, chucdanh));

            pcdienthoai = phucapdienthoai(phucap, ngaycongtt, ngaycongchedo);
            Logger.info("Phụ cấp điện thoại tính được của nv thứ " + i + " là: " + String.valueOf(pcdienthoai));

            //verify luong input voi luong output
            double pcdtinput = Double.parseDouble(readDataFromExcel(fileInput, "Dienthoai", i));
            Logger.info("Phụ cấp điện thoại input của nv thứ " + i + " là: " + String.valueOf(pcdtinput));
            verifyLuong(pcdtinput, pcdienthoai);

            //ghi thong tin luong cung vao file excel
            excel1.setCellData(String.valueOf(pcdienthoai), i, 10);
        }
    }

    public void tinhTongphucap(String fileInput, String fileOutput) throws Exception {
        ExcelHelpers excel1 = setExcellFile(fileOutput);
        excel1.setCellData("Tổng phụ cấp", 0, 11);

        for (int i = 1; i < getLastRowNo(fileInput); i++) {
            pcdoanthe = Double.parseDouble(readDataFromExcel(fileOutput, "Phụ cấp đoàn thể", i));
            pcanca = Double.parseDouble(readDataFromExcel(fileOutput, "Phụ cấp ăn ca", i));
            pcdienthoai = Double.parseDouble(readDataFromExcel(fileOutput, "Phụ cấp điện thoại", i));

            tongphucap = tongphucap(pcdoanthe, pcanca, pcdienthoai);
            Logger.info("Tổng phụ cấp tính được của nv thứ " + i + " là: " + String.valueOf(tongphucap));

            //verify luong input voi luong output
            double tongpcinput = Double.parseDouble(readDataFromExcel(fileInput, "Tongphucap", i));
            Logger.info("Tổng phụ cấp input của nv thứ " + i + " là: " + String.valueOf(tongpcinput));
            verifyLuong(tongpcinput, tongphucap);

            //ghi thong tin luong cung vao file excel
            excel1.setCellData(String.valueOf(tongphucap), i, 11);
        }
    }

    public void tinhTongthunhap(String fileInput, String fileOutput) throws Exception {
        ExcelHelpers excel1 = setExcellFile(fileOutput);
        excel1.setCellData("Tổng thu nhập", 0, 12);

        for (int i = 1; i < getLastRowNo(fileInput); i++) {
            luongconlai = Double.parseDouble(readDataFromExcel(fileOutput, "Lương còn lại", i));
            tongphucap = Double.parseDouble(readDataFromExcel(fileOutput, "Tổng phụ cấp", i));

            tongthunhap = tongthunhap(luongconlai, tongphucap);
            Logger.info("Tổng thu nhập tính được của nv thứ " + i + " là: " + String.valueOf(tongthunhap));

            //verify luong input voi luong output
            double tongtninput = Double.parseDouble(readDataFromExcel(fileInput, "Tongthunhap", i));
            Logger.info("Tổng thu nhập input của nv thứ " + i + " là: " + String.valueOf(tongtninput));
            verifyLuong(tongtninput, tongthunhap);

            //ghi thong tin luong cung vao file excel
            excel1.setCellData(String.valueOf(tongthunhap), i, 12);
        }
    }

    public void tinhSotienconlai(String fileInput, String fileOutput) throws Exception {
        ExcelHelpers excel1 = setExcellFile(fileOutput);
        excel1.setCellData("Số tiền còn lại", 0, 13);

        for (int i = 1; i < getLastRowNo(fileInput); i++) {
            tongthunhap = Double.parseDouble(readDataFromExcel(fileOutput, "Tổng thu nhập", i));

            congdoan = Double.parseDouble(readDataFromExcel(fileInput, "Congdoanphi", i));
            truythucongdoan = Double.parseDouble(readDataFromExcel(fileInput, "Truythucongdoan", i));
            khautru = Double.parseDouble(readDataFromExcel(fileInput, "Khautru", i));
            khautrugiuluong = Double.parseDouble(readDataFromExcel(fileInput, "Khautrugiuluong", i));

            sotienconlai = sotienconlai(tongthunhap, congdoan, truythucongdoan, khautru, khautrugiuluong);
            Logger.info("Số tiền còn lại tính được của nv thứ " + i + " là: " + String.valueOf(sotienconlai));

            //verify luong input voi luong output
            double sotienClinput = Double.parseDouble(readDataFromExcel(fileInput, "Sotienconlai", i));
            Logger.info("Số tiền còn lại input của nv thứ " + i + " là: " + String.valueOf(sotienClinput));
            verifyLuong(sotienClinput, sotienconlai);

            //ghi thong tin luong cung vao file excel
            excel1.setCellData(String.valueOf(sotienconlai), i, 13);
        }
    }

    public double luongCung(String nhomLuong) {
        switch (nhomLuong) {
            case "HAY": {
                luongcung = Math.round(luongCb * tylehuong * ki * (ngaycongtt + ngaynghicoluong + ngaytruc) / ngaycongchedo) * 10 / 10;
                break;
            }
            case "PC": {
                luongcung = Math.round((luongCb * 30 / 100 + luongCb * 70 / 100 * kpi) * tylehuong * (ngaycongtt + ngaynghicoluong + ngaytruc) / ngaycongchedo) * 10 / 10;
                break;
            }
            case "CN": {
                luongcung = Math.round((luongCb * 70 / 100 * ki + luongCb * 30 / 100 * kpi * ki) * tylehuong * (ngaycongtt + ngaynghicoluong + ngaytruc) / ngaycongchedo) * 10 / 10;
                break;
            }
            case "AC": {
                luongcung = Math.round(luongCb * tylehuong * ki * (ngaycongtt + ngaynghicoluong + ngaytruc) / ngaycongchedo + Math.min(luongCb * 20 / 100 * (ngaycongtt + ngaynghicoluong + ngaytruc) / ngaycongchedo, kpi * luongCb * 20 / 100 * (ngaycongtt + ngaynghicoluong + ngaytruc) / ngaycongchedo)) * 10 / 10;
                break;
            }
            case "KD": {
                luongcung = Math.round(luongCb * kpi * (ngaycongtt + ngaynghicoluong + ngaytruc) / ngaycongchedo) * 10 / 10;
                break;
            }
        }
        return luongcung;
    }

    public void verifyLuong(double luonginput, double luongoutput) {
        Assert.assertEquals(luonginput, luongoutput, 0.00);
    }

    public double luongThamNien(double namcongtac) {
        double luongthamnien = 0;
        double muccap = 0;

        if (namcongtac < 3) {
            muccap = 0;
        } else if (namcongtac < 5 & namcongtac >= 3) {
            muccap = 0.03;
        } else {
            muccap = 0.05;
        }

        luongthamnien = Math.round(luongCb * muccap * ki * ngaycongtinhluong / ngaycongchedo) * 10 / 10;
        return luongthamnien;
    }

    public double luonglephep(String nhomLuong) {
        double luonglephep = 0;
        if (nhomLuong.equals("CN")) {
            luonglephep = Math.round(luongCb * ngaycongpheple / ngaycongchedo) * 10 / 10;
        } else {
            luonglephep = Math.round(luongbaohiem * ngaycongpheple / ngaycongchedo) * 10 / 10;
        }
        return luonglephep;
    }

    public double boiduongtrucle() {
        double luongtrucle = 0;
        luongtrucle = Math.round(luongbaohiem * ngaycongtrucle / ngaycongchedo * 200 / 100) * 10 / 10;
        return luongtrucle;
    }

    public double tongLuong(double number1, double number2, double number3, double number4, double number5, double number6, double number7, double number8, double number9) {
        double tongluong = 0;
        tongluong = Math.round(number1 + number2 + number3 + number4 + number5 + number6 + number7 + number8 + number9) * 10 / 10;
        return tongluong;
    }

    public double thue(String doituong, double tongluong, double anca, double dienthoai, double doanthe, double bhxh, double bhyt, double bhtn, double truythu, double noNPT) {
        double thue = 0;
        if (doituong.equals("OS") || doituong.equals("Thử việc")) {
            thue = Math.round(tongluong * 10 / 100) * 10 / 10;
        } else {
            double thunhapchuithue;
            thunhapchuithue = tongluong + (anca + dienthoai + doanthe) - (bhxh + bhyt + bhtn + truythu) - anca - dienthoai - 11000000 - noNPT * 4400000;
            if (thunhapchuithue <= 5000000) {
                thue = Math.round(thunhapchuithue * 5 / 100) * 10 / 10;
            } else if (5000000 < thunhapchuithue && thunhapchuithue <= 10000000) {
                thue = Math.round(thunhapchuithue * 10 / 100 - 250000) * 10 / 10;
            } else if (10000000 < thunhapchuithue && thunhapchuithue <= 18000000) {
                thue = Math.round(thunhapchuithue * 15 / 100 - 750000) * 10 / 10;
            } else if (18000000 < thunhapchuithue && thunhapchuithue <= 32000000) {
                thue = Math.round(thunhapchuithue * 20 / 100 - 1650000) * 10 / 10;
            } else if (32000000 < thunhapchuithue && thunhapchuithue <= 52000000) {
                thue = Math.round(thunhapchuithue * 25 / 100 - 3250000) * 10 / 10;
            } else if (52000000 < thunhapchuithue && thunhapchuithue <= 80000000) {
                thue = Math.round(thunhapchuithue * 30 / 100 - 5850000) * 10 / 10;
            } else {
                thue = Math.round(thunhapchuithue * 35 / 100 - 9850000) * 10 / 10;
            }
        }
        return thue;
    }

    public double luongconlai(double tongluong, double bhxh, double bhyt, double bhtn, double truythubhxh, double thue, double quydenon, double truythuthue) {
        double luongCl = 0;
        luongCl = Math.round(tongluong - (bhxh + bhyt + bhtn + truythubhxh + thue + quydenon + truythuthue)) * 10 / 10;
        return luongCl;
    }

    public double phucapdoanthe(double heso) {
        double pc = 0;
        pc = Math.round(heso * 1490000) * 10 / 10;
        return pc;
    }

    public double phucapanca(double congthucte, double congchedo) {
        double pc = 0;
        pc = Math.round(congthucte / congchedo * 680000) * 10 / 10;
        return pc;
    }

    public double phucapdienthoai(double muccap, double congthucte, double congchedo) {
        double pc = 0;
        pc = Math.round(muccap * congthucte / congchedo) * 10 / 10;
        return pc;
    }

    public double tongphucap(double pcdoanthe, double pcanca, double pcdt) {
        double pc = 0;
        pc = Math.round(pcdoanthe + pcanca + pcdt) * 10 / 10;
        return pc;
    }

    public double tongthunhap(double luongconlai, double tongphucap) {
        double pc = 0;
        pc = Math.round(luongconlai + tongphucap) * 10 / 10;
        return pc;
    }

    public double sotienconlai(double tongthunhap, double congdoan, double truythucongdoan, double khautru, double khautrugiuluong) {
        double pc = 0;
        pc = Math.round(tongthunhap - congdoan - truythucongdoan - khautru - khautrugiuluong) * 10 / 10;
        return pc;
    }

}
