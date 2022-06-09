import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.StringTokenizer;

class ConnectedClient extends Thread{ //Ŭ���̾�Ʈ���ӽ� �����带 ������ �������� Ŭ����
	Socket socket;
	OutputStream outStream;    	
	DataOutputStream dataOutStream;
	InputStream inStream;       	
	DataInputStream dataInStream; //���Ϲ� ��Ʈ�� ����
	String msg; //�޽��� �ӽ÷� �ٷ�� ���� �پ��ϰ� ����
	String CID; //Ŭ���̾�Ʈ�� �̸��� �ٷ�� ���� ���̴� ���ڿ�
	String pwd;
	Server server; //���� Ŭ������ �޼��峪 ������ ����ϱ� ����
	int checkvalue;
	static dbmanager dbm;
	
	
	final String END = "END_MSG";
	
	ConnectedClient(Socket _s, Server _ss){ //�ٸ� Ŭ������ ���� ����� ������
		this.socket = _s;	
		this.server = _ss;
	}
	
	public void run() { //������� ������ Ŭ���̾�Ʈ�� ���ÿ� ���� ��������
		try {
			outStream =  this.socket.getOutputStream();
			dataOutStream = new DataOutputStream(outStream);
			inStream =  this.socket.getInputStream();
			dataInStream = new DataInputStream(inStream); //��Ʈ���� ����
			while(true) { //Ŭ���̾�Ʈ�� �Ǽ����� �����ϱ� ���� �ݺ���
				
				msg = dataInStream.readUTF(); // �޽����� ���������� �޾ƿ�
				
				System.out.println("���� �Է�:" +msg);
				
				StringTokenizer stk = new StringTokenizer(msg, "///"); //�޽��� Ÿ���� //�� �з��ؼ� �ٸ��� ó��
				
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
		}catch(IOException e1) { //Ŭ���̾�Ʈ�� ������ �������� ó�����ֱ� ���� ����ó��
			try {
				
				this.socket.close(); //������ ���������� �ݾ���
			}catch (IOException e2) {
			}
			
			server.clients.remove(this); //������ arraylist���� �ش� Ŭ���̾�Ʈ ���� ������ ��������
			
		}catch(Exception e) {
			
			
		}
	}
}