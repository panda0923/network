package com.bit2015.network.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.bit2015.network.chat.ChatServer;

public class HttpThread extends Thread {
	private static final String WEB_ROOT="C:\\bit2015\\workspace\\Network\\web-root";
	private Socket socket;

	public HttpThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader bufferedReader = null;
		OutputStream out = null;
		try{
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out=socket.getOutputStream();
		InetSocketAddress inetsocketAddress = (InetSocketAddress) socket
				.getRemoteSocketAddress();

		HttpServer.log("연결됨:" + inetsocketAddress.getHostName());
		//3.요청처리
		String request = bufferedReader.readLine();
				while(true){
					HttpServer.log("request:"+request);
					
					String[] tokens =request.split("");//분리
					
							
					if("GET".equals(tokens[0]) ==true){
						sendStaticResource(out,tokens[1],tokens[2]);
					
					}else{
						HttpServer.log("에러:지원하지 않는 요청 명령("+tokens[0]+")");
					}
					
				}
				}catch(IOException e){
					HttpServer.log("error:"+ e);
			
				}
			
		
		
	}

	private void sendStaticResource(OutputStream out, String path,
			String protocol)throws IOException {
		if("/".equals(path)){
			path="/index.html";
		}
		String extendsion = path.substring(path.lastIndexOf("."));
		
		if(".html".equals(extendsion)==false&&".html".equals(extendsion)==false){
			sendError404(out,protocol);
			return;
		}
		File file = new File(WEB_ROOT,path);
		if(file.exists()==false){
			sendError404(out,protocol);
			return;
		}
		
	}

	private void sendError404(OutputStream out, String protocol)throws IOException {
		//header
		out.write((protocol+"404 File Not Found\r\n").getBytes());
		out.write("Content-Type:text/html; charset=UTF-8\r\n".getBytes());
		
		out.write("\r\n".getBytes());
		
		out.write("<h1>File Not Found</h1>".getBytes());
		
		out.flush();
	}

}
