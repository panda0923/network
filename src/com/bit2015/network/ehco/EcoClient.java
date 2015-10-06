package com.bit2015.network.ehco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EcoClient {
	public static final String SERVER_ADDRESS="192.168.1.92";
	public static final int SERVER_PORT=10002;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		Socket socket = null;
		
		
		try{
		
			//Scanner scan =new Scanner(System.in);
		socket = new Socket();
		socket.connect( new InetSocketAddress(SERVER_ADDRESS,SERVER_PORT));
		
		//쓰고받기
		
		BufferedReader reader= new	BufferedReader(new InputStreamReader(System.in));
		PrintWriter printwriter = null;
		
		//OutputStream os = socket.getOutputStream();
		//InputStream is = socket.getInputStream();
		
		while(true){
			new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printwriter=new PrintWriter(socket.getOutputStream());
			
			System.out.print(">>");
			String data = reader.readLine();
			if("exit".equals(data)== true){
	
				break;
			}
			//String data=scan.nextLine();
			//if("exit".equals(data)==true){
			//	break;
			//}
			data = data+ "\n"; 
			//os.write(data.getBytes("UTF-8"));
			
			//byte[] buffer = new byte[ 128 ];
			//int readByteCount =is.read( buffer );
			//data=new String (buffer,0,readByteCount,"UTF-8");
			System.out.println("<<<"+data);
			printwriter.print(data);
			printwriter.flush();
			String echo =reader.readLine();
			System.out.print("서버로부터 전달받은 문자열:+"+echo);
			
			}
		reader.close();
		printwriter.close();
		//os.close();
		//is.close();
		//소캣닫기
		if(socket.isClosed()==false){
		socket.close();
		}
		
		}catch(IOException ex){
			System.out.println("<<에러:"+ex);
		}
	}

}


