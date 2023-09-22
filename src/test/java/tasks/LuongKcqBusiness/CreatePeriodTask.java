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

public class CreatePeriodTask implements Task {
    @Override
    public <T extends Actor> void performAs(T t) {

    }

    public static ImportEmployeeTask callApiCreatePeriod(Map<String, String> map)
    {
        String endPoint = constant.create_period;
        Object entry = Utilities.getObjectFromString("LuongKCQ\\Tao_ky_luong",map);

        JsonPath responseEntry = CallAPICommon.callApiPostRequest(endPoint,entry);
        Serenity.setSessionVariable("responseCreatePeriod").to(responseEntry);
        return instrumented(ImportEmployeeTask.class,map);
    }
}
