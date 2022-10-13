package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.task.Adapter.CourseAdapter;
import com.example.task.Modal.CourseModal;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.sql.SQLOutput;
import java.util.ArrayList;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {
    LinearLayout Gifs,Mainlayout;
    LinearLayout images;
    WebView webView;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gifs=findViewById(R.id.gifll);
        images=findViewById(R.id.imagell);
        webView=findViewById(R.id.gifdisplay);
        btn1=findViewById(R.id.uploadbtn);
        btn1.setVisibility(View.INVISIBLE);
        Mainlayout=findViewById(R.id.mainlayout);


//fetching uploaded images f
        images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Imageview.class);
                startActivity(intent);
            }
        });

        //gif image fetch from api

        Gifs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,GifActivity.class);
                startActivity(intent);
            }
        });
        try {

            Bundle bundle = getIntent().getExtras();
            String path = bundle.getString("gifurl");
            if(!bundle.isEmpty())
            {
                btn1.setVisibility(View.VISIBLE);
                Mainlayout.setBackgroundColor(Color.WHITE);

                System.out.println("hds"+path);
                webView.loadUrl(path);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        webView.setVisibility(View.INVISIBLE);
                        btn1.setVisibility(View.INVISIBLE);
                        uploadgif1(path);
                    }
                });
            }

        }catch (Exception e){

        }

    }


    private void uploadgif1(String path) {
                String url="https://snoo.gl/api/v2/method/account.insertimages";

        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("responsegifupoload"+response);
                ShowAddDetailsDialog1();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

        })
        {
            @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();
            params.put("images", path);

            return params;
        }

//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<String, String>();
//                headers.put("images", path);
//                return headers;
//            }

        };
        RequestQueue queue=Volley.newRequestQueue(MainActivity.this);
        request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);

    }

    //after upload Dialog box
    private void ShowAddDetailsDialog1() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this, R.style.Theme_Task);
        View mV1 = getLayoutInflater().inflate(R.layout.dailog_show_single_image, null);
        Button btn = mV1.findViewById(R.id.okbtn);
        builder1.setView(mV1);
        final AlertDialog dialog1 = builder1.create();
        dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog1.setCancelable(false);
        if (!dialog1.isShowing()) {
            dialog1.show();
            dialog1.getWindow().setLayout((int) (getResources().getDisplayMetrics().widthPixels * 0.9),
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
                Intent intent = new Intent(MainActivity.this, Imageview.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }

}
