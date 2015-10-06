package com.bit2015.network.ehco;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
///다중쓰레드 사용하기!!!
public class EhcoServer {
	private static final int Port=10002;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerSocket serverSocket = null;
		
		try{
			//1.서버 소캣 생성
			serverSocket = new ServerSocket();
			
			//2.바인딩
			InetAddress inetAddress = InetAddress.getLocalHost();
			String hostAddress = inetAddress.getHostAddress();//id찾기
			
			serverSocket.bind( new InetSocketAddress( hostAddress , Port ) );
			System.out.println("[서버] 바인딩 "+hostAddress+":"+Port);
			
			//3.연결 요청 대기
			while(true){
			System.out.println("[서버] 연결 기다림");
			Socket socket = serverSocket.accept();//이 지점에서 대기 상태
				
			//EchoServerReceiveThread 생성!!!
			Thread thread = new EchoServerReceiveeThread(socket);
			thread.start();
			
			}
		}catch(IOException e){
			System.out.println("[서버]에러"+e);
		}finally{
			if(serverSocket !=null &&serverSocket.isClosed()==false){
				try {
					serverSocket.close();
				} catch (IOException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		}
		
		}
}
