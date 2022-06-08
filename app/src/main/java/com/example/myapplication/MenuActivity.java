package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    View v_d;
    EditText et1;
    EditText et2;
    TextView tv1;
    TextView tv2;
    ArrayList<Menu> menuDataList;
    Connector connector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        this.InitializeMenuData();
        ListView listView = (ListView)findViewById(R.id.listView);
        final ListViewAdapter myAdapter = new ListViewAdapter(this,menuDataList);
        listView.setAdapter(myAdapter);


        tv1 = (TextView) findViewById(R.id.menu);
        tv2 = (TextView) findViewById(R.id.price);


        ExtendedFloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder d = new AlertDialog.Builder(MenuActivity.this);

                d.setTitle("메뉴 추가");
                v_d = (View) View.inflate(MenuActivity.this, R.layout.dialog, null);
                d.setView(v_d);
                d.setPositiveButton("입력", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       EditText et_menu = (EditText) v_d.findViewById(R.id.menu);
                       EditText et_price = (EditText) v_d.findViewById(R.id.price);

                        String menu = et_menu.getText().toString();
                        int price = Integer.parseInt(et_price.getText().toString());

                        connector.menu_info(menu,price);


                        menuDataList.add(new Menu(menu,price));
                        myAdapter.notifyDataSetChanged();


                    }
                });
                d.show();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getName(),
                        Toast.LENGTH_LONG).show();
            }
        });


    }

    public class ListViewAdapter extends BaseAdapter {
        Context mContext = null;
        LayoutInflater mLayoutInflater = null;
        ArrayList<Menu> items;

        public ListViewAdapter(Context context,ArrayList<Menu> data){
            mContext = context;
            items = data;
            mLayoutInflater = LayoutInflater.from(mContext);
        }
        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Menu getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View converView, ViewGroup parent) {
            View view = mLayoutInflater.inflate(R.layout.item_listview, null);

            TextView menuName = (TextView)view.findViewById(R.id.menu);
            TextView price = (TextView)view.findViewById(R.id.price);

            menuName.setText(items.get(position).getName());
            price.setText(items.get(position).getPrice());


            return view;
        }
    }

    public void InitializeMenuData(){
        menuDataList = new ArrayList<Menu>();
        menuDataList.add(new Menu("엽떡",10000));
        menuDataList.add(new Menu("튀김",1000));


    }

}