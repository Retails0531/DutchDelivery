package com.example.myapplication;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class MessageListener extends Thread{ // 채팅 화면 접속시 서버의 메세지를 항시 받아올 스레드 선언
    Socket socket;
    InputStream inStream;
    DataInputStream dataInStream; //스트림 선언
    String msg="";
    boolean flag = false; // quit에 대한 요청 수락시 true로 바뀜
    MessageListener(Socket _s){
        socket = _s;
    }

    public void run() {
        try {
            inStream = this.socket.getInputStream();
            dataInStream = new DataInputStream(inStream);


            while(true) {//반복문을 계속돌면서 상시 read해줌
                msg = dataInStream.readUTF(); //
                StringTokenizer stk = new StringTokenizer(msg, "///"); //메시지 타입을 분류 해줌
                String query= stk.nextToken();

                    if(query.equals("LogIn")){
                        String value = stk.nextToken();

                        if(value.equals("0")){

                        }
                        else if(value.equals("1")){

                        }
                        else if(value.equals("2")){

                        }

                    }
                    if(query.equals("Sign")){

                    }
                    if(query.equals("Store")){

                    }
                    if(query.equals("Menu")){

                    }


                }


        } catch(Exception e) {

        }

    }


}

