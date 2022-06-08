package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private LinearLayoutManager llm;
    private List<Integer> count;
    private int i = 0;
    private ArrayList<Store> dataList;
    Connector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.InitializeData();

        rv = (RecyclerView) findViewById(R.id.main_rv);
        llm = new LinearLayoutManager(this);

        count = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.main_rv);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager); // LayoutManager 등록
        recyclerView.setAdapter(new RecyclerAdapter(dataList));

        Button btnCardview = (Button)findViewById(R.id.cardview);
        btnCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                connector.store_check();

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BusinessesAuth.class);
                startActivity(intent);
            }
        });


    }
    public void InitializeData(){
        dataList = new ArrayList<>();
        dataList.add(new Store("입력",R.drawable.image));
    }
}