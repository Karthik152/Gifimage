package com.example.task.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.MainActivity;
import com.example.task.Modal.CourseModal;
import com.example.task.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<CourseModal> courseModalArrayList;
    private Context context;

    // creating a constructor for our variables.
    public CourseAdapter(ArrayList<CourseModal> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.griditem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        CourseModal modal = courseModalArrayList.get(position);
        holder.courseIV.loadUrl(modal.getCourseimg());
        System.out.println("position gif1"+modal.getCourseimg());
//        holder.courseIV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("123");
//                Intent intent=new Intent(context, MainActivity.class);
//                intent.putExtra("gifurl",modal.getCourseimg());
//                System.out.println("position gif"+modal.getCourseimg());
//
//
//            }
//        });
//        holder.courseIV.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // open another activity on item click
//                Intent intent = new Intent(context, MainActivity.class);
//                intent.putExtra("gifurl", modal.getCourseimg()); // put image data in Intent
//
//                System.out.println("dsf"+ modal.getCourseimg());
//                context.startActivity(intent);
//            }
//        });
        holder.courseIV.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // open another activity on item click
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("gifurl", modal.getCourseimg()); // put image data in Intent

                System.out.println("dsf" + modal.getCourseimg());
                context.startActivity(intent);
                return true;
            }

        });


//        Picasso.get().load(modal.getCourseimg()).into(holder.courseIV);
//        Glide.with(context).load(modal.getCourseimg()).into(holder.courseIV);

    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView courseNameTV, courseModeTV, courseTracksTV;
        private WebView courseIV;
        private LinearLayout line;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            courseIV = itemView.findViewById(R.id.idIVCourse);
            courseIV.setEnabled(true);
            line=itemView.findViewById(R.id.line1);
        }
    }
}
