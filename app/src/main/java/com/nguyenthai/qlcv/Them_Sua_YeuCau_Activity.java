package com.nguyenthai.qlcv;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Them_Sua_YeuCau_Activity extends AppCompatActivity {

    public static String trangthai="them_moi";

    private ProgressBar loading;
    private Spinner spinner_duan;
    private ArrayList<String> duan;
    private TextView tieu_de;
    private String URL_DSDUAN ="http://10.90.199.213:8080/api/duan.php/danhsach_duan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sua_yeucau);

        spinner_duan = findViewById(R.id.yc_spinner_tenduan);
        spinner_duan();
        check_trangthai();

    }

    private void check_trangthai() {

        tieu_de = findViewById(R.id.txt_yc_tuade);
        if(trangthai.equals("them_moi"))
            tieu_de.setText("THÊM YÊU CẦU");
        else
            tieu_de.setText("SỬA YÊU CẦU");

    }


    private void spinner_duan() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_DSDUAN, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String status = response.getString("status");
                            if(status.equals("success"))
                            {
                                duan = new ArrayList<String>();
                                final JSONArray jsonArray = response.getJSONArray("danhsach_duan");
                                for(int j =0; j< jsonArray.length(); j++)
                                {
                                    JSONObject object = jsonArray.getJSONObject(j);
                                    duan.add(object.getString("ten_du_an").trim());
                                }
                                ArrayAdapter userAdapter = new ArrayAdapter(Them_Sua_YeuCau_Activity.this, R.layout.support_simple_spinner_dropdown_item, duan);
                                spinner_duan.setAdapter(userAdapter);
                                //Chọn item
                                spinner_duan .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        // TODO Auto-generated method stub
                                        int spinner1Position = (int) spinner_duan.getItemIdAtPosition(position);
                                        try {
                                            JSONObject object = jsonArray.getJSONObject(spinner1Position);
                                            Toast.makeText(Them_Sua_YeuCau_Activity.this, object.getInt("id") + object.getString("ten_du_an"), Toast.LENGTH_SHORT).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> arg0) {
                                        // TODO Auto-generated method stub
                                    }
                                });
                              //  loading.setVisibility(View.GONE);
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
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }


}