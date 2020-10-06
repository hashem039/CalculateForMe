package com.hmeng.calculateforme.Utils;

import android.os.Environment;

public class Constants {
    public static final String MESURE_UNIT = "unit";
    public static final String CLEAR_STACK_ACTIVITY = "clearStackActivity";
    public static final String LANG_AR = "0";
    public static final String LANG_EN = "1";

    public static final String LANG_AR_CODE = new String("ع");
    public static final String LANG_EN_CODE = "En";

    public static final String GUEST_ID = "0";


    public static final int FNF_MAX_SIZE = 7;
    public static final String OS_TYPE = "1"; // For android OS_TYPE = 1

    public static final int DEFAULT_DISTANCE = 1;
    public static final String DEFAULT_SERVICES_NAMES = "0";

    public static final String TRUE = "true";
    public static final String FALSE = "false";

    public static final int MAX_PASSWORD_LENGTH = 30;
    public static final int MIN_PASSWORD_LENGTH = 8;
    public static final int MAX_GSM_LENGTH = 10;
    public static final int MIN_GSM_LENGTH = 7;

    public static final int OFFERS_PAGER_IMAGES_SIZE = 6;

    public final static String LOCK_SERVICE_IS_ACTIATED = "YES";
    public final static int SERVER_ERROR_CODE = -15000;
    public static String localStorage = Environment.getExternalStorageDirectory().getAbsoluteFile().getPath() + "/CalculateForMe/";
    public static String localStorageProfiles = localStorage+"profiles/";


    //Param constants

    public static final String SETTINGS_LOCALE = "app_language";
    public static final String LANG_CHANGED_BY_USER = "lang_changed_by_user";
}
