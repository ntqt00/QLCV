package com.nguyenthai.qlcv.ui.yeucau;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.nguyenthai.qlcv.R;
import com.nguyenthai.qlcv.ui.duan.DuAnModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YeuCauFragment extends Fragment {



    private Context globalContext = null;
    RecyclerView rv_yc;
    ArrayList<YeuCauModel> ycm;



    YeuCauAdapter yc_adapter;
    Button btn_add;
    private static String URL ="http://10.90.199.213:8080/api/yeucau.php/danhsach";
    private ProgressBar loading;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_yeucau, container, false);

        // ánh xạ
        rv_yc = view.findViewById(R.id.rv_yeucau);
        loading = view.findViewById(R.id.yeu_cau_loading);
        // ẩn process bar
        loading.setVisibility(View.VISIBLE);
        danhsach_yeucau();
        return view;
    }

       private void danhsach_yeucau() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.getString("status");
                            if(status.equals("success"))
                            {
                                ycm = new ArrayList<>();
                                JSONArray jsonArray = response.getJSONArray("danhsach");
                                for(int j =0; j< jsonArray.length(); j++)
                                {
                                    JSONObject object = jsonArray.getJSONObject(j);
                                    String ma_yeu_cau = object.getString("ma_yc").trim();
                                    String ten_du_an = object.getString("ten_du_an").trim();
                                    String loai_yeu_cau = object.getString("ten_loai").trim();
                                    String yeu_cau = object.getString("yeu_cau").trim();
                                    String don_vi_yeu_cau = object.getString("khach_hang").trim();
                                    String nguoi_tao = object.getString("nguoi_tao").trim();
                                    String nguoi_xu_ly = object.getString("nguoi_xu_ly").trim();
                                    String tan_suat = object.getString("tuan_xuat").trim();
                                    String muc_do = object.getString("muc_do").trim();
                                    String uu_tien = object.getString("uu_tien").trim();
                                    String trang_thai = object.getString("trang_thai").trim();

                                    if(trang_thai.equals("2"))
                                        trang_thai = "Đã xử lý";
                                    else trang_thai = "Chưa xử lý";

                                    String gui_sms = object.getString("send_sms").trim();
                                    String ngay_tao = object.getString("send_sms").trim();
                                    String ngay_bd = object.getString("ngay_tao").trim();
                                    String ngay_kt = object.getString("ngay_kt").trim();
                                    String ngay_kttt = object.getString("ngay_kt_tt").trim();

                                    ycm.add(new YeuCauModel(ma_yeu_cau ,ten_du_an ,loai_yeu_cau ,yeu_cau ,don_vi_yeu_cau ,nguoi_tao ,nguoi_xu_ly,tan_suat,muc_do ,uu_tien ,trang_thai ,gui_sms, ngay_tao, ngay_bd, ngay_kt, ngay_kttt));
                                }
                                yc_adapter = new YeuCauAdapter(getContext(), ycm);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                                rv_yc.setAdapter(yc_adapter);
                                rv_yc.setLayoutManager(linearLayoutManager);
                                loading.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
    }
}