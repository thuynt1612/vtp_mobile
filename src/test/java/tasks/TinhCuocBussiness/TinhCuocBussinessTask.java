package tasks.TinhCuocBussiness;

import common.Utilities;
import config.constant;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import tasks.Common.CallAPICommon;
import java.util.Map;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class TinhCuocBussinessTask implements Task {
 private Map<String, String> map;
    @Override
    public <T extends Actor> void performAs(T t) {

    }

    public static TinhCuocBussinessTask callApiTinhCuoc(Map<String, String> map)
    {
        String endPoint = constant.tinh_cuoc;
        Object entry = Utilities.getObjectFromString("TinhCuoc",map);

        JsonPath responseEntry = CallAPICommon.callApiPostRequest(endPoint,entry);
        Serenity.setSessionVariable("responseTinhCuoc").to(responseEntry);
        return instrumented(TinhCuocBussinessTask.class,map);
    }
}
