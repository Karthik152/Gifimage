package com.example.task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.task.Modal.CourseModal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Imageview extends AppCompatActivity {

    String url="https://snoo.gl/api/v2/method/account.getimages";

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);
        webView=findViewById(R.id.fetchimg);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        gifview();
    }

    public  void gifview(){
        String url="https://snoo.gl/api/v2/method/account.getimages";
        RequestQueue queue = Volley.newRequestQueue(Imageview.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("responsegif:"+response);

                try {
                    JSONArray itemsDetails = response.getJSONArray("items");
                    if(itemsDetails.length()>0){
                        System.out.println("idsd"+itemsDetails);

                        for (int i = 0; i < itemsDetails.length(); i++) {
                            JSONObject object = itemsDetails.getJSONObject(i);
                            String image=object.getString("url");
                            System.out.println("imgurl"+image);
                            webView.loadUrl(image);
                        }
                    }
                    else
                        System.out.println("No product to add");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                }


            }
        }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("error"+error);

            }
        });


        queue.add(jsonObjectRequest);

    }
}