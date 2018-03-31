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



    //登录更新本地数据库
    public static void putMe(Context context, String username, String password, String nickName, String avatarurl, String desc) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME, username);
        editor.putString(PASSWORD, password);
        editor.putString(NICKNAME, nickName);
        editor.putString(AVATARURL, avatarurl);
        editor.putString(DESC, desc);
        editor.apply();

        Cache.username = username;
    }
    public static void putLoginType(int type,Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(LOGIN_TYPE ,type);
        editor.apply();
    }
    public static int getLoginType(Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        return sharedPreferences.getInt(LOGIN_TYPE, 3);
    }
    public static void putBaseApiUrl(String apiUrl, Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BASE_API_URL,apiUrl);
        editor.apply();
    }
    public static String getBaseApiUrl(Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        return sharedPreferences.getString(BASE_API_URL, "");
    }
    public static void putBaseWebUrl(String webUrl, Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(BASE_WEB_URL,webUrl);
        editor.apply();
    }
    public static String getBaseWebUrl(Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        return sharedPreferences.getString(BASE_WEB_URL, "");
    }
    public static void putMyToken(String mytoken, Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(MYTOKEN,mytoken);
        editor.apply();
    }
    public static String getMyToken(Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        return sharedPreferences.getString(MYTOKEN, "");
    }
    public static void putUsername(String username, Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME ,username);
        editor.apply();
    }
    public static String getUsername(Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        return sharedPreferences.getString(USERNAME, "");
    }
    public static void putType(String type, Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TYPE ,type);
        editor.apply();
    }
    public static String getType(Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        return sharedPreferences.getString(TYPE, "");
    }
    public static void putUid(String uid, Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LOGIN_TOKEN ,uid);
        editor.apply();
    }
    public static String getUid(Context context) {
        SharedPreferences sharedPreferences = loginSharedPreferences(context);
        return sharedPreferences.getString(LOGIN_TOKEN, "");
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
