package com.bit2015.network.time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.soap.Node;

public class TimeServer {
	private static final int BUFFER_SIZE=1024;
	private static final int PORT=40000;
	public static void main(String[] args) {
	DatagramSocket datagramSocket =null;
	
	try{
		//1.서버 소캣 생성
		datagramSocket =new DatagramSocket(PORT);
		//2.수신대기
		log("packet 수신대기");
		
		DatagramPacket receivePacket = new DatagramPacket
										(new byte[BUFFER_SIZE],BUFFER_SIZE);
		datagramSocket.receive(receivePacket);
		//3.수신데이터출력
		String message= new String(receivePacket.getData(),0,receivePacket.getLength(),"UTF-8");
		log("packet수신:"+message);
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss a" );
		String date = format.format( new Date());
		System.out.println(receivePacket.getAddress().getAddress()+"에 현재 시간 "+date+"을 전송합니다.");
		
		DatagramPacket sendPacket = new DatagramPacket(date.getBytes(),date.getBytes().length,receivePacket.getAddress(),receivePacket.getPort());
		datagramSocket.send(sendPacket);
		datagramSocket.close();
	}catch(IOException ex){
		log("에러:"+ex);
		}
	}
	public static void log(String log){
		System.out.println("[TimeServer]:"+log);
	}
}