package com.nguyenthai.qlcv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.android.volley.Request.Method.PATCH;

public class MainActivity extends AppCompatActivity {

    private EditText txt_tendangnhap;
    private EditText txt_matkhau;
    private static String URL_LOGIN ="http://10.90.199.213:8080/api/nguoidung.php/dangnhap";
    private ProgressBar loading;
    SessionNguoiDung Session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_tendangnhap= findViewById(R.id.txttentaikhoan);
        txt_matkhau=findViewById(R.id.txtmatkhau);
        loading = findViewById(R.id.processbar);
        loading.setVisibility(View.GONE);

        Session = new SessionNguoiDung(this);


    }

    public void btn_dangnhap(View view) {

        String tentaikhoan = txt_tendangnhap.getText().toString().trim();
        String matkhau = txt_matkhau.getText().toString().trim();
        if(!tentaikhoan.isEmpty() || !matkhau.isEmpty())
        {
            dangnhap(tentaikhoan, matkhau);
        }
        else
        {
            txt_tendangnhap.setError("Nhập tên đăng nhập");
            txt_matkhau.setError("Nhập mật khẩu");
        }

    }

    private void dangnhap(final String tentaikhoan, final String matkhau) {

        loading.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(StringRequest.Method.POST, URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");

                    if(status.equals("success"))
                    {
                        JSONArray jsonArray = jsonObject.getJSONArray("nguoidung");
                        for(int i=0; i<jsonArray.length(); i++)
                        {
                            loading.setVisibility(View.GONE);
                            JSONObject object = jsonArray.getJSONObject(i);
                            String hoten = object.getString("ho_ten").trim();
                            String taikhoan = object.getString("tai_khoan").trim();
                            Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            Session.taoSession(hoten, taikhoan);
                            Intent in = new Intent(MainActivity.this, QLCV.class);
                            startActivity(in);
                            finish();
                        }
                    }
                    else if(status.equals("fail"))
                    {
                        Toast.makeText(MainActivity.this, "Sai mật khẩu hoặc tài khoản", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    //Toast.makeText(MainActivity.this, "LỔI"+ e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "LỔI "+ error.toString(), Toast.LENGTH_LONG).show();
                loading.setVisibility(View.GONE);
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put( "tentaikhoan", tentaikhoan);
                params.put("matkhau", matkhau);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}