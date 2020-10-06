package com.hmeng.calculateforme.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hmeng.calculateforme.Data.BalconyRadiusOperationTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utils {
    private static DateFormat sDateTimeFormat = new SimpleDateFormat(
            "dd/MM/yyyy hh:mm", Locale.ENGLISH);
    private static DateFormat sTimeFormat = new SimpleDateFormat(
            "hh:mm aa", Locale.ENGLISH);
    private static DateFormat sDateFormat = new SimpleDateFormat(
            "dd/MM/yyyy", Locale.ENGLISH);
    private static DateFormat sDateTimeFormat2 = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    public static DateFormat sDateFormat2 = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static List<BalconyRadiusOperationTask> DataCache =new ArrayList<>();

    public static String searchString = "";

    public static void show(Context c, String message){
        Toast.makeText(c, message, Toast.LENGTH_SHORT).show();
    }

    public static void openActivity(Context c,Class clazz){
        Intent intent = new Intent(c, clazz);
        c.startActivity(intent);
    }
    public static float getStatusBarHeight(Context context) {
        float result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimension(resourceId);//.getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * This method will allow us send a serialized scientist objec  to a specified
     *  activity
     */
    public static void sendScientistToActivity(Context c, BalconyRadiusOperationTask scientist,
                                               Class clazz){
        Intent i=new Intent(c,clazz);
        i.putExtra("SCIENTIST_KEY", (Parcelable) scientist);
        c.startActivity(i);
    }

    /**
     * This method will allow us receive a serialized scientist, deserialize it and return it,.
     */
    public  static BalconyRadiusOperationTask receiveScientist(Intent intent, Context c){
        try {
            return (BalconyRadiusOperationTask) intent.getSerializableExtra("SCIENTIST_KEY");
        }catch (Exception e){
            e.printStackTrace();
            show(c,"RECEIVING-SCIENTIST ERROR: "+e.getMessage());
        }
        return null;
    }

    public static void showProgressBar(ProgressBar pb){
        pb.setVisibility(View.VISIBLE);
    }
    public static void hideProgressBar(ProgressBar pb){
        pb.setVisibility(View.GONE);
    }

    public static String getFormatedDate(Date date) {
        return sDateFormat.format(date);
    }
    public static String getFormatedDateTime(Date date) {
        return sDateTimeFormat.format(date);
    }
    public static String getFormatedTime(Date date) {
        return sTimeFormat.format(date);
    }
    private static final String arabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";
    public static String arabicToDecimal(String number) {
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

}
