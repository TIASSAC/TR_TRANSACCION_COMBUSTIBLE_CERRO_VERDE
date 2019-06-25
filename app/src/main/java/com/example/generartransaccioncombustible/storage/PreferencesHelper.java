package com.example.generartransaccioncombustible.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {
    private static final String SALES_PREFERENCES = "SALESPREFERENCES";
    private static final String PREFERENCES_USERNAME = SALES_PREFERENCES + ".username";
    private static final String PREFERENCES_PASSWORD = SALES_PREFERENCES + ".password";
    private static final String PREFERENCES_NAME = SALES_PREFERENCES + ".name";
    private static final String PREFERENCES_MAIL = SALES_PREFERENCES + ".mail";

    private PreferencesHelper() {
        //no instance
    }

    public static void signOut(Context context) {
        SharedPreferences.Editor editor = getEditor(context);
        editor.remove(PREFERENCES_USERNAME);
        editor.remove(PREFERENCES_NAME);
        editor.remove(PREFERENCES_MAIL);
        editor.apply();
    }

    public static void saveSession(Context context, String username, String name, String mail)
    {
        SharedPreferences.Editor editor = getEditor(context);
        editor.putString(PREFERENCES_USERNAME, username);
        editor.putString(PREFERENCES_NAME, name);
        //editor.putString(PREFERENCES_LASTNAME, lastName);
        editor.putString(PREFERENCES_MAIL, mail);
        editor.apply();
    }

    public static String getNameUserSession(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String name= sharedPreferences.getString(PREFERENCES_NAME,null);

        return name;
    }

    public static String getUserSession(Context context)
    {
        SharedPreferences sharedPreferences= getSharedPreferences(context);
        String name= sharedPreferences.getString(PREFERENCES_USERNAME,null);

        return name;
    }

    public static boolean isSignedIn(Context context) {
        final SharedPreferences preferences = getSharedPreferences(context);
        return preferences.contains(PREFERENCES_USERNAME); //&&
        //preferences.contains(PREFERENCES_PASSWORD);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences preferences = getSharedPreferences(context);
        return preferences.edit();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(SALES_PREFERENCES, Context.MODE_PRIVATE);
    }

}
