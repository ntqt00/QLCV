package com.nguyenthai.qlcv;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.time.Instant;
import java.util.HashMap;

public class SessionNguoiDung {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String HOTEN = "HOTEN";
    public static final String TAIKHOAN = "TAIKHOAN";

    public SessionNguoiDung(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("LOGIN", PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void taoSession(String hoten, String taikhoan)
    {
        editor.putBoolean(LOGIN, true);
        editor.putString(HOTEN, hoten);
        editor.putString(TAIKHOAN, taikhoan);
        editor.apply();
    }

    public boolean isLoggin()
    {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin()
    {
        if(!this.isLoggin())
        {
            Intent in = new Intent(context, MainActivity.class);
            context.startActivities(new Intent[]{in});
            ((QLCV) context).finish();
        }
    }

    public HashMap<String, String> thongtinnguoidung()
    {
        HashMap<String, String> nguoidung = new HashMap<>();
        nguoidung.put(HOTEN, sharedPreferences.getString(HOTEN, null));
        nguoidung.put(TAIKHOAN, sharedPreferences.getString(TAIKHOAN, null));
        return nguoidung;
    }

    public void logout()
    {
        editor.clear();
        editor.commit();
        Intent in = new Intent(context, MainActivity.class);
        context.startActivities(new Intent[]{in});
        ((QLCV) context).finish();
    }

}
