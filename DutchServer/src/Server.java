import java.io.*;
import java.io.IOException;
import java.net.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Server {   // ����Ŭ����
	ServerSocket ss = null;
	ArrayList<ConnectedClient> clients = new ArrayList<ConnectedClient>(); //������ ���� ����Ʈ�� �������� ��̸���Ʈ
	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.ss = new ServerSocket(60000); //���� ������ default�� 60000
			System.out.println("Server > Server Socket is Created...");
			while(true) { //��� �ݺ��ϸ鼭
				Socket socket = server.ss.accept(); //Ŭ���̾�Ʈ�� �޾���
				ConnectedClient  c = new ConnectedClient(socket, server); //Ŭ���̾�Ʈ�� �������� �ٷ�� ���� connectedclient Ŭ������ ���������
				server.clients.add(c);//Ŭ���̾�Ʈ�� ��̸���Ʈ�� �߰�������
				c.start(); // 
			}
			
		}catch(SocketException e) {
			System.out.println("Server > ���� ���� ���� �߻�, ��������");
		}catch(IOException e) {
			System.out.println("Server > ����� ���� �߻�");
		}

	}

}