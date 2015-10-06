package com.bit2015.network.ehco;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class UDPEchoServer {
	private static final int BUFFER_SIZE=1024;
	private static final int PORT=40000;
	public static void main(String[] args){
		DatagramSocket datagramSocket=null;
		Scanner scan=null;
		try{
			//1.UDP서버소캣생상
			datagramSocket = new DatagramSocket(PORT);
			//2.수신대기
			
			log("packet 수신대기");
	
			while(true){
				
			DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE],BUFFER_SIZE);
			datagramSocket.receive(receivePacket);
			
			//3.수신 데이터 출력
			String message =new String(receivePacket.getData(),0,receivePacket.getLength(),"UTF-8");
			log("Packet 수신:"+message);
	
			
			//4.데이터 보내기(생성자 오버로드)
			DatagramPacket sendPacket = new DatagramPacket(
											receivePacket.getData(), 
											receivePacket.getLength(),
											receivePacket.getAddress(), 
											receivePacket.getPort());
			datagramSocket.send(sendPacket);
			if("quit".equals(message)){
				System.out.println("클라이언트에서 연결 종료");
				break;
				}
			
			}
			
		}catch(IOException e){
			log("에러:"+e);
		}finally{
			datagramSocket.close();
			log("서버종료");
		}
	}
	public static void log(String log){
		System.out.println("[UDPEchoServer]:"+log);
	}
}
