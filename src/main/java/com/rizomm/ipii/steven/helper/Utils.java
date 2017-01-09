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

    /**
     * Method isEmpty to test is string is empty
     *
     * @param s of type String
     * @return boolean
     */
    public static boolean isEmpty(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Method isNotEmpty to test is string is not empty
     *
     * @param s of type String
     * @return boolean
     */
    public static boolean isNotEmpty(String s) {
        return !Utils.isEmpty(s);
    }

    /**
     * Method isEmpty to test is int is empty
     *
     * @param i of type int
     * @return boolean
     */
    public static boolean isEmpty(int i) {
        if (i == 0) {
            return true;
        }
        return false;
    }

    /**
     * Method isNotEmpty to test is string is not empty
     *
     * @param i of type int
     * @return boolean
     */
    public static boolean isNotEmpty(int i) {
        return !Utils.isEmpty(i);
    }

    /**
     * Method isEmpty to test is json is empty
     *
     * @param json  of type JSONObject
     * @param value of type String
     * @return boolean
     */
    public static boolean isEmpty(JSONObject json, String value) {
        if (!json.has(value) && json.isNull(value)) {
            return true;
        }
        return false;
    }

    /**
     * Method isNotEmpty to test is json is not empty
     *
     * @param json  of type JSONObject
     * @param value of type String
     * @return boolean
     */
    public static boolean isNotEmpty(JSONObject json, String value) {
        return !Utils.isEmpty(json, value);
    }

    /**
     * Method isEmpty to test is Object is empty
     *
     * @param o of type Object
     * @return boolean
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        return false;
    }

    /**
     * Method isTooLarge is string is too large with size max
     *
     * @param json    of type JSONObject
     * @param value   of type String
     * @param sizeMax of type int
     * @return boolean
     * @throws JSONException when
     */
    public static boolean isTooLarge(JSONObject json, String value, int sizeMax) throws JSONException {
        if (json.getString(value).length() > sizeMax) {
            return true;
        }
        return false;
    }

    /**
     * Method isNotEmpty to test is Object is not empty
     *
     * @param i of type Object
     * @return boolean
     */
    public static boolean isNotEmpty(Object i) {
        return !Utils.isEmpty(i);
    }

    /**
     * Method generateMessageSuccess201 for generate a code http 201 with message
     *
     * @param message of type String
     * @return Map<String, Object>
     */
    public static Map<String, Object> generateMessageSuccess201(String message) {
        return generateMessage(message, 201, false);
    }

    /**
     * Method generateMessageSuccess200 for generate a code http 200 with message
     *
     * @param message of type String
     * @return Map<String, Object>
     */
    public static Map<String, Object> generateMessageSuccess200(String message) {
        return generateMessage(message, 200, false);
    }

    /**
     * Method generateMessageError400 for generate a code http 400 with message
     *
     * @param message of type String
     * @return Map<String, Object>
     */
    public static Map<String, Object> generateMessageError400(String message) {
        return generateMessage(message, 400, true);
    }

    /**
     * Method generateMessageSuccess200 for generate a code http 200 with message by json
     *
     * @param message of type JSONObject
     * @return Map<String, Object>
     */
    public static Map<String, Object> generateMessageSuccess200(JSONObject message) {
        return generateMessage(message, 200, false);
    }

    /**
     * Method generateMessage for generate message of response with json
     *
     * @param message  of type JSONObject
     * @param codeHttp of type int
     * @param error    of type boolean
     * @return Map<String, Object>
     */
    private static Map<String, Object> generateMessage(JSONObject message, int codeHttp, boolean error) {
        Map<String, Object> result = new HashMap();
        result.put("CODE_HTTP", codeHttp);
        result.put("MESSAGE_HTTP", message.toString());
        result.put("ERROR", error);
        return result;
    }

    /**
     * Method generateMessage for generate message of response with string
     *
     * @param message  of type String
     * @param codeHttp of type int
     * @param error    of type boolean
     * @return Map<String, Object>
     */
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

    /**
     * Method isInt test if string is a int
     *
     * @param string of type String
     * @return boolean
     */
    public static boolean isInt(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }


    /**
     * Method isDouble test if string is a double
     *
     * @param string of type String
     * @return boolean
     */
    public static boolean isDouble(String string) {
        try {
            Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    /**
     * Method convertDoubleToDixieme to convert string double to string dixieme
     *
     * @param string of type String
     * @return double
     */
    public static double convertDoubleToDixieme(String string) {
        if (isDouble(string)) {
            Double d = Double.parseDouble(string);
            return convertDoubleToDixieme(d);
        }
        return 0d;
    }

    /**
     * Method convertDoubleToDixieme to convert double to string dixieme
     *
     * @param d of type Double
     * @return double
     */
    public static double convertDoubleToDixieme(Double d) {
        BigDecimal bd = new BigDecimal(d);
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        d = bd.doubleValue();
        return d;
    }

    /**
     * Method isNotConvertDoubleToDixieme is not converting to string dixieme
     *
     * @param d of type Double
     * @return boolean
     */
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

    /**
     * Method convertDoubleToStringWithDixieme
     *
     * @param d of type double
     * @return String
     */
    public static String convertDoubleToStringWithDixieme(double d) {
        NumberFormat format = NumberFormat.getInstance();
        format.setMinimumFractionDigits(2);
        return format.format(d);
    }


    /**
     * Method isValidateSortByProduct check is a validate sort by to product
     *
     * @param sortBy of type String
     * @return String
     */
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

    /**
     * Method isValidatePosition check if is asc or desc to sort by
     *
     * @param position of type String
     * @return String
     */
    public static String isValidatePosition(String position) {
        if (position.equals("desc")) {
            return position;
        }
        return "asc";
    }
}
