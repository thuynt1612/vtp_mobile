package common.api;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CommonApi {
    public static JsonPath postAuthenApi(String url,Object object) {
        RestAssured.baseURI = url;
        HeaderRequest headerRequest = new HeaderRequest();
        Map<String, String> headerMapRequest = headerRequest.headerMapNoToken();

        Response response = given()
                .headers(headerMapRequest)
                .body(object.toString())
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
    public static JsonPath postApi(String url,Object object,String authen) {
        RestAssured.baseURI = url;
        HeaderRequest headerRequest = new HeaderRequest();
        Map<String, String> headerMapRequest = headerRequest.headerMap(authen);

        Response response = given()
                .headers(headerMapRequest)
                .body(object.toString())
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

    public static JsonPath postApiAuthenBase64(String url,Object object,String authen) {
        RestAssured.baseURI = url;
        HeaderRequest headerRequest = new HeaderRequest();
        Map<String, String> headerMapRequest = headerRequest.headerMapTokenBase64(authen);

        Response response = given()
                .headers(headerMapRequest)
                .body(object.toString())
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
