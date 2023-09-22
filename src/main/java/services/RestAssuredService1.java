package services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class RestAssuredService1 {
    private RestAssuredService1() {
    }

    public static JsonPath callCreateEwalletRestfulServicePost(String url, Object obj, Map<String, String> map) {
        RestAssured.baseURI = url;
        String json = new Gson().toJson(obj);
        Response response = given()
                .headers(map)
                .body(json)
                .post()
                .then()
                .extract()
                .response();
        try {
            return response.jsonPath();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static JsonPath callAPIGet(String url, Map<String, ?> headers, Map<String, ?> params) {
        RestAssured.baseURI = url;
        RestAssured.defaultParser = Parser.JSON;
        Response response = given()
                .headers(headers)
                .params(params)
                .when()
                .get()
                .then()
                .extract()
                .response();
        try {
            return response.jsonPath();
        } catch (Exception ex) {
            return null;
        }
    }

    public static JsonPath callAPIRestful(String url, JsonObject obj, Map<String, String> map) {
        RestAssured.baseURI = url;
        String test = obj.toString();
        Response response = given()
                .headers(map)
                .body(obj.toString())
                .post()
                .then()
                .extract()
                .response();
        try {
            return response.jsonPath();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public static JsonPath callAPIPost(String url, HashMap<String, Object> mapBody, Map<String, String> map) {
        RestAssured.baseURI = url;
//        Logger.endpoint(url);
        Serenity.recordReportData().withTitle("URL").andContents(url);
//        JSONObject mapBodyToJson = mapBody.toJson();
//        String beautyRequest = beautyStringJSon(mapBody.toJson().toString());
//        Serenity.recordReportData().withTitle("Request Body").andContents(beautyRequest);
//        Logger.body(beautyRequest);
        Response response = given()
                .headers(map)
                .body(mapBody)
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
}
