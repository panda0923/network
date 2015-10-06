package com.bit2015.network.chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	private static final int Port=30000;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		List<PrintWriter> listPrintWriters = new ArrayList<PrintWriter>();
		
		
		try{
			//1.서버 소캣생성
			serverSocket = new ServerSocket();
			
			//2.바인딩
			InetAddress inetAddress =InetAddress.getLocalHost();//컴터마다 호스트가 다르기때문에 프로그램을 통해 호스트를 가져온다
			String hostAddress = inetAddress.getHostAddress();//호스트주소 받아오기 
			serverSocket.bind(new InetSocketAddress(hostAddress,Port));
			log("연결기다림"+hostAddress+":"+Port);
			
			//3.연결 요청 기다림
			while(true){
			Socket socket = serverSocket.accept();
			Thread thread = new ChatServerProcessThread(socket,listPrintWriters);
			thread.start();
			
			}
		}catch(IOException e){
			
			log("에러"+e);
		}finally{
			if(serverSocket !=null && serverSocket.isClosed()==false){
				try{
					serverSocket.close();
				}catch(IOException e){
					
					log("에러"+e);
				}
			}
		}
	}
	public static void log(String log){//공통적으로 사용하기때문에 함수로 정의
		System.out.println("[ChatServer]"+log);
	}
}
