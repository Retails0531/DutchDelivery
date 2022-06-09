import java.io.*;
import java.io.IOException;
import java.net.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Server {   // 메인클래스
	ServerSocket ss = null;
	ArrayList<ConnectedClient> clients = new ArrayList<ConnectedClient>(); //접속한 유저 리스트를 저장해줄 어레이리스트
	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.ss = new ServerSocket(60000); //서버 소켓은 default가 60000
			System.out.println("Server > Server Socket is Created...");
			while(true) { //계속 반복하면서
				Socket socket = server.ss.accept(); //클라이언트를 받아줌
				ConnectedClient  c = new ConnectedClient(socket, server); //클라이언트를 서버에서 다루기 위해 connectedclient 클래스에 연결시켜줌
				server.clients.add(c);//클라이언트를 어레이리스트에 추가시켜줌
				c.start(); // 
			}
			
		}catch(SocketException e) {
			System.out.println("Server > 소켓 관련 예외 발생, 서버종료");
		}catch(IOException e) {
			System.out.println("Server > 입출력 예외 발생");
		}

	}

}