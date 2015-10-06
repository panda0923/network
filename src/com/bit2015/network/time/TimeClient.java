package com.bit2015.network.time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Date;

public class TimeClient {
	private static final String SERVER_IP="192.168.1.92";
	private static final int SERVER_PORT=40000;
	private static final int BUFFER_SIZE=1024;
	public static void main(String[] args) {
		DatagramSocket datagramSocket=null;
		
		
		try{
			//1.클라이언트 소캣생새성
			datagramSocket=new DatagramSocket();
			log("연결");
			//2.packet보내기
		
			byte[] data = "".getBytes();
			//DatagramPacket sendPacket
			// =new DatagramPacket(data,data.length,
			//		 new InetSocketAddress(SERVER_IP,SERVER_PORT));
			//3.데이터 받기
			DatagramPacket sendPacket = new DatagramPacket("".getBytes(),"".getBytes().length,
					new InetSocketAddress(SERVER_IP,SERVER_PORT));
			
			datagramSocket.receive(sendPacket);
			DatagramPacket receivePacket = new DatagramPacket(new byte[BUFFER_SIZE],BUFFER_SIZE);
			datagramSocket.receive(receivePacket);
			//4.받은 데이터 출력
			String msg =new String(receivePacket.getData(),0,receivePacket.getData().length);
			System.out.println("<<"+msg);
			
			datagramSocket.close();
		}catch(IOException e){
			log("에러:"+e);
		}

	}
	public static void log(String log){
		System.out.println("[TimeClient]:"+log);
	}
}
