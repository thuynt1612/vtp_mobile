package common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.serenitybdd.core.Serenity;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.google.common.base.Strings.isNullOrEmpty;

public class Utilities {
    public static void reportAndlogForModel(String title, Object oject) {
        String logInfo = "";
        if (oject instanceof Object) {
            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter(); // Converting the Java object into a JSON string
            try {
                logInfo = ow.writeValueAsString(oject);
                Serenity.recordReportData().withTitle(title).andContents(logInfo);
                Logger.body(logInfo);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<String, Object> object2HashMap(Object model) {
        if (model == null) return null;
        ObjectMapper objectMapper = new ObjectMapper();
        // object -> Map
        Map<?, ?> map = objectMapper.convertValue(model, Map.class);
        Map<String, Object> result = new HashMap<>();
        map.forEach((key, value) -> result.put((String) key, value));
        return result;
    }

    public static void waitAction(int miliSecc) {
        try {
            Thread.sleep(miliSecc);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static Object getObjectFromString(String nameJson, Map<String, String> listMap) {
        Object object;
        String exampleRequest = "";
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\file\\" + nameJson);
        try {
            exampleRequest = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        object = convertVariableToValueInFile2(listMap, exampleRequest);
        return object;
    } /*  dùng set up data file JSON to JSON OBJECT or JSON ARRAY */

    public static Object convertVariableToValueInFile2(Map<String, String> listVariable, String file) {
        String pattern = "\\$\\{\\{(.+?)}}";
        List<String> allMatches = new ArrayList<>();
        Matcher matcher = Pattern.compile(pattern).matcher(file);
        while (matcher.find()) {
            allMatches.add(matcher.group().trim());
        }
        for (String perMatch : allMatches) {
            perMatch = perMatch.replaceAll("\\$\\{\\{", "").replaceAll("}}", "");
            String value = listVariable.get(perMatch.trim());
            if (!isNullOrEmpty(value)) file = file.replaceAll("\\$\\{\\{" + perMatch + "}}", value);
        }
        JsonParser parser = new JsonParser();
        Object object = null;
        parser.parse(file);
        if (parser.parse(file) instanceof JsonObject) {
            object = (JsonObject) (parser.parse(file));
        } else if (parser.parse(file) instanceof JsonArray) {
            object = (JsonArray) (parser.parse(file));
        }
        return object;
    }

    public static String getStringFromFileJson(String nameJson) {
        String exampleRequest = "";
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\file\\" + nameJson);
        try {
            exampleRequest = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exampleRequest;
    }

//    public static void verifyResponseEquals(String requiredActor, String subject, float actual, float expected) {
//        String verifyMessage = "Verify " + subject + " ACTUAL was " + actual + " AND EXPECTED was " + expected;
//        Logger.verifyResponse(verifyMessage);
//        theActorCalled(requiredActor).attemptsTo(
//                Ensure.that(actual).isEqualTo(expected)
//        );
//    }
//    public static void verifyResponseEquals(String requiredActor, String subject, BigDecimal actual, BigDecimal expected) {
//        String verifyMessage = "Verify " + subject + " ACTUAL was " + actual + " AND EXPECTED was " + expected;
//        Logger.verifyResponse(verifyMessage);
//        theActorCalled(requiredActor).attemptsTo(Ensure.that(actual).isEqualTo(expected));
//    }
//    public static void verifyIsNull(String requiredActor, String subject, String actual) {
//        String verifyMessage = "Verify " + subject + " ACTUAL was " + actual + " AND EXPECTED was null";
//        Logger.verifyResponse(verifyMessage);
//        theActorCalled(requiredActor).attemptsTo(Ensure.that(actual).isNull());
//    }
//    public static void verifyIsNotNull(String requiredActor, String subject, String actual) {
//        String verifyMessage = "Verify " + subject + " ACTUAL was " + actual + " AND EXPECTED was not null";
//        Logger.verifyResponse(verifyMessage);
//        theActorCalled(requiredActor).attemptsTo(Ensure.that(actual).isNotNull());
//    }
//    public static void verifyIsBoolean(String requiredActor, String subject, Boolean actual) {
//        String verifyMessage = "Verify " + subject + " ACTUAL was " + actual;
//        Logger.verifyResponse(verifyMessage);
//        theActorCalled(requiredActor).attemptsTo(Ensure.that(actual).isTrue());
//    }

    public static String getRandomString(int length) {
        String saltChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rand = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rand.nextFloat() * saltChar.length());
            salt.append(saltChar.charAt(index));
        }
        return salt.toString();
    }

    public static String getRandomAlphabetUppercase(int length) {
        String saltChar = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rand = new Random();
        while (salt.length() < length) { // length of the random string.
            int index = (int) (rand.nextFloat() * saltChar.length());
            salt.append(saltChar.charAt(index));
        }
        return salt.toString();
    }

    public static String getRandomNumber(int lenght) {
        String saltChar = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rand = new Random();
        float floatNumber = rand.nextFloat();
        while (salt.length() < lenght) { // length of the random string.
            int index = (int) (rand.nextFloat() * saltChar.length());
            salt.append(saltChar.charAt(index));
        }
        return salt.toString();
    }


    public static HashMapExtend < String, Object > getHashMapFromString(String nameJson, Map < String, String > listMap) {
        HashMapExtend < String, Object > dataHashMap;
        String exampleRequest = "";
        File file = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\file\\" + nameJson);
        try {
            exampleRequest = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        dataHashMap = convertVariableToValueInFile(listMap, exampleRequest);
        return dataHashMap;
    }
    public static HashMapExtend < String, Object > convertVariableToValueInFile(Map < String, String > listVariable, String file) {
        String pattern = "\\$\\{\\{(.+?)}}";
        List < String > allMatches = new ArrayList < > ();
        Matcher matcher = Pattern.compile(pattern).matcher(file);
        while (matcher.find()) {
            allMatches.add(matcher.group().trim());
        }
        for (String perMatch: allMatches) {
            perMatch = perMatch.replaceAll("\\$\\{\\{", "").replaceAll("}}", "");
            String value = listVariable.get(perMatch.trim());
            if (!isNullOrEmpty(value)) file = file.replaceAll("\\$\\{\\{" + perMatch + "}}", value);
        }
        HashMapExtend < String, Object > result = new HashMapExtend < > ();
        try {
            result = new ObjectMapper().readValue(file, new TypeReference< HashMapExtend < String, Object >>() {});
        } catch (IOException e) {
            e.getStackTrace();
        }
        return result;
    }
}
