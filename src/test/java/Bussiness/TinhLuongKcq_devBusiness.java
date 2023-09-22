package Bussiness;

import bll.*;
import common.Logger;
import helpers.ExcelHelpers;
import io.cucumber.java.Before;
import io.restassured.path.json.JsonPath;
import model.EmployeeModel;
import model.TinhCuocModel;
import model.ValueConfigModel;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import tasks.LuongKcqBusiness.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TinhLuongKcq_devBusiness {
    private Actor auto = Actor.named("Auto");
    private JsonPath responsePeriod;
    private String periodId;
    private String ObjIdNo;
    private JsonPath responseEmp;
    private String empId;
    private String signeddate;
    private String contractType;
    private String position_dec;
    private double ngaycongtt;
    private double ngaynghicoluong;
    private double ngaycongpheple;
    private double ngaycongtrucle;
    private double ngaycongchedo;
    private double ngaycongtinhluong;
    private double luongmuctieu;
    private String loaihd;
    private double hesotv;
    private String nhomtinhluong;
    private double pcnguoipt;
    private double pcantrua;
    private double luongcoban;
    private double pcdt;
    private double namthamnien;
    private double ki;
    private double kpi;
    private double luongbosung;
    private double pcqlkd;
    private double thuonghtkhkdl;
    private double truythubhxh;
    private double thuquydenon;
    private double truythuthue;
    private double truythuccongdoan;
    private double khautru;
    private double giuluong;
    private double luongbaohiem;
    private double hesodangdoan;


    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }


    public double getphucapdt(String hesodt, String chucdanh) {
        double pcdt = 0;
        JSONObject json = new JSONObject(hesodt);
        pcdt = json.getDouble(chucdanh);
        return pcdt;
    }

    public void callApiImport(String fileName, String ObjIdNo, String periodId) {
        String file = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileName;
        File filePath = new File(file);
        auto.attemptsTo(ImportEmployeeTask.callApiImport(filePath, ObjIdNo, periodId));

        //verify code cua api
        JsonPath response = Serenity.sessionVariableCalled("responseImportEmployee");
        Assert.assertEquals(response.getString("code"),"200");
    }

    public void callApiImportKHM(String fileName, String ObjIdNo, String periodId) {
        String file = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileName;
        File filePath = new File(file);
        auto.attemptsTo(ImportEmployeeTask.callApiImportKHM(filePath, ObjIdNo, periodId));

        //verify code cua api
        JsonPath response = Serenity.sessionVariableCalled("responseImportKHM");
        Assert.assertEquals(response.getString("code"),"200");
    }

    public void callApiImportSalary(String fileName, String ObjIdNo, String periodId) {
        String file = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileName;
        File filePath = new File(file);
        auto.attemptsTo(ImportEmployeeTask.callApiImportSalary(filePath, ObjIdNo, periodId));

        //verify code cua api
        JsonPath response = Serenity.sessionVariableCalled("responseImportSalary");
        Assert.assertEquals(response.getString("code"),"200");
    }

    public void callApiCreatePeriod(String namePeriod, String startDate, String endDate) {
        //them buoc kiem tra neu ky luong chua ton tai thi moi tao
        Map<String, String> map = new HashMap<>();
        map.put("name", namePeriod);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        auto.attemptsTo(CreatePeriodTask.callApiCreatePeriod(map));
        responsePeriod = Serenity.sessionVariableCalled("responseCreatePeriod");
        periodId = responsePeriod.getString("result.id");
        Logger.endpoint("id ky luong la: " + periodId);
    }

    public String callApiGetOrg(String orgId) {
        String authen = "VlRfMTk2OTYxOktwbWdAMjAyMkA=";
        Map<String, String> map = new HashMap<>();
        map.put("ObjId", orgId);
        auto.attemptsTo(GetOrgFromSapTask.callApiGetOrg(map, authen));
        JsonPath response = Serenity.sessionVariableCalled("responseGetOrgSap");

        //verify msgcode cua api
        Assert.assertEquals(response.getString("msgCode"),"200");

        //tim phan tu co name la kcq, va so sanh parentid voi orgId input
        List<Map> books = response.param("name", "TCQP").get("data.findAll { data -> data.ShortName == name }");
        String ParentId = books.get(0).get("ParentId").toString();
        Assert.assertEquals(orgId, ParentId);

        //lay thong tin orgid cua kcq
        ObjIdNo = books.get(0).get("ObjIdNo").toString();
        Logger.info("Id kcq la: " + ObjIdNo);
        return ObjIdNo;
    }

    public JsonPath callApiGetEmp(String date, String ObjIdNo) {
        String authen = "VlRfMTk2OTYxOktwbWdAMjAyMkA=";
        Map<String, String> map = new HashMap<>();
        map.put("ObjId", ObjIdNo);
        map.put("EffectiveDate", date);
        auto.attemptsTo(GetEmpFromSapTask.callApiGetEmp(map, authen));
        responseEmp = Serenity.sessionVariableCalled("responseGetEmpSap");

        //verify msgcode cua api
        Assert.assertEquals(responseEmp.getString("msgCode"),"200");
        return responseEmp;
    }

    public void verifySync(JsonPath responseEmp, String startdatePeriod, String enddatePeriod, String periodId) {
        //lay so luong nhan vien
        int noNumber = responseEmp.getInt("metadata.totalElements");
        Logger.info("So luong nhan vien la: " + noNumber);

        for (int i = 0; i < noNumber; i++) {

//            if (responseEmp.getString("data[" + i + "].EmployeeId").charAt(0) == '0' && responseEmp.getString("data[" + i + "].EmployeeId").charAt(1) != '0') {
//                StringBuilder sb = new StringBuilder(responseEmp.getString("data.EmployeeId[" + i + "]"));
//                empId = sb.substring(1);
//                System.out.println("empid la: " + empId);
//            } else if (responseEmp.getString("data[" + i + "].EmployeeId").charAt(0) == '0' && responseEmp.getString("data[" + i + "].EmployeeId").charAt(1) == '0') {
//                StringBuilder sb = new StringBuilder(responseEmp.getString("data[" + i + "].EmployeeId"));
//                empId = sb.substring(2);
//                System.out.println("empid la: " + empId);
//            } else if (responseEmp.getString("data[" + i + "].EmployeeId").charAt(0) != '0') {
//                empId = responseEmp.getString("data[" + i + "].EmployeeId");
//                System.out.println("empid la: " + empId);
//            }
            getEmp(responseEmp,i);

            //verify manv, ten nv
            EmployeeModel model = EMPLOYEEbll.getEmployeeInfo(empId);
            Assert.assertEquals(model.employeeId, empId);
            Assert.assertEquals(model.fullName, responseEmp.getString("data[" + i + "].Personel0002.LastName").replace("[", "").replace("]", "") + " " + responseEmp.getString("data[" + i + "].Personel0002.FirstName").replace("[", "").replace("]", ""));


            //verify thong tin chuc danh, doi tuong (chia ra 1 hdld va nhieu hdld) 011350: moi dong bo dc 1 hd, truong EESubGroup chua day dc
            EmployeeModel workingmodel = WORKING_PROCESSbll.getWorkingInfo(empId, periodId);
            if (!responseEmp.getString("data[" + i + "].OrgAssignment0001").contains("], [")) {
                if (responseEmp.getString("data[" + i + "].OrgAssignment0001.PositionDescription").contains("null") || !responseEmp.getString("data[" + i + "].OrgAssignment0001").contains("PositionDescription")) {
                    System.out.println("khong co chuc danh  ");
                    position_dec = "";
                } else if (responseEmp.getString("data[" + i + "].OrgAssignment0001.EESubGroup").contains("null")) {
                    System.out.println("khong thuoc doi tuong nao  ");
                    contractType = "";
                } else if (!responseEmp.getString("data[" + i + "].OrgAssignment0001.PositionDescription").contains("null") && !responseEmp.getString("data[" + i + "].OrgAssignment0001.EESubGroup").contains("null")) {
                    Assert.assertEquals(workingmodel.position_dec, responseEmp.getString("data[" + i + "].OrgAssignment0001.PositionDescription").replace("[", "").replace("]", ""));
                    position_dec = workingmodel.position_dec;
                    Assert.assertEquals(workingmodel.contractType, responseEmp.getString("data[" + i + "].OrgAssignment0001.EESubGroup").replace("[", "").replace("]", ""));
                    contractType = workingmodel.contractType;
                } else if (responseEmp.getString("data[" + i + "].OrgAssignment0001.PositionDescription").contains("null") && !responseEmp.getString("data[" + i + "].OrgAssignment0001.EESubGroup").contains("null")) {
                    Assert.assertEquals(workingmodel.contractType, responseEmp.getString("data[" + i + "].OrgAssignment0001.EESubGroup").replace("[", "").replace("]", ""));
                    contractType = workingmodel.contractType;
                } else if (!responseEmp.getString("data[" + i + "].OrgAssignment0001.PositionDescription").contains("null") && responseEmp.getString("data[" + i + "].OrgAssignment0001.EESubGroup").contains("null")) {
                    Assert.assertEquals(workingmodel.position_dec, responseEmp.getString("data[" + i + "].OrgAssignment0001.PositionDescription").replace("[", "").replace("]", ""));
                    position_dec = workingmodel.position_dec;
                }
            } else {
                String[] startdate = responseEmp.getString("data[" + i + "].OrgAssignment0001.StartDate").replace("[", "").replace("]", "").split(", ");
                String[] enddate = responseEmp.getString("data[" + i + "].OrgAssignment0001.EndDate").replace("[", "").replace("]", "").split(", ");
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate d1 = LocalDate.parse(startdatePeriod, df);
                LocalDate d2 = LocalDate.parse(enddatePeriod, df);

                for (int j = 0; j < startdate.length; j++) {
                    LocalDate start = LocalDate.parse(startdate[j], df);
                    LocalDate end = LocalDate.parse(enddate[j], df);

                    if ((start.compareTo(d1) <= 0 && d2.compareTo(end) <= 0) || (d1.compareTo(end) < 0 && start.compareTo(d1) < 0) || (start.compareTo(d2) < 0 && d2.compareTo(end) < 0)) {
                        if (!responseEmp.getString("data[" + i + "].OrgAssignment0001[" + j + "]").contains("PositionDescription")) {
                            System.out.println("khong co chuc danh   ");
                            position_dec = "";
                        } else {
//                                EmployeeModel workingmodel1 = WORKING_PROCESSbll.getWorkingByEmpMutiContract(empId,periodId,1,j);
                            Assert.assertEquals(workingmodel.position_dec, responseEmp.getString("data[" + i + "].OrgAssignment0001[" + j + "].PositionDescription"));
                            position_dec = workingmodel.position_dec;
                        }

                        if (!responseEmp.getString("data[" + i + "].OrgAssignment0001[" + j + "]").contains("EESubGroup")) {
                            System.out.println("khong thuoc doi tuong nao   ");
                            contractType = "";
                        } else {
                            Assert.assertEquals(workingmodel.contractType, responseEmp.getString("data[" + i + "].OrgAssignment0001[" + j + "].EESubGroup").replace("[", "").replace("]", ""));
                            contractType = workingmodel.contractType;
                        }
                    }
                }
            }

            //120472: ko co ngay ky, 108438: co nhieu hd (verify ngay ky hdld)
            if (responseEmp.getString("data["+ i + "].SubGroup0016.SignedDate") == null || position_dec.equals("H3") || position_dec.equals("H7") ){
                System.out.println("Khong co HDLD:   ");
                signeddate = "";
                namthamnien = 0;
            } else if (responseEmp.getString("data["+ i + "].SubGroup0016.SignedDate").contains(",")) {
                Assert.assertEquals(model.contractDate, responseEmp.getString("data["+ i + "].SubGroup0016.SignedDate").substring(1,11));
                signeddate = model.contractDate;
                System.out.println("ngay ky hd tren sap la :   " + responseEmp.getString("data["+ i + "].SubGroup0016.SignedDate"));

                //tinh so nam tham nien tu ngay ky hdld
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate d1 = LocalDate.parse(startdatePeriod, df);
                LocalDate d3 = LocalDate.parse(signeddate, df);

                double datediff = ChronoUnit.DAYS.between(d1, d3) + 1;
                namthamnien = (int) Math.round(datediff / 365);
                System.out.println("so nam tham nien la: " + namthamnien);

            } else if (responseEmp.getString("data["+ i + "].SubGroup0016.SignedDate") != null) {
                Assert.assertEquals(model.contractDate, responseEmp.getString("data["+ i + "].SubGroup0016.SignedDate"));
                signeddate = model.contractDate;
                System.out.println("ngay ky hd tren sap la :   " + responseEmp.getString("data["+ i + "].SubGroup0016.SignedDate"));

                //tinh so nam tham nien tu ngay ky hdld
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate d1 = LocalDate.parse(startdatePeriod, df);
                LocalDate d2 = LocalDate.parse(signeddate, df);

                double datediff = ChronoUnit.DAYS.between(d2, d1) + 1;
                namthamnien = (int) Math.round(datediff / 365);
                System.out.println("so nam tham nien la: " + namthamnien);
            }

            //verify thong tin ngay cong (moi tinh chua verify do dev con loi)
            if (!responseEmp.getString("data[" + i + "]").contains("TimeAttendance9012")) {
                ngaycongtt = 0;
                ngaycongpheple = 0;
                ngaynghicoluong = 0;
                ngaycongtrucle = 0;
                ngaycongtinhluong = 0;
            } else {
                String[] startdate = responseEmp.getString("data[" + i + "].TimeAttendance9012.StartDate").replace("[", "").replace("]", "").split(", ");
                String[] enddate = responseEmp.getString("data[" + i + "].TimeAttendance9012.EndDate").replace("[", "").replace("]", "").split(", ");
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate d1 = LocalDate.parse(startdatePeriod, df);
                LocalDate d2 = LocalDate.parse(enddatePeriod, df);

                for (int j = 0; j < startdate.length; j++) {
                    LocalDate start = LocalDate.parse(startdate[j], df);
                    LocalDate end = LocalDate.parse(enddate[j], df);

                    if ((d1.compareTo(start) <= 0 && end.compareTo(d2) <= 0)) {
                        if (!responseEmp.getString("data[" + i + "].TimeAttendance9012[" + j + "]").contains("MaChamCong")) {
                            System.out.println("Khong co cham cong");
                            ngaycongtt += 0;
                            ngaycongpheple += 0;
                            ngaynghicoluong += 0;
                            ngaycongtrucle += 0;
                        } else {
                            switch (responseEmp.getString("data[" + i + "].TimeAttendance9012[" + j + "].MaChamCong")) {
                                case "X:8": {
                                    ngaycongtt += 1;
                                    break;
                                }
                                case "X:4":
                                case "Ro:4,X:4":
                                case "X:4,Ro:4": {
                                    ngaycongtt += 0.5;
                                    break;
                                }
                                case "P:8":
                                case "L:8": {
                                    ngaycongpheple += 1;
                                    break;
                                }
                                case "X:4,P:4":
                                case "P:4,X:4": {
                                    ngaycongtt += 0.5;
                                    ngaycongpheple += 0.5;
                                    break;
                                }
                                case "GL:4,L:4":
                                case "L:4,GL:4": {
                                    ngaycongpheple += 0.5;
                                    ngaycongtrucle += 0.5;
                                    break;
                                }
                                case "DL:8":
                                case "Rv:8": {
                                    ngaynghicoluong += 1;
                                    break;
                                }
                                case "L:8,GL:8":
                                case "GL:8,L:8":
                                case "NB:8,GN:8":
                                case "GN:8,NB:8": {
                                    ngaycongtrucle += 1;
                                    break;
                                }
                                case "GN:4,NB:4":
                                case "NB:4,GN:4": {
                                    ngaycongtrucle += 0.5;
                                    break;
                                }
                                default: {
                                    ngaycongtt += 0;
                                    ngaycongpheple += 0;
                                    ngaynghicoluong += 0;
                                    ngaycongtrucle += 0;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            ngaycongtinhluong = ngaycongtt + ngaycongpheple + ngaynghicoluong + ngaycongtrucle;
            ngaycongchedo = countNgaycongchedo(startdatePeriod);

//            Assert.assertEquals(workingmodel.actual_workday, ngaycongtt);
//            Assert.assertEquals(workingmodel.holidays_absence, ngaycongpheple);
//            Assert.assertEquals(workingmodel.holidays_workday, ngaynghicoluong);
//            Assert.assertEquals(workingmodel.paid_leave_absence, ngaycongtrucle);
//            Assert.assertEquals(workingmodel.total_workday, ngaycongtinhluong);
//            Assert.assertEquals(getDatePeriod(startdatePeriod, "period_workday"), ngaycongchedo);

            System.out.println("Ngay cong thuc te cua nhan vien thu " + i + " la: " + ngaycongtt);
            System.out.println("Ngay cong le phep cua nhan vien thu " + i + " la: " + ngaycongpheple);
            System.out.println("Ngay cong nghi co luong cua nhan vien thu " + i + " la: " + ngaynghicoluong);
            System.out.println("Ngay cong truc le cua nhan vien thu " + i + " la: " + ngaycongtrucle);
            System.out.println("Ngay cong tinh luong cua nhan vien thu " + i + " la: " + ngaycongtinhluong);

            //verify luong muc tieu
            if (!responseEmp.getString("data[" + i + "]").contains("BasicPay0008")) {
                luongmuctieu = 0;
                Assert.assertEquals(workingmodel.base_salary, luongmuctieu, 0.0);
            } else {
                String gradeId = responseEmp.getString("data[" + i + "].BasicPay0008.BaseSalaryGradeId").replace("[", "").replace("]", "");
                String stepId = responseEmp.getString("data[" + i + "].BasicPay0008.BaseSalaryStepId").replace("[", "").replace("]", "");
                String tableId = responseEmp.getString("data[" + i + "].BasicPay0008.BaseSalaryTableId").replace("[", "").replace("]", "");
                if (gradeId.equals("NA") || stepId.equals("NA") || tableId.equals("NA")) {
                    luongmuctieu = 0.0;
                    Assert.assertEquals(workingmodel.base_salary, luongmuctieu, 0.0);
                } else {
                    luongmuctieu = Double.parseDouble(BASE_SALARY_MAPPINGbll.getBaseSalaryInfo(gradeId, stepId, tableId));
                    Formatter formatter = new Formatter();
                    formatter.format("%.2f", workingmodel.base_salary);
                    Assert.assertEquals(formatter.toString(), BASE_SALARY_MAPPINGbll.getBaseSalaryInfo(gradeId, stepId, tableId));
                }
            }

            //get thong tin config
            if (!position_dec.equals("") && !contractType.equals("")) {
                ValueConfigModel configmodel = getInfoConfig(position_dec, contractType, "9004422");
                loaihd = configmodel.loaihdd;
                hesotv = configmodel.hesotv;
                nhomtinhluong = configmodel.nhomtinhluong;
                pcnguoipt = configmodel.nguoipt;
                pcantrua = configmodel.pcantrua;
                luongcoban = configmodel.luongcoban;
                pcdt = configmodel.pcdt;
            } else if (position_dec.equals("") && !contractType.equals("")) {
                pcdt = 0.0;
                loaihd = getloaihdld(SYSTEM_PARAMETERbll.getConfigByName("Loại hợp đồng"), contractType);
                luongcoban = Double.parseDouble(SYSTEM_PARAMETERbll.getConfigByName("Mức lương cơ sở/cơ bản"));
                pcnguoipt = Double.parseDouble(SYSTEM_PARAMETERbll.getConfigByName("Cấu hình người phụ thuộc"));
                nhomtinhluong = getnhomtinhluong(SYSTEM_PARAMETERbll.getConfigByName("Nhóm tính lương"), "9004422");
                pcantrua = Double.parseDouble(SYSTEM_PARAMETERbll.getConfigByName("Cấu hình phụ cấp ăn trưa"));
                hesotv = Double.parseDouble(SYSTEM_PARAMETERbll.getConfigByName("Hệ số thử việc"));
            } else if (!position_dec.equals("") && contractType.equals("")) {
                loaihd = "";
                pcdt = getpcdt(SYSTEM_PARAMETERbll.getConfigByName("Cấu hình phụ cấp điện thoại"), "9004422");
                luongcoban = Double.parseDouble(SYSTEM_PARAMETERbll.getConfigByName("Mức lương cơ sở/cơ bản"));
                pcnguoipt = Double.parseDouble(SYSTEM_PARAMETERbll.getConfigByName("Cấu hình người phụ thuộc"));
                nhomtinhluong = getnhomtinhluong(SYSTEM_PARAMETERbll.getConfigByName("Nhóm tính lương"), "9004422");
                pcantrua = Double.parseDouble(SYSTEM_PARAMETERbll.getConfigByName("Cấu hình phụ cấp ăn trưa"));
                hesotv = Double.parseDouble(SYSTEM_PARAMETERbll.getConfigByName("Hệ số thử việc"));
            } else if (position_dec.equals("") && contractType.equals("")) {
                loaihd = "";
                pcdt = 0.0;
                ValueConfigModel configmodel = getInfoConfig(position_dec, contractType, "9004422");
                hesotv = configmodel.hesotv;
                nhomtinhluong = configmodel.nhomtinhluong;
                pcnguoipt = configmodel.nguoipt;
                pcantrua = configmodel.pcantrua;
                luongcoban = configmodel.luongcoban;
            }
            System.out.println("Loại hd...." + loaihd);
            System.out.println("Hệ số thử việc...." + hesotv);
            System.out.println("Nhóm tính luong...." + nhomtinhluong);
            System.out.println("Hệ số npt...." + pcnguoipt);
            System.out.println("Pc ăn trưa...." + pcantrua);
            System.out.println("Lương cơ bản...." + luongcoban);
            System.out.println("Pc điện thoại...." + pcdt);


        }


    }

    public void verifyImportInfo(JsonPath responseEmp,String filename,String periodId) throws Exception {
        int noNumber = responseEmp.getInt("metadata.totalElements");

        for (int i = 0; i < noNumber; i++) {
            empId = getEmp(responseEmp,i);

            //verify thong tin import vao db
            EmployeeModel empInputModel = EMPLOYEE_DATA_INPUTbll.getEmpInputInfo(empId,periodId);
            ki = Double.parseDouble(readDataFromExcel(filename,"Ki cá nhân",i+1));
            kpi = Double.parseDouble(readDataFromExcel(filename,"Điểm KPI",i+1));
            luongbosung = Double.parseDouble(readDataFromExcel(filename,"Lương bổ sung",i+1));
            pcqlkd = Double.parseDouble(readDataFromExcel(filename,"Phụ cấp quản lý kinh doanh",i+1));
            thuonghtkhkdl = Double.parseDouble(readDataFromExcel(filename,"Thưởng hoàn thành kế hoạch kinh doanh",i+1));
            truythubhxh = Double.parseDouble(readDataFromExcel(filename,"Truy Thu tiền BHXH",i+1));
            thuquydenon = Double.parseDouble(readDataFromExcel(filename,"Thu quỹ đền ơn đáp nghĩa",i+1));
            truythuthue = Double.parseDouble(readDataFromExcel(filename,"Truy thu, truy lĩnh thuế",i+1));
            truythuccongdoan = Double.parseDouble(readDataFromExcel(filename,"TRUY THU TRUY LĨNH CÔNG ĐOÀN",i+1));
            khautru = Double.parseDouble(readDataFromExcel(filename,"Khấu trừ ",i+1));
            giuluong = Double.parseDouble(readDataFromExcel(filename,"Giữ lương",i+1));
            luongbaohiem = Double.parseDouble(readDataFromExcel(filename,"Lương bảo hiểm",i+1));
            hesodangdoan = Double.parseDouble(readDataFromExcel(filename,"Hệ số đảng đoàn",i+1));

            Assert.assertEquals(ki,empInputModel.ki,0.0);
            Assert.assertEquals(kpi,empInputModel.kpi,0.0);
            Assert.assertEquals(luongbosung,empInputModel.luongbosung,0.0);
            Assert.assertEquals(pcqlkd,empInputModel.pcqlkd,0.0);
            Assert.assertEquals(thuonghtkhkdl,empInputModel.thuonghtkhkdl,0.0);
            Assert.assertEquals(truythubhxh,empInputModel.truythubhxh,0.0);
            Assert.assertEquals(thuquydenon,empInputModel.thuquydenon,0.0);
            Assert.assertEquals(truythuthue,empInputModel.truythuthue,0.0);
            Assert.assertEquals(truythuccongdoan,empInputModel.truythuccongdoan,0.0);
            Assert.assertEquals(khautru,empInputModel.khautru,0.0);
            Assert.assertEquals(giuluong,empInputModel.giuluong,0.0);
            Assert.assertEquals(luongbaohiem,empInputModel.luongbaohiem,0.0);
            Assert.assertEquals(hesodangdoan,empInputModel.hesodangdoan,0.0);
        }
    }

    public void verifyImportKHM(JsonPath responseEmp,String filename,String periodId) throws Exception {
        int noNumber = responseEmp.getInt("metadata.totalElements");

        for (int i = 0; i < noNumber; i++) {
            empId = getEmp(responseEmp,i);

            //verify thong tin import vao db
            EmployeeModel empInputModel = EMPLOYEE_DATA_INPUTbll.getEmpInputInfo(empId,periodId);
            ki = Double.parseDouble(readDataFromExcel(filename,"Ki cá nhân",i+1));
            kpi = Double.parseDouble(readDataFromExcel(filename,"Điểm KPI",i+1));
            luongbosung = Double.parseDouble(readDataFromExcel(filename,"Lương bổ sung",i+1));
            pcqlkd = Double.parseDouble(readDataFromExcel(filename,"Phụ cấp quản lý kinh doanh",i+1));
            thuonghtkhkdl = Double.parseDouble(readDataFromExcel(filename,"Thưởng hoàn thành kế hoạch kinh doanh",i+1));
            truythubhxh = Double.parseDouble(readDataFromExcel(filename,"Truy Thu tiền BHXH",i+1));
            thuquydenon = Double.parseDouble(readDataFromExcel(filename,"Thu quỹ đền ơn đáp nghĩa",i+1));
            truythuthue = Double.parseDouble(readDataFromExcel(filename,"Truy thu, truy lĩnh thuế",i+1));
            truythuccongdoan = Double.parseDouble(readDataFromExcel(filename,"TRUY THU TRUY LĨNH CÔNG ĐOÀN",i+1));
            khautru = Double.parseDouble(readDataFromExcel(filename,"Khấu trừ ",i+1));
            giuluong = Double.parseDouble(readDataFromExcel(filename,"Giữ lương",i+1));
            luongbaohiem = Double.parseDouble(readDataFromExcel(filename,"Lương bảo hiểm",i+1));
            hesodangdoan = Double.parseDouble(readDataFromExcel(filename,"Hệ số đảng đoàn",i+1));

            Assert.assertEquals(ki,empInputModel.ki,0.0);
            Assert.assertEquals(kpi,empInputModel.kpi,0.0);
            Assert.assertEquals(luongbosung,empInputModel.luongbosung,0.0);
            Assert.assertEquals(pcqlkd,empInputModel.pcqlkd,0.0);
            Assert.assertEquals(thuonghtkhkdl,empInputModel.thuonghtkhkdl,0.0);
            Assert.assertEquals(truythubhxh,empInputModel.truythubhxh,0.0);
            Assert.assertEquals(thuquydenon,empInputModel.thuquydenon,0.0);
            Assert.assertEquals(truythuthue,empInputModel.truythuthue,0.0);
            Assert.assertEquals(truythuccongdoan,empInputModel.truythuccongdoan,0.0);
            Assert.assertEquals(khautru,empInputModel.khautru,0.0);
            Assert.assertEquals(giuluong,empInputModel.giuluong,0.0);
            Assert.assertEquals(luongbaohiem,empInputModel.luongbaohiem,0.0);
            Assert.assertEquals(hesodangdoan,empInputModel.hesodangdoan,0.0);
        }
    }
    public String getEmp(JsonPath responseEmp,int i) {
        if (responseEmp.getString("data[" + i + "].EmployeeId").charAt(0) == '0' && responseEmp.getString("data[" + i + "].EmployeeId").charAt(1) != '0') {
            StringBuilder sb = new StringBuilder(responseEmp.getString("data.EmployeeId[" + i + "]"));
            empId = sb.substring(1);
        } else if (responseEmp.getString("data[" + i + "].EmployeeId").charAt(0) == '0' && responseEmp.getString("data[" + i + "].EmployeeId").charAt(1) == '0') {
            StringBuilder sb = new StringBuilder(responseEmp.getString("data[" + i + "].EmployeeId"));
            empId = sb.substring(2);
        } else if (responseEmp.getString("data[" + i + "].EmployeeId").charAt(0) != '0') {
            empId = responseEmp.getString("data[" + i + "].EmployeeId");
        }
        return empId;
    }
    public void callApiSyncOrg(String periodId, String orgId) {
        Map<String, String> map = new HashMap<>();
        map.put("periodId", periodId);
        map.put("organizationId", orgId);
        auto.attemptsTo(SyncOrgTask.callApiSyncOrg(map));

        //verify msgcode cua api
        JsonPath response = Serenity.sessionVariableCalled("responseSyncOrg");
        Assert.assertEquals(response.getString("code"),"200");
    }

    public void callApiSyncEmp(String periodId, String orgId) {
        Map<String, String> map = new HashMap<>();
        map.put("periodId", periodId);
        map.put("organizationId", orgId);
        auto.attemptsTo(SyncEmpTask.callApiSyncEmp(map));

        //verify msgcode cua api
        JsonPath response = Serenity.sessionVariableCalled("responseSyncEmp");
        Assert.assertEquals(response.getString("code"),"200");
    }

    public String getDatePeriod(String periodId, String column) {
        String date = PERIODbll.getdate(periodId, column);
        return date;
    }

    public double countNgaycongchedo(String startdate) {
        int month = Integer.parseInt(startdate.substring(5, 7));
        int year = Integer.parseInt(startdate.substring(0, 4));
        Calendar calendar = Calendar.getInstance();
        // Note that month is 0-based in calendar, bizarrely.
        calendar.set(year, month - 1, 1);
        double daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        double count = 0;
        for (int day = 1; day <= daysInMonth; day++) {
            calendar.set(year, month - 1, day);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SUNDAY) {
                count++;
            }
        }
        double day = daysInMonth - count;
        return day;

    }

    //get thong tin cau hinh trong db
    public ValueConfigModel getInfoConfig(String PositionDes, String EESubGroup, String nhomtlcanhan) {
        ValueConfigModel model = new ValueConfigModel.Builder()
                .setloaihdld(getloaihdld(SYSTEM_PARAMETERbll.getConfigByName("Loại hợp đồng"), EESubGroup))
                .setluongcoban(Double.parseDouble(SYSTEM_PARAMETERbll.getConfigByName("Mức lương cơ sở/cơ bản")))
                .setnguoipt(Double.parseDouble(SYSTEM_PARAMETERbll.getConfigByName("Cấu hình người phụ thuộc")))
                .setnhomtinhluong(getnhomtinhluong(SYSTEM_PARAMETERbll.getConfigByName("Nhóm tính lương"), nhomtlcanhan))
                .setpcantrua(Double.parseDouble(SYSTEM_PARAMETERbll.getConfigByName("Cấu hình phụ cấp ăn trưa")))
                .setpcdt(getpcdt(SYSTEM_PARAMETERbll.getConfigByName("Cấu hình phụ cấp điện thoại"), PositionDes))
                .sethesotv(Double.parseDouble(SYSTEM_PARAMETERbll.getConfigByName("Hệ số thử việc")))
                .build();
        return model;
    }

    public double getpcdt(String cauhinhpcdt, String PositionDes) {
        try {
            JSONObject json = new JSONObject(cauhinhpcdt);
            double pcdt = json.getDouble(PositionDes);
            return pcdt;
        } catch (JSONException ex) {
            System.out.println("Chuc danh khong duoc cau hinh pcdt");
            return 0.0;
        }

    }

    public String getloaihdld(String cauhinhloaihd, String EESubGroup) {
        JSONObject json = new JSONObject(cauhinhloaihd);
        String hdld = json.getString(EESubGroup);
        return hdld;
    }

    public String getnhomtinhluong(String nhomtinhluong, String nhomtlcanhan) {
        JSONObject json = new JSONObject(nhomtinhluong);
        String hdld = json.getString(nhomtlcanhan);
        return hdld;
    }

    public ExcelHelpers setExcellFile(String fileName) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileName;
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(filePath, "Sheet1");
        return excel;
    }

    public int getLastRowNo(String fileName) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileName;

        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(filePath, "Sheet1");
        int lastRow = excel.getLastRow();
        return lastRow;
    }

    public String readDataFromExcel(String fileName, String columnName, int rownum) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\file\\Excel\\" + fileName;

        // Setup đường dẫn của file excel
        ExcelHelpers excel = new ExcelHelpers();
        excel.setExcelFile(filePath, "Sheet1");
        String value = "";
        value = excel.getCellData(columnName, rownum);
        return value;
    }

    public void writeStaffInfo(JsonPath responeEmp, String fileInput) throws Exception {
        int noNumber = responeEmp.getInt("metadata.totalElements");
        ExcelHelpers excel = setExcellFile(fileInput);
        for (int i = 0; i < noNumber; i++) {
            String empId = responeEmp.getString("data[" + i + "].EmployeeId");
            String fullname = responeEmp.getString("data[" + i + "].FullName");
            excel.setCellData(empId, i + 1, 4);
            excel.setCellData(fullname, i + 1, 5);
        }
    }

}
