package com.example.mainpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<ViewHolderPost> {

    private ArrayList<Post> myDataList = null;

    public MyAdapter(ArrayList<Post> dataList) {
        myDataList = dataList;
    }

    @Override
    public ViewHolderPost onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //전개자(Inflater)를 통해 얻은 참조 객체를 통해 뷰홀더 객체 생성
        View view = inflater.inflate(R.layout.post_list_item, parent, false);
        ViewHolderPost viewHolder = new ViewHolderPost(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderPost viewHolder, int position) {
        //ViewHolder가 관리하는 View에 position에 해당하는 데이터 바인딩
        viewHolder.imageView.setImageResource(myDataList.get(position).getImageResourceID());
        viewHolder.title.setText(myDataList.get(position).getMovieTitle());
        viewHolder.grade.setText(myDataList.get(position).getMovieGrade());

    }

    @Override
    public int getItemCount() {
        //Adapter가 관리하는 전체 데이터 개수 반환
        return myDataList.size();

    }
}