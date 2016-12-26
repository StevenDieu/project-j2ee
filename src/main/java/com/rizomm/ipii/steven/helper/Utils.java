package com.rizomm.ipii.steven.helper;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.core.Response;
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
        if(!json.has(value) && json.isNull(value)){
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(JSONObject json, String value) {
        return !Utils.isEmpty(json,value);
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Object i) {
        return !Utils.isEmpty(i);
    }

    public static Map<String, Object> generateMessageSuccess201(String message) {
        return generateMessage(message,201,false);
    }

    public static Map<String, Object> generateMessageError400(String message){
        return generateMessage(message,400,true);
    }


    private static Map<String, Object> generateMessage(String message, int codeHttp, boolean error){
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
        result.put("MESSAGE_HTTP",messageReturn);
        result.put("ERROR",error);
        return result;
    }

    public static boolean isInt(String string){
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e){
            return false;
        }

        return true;
    }

    public static boolean isFloat(String string) {
        try{
            Float.parseFloat(string);
        } catch (NumberFormatException e){
            return false;
        }

        return true;
    }


}
