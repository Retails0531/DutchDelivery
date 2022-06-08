package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    Connector connector ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Log.e("Debug", Utility.INSTANCE.getKeyHash(this));


        Button btnLogin = (Button) findViewById(R.id.join_button);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et_id = (EditText)findViewById(R.id.login_id);
                EditText et_pw = (EditText)findViewById(R.id.login_password);

                String id =et_id.getText().toString();
                String pw = et_pw.getText().toString();


                connector.login_info(id,pw);




            }
        });
    }


}