package com.example.myapplication;

import android.content.Intent;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import androidx.appcompat.app.AppCompatActivity;

public class Connector extends AppCompatActivity {
    Socket socket;
    OutputStream outStream = null;
    DataOutputStream dataOutStream = null;
    Connector connector = new Connector();
    MessageListener ml = null;


    String LOGIN="login";
    String SIGN="sign";
    String STORE_UPDATE="store";
    String STORE_CHECK="storeCheck";
    String MENU="menu";

    public Connector(){
        try{
            connector.socket = new Socket("52.79.148.177 " ,60000);
            System.out.println("연결이 성공했습니다.");

            ml = new MessageListener(connector.socket); //서버의 메시지를 받아줄 메시지 스레드를 생성
            ml.start();

            outStream = connector.socket.getOutputStream();
            dataOutStream = new DataOutputStream(outStream);

        }catch(Exception e) {
            System.out.println("연결이 실패되었습니다.");
        }

    }



    public void sign_info(String sign_id,String sign_pw, String phone, String email){
        try{
            dataOutStream.writeUTF(SIGN+"///"+sign_id+"///"+sign_pw+"///"+phone+"///"+email);
        }catch(Exception e) {

        }

    }

    public  void sign_check(Boolean sign){
        if(sign.equals("SIGN_OK")){

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
    }

    public  void login_info(String login_id, String login_pw){
        try{
            dataOutStream.writeUTF(LOGIN+"///"+login_id+"///"+login_pw);
        }catch(Exception e) {

        }
    }

    public void login_check(Boolean login){
        if(login.equals("LOGIN_OK")){

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    public void store_info(ImageView img , String storeName, String location, String stdFee, String locFee, String typeFee ){
        try{
            dataOutStream.writeUTF(STORE_UPDATE+"///"+img+"///"+storeName+"///"+location+"///"+stdFee+"///"+locFee+"///"+typeFee);
        }catch(Exception e) {

        }
    }

    public void store_update(ImageView img , String storeName, String location, String stdFee, String locFee, String typeFee ){

        EditText et_stroeName =(EditText)findViewById(R.id.storeName) ;
        EditText et_location =(EditText)findViewById(R.id.storeName) ;
        EditText et_stdFee =(EditText)findViewById(R.id.storeName) ;
        EditText et_locFee =(EditText)findViewById(R.id.storeName) ;
        EditText et_typeFee =(EditText)findViewById(R.id.storeName) ;

        et_stroeName.setText(storeName);
        et_location.setText(location);
        et_stdFee.setText(stdFee);
        et_locFee.setText(locFee);
        et_typeFee.setText(typeFee);


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }


    public void menu_info(String menu, int price){
        try{
            dataOutStream.writeUTF(MENU+"///"+menu+"///"+price);
        }catch(Exception e) {

        }
    }

    public void store_check(){
        try{
            dataOutStream.writeUTF(STORE_CHECK);
        }catch(Exception e) {

        }
    }





}

