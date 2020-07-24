package com.nguyenthai.qlcv;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.HashMap;

public class QLCV extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    SessionNguoiDung Session;
    private TextView txt_hoten, txt_taikhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlcv);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        View v = navigationView.getHeaderView(0);


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_yeucau, R.id.nav_bsc)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        kiemtra_session(v);
    }

    public void kiemtra_session(View v)
    {
        txt_taikhoan = (TextView)v.findViewById(R.id.txt_taikhoan);
        txt_hoten = ((TextView)v.findViewById(R.id.txt_hoten));
        Session = new SessionNguoiDung(this);
        Session.checkLogin();
        HashMap<String, String> nguoidung = Session.thongtinnguoidung();
        String hoten = nguoidung.get(Session.HOTEN);
        String taikhoan = nguoidung.get(Session.TAIKHOAN);
        txt_hoten.setText(hoten);
        txt_taikhoan.setText(taikhoan);
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    public void btn_themyc(View view) {
        Them_Sua_YeuCau_Activity.trangthai = "them_moi";
        Intent  in = new Intent (QLCV.this, Them_Sua_YeuCau_Activity.class);
        startActivity(in);
         }
}