package com.hmeng.calculateforme;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.LocaleList;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.core.os.ConfigurationCompat;

import com.hmeng.calculateforme.Data.SharedPreferencesManager;
import com.hmeng.calculateforme.Helpers.LocaleHelper;
import com.hmeng.calculateforme.Utils.Constants;
import com.hmeng.calculateforme.Utils.TypefaceUtil;
import com.hmeng.calculateforme.Utils.Utils;

import java.io.File;
import java.util.Locale;
import java.util.UUID;
import java.util.logging.Logger;

public class CommunityApplication  extends Application {
    private static CommunityApplication sInstance;
    private Locale mLocale;
    public static synchronized CommunityApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.setLocale(base));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleHelper.setLocale(this);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        LocaleHelper.setLocale(getBaseContext());
       // LocaleHelper.setNewLocale(getApplicationContext(), LocaleHelper.ARABIC);

        String lang = SharedPreferencesManager.readFromPreferences(getApplicationContext(), null,
                SharedPreferencesManager.KEY_LANGUAGE, null);
        Boolean isLangChangedByUser = SharedPreferencesManager.readFromPreferences(getApplicationContext(), null,
                SharedPreferencesManager.LANG_CHANGED_BY_USER, "false").equals(Constants.TRUE) ? true : false;
        if (lang == null) {
            getSystemLanguage();
        } else {
            if (isLangChangedByUser) {
                //changeLang(lang);
            } else {
                getSystemLanguage();
            }
        }
    }
    public void getSystemLanguage() {
        String systemLocale = getCurrentLocale(getApplicationContext());
        setApplicationLang(systemLocale);
    }

    String getCurrentLocale(Context context){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return LocaleList.getDefault().get(0).getLanguage();
        } else{
            return Locale.getDefault().getLanguage();
        }
        }


    public void setApplicationLang(String lang) {
        Configuration config = getBaseContext().getResources()
                .getConfiguration();
       /* if (!lang.equals("")){

            SharedPreferencesManager.saveToPreferences(getInstance().getApplicationContext(), null,
                    SharedPreferencesManager.KEY_LANGUAGE, lang);

            String isLangChangedByUser = SharedPreferencesManager.readFromPreferences(getApplicationContext(), null,
                    SharedPreferencesManager.LANG_CHANGED_BY_USER, null);
            if( isLangChangedByUser == null ){
                SharedPreferencesManager.saveToPreferences(getApplicationContext(), null,
                        SharedPreferencesManager.LANG_CHANGED_BY_USER, Constants.FALSE);
            }
            else{
                SharedPreferencesManager.saveToPreferences(getApplicationContext(), null,
                        SharedPreferencesManager.LANG_CHANGED_BY_USER, Constants.TRUE);
            }*/


            mLocale = new Locale(lang);
            Locale.setDefault(mLocale);
            Configuration conf = new Configuration(config);
            conf.locale = mLocale;
            //this.LANG = getLang();
            getBaseContext().getResources().updateConfiguration(conf,
                    getBaseContext().getResources().getDisplayMetrics());
            // doRestart(getAppContext());
        }

    public void changeLang(String lang) {
        Configuration config = getAppContext().getResources()
                .getConfiguration();
        String localeLang = config.locale.getDisplayLanguage();
        if (!lang.equals("") && !localeLang.equals(lang)) {

            SharedPreferencesManager.saveToPreferences(getInstance().getApplicationContext(), SharedPreferencesManager.DEFAULT_FILE_NAME,
                    SharedPreferencesManager.KEY_LANGUAGE, lang);

            String isLangChangedByUser = SharedPreferencesManager.readFromPreferences(getAppContext(), null,
                    SharedPreferencesManager.LANG_CHANGED_BY_USER, null);
            if( isLangChangedByUser == null ){
                SharedPreferencesManager.saveToPreferences(getAppContext(), null,
                        SharedPreferencesManager.LANG_CHANGED_BY_USER, Constants.FALSE);
            }
            else{
                SharedPreferencesManager.saveToPreferences(getAppContext(), null,
                        SharedPreferencesManager.LANG_CHANGED_BY_USER, Constants.TRUE);
            }

            mLocale = new Locale(lang);
            Locale.setDefault(mLocale);
            Configuration conf = new Configuration(config);
            conf.locale = mLocale;
            //this.LANG = getLang();
            getAppContext().getResources().updateConfiguration(conf,
                    getAppContext().getResources().getDisplayMetrics());
            //doRestart(getAppContext());
        }
    }
  /*  private static final String TAG = "CalculateForMe_TAG";

    public static final boolean DEBUG_MODE = true;
    public static final int DEV = 0;
    public static final int STAGE = 1;
    public static final int LIVE = 2;
    public static final int MODE = DEV;

    public static String APP_VERSION;
    public static String DEVICE_ID;
    public static String DEVICE_Name;
    public static String LANG;
    public static int STATUS_BAR_HEIGHT;
    public static int NOTIFICATIONS_COUNTER = 0;
    public static boolean mIsComingFromSignUpPopup = false;
    private Locale mLocale;
    public static boolean mIsPreferredBundlesLoadedSuccessfully = false;
   // public static dbHelper db;
    public static boolean FILE_PERMISSION;

  *//*  public static dbHelper getDBHelper() {
        if (db == null) {
            db = new dbHelper(getInstance());
        }
        return db;
    }*//*

    private static CommunityApplication sInstance;

    public static synchronized CommunityApplication getInstance() {
        return sInstance;
    }

    public static Context getAppContext() {
        return sInstance.getApplicationContext();
    }

    public static void setIsComingFromSignUpPopupFlag( boolean isComing ){
        mIsComingFromSignUpPopup = isComing;
    }
    public static class FontChangeCrawler
    {
        private Typeface typeface;

        public FontChangeCrawler(Typeface typeface)
        {
            this.typeface = typeface;
        }

        public FontChangeCrawler(AssetManager assets, String assetsFontFileName)
        {
            typeface = Typeface.createFromAsset(assets, assetsFontFileName);
        }

        public void replaceFonts(ViewGroup viewTree)
        {
            View child;
            for(int i = 0; i < viewTree.getChildCount(); ++i)
            {
                child = viewTree.getChildAt(i);
                if(child instanceof ViewGroup)
                {
                    // recursive call
                    replaceFonts((ViewGroup)child);
                }
                else if(child instanceof TextView)
                {
                    // base case
                    ((TextView) child).setTypeface(typeface);
                }
            }
        }
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/OpenSans-Semibold.ttf");
//        NukeSSLCerts nukeSSLCerts = new NukeSSLCerts();
//        nukeSSLCerts.nuke();


        sInstance = this;
       // db = new dbHelper(this);

        APP_VERSION = getAppVersion();
        DEVICE_ID = getUniqueDeviceID();
        DEVICE_Name = getDeviceName();

        STATUS_BAR_HEIGHT = (int) Utils.getStatusBarHeight(this);

        String lang = SharedPreferencesManager.readFromPreferences(getInstance().getApplicationContext(), null,
                SharedPreferencesManager.KEY_LANGUAGE, null);
        Boolean isLangChangedByUser = SharedPreferencesManager.readFromPreferences(getInstance().getApplicationContext(), null,
                SharedPreferencesManager.LANG_CHANGED_BY_USER, "false").equals(Constants.TRUE) ? true : false;
        if (lang == null) {
            getSystemLanguage();
        } else {
            if (isLangChangedByUser) {
                changeLang(lang);
            } else {
                getSystemLanguage();
            }
        }
        LANG = getLang();

        File appDirectory = new File(Constants.localStorage);
        if (!appDirectory.exists()) {
            boolean s =  appDirectory.mkdir();
        }
        File profiles = new File(Constants.localStorageProfiles);
        if (!profiles.exists()) {
            boolean s = profiles.mkdir();
        }
        //String userName = SharedPreferencesManager.readFromPreferences(this, null, SharedPreferencesManager.USER_NAME, "");
       *//* if (!userName.equals("")) {
            Intent i = new Intent(this, RoosterConnectionService.class);
            startService(i);
        }*//*

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            FILE_PERMISSION = checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        } else {
            FILE_PERMISSION = true;
        }
    }



    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mLocale != null) {
            Locale.setDefault(mLocale);
            Configuration config = new Configuration(newConfig);
            config.locale = mLocale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }
    }

    *//**
     * Returns a string of the app version as specified in the package manifest
     *
     * @return A string of the app version.
     *//*
    public String getAppVersion() {
        PackageManager manager = getPackageManager();
        PackageInfo info;
        String version;
        try {
            info = manager.getPackageInfo(getPackageName(), 0);
            version = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            version = "1";
        }
        return version;
    }

    *//**
     * Returns a string of the app language
     *
     * @return A string of the app language.
     *//*
    public String getLang() {
        String lang = SharedPreferencesManager.readFromPreferences(getInstance().getApplicationContext(), null,
                SharedPreferencesManager.KEY_LANGUAGE, "en");

        return (lang.substring(0, 2).equals("en") ? Constants.LANG_EN
                : Constants.LANG_AR);
    }

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }


    public void getSystemLanguage() {
        String systemLocale = Locale.getDefault().getLanguage();
        setApplicationLang(systemLocale);
    }

    public void setApplicationLang(String lang) {
        Configuration config = getBaseContext().getResources()
                .getConfiguration();
        if (!lang.equals("")){

            SharedPreferencesManager.saveToPreferences(getInstance().getApplicationContext(), null,
                    SharedPreferencesManager.KEY_LANGUAGE, lang);

            String isLangChangedByUser = SharedPreferencesManager.readFromPreferences(getApplicationContext(), null,
                    SharedPreferencesManager.LANG_CHANGED_BY_USER, null);
            if( isLangChangedByUser == null ){
                SharedPreferencesManager.saveToPreferences(getApplicationContext(), null,
                        SharedPreferencesManager.LANG_CHANGED_BY_USER, Constants.FALSE);
            }
            else{
                SharedPreferencesManager.saveToPreferences(getApplicationContext(), null,
                        SharedPreferencesManager.LANG_CHANGED_BY_USER, Constants.TRUE);
            }


            mLocale = new Locale(lang);
            Locale.setDefault(mLocale);
            Configuration conf = new Configuration(config);
            conf.locale = mLocale;
            this.LANG = getLang();
            getBaseContext().getResources().updateConfiguration(conf,
                    getBaseContext().getResources().getDisplayMetrics());
           // doRestart(getAppContext());
        }
    }

//    public static NotificationsObserver getObserver() {
//        return mNotificationsObserver;
//    }


    public void changeLang(String lang) {
        Configuration config = getAppContext().getResources()
                .getConfiguration();
        String localeLang = config.locale.getDisplayLanguage();
        if (!lang.equals("") && !localeLang.equals(lang)) {

            SharedPreferencesManager.saveToPreferences(getInstance().getApplicationContext(), SharedPreferencesManager.DEFAULT_FILE_NAME,
                    SharedPreferencesManager.KEY_LANGUAGE, lang);

            String isLangChangedByUser = SharedPreferencesManager.readFromPreferences(getAppContext(), null,
                    SharedPreferencesManager.LANG_CHANGED_BY_USER, null);
            if( isLangChangedByUser == null ){
                SharedPreferencesManager.saveToPreferences(getAppContext(), null,
                        SharedPreferencesManager.LANG_CHANGED_BY_USER, Constants.FALSE);
            }
            else{
                SharedPreferencesManager.saveToPreferences(getAppContext(), null,
                        SharedPreferencesManager.LANG_CHANGED_BY_USER, Constants.TRUE);
            }

            mLocale = new Locale(lang);
            Locale.setDefault(mLocale);
            Configuration conf = new Configuration(config);
            conf.locale = mLocale;
            this.LANG = getLang();
            getAppContext().getResources().updateConfiguration(conf,
                    getAppContext().getResources().getDisplayMetrics());
            //doRestart(getAppContext());
        }
    }



*//*    public String getUserId() {
        String userId = SharedPreferencesManager.readFromPreferences(getApplicationContext(), null,
                SharedPreferencesManager.PREF_USER_ID, null);

        if (userId == null) {
            userId = AppConstants.GUEST_ID;
        }

        return userId;
    }*//*

    public interface SignInCallback {
        void onSignInSucceeded();

        void onSignInFailed();
    }

    *//**
     * Return pseudo unique ID
     *
     * @return ID
     *//*
    private static String getUniqueDeviceID() {
        String m_szDevIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) +
                (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) +
                (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);

        String serial = null;
        try {
            serial = Build.class.getField("SERIAL").get(null).toString();

            // Return the serial for api >= 9
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            // Default value
            serial = Settings.Secure.ANDROID_ID;
        }

        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    *//**
     * Gets the current registration ID for application on GCM service.
     * <p/>
     * If result is empty, the app needs to register.
     *
     * @return registration ID, or empty string if there is no existing
     * registration ID.
     *//*
    public String getRegistrationId() {
        String registrationId = SharedPreferencesManager
                .getRegistrationId(getInstance().getApplicationContext());
        if (registrationId == null || registrationId.equals("")) {
            return "";
        }
        // Check if app was updated; if so, it must clear the registration ID
        // since the existing regID is not guaranteed to work with the new
        // app version.
        String registeredVersion = SharedPreferencesManager
                .getAppVersionOnRegistration(getInstance().getApplicationContext());
        String currentVersion = getAppVersion();
        if (!registeredVersion.equals(currentVersion)) {
            return "";
        }
        return registrationId;
    }

*//*    private void goToSignUp() {
        // Clear preferences
        setIsComingFromSignUpPopupFlag(true);
        SharedPreferencesManager.clearPreferences(getApplicationContext());

        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
        CommunityApplication.getInstance().startActivity(intent);


        // Remove all notifications
        NotificationManager notificationManager = (NotificationManager) CommunityApplication.getInstance()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancelAll();

    }*//*


    private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";

    private static String arabicToDecimal(String number) {
        char[] chars = new char[number.length()];
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            //0x60c
            //0x2e
            if (ch >= 0x0660 && ch <= 0x0669)
                ch -= 0x0660 - '0';
            else if (ch >= 0x06f0 && ch <= 0x06F9)
                ch -= 0x06f0 - '0';
            else if (ch == 0x60c)
                ch = 0x2e;
            else if (ch == '٫')
                ch = '.';
            chars[i] = ch;
        }
        return new String(chars);
    }

    public static String formatNumber(String oldNumber) {
        String number = "";
        try {
            number = arabicToDecimal(oldNumber);
            number = number.trim();
            for (int i = 0; i < number.length(); i++) {
                try {
                    char chr = number.charAt(i);
                    if (!Character.isDigit(chr) && chr != '-') {
                        number = number.replace(chr, '.');
                        break;
                    }
                } catch (Exception ex) {
                }
            }
        } catch (Exception ex) {
            number = oldNumber;
        }

        try {
            float tempNo = Float.parseFloat(number);
            String tempStr = String.valueOf(tempNo);
            if (tempStr.endsWith(".0")) {
                tempStr = tempStr.replace(".0", "");
                //tempStr = String.format("%.0f", Integer.parseInt(tempStr));
                tempStr = String.format("%1$d", Integer.parseInt(tempStr));
                number = tempStr;
            } else {
                tempStr = String.format("%.1f", Float.parseFloat(tempStr));
                number = tempStr;
            }

        } catch (Exception ex) {
            number = oldNumber;
        }
        return number;
    }

    public void doRestart1(Context c) {
        try {
            // check if the context is given
            if (c != null) {
                // fetch the packagemanager so we can get the default launch
                // activity
                PackageManager pm = c.getPackageManager();
                // check if we got the PackageManager
                if (pm != null) {
                    // create the intent with the default start activity for
                    // your application
                    Intent mStartActivity = pm.getLaunchIntentForPackage(c
                            .getPackageName());
                    if (mStartActivity != null) {
                        mStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        // create a pending intent so the application is
                        // restarted after System.exit(0) was called.
                        // We use an AlarmManager to call this intent in 100ms
                        int mPendingIntentId = 223344;
                        PendingIntent mPendingIntent = PendingIntent
                                .getActivity(c, mPendingIntentId,
                                        mStartActivity,
                                        PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager mgr = (AlarmManager) c
                                .getSystemService(Context.ALARM_SERVICE);
                        mgr.set(AlarmManager.RTC,
                                System.currentTimeMillis() + 100,
                                mPendingIntent);
                        // kill the application
                        System.exit(0);
                    } else {
                    }
                } else {
                }
            } else {
            }
        } catch (Exception ex) {
        }
    }

    public void doRestart(Context c) {
        try {
            // check if the context is given
            if (c != null) {
                // fetch the packagemanager so we can get the default launch activity
                PackageManager pm = c.getPackageManager();
                // check if we got the PackageManager
                if (pm != null) {
                    // create the intent with the default start activity for
                    // your application
                    Intent mStartActivity = pm.getLaunchIntentForPackage(c.getPackageName());
                    if (mStartActivity != null) {
                        mStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        // create a pending intent so the application is
                        // restarted after System.exit(0) was called.
                        //We use an AlarmManager to call this intent in 100ms
//                        int mPendingIntentId = 223344;
//                        PendingIntent mPendingIntent = PendingIntent.getActivity(c, mPendingIntentId,mStartActivity,PendingIntent.FLAG_CANCEL_CURRENT);
//                        AlarmManager mgr = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
//                        mgr.set(AlarmManager.RTC,System.currentTimeMillis() + 100,mPendingIntent);
                        // kill the application
                        System.exit(0);
                    } else {
                    }
                } else {
                }
            } else {
            }
        } catch (Exception ex) {
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager
                .getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

*//*    public static void clearApplicationData() {
        // getInstance().deleteDatabase(Constant.localStorage+dbHelper.DATABASE_NAME);
        if (db != null) {
            db.close();
        }
        File cache = getInstance().getApplicationContext().getCacheDir();
        File appDir = new File(cache.getParent());
        if (appDir.exists()) {
            String[] children = appDir.list();
            for (String s : children) {
                if (!s.equals("lib")) {
                    deleteDir(new File(appDir, s));
                }
            }
        }
        db = new dbHelper(sInstance);

    }*//*

*//*    public static void createNewHelper(){
        db = new dbHelper(getInstance());

    }*//*
    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (String aChildren : children) {
                boolean success = deleteDir(new File(dir, aChildren));
                if (!success) {
                    return false;
                }
            }
        }

        return dir != null && dir.delete();
    }

    public static int getNotificationsCount(){
        return NOTIFICATIONS_COUNTER;
    }

    public static void setNotificationsCount( int count ){
        NOTIFICATIONS_COUNTER = count;
    }
    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

//        String phrase = "";
        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
//                phrase += Character.toUpperCase(c);
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
//            phrase += c;
            phrase.append(c);
        }

        return phrase.toString();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, "en"));
    }
*/
    /*public static void showDialog(final Activity context, final String syriatelink, final String storelink, int isCurrentVersionActive) {
    *//*final SpannableString spanString = new SpannableString(getString(R.string.txt_new_update) + "\n" + Syriatelink);
    Linkify.addLinks(spanString, Linkify.ALL);
   android.app.AlertDialog ad = new android.app.AlertDialog.Builder(SettingsActivity.this).setNegativeButton(android.R.string.cancel, null)
            .setMessage(spanString)
            .create();

    ad.show();
    ((TextView) ad.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());*//*
        final Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update);

        View vSyriatelLink =  dialog.findViewById(R.id.v_syriatel_link);
        View vStoreLink =  dialog.findViewById(R.id.v_store_link);
        Button cancel = (Button) dialog.findViewById(R.id.btn_cancel);

        if (isCurrentVersionActive == 0){
            cancel .setVisibility(View.GONE);
        }
        else {
            cancel .setVisibility(View.VISIBLE);
        }

        if (syriatelink.equals("null") || syriatelink.equals("")){
            vSyriatelLink.setVisibility(View.GONE);
        }
        else {
            vSyriatelLink.setVisibility(View.VISIBLE);
        }

        if (storelink.equals("null") || storelink.equals("")){
            vStoreLink.setVisibility(View.GONE);
        }
        else {
            vStoreLink.setVisibility(View.VISIBLE);
        }

        vSyriatelLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(syriatelink));
                context.startActivity(browserIntent);
            }});

        vStoreLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(storelink));
                context.startActivity(browserIntent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                String userId = SharedPreferencesManager
                        .readFromPreferences(context, null, SharedPreferencesManager.PREF_USER_ID, null);
                String mobile = SharedPreferencesManager
                        .readFromPreferences(context, null, SharedPreferencesManager.PREF_MOBILE, null);
                String privateKey = SharedPreferencesManager
                        .readFromPreferences(context, null, SharedPreferencesManager.PREF_PRIVATE_KEY, null);

                if (userId == null || mobile == null || privateKey == null) {
                    // If userId or mobile is not stored before, Go to intro page
                    // String gsm = check();
                    Intent intent = new Intent(context, CheckGsmActivity.class);
                    //  intent.putExtra("checkGsm", gsm);
                    context.startActivity(intent);
                } else { // Go to main activity
                    context.startActivity(new Intent(context, MainActivity.class));
                }
                context.finish();
            }
        });

        dialog.show();
    }

*/
}
