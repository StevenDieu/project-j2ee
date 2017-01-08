package com.rizomm.ipii.steven.helper;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by steven on 17/11/2016.
 */
public class Utils {

    public static boolean isEmpty(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String s) {
        return !Utils.isEmpty(s);
    }

    public static boolean isEmpty(int i) {
        if (i == 0) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(int i) {
        return !Utils.isEmpty(i);
    }

    public static boolean isEmpty(JSONObject json, String value) {
        if (!json.has(value) && json.isNull(value)) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(JSONObject json, String value) {
        return !Utils.isEmpty(json, value);
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        return false;
    }

    public static boolean isTooLarge(JSONObject json, String value, int sizeMax) throws JSONException {
        if (json.getString(value).length() > sizeMax) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Object i) {
        return !Utils.isEmpty(i);
    }

    public static Map<String, Object> generateMessageSuccess201(String message) {
        return generateMessage(message, 201, false);
    }

    public static Map<String, Object> generateMessageSuccess200(String message) {
        return generateMessage(message, 200, false);
    }

    public static Map<String, Object> generateMessageError400(String message) {
        return generateMessage(message, 400, true);
    }

    public static Map<String, Object> generateMessageSuccess200(JSONObject message) {
        return generateMessage(message, 200, false);
    }

    private static Map<String, Object> generateMessage(JSONObject message, int codeHttp, boolean error) {
        Map<String, Object> result = new HashMap();
        result.put("CODE_HTTP", codeHttp);
        result.put("MESSAGE_HTTP", message.toString());
        result.put("ERROR", error);
        return result;
    }

    private static Map<String, Object> generateMessage(String message, int codeHttp, boolean error) {
        Map<String, Object> result = new HashMap();
        result.put("CODE_HTTP", codeHttp);
        String messageReturn = "{\"message\" :" + message + "}";
        try {
            JSONObject json = new JSONObject();
            json.put("message", message);
            messageReturn = json.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        result.put("MESSAGE_HTTP", messageReturn);
        result.put("ERROR", error);
        return result;
    }

    public static boolean isInt(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }


    public static boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    public static double convertDoubleToDixieme(String string) {
        if (isDouble(string)) {
            Double d = Double.parseDouble(string);
            return convertDoubleToDixieme(d);
        }
        return 0d;
    }

    public static double convertDoubleToDixieme(Double d) {
        BigDecimal bd = new BigDecimal(d);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        d = bd.doubleValue();
        return d;
    }

    public static boolean isNotConvertDoubleToDixieme(Double d) {
        try {
            BigDecimal bd = new BigDecimal(d);
            bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
            bd.doubleValue();
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    public static String convertDoubleToStringWithDixieme(double d) {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(2);
        return format.format(d);
    }


    public static String isValidateSortByProduct(String sortBy) {
        switch (sortBy) {
            case "id":
                return sortBy;
            case "price":
                return sortBy;
            case "name":
                return sortBy;
            default:
                return "id";
        }
    }

    public static String isValidatePosition(String position) {
        if (position.equals("desc")) {
            return position;
        }
        return "asc";
    }
}
