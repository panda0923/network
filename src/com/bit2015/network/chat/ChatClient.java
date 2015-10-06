package com.bit2015.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatClient{
	public static final String SERVER_ADDRESS="192.168.1.92";
	public static final int SERVER_PORT=30000;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Socket socket =null;
		Scanner scan =new Scanner(System.in);
		
		
		try{
			//1.socket 생성
			socket = new Socket();
			//2.연결
			socket.connect( new InetSocketAddress(SERVER_ADDRESS,SERVER_PORT));
			//3.reader/writer 생성
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream() ) );
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
			
			//4.join 프로토콜
			System.out.println("닉네임:");
			String nickname=scan.nextLine();
			printWriter.println("join:"+nickname);
			printWriter.flush();
			bufferedReader.readLine();
			
			//6.ChatClientReciveThread시작
			Thread thread = new ChatClientReciveThread(bufferedReader);
			thread.start();
			
			
			//7.키보드 입력처리
			while(true){
				// System.out.println(">>");
				String input =scan.nextLine();
				if("quit".equals(input)==true)
				{
					printWriter.println("quit");
					printWriter.flush();
					break;
				}else{
					printWriter.println("message:"+input);
					printWriter.flush();
				}
			}
			// 10. 자원정리 
			scan.close(); 
			bufferedReader.close(); 
			printWriter.close(); 
			if (socket.isClosed() == false) { 
			 	socket.close(); 
			 		} 

		
		}
		catch(IOException e){
			
			log("에러"+e);
		}
				
		
	}
	public static void log(String log){//공통적으로 사용하기때문에 함수로 정의
		System.out.println("[ChatClient]"+log);
		}
	}

