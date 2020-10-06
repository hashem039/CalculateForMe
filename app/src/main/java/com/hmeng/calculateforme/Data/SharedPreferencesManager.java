package com.hmeng.calculateforme.Data;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    public static final String PREF_GID = "PREF_GID";
    SharedPreferences.Editor editor;
    SharedPreferences pref;
    public static final String LANG_CHANGED_BY_USER = "com.hmeng.calculateforme.LANG_CHANGED_BY_USER";
    public static final String KEY_LANGUAGE = "com.hmeng.calculateforme.key_language";
    private static final String KEY_IS_WAITING_FOR_SMS = "IsWaitingForSms";
    public static final String DEFAULT_FILE_NAME = "root_preferences";

    public static final String PREF_REGISTRATION_ID = "sy.syriatel.selfservice.registrationId";
    public static final String PREF_REGISTRATION_TIME_APP_VERSION = "sy.syriatel.selfservice.registrationTimeAppVersion";

    //calculateForMe
    public static final String BALCONY_RADIUS_SERVICE_DATA = "com.hmeng.calculateforme.BalconyRadiusDATA_SharedPreference";

    // HM last content update
    public static final String LAST_CONTENT_UPDATE_DATE = "sy.syriatel.selfservice.last_content_update_date";

    public static void saveToPreferences(Context context, String prefFileName, String preferenceName, String preferenceValue) {
        if (prefFileName == null) {
            prefFileName = DEFAULT_FILE_NAME;
        }
        if (context != null){


            SharedPreferences sharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(preferenceName, preferenceValue);
            editor.commit();
        }
    }

    public static String readFromPreferences(Context context, String prefFileName, String preferenceName, String defaultValue) {
        if (prefFileName == null) {
            prefFileName = DEFAULT_FILE_NAME;
        }
        SharedPreferences sharedPreferences;
        try {
            sharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);

        } catch (Exception ex) {

            sharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        }
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    /**
     * Retrieves the stored registration id in shared preferences
     *
     * @param context the current context
     * @return the stored registration id in shared preferences, or an empty
     * string if none is stored
     */
    public static String getRegistrationId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(
                DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getString(PREF_REGISTRATION_ID, "");
    }

    /**
     * Retrieves the stored app version upon registration in shared preferences
     *
     * @param context the current context
     * @return the stored app version in shared preferences, or "-1" if none is
     * stored
     */
    public static String getAppVersionOnRegistration(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(
                DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        return prefs.getString(PREF_REGISTRATION_TIME_APP_VERSION, "");
    }

/*    *//**
     * Stores the passed in registration id in shared preferences
     *
     * @param context        the current context
     * @param registrationId the registration id to be stored
     *//*
*//*    public static void storeRegistrationId(Context context,
                                           String registrationId) {
        SharedPreferences prefs = context.getSharedPreferences(
                DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(PREF_REGISTRATION_ID, registrationId)
                .putString(PREF_REGISTRATION_TIME_APP_VERSION, CommunityApplication.getInstance().getAppVersion())
                .commit();
    }*/

    public static void clearPreferences(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(
                DEFAULT_FILE_NAME, Context.MODE_PRIVATE);
        prefs.edit().clear().commit();
    }
}
