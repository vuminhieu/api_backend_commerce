package com.example.backend_commerce.ultils;

import java.util.Map;

public class HandlesResponse {

    public static <T> Map<String, Object> responseSuccess(String msg, T data) {
        msg = (msg != null) ? msg : "";
        return Map.of("result", "success", "message", msg, "data", data);
    }

    public static Map<String, String> ResponseError(String msg, String ElmError) {
        msg = (msg != null) ? msg : "";
        ElmError = (ElmError != null) ? ElmError : "";
        return Map.of("result", "error", "message", msg, "data", "", "elmError", ElmError);
    }

    public static Map<String, String> ResponseWarring(String msg) {
        return Map.of("result", "warring", "message", msg);
    }


}
