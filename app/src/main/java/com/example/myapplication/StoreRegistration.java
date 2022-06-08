package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;

public class StoreRegistration extends AppCompatActivity {
    String name;
    ArrayList<Store> dataList;
    private ImageView imageView;
    private final int GET_GALLERY_IMAGE = 200;
    Connector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_regitsration);

        Button btnMenu = (Button) findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(intent);
            }
        });

        imageView = (ImageView) findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent();
               intent.setType("image/*");
               intent.setAction(Intent.ACTION_GET_CONTENT);
               startActivityForResult(intent,0);
            }
        });





        Button btnRegistration = (Button) findViewById(R.id.btn_registration);
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView et_thum = (ImageView)findViewById(R.id.image);
                EditText et_storeName = (EditText) findViewById(R.id.storeName);
                EditText et_storeLoc = (EditText)findViewById(R.id.storeLoc);
                EditText et_stdFee = (EditText)findViewById(R.id.stdFee);
                EditText et_locFee = (EditText)findViewById(R.id.locFee);
                EditText et_typeFee = (EditText)findViewById(R.id.typeFee);

                String storeName = et_storeName.getText().toString();
                String storeLoc = et_storeLoc.getText().toString();
                String stdFee = et_stdFee.getText().toString();
                String locFee = et_locFee.getText().toString();
                String typeFee = et_typeFee.getText().toString();


                connector.store_info(et_thum,  storeName,  storeLoc,  stdFee,  locFee,  typeFee );



                dataList.add(new Store(name,R.drawable.image));




                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());

                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();

                    imageView.setImageBitmap(img);
                } catch (Exception e) {

                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
            }
        }
    }





}