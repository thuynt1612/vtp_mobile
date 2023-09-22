package tasks.Common;


import common.Logger;
import common.Utilities;
import common.api.CommonApi;
import common.api.HeaderRequest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class CallAPICommon {
    public static String callAPIGetToken() {
        Logger.info("******************************** callAPIGetToken ******************************** ");
        String url =  helpers.PropertiesManager.getAuthenUrl();
        String username = helpers.PropertiesManager.getAuthenUsername();
        String password = helpers.PropertiesManager.getAuthenPassword();

        String postoffice = helpers.PropertiesManager.getAuthenPostOffice();
        Map<String, String> map = new HashMap<String, String>();
        map.put("username",username);
        map.put("password",password);
        map.put("postoffice",postoffice);

        Object lg = Utilities.getObjectFromString("Login",map);

        JsonPath authen = CommonApi.postAuthenApi(url,lg);
        String authorizationToken = authen.getString("data.token");
        Serenity.setSessionVariable("Login").to(authen.getString("data.info.userid"));

        Logger.endpoint(url);
        Logger.body(lg.toString());
        Logger.response(authen);
        return authorizationToken;
    }
    public static JsonPath callApiPostRequest(String endPoint,Object object) {
        String authen = CallAPICommon.callAPIGetToken();
        String baseUrl = helpers.PropertiesManager.getCurrentBaseUrl();
        String url = baseUrl + endPoint;
        JsonPath responseEntry = CommonApi.postApi(url,object,authen);

        Logger.endpoint(url);
        Logger.body(object.toString());
        Logger.response(responseEntry);
        return responseEntry;
    }

    public static JsonPath callApiPostRequest(String url,Object object,String authen) {
        JsonPath responseEntry = CommonApi.postApiAuthenBase64(url,object,authen);

        Logger.endpoint(url);
        Logger.body(object.toString());
        Logger.response(responseEntry);
        return responseEntry;
    }

    public static JsonPath callApiJsonObjectPostRequest(String endPoint, String authorization, String objectStr) {
        String baseUrl = helpers.PropertiesManager.getCurrentBaseUrl();
        if (!endPoint.contains("http")) endPoint = baseUrl + endPoint;
        HeaderRequest headerRequest = new HeaderRequest();
        Map<String, String> headerMapRequest = headerRequest.headerMap(authorization);
        JsonPath rescallApiPostRequest = callAPIJsonObjectPost(endPoint, objectStr, headerMapRequest);
        return rescallApiPostRequest;
    }


    private static JsonPath callAPIJsonObjectPost(String url, String objectStr, Map<String, String> map) {
        RestAssured.baseURI = url;
//        Logger.endpoint(url);
        Serenity.recordReportData().withTitle("URL").andContents(url);
//        String beautyRequest = beautyStringJSon(object.toString());
//        Serenity.recordReportData().withTitle("Request Body").andContents(beautyRequest);
//        System.out.println(map.toString());
//        Logger.body(beautyRequest);
        Response response = given()
                .headers(map)
                .body(objectStr)
                .post()
                .then()
                .extract()
                .response();
        try {
            System.out.println(response);
            JsonPath jsPath = response.jsonPath();
            Serenity.recordReportData().withTitle("Response Data").andContents(jsPath.prettify());
            return jsPath;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static JsonPath callAPIKibanaservice(String url, String user, String pass, String json, Map<String, String> map) {
        RestAssured.baseURI = url;
        Serenity.recordReportData().withTitle("URL").andContents(url);
        Serenity.recordReportData().withTitle("Request Body").andContents(json);
        System.out.println(json);
        Response response = given()
                .auth()
                .preemptive()
                .basic(user, pass)
                .headers(map)
                .contentType(ContentType.TEXT)
                .body(json)
                .post()
                .then()
                .extract()
                .response();
        try {
            JsonPath jsPath = response.jsonPath();
//            Logger.response(jsPath);
            Serenity.recordReportData().withTitle("Response Data").andContents(jsPath.prettify());
            return jsPath;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static JsonPath callAPIWithFormData(String endPoint, File filePath, String orgCode, String periodId) {
        String baseUrl = helpers.PropertiesManager.getCurrentBaseUrl();
        String url = baseUrl + endPoint;
        Logger.endpoint(url);

        RestAssured.baseURI = url;
        Serenity.recordReportData().withTitle("URL").andContents(url);
        Response response = given()
//                .contentType("multipart/form-data")
                .multiPart("multipartFile" ,filePath,"multipart/form-data")
                .formParam("orgId",orgCode)
                .formParam("periodId",periodId)
                .post(url)
                .andReturn();
        try {
            JsonPath jsPath = response.jsonPath();
            Logger.response(jsPath);
            Serenity.recordReportData().withTitle("Response Data").andContents(jsPath.prettify());
            return jsPath;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static JsonPath callApiPostNoAuthen(String endPoint,Object object) {
        String baseUrl = helpers.PropertiesManager.getCurrentBaseUrl();
        String url = baseUrl + endPoint;
        JsonPath responseEntry = CommonApi.postAuthenApi(url,object);

        Logger.endpoint(url);
        Logger.body(object.toString());
        Logger.response(responseEntry);
        return responseEntry;
    }
}
