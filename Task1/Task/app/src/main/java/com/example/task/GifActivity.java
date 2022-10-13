package com.example.task;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.task.Adapter.CourseAdapter;
import com.example.task.Modal.CourseModal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GifActivity extends AppCompatActivity {

    RecyclerView courseRV;
    CourseAdapter adapter;
    private ArrayList<CourseModal> courseModalArrayList;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        progressBar=findViewById(R.id.idPB);
        courseRV=findViewById(R.id.idRVCourses);


        courseModalArrayList = new ArrayList<>();
        gifview();
        buildRecyclerView();
    }


    public  void gifview(){
        String url="https://api.tenor.com/v1/search?key=X2918E9QRHPH&q=trending&limit=15";
        RequestQueue queue = Volley.newRequestQueue(GifActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("responsegif:"+response);
                progressBar.setVisibility(View.GONE);

                try {
                    JSONArray itemsDetails = response.getJSONArray("results");
                    if(itemsDetails.length()>0){

                        for (int i = 0; i < itemsDetails.length(); i++) {
                            JSONObject object = itemsDetails.getJSONObject(i);
                            JSONArray mediaa=object.getJSONArray("media");
                            JSONObject AAA=mediaa.getJSONObject(0);
                            JSONObject cc=AAA.getJSONObject("tinygif");
                            String gif=cc.getString("url");
                            System.out.println("osad"+cc.getString("url"));
                            System.out.println("media"+mediaa);
                            courseModalArrayList.add(new CourseModal("", gif, "", ""));
                            buildRecyclerView();

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

    private void buildRecyclerView() {
        adapter = new CourseAdapter(courseModalArrayList, GifActivity.this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        courseRV.setHasFixedSize(true);
        // setting layout manager
        // to our recycler view.
        courseRV.setLayoutManager(gridLayoutManager);
        // setting adapter to
        // our recycler view.
        courseRV.setAdapter(adapter);
    }
}