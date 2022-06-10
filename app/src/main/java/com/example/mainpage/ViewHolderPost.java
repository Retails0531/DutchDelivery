package com.example.mainpage;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolderPost extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView title;
    TextView grade;

    ViewHolderPost(View itemView)
    {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView2);
        title = itemView.findViewById(R.id.title);
        grade = itemView.findViewById(R.id.grade);
    }
}