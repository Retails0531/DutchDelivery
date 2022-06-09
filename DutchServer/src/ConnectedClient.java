import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.StringTokenizer;

class ConnectedClient extends Thread{ //클라이언트접속시 스레드를 돌리며 관리해줄 클래스
	Socket socket;
	OutputStream outStream;    	
	DataOutputStream dataOutStream;
	InputStream inStream;       	
	DataInputStream dataInStream; //소켓및 스트림 선언
	String msg; //메시지 임시로 다루기 위해 다양하게 쓰임
	String CID; //클라이언트의 이름을 다루기 위해 쓰이는 문자열
	String pwd;
	Server server; //서버 클래스의 메서드나 변수를 사용하기 위함
	int checkvalue;
	static dbmanager dbm;
	
	
	final String END = "END_MSG";
	
	ConnectedClient(Socket _s, Server _ss){ //다른 클래스에 사용시 써먹을 생성자
		this.socket = _s;	
		this.server = _ss;
	}
	
	public void run() { //스레드로 개개의 클라이언트를 동시에 따로 관리해줌
		try {
			outStream =  this.socket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			inStream =  this.socket.getInputStream();
			dataInStream = new DataInputStream(inStream); //스트림들 연결
			while(true) { //클라이언트와 의소통을 유지하기 위해 반복문
				
				msg = dataInStream.readUTF(); // 메시지를 지속적으로 받아옴
				
				System.out.println("들어온 입력:" +msg);
				
				StringTokenizer stk = new StringTokenizer(msg, "///"); //메시지 타입을 //로 분류해서 다르게 처리
				
				if(stk.nextToken().equals("LogIn")) {
					
					CID = stk.nextToken();
					pwd = stk.nextToken();
					checkvalue = dbm.login(CID,pwd);
					dataOutStream.writeUTF("LogIn///"+checkvalue);
				}
				else {
					dataOutStream.writeUTF("Response///500///SyntexError///"+END);
				}
				
				
			}
		}catch(IOException e1) { //클라이언트의 연결이 끊겼을시 처리해주기 위한 예외처리
			try {
				
				this.socket.close(); //소켓을 안정적으로 닫아줌
			}catch (IOException e2) {
			}
			
			server.clients.remove(this); //서버에 arraylist에서 해당 클라이언트 접속 정보를 삭제해줌
			
		}catch(Exception e) {
			
			
		}
	}
}