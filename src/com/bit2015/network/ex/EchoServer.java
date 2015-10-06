package com.bit2015.network.ex;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			ServerSocket server = new ServerSocket(10001);
			System.out.println("접속을 기다립니다.");
			
			Socket socket = server.accept();
			InetAddress inetaddr = socket.getInetAddress();
			System.out.println(inetaddr.getHostAddress()+"로 부터 접속하였습니다.");
			OutputStream out = socket.getOutputStream();
			InputStream in = socket.getInputStream();
			PrintWriter pw = socket(new OutputStreamWriter(out));
			
		}
	}

}
