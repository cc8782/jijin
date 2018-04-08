package com.example.app.cache;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by rainL on 2015-12-10.
 */
public class Cache {
    public static String username;
    public static final String FORMAT = "_%s";//为不同用户建立不同cache
    public static final String USERNAME = "username";
    public static final String MYTOKEN= "mytoken";
    public static final String PASSWORD = "password";
    public static final String NICKNAME = "nickname";
    public static final String AVATARURL = "avatarurl";
    public static final String AUTO_LOGIN = "autologin";
    public static final String TYPE= "type";
    public static final String LOGIN_TOKEN= "loginToken";
    public static final String LOGIN_TYPE= "loginType";
    public static final String BASE_API_URL="baseApiUrl";
    public static final String BASE_WEB_URL="baseWebUrl";

    public static final String DESC = "desc";

    private Cache() {
    }

    public static void put(Context context, String key, String value) {
        SharedPreferences sharedPreferences = sharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public static String getAutoLogin(Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        return sharedPreferences.getString(AUTO_LOGIN, "");
    }
    public static void putAutoLogin(String url, Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AUTO_LOGIN,url);
        editor.apply();
    }
    public static String getPassword(Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        return sharedPreferences.getString("password", "");
    }









    private static SharedPreferences sharedPreferences(Context context) {
        return context.getSharedPreferences(String.format("activity" + FORMAT, Cache.username), Context.MODE_PRIVATE);
    }

    private static SharedPreferences loginSharedPreferences(Context context) {
        return context.getSharedPreferences("activity", Context.MODE_PRIVATE);
    }



    public Cache getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final Cache INSTANCE = new Cache();
    }

}
