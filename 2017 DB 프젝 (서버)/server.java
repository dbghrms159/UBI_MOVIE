import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class server {
	
	public static void main(String[] args) {
		
		
		try {
			DataBaseMssql sql = new DataBaseMssql();
			int port = 8008;																//portNumber 8008
			System.out.println("Starting Java server");					
			ServerSocket server = new ServerSocket(port);									//���� ���� ���� 
			System.out.println("Listening at port "+ port + " ....");
			
			while(true) {
				
				Socket sock = server.accept();												//�ܺ� ���Ͽ� ���� ��û ���
				InetAddress host = sock.getLocalAddress();									//������ ������ �ִ� IP�ּ� ����
				int clientPort = sock.getPort();											//������ ������ �ִ� portNumber�� ����
				System.out.println("A client connected host : "+ host + ", port : "+ clientPort);
				
				ObjectInputStream in = new ObjectInputStream(sock.getInputStream());		//����Ʈ ���� �����͸� Object���� ���� ��ȯ
				Object obj = in.readObject();												//�޾ƿ� object�� �о�� ������ ����
				System.out.println("Input : "+ obj);
				
				ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());	//Object������ ����Ʈ �������� ��ȯ
				//obj = "33";
				out.writeObject(sql.Main_List((String)obj));										//Onject�� outputStream�� ����
				out.flush();																//���ۿ� ���� ��� ��Ʈ���� ����Ѵ�.
				sock.close();																//���� ����
			}
		}catch (Exception e) {
			
		}
	}
}
