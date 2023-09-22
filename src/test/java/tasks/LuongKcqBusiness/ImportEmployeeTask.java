package tasks.LuongKcqBusiness;

import common.Utilities;
import config.constant;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import tasks.Common.CallAPICommon;
import tasks.TinhCuocBussiness.TinhCuocBussinessTask;

import java.io.File;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ImportEmployeeTask implements Task {
    @Override
    public <T extends Actor> void performAs(T t) {

    }

    public static ImportEmployeeTask callApiImport(File filePath, String orgCode, String periodId)
    {
        String endPoint = constant.upload_employee_data;
        JsonPath responseEntry = CallAPICommon.callAPIWithFormData(endPoint,filePath,orgCode,periodId);
        Serenity.setSessionVariable("responseImportEmployee").to(responseEntry);
        return instrumented(ImportEmployeeTask.class,filePath,orgCode,periodId);
    }

    public static ImportEmployeeTask callApiImportKHM(File filePath, String orgCode, String periodId)
    {
        String endPoint = constant.upload_new_customer;
        JsonPath responseEntry = CallAPICommon.callAPIWithFormData(endPoint,filePath,orgCode,periodId);
        Serenity.setSessionVariable("responseImportKHM").to(responseEntry);
        return instrumented(ImportEmployeeTask.class,filePath,orgCode,periodId);
    }

    public static ImportEmployeeTask callApiImportSalary(File filePath, String orgCode, String periodId)
    {
        String endPoint = constant.upload_arrears_salary;
        JsonPath responseEntry = CallAPICommon.callAPIWithFormData(endPoint,filePath,orgCode,periodId);
        Serenity.setSessionVariable("responseImportSalary").to(responseEntry);
        return instrumented(ImportEmployeeTask.class,filePath,orgCode,periodId);
    }
}
