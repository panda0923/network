package com.bit2015.network.ehco;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPEchoClient {
	private static final String SERVER_IP="192.168.1.92";
	private static final int SERVER_PORT=40000;
	private static final int BUFFER_SIZE=1024;
	public static void main(String[] args) {
		DatagramSocket datagramSocket = null;
		Scanner scan=null;
		try{
			scan=new Scanner(System.in);
			
		//1.UDP 클라이언트 소캣생성
		datagramSocket = new DatagramSocket();
		log("연결");
		//2.packet보내기
		while(true){
			//사용자 입력받기
			System.out.println("입력하세요:");
		String message = scan.nextLine();
		if("quit".equals(message)==true){
			;
			System.out.println("연결종료");
			break;
			}
		byte[]data=message.getBytes();
		
		DatagramPacket sendPacket = new DatagramPacket(data,data.length,
										new InetSocketAddress(SERVER_IP,SERVER_PORT));
		datagramSocket.send(sendPacket);
		
		//3.데이터 받기
		
		DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE],BUFFER_SIZE);
		datagramSocket.receive(receivePacket);
		//4.받은 데이터 출력
		message = new String(receivePacket.getData(),0,receivePacket.getLength(),"UTF-8");
		System.out.println("<<"+message);
	
		}
		datagramSocket.close();
		scan.close();
		}catch(IOException e){
			log("에러:"+e);
		}
	}
	public static void log(String log){
		System.out.println("[UDPEchoClient]:"+log);
	}
}
