package common.api;

import java.util.HashMap;
import java.util.Map;

public class HeaderRequest {
    public static Map<String, String> headerMap(String authorization) {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Content-Type", "application/json");
        hashMap.put("x-access-token",authorization);
        hashMap.put("accept","*/*");
        return hashMap;
    }

    public static Map<String, String> headerMapNoToken() {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Content-Type", "application/json");
        hashMap.put("accept","*/*");
        return hashMap;
    }

    public static Map<String, String> headerMapTokenBase64(String authorization) {
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Content-Type", "application/json");
        hashMap.put("Authorization","Basic " + authorization);
        return hashMap;
    }
}
