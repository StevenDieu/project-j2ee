package com.rizomm.ipii.steven.helper;

/**
 * Created by steven on 17/11/2016.
 */
public class Utils {

    public static boolean isEmpty(String s){
        if(s == null || s.isEmpty()){
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String s){
        return !Utils.isEmpty(s);
    }

    public static boolean isEmpty(int i){
        if(i == 0){
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(int i){
        return !Utils.isEmpty(i);
    }

}
