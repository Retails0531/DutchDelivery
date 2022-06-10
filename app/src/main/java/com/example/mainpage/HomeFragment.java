package com.example.mainpage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private ArrayList<Post> dataList;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home, container, false);



        RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager); // LayoutManager 등록
        recyclerView.setAdapter(new MyAdapter(dataList));  // Adapter 등록
        return view;
    }@Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InitializeData();
    }

    public void InitializeData()
    {
        System.out.println("ddddddddddddd");
        dataList = new ArrayList<>();

        dataList.add(new Post(R.drawable.image1,"어벤져스", "12세관람가"));
        dataList.add(new Post(R.drawable.image2,"미션임파서블", "15세관람가"));
        dataList.add(new Post(R.drawable.image3,"아쿠아맨", "12세관람가"));
    }


}