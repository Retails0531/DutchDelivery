package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignActivity extends AppCompatActivity {
    Connector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        Button btnSign = (Button) findViewById(R.id.btn_sign);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText et_id = (EditText)findViewById(R.id.sign_id);
                EditText et_pw = (EditText)findViewById(R.id.sign_pw);
                EditText et_phone = (EditText)findViewById(R.id.phone);
                EditText et_email = (EditText)findViewById(R.id.email);

                String id = et_id.getText().toString();
                String pw = et_pw.getText().toString();
                String phone = et_phone.getText().toString();
                String email = et_email.getText().toString();

                connector.sign_info(id,pw,phone,email);




            }
        });
    }
}