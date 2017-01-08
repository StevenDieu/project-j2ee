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
     * Method isEmpty ...
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
     * Method isNotEmpty ...
     *
     * @param s of type String
     * @return boolean
     */
    public static boolean isNotEmpty(String s) {
        return !Utils.isEmpty(s);
    }

    /**
     * Method isEmpty ...
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
     * Method isNotEmpty ...
     *
     * @param i of type int
     * @return boolean
     */
    public static boolean isNotEmpty(int i) {
        return !Utils.isEmpty(i);
    }

    /**
     * Method isEmpty ...
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
     * Method isNotEmpty ...
     *
     * @param json  of type JSONObject
     * @param value of type String
     * @return boolean
     */
    public static boolean isNotEmpty(JSONObject json, String value) {
        return !Utils.isEmpty(json, value);
    }

    /**
     * Method isEmpty ...
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
     * Method isTooLarge ...
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
     * Method isNotEmpty ...
     *
     * @param i of type Object
     * @return boolean
     */
    public static boolean isNotEmpty(Object i) {
        return !Utils.isEmpty(i);
    }

    /**
     * Method generateMessageSuccess201 ...
     *
     * @param message of type String
     * @return Map<String, Object>
     */
    public static Map<String, Object> generateMessageSuccess201(String message) {
        return generateMessage(message, 201, false);
    }

    /**
     * Method generateMessageSuccess200 ...
     *
     * @param message of type String
     * @return Map<String, Object>
     */
    public static Map<String, Object> generateMessageSuccess200(String message) {
        return generateMessage(message, 200, false);
    }

    /**
     * Method generateMessageError400 ...
     *
     * @param message of type String
     * @return Map<String, Object>
     */
    public static Map<String, Object> generateMessageError400(String message) {
        return generateMessage(message, 400, true);
    }

    /**
     * Method generateMessageSuccess200 ...
     *
     * @param message of type JSONObject
     * @return Map<String, Object>
     */
    public static Map<String, Object> generateMessageSuccess200(JSONObject message) {
        return generateMessage(message, 200, false);
    }

    /**
     * Method generateMessage ...
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
     * Method generateMessage ...
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
     * Method isInt ...
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
     * Method isDouble ...
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
     * Method convertDoubleToDixieme ...
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
     * Method convertDoubleToDixieme ...
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
     * Method isNotConvertDoubleToDixieme ...
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
     * Method convertDoubleToStringWithDixieme ...
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
     * Method isValidateSortByProduct ...
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
     * Method isValidatePosition ...
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
