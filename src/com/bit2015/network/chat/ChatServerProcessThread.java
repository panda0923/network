package com.bit2015.network.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

public class ChatServerProcessThread extends Thread {
	//in,out받아내기
	private static final String PROTOCOL_SERVER=":";
	private String nickname;
	private Socket socket;
	private List<PrintWriter>listPrintWriters;
	
	public ChatServerProcessThread(Socket socket,List<PrintWriter> listPrintWriters){//파라미터로 받아오는 socket
		this.socket=socket;//여기 sokcet
		this.listPrintWriters=listPrintWriters;
	}
	@Override
	public void run() {
		BufferedReader br= null;
		PrintWriter pw=null;
		
		try{
		//1.스트림얻기
		br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
		pw = new PrintWriter(socket.getOutputStream());
		//2.어디서 연결됐다 표시(리모트 호스트 정보 얻기)클라이언트 정보얻어오기
		InetSocketAddress inetSocketAddress = (InetSocketAddress)
				socket.getRemoteSocketAddress();
		
		String remoteHostAddress = inetSocketAddress.getHostName();
		int remoteHostPort = inetSocketAddress.getPort();
		ChatServer.log("연결됨 from"+remoteHostAddress+":"+inetSocketAddress);
		
		//3.요청처리
		while(true){
			String request = br.readLine();
			if(request ==null){
				ChatServer.log("클라이언트로부터 연결 끊김");
				break;
			}
			String [] tokens = request.split(PROTOCOL_SERVER);
			if("join".equals(tokens[0])){
				doJoin(pw,tokens[1]);
			}else if("quit".equals(tokens[0])){
				doQuit(pw);
				
			}else if("message".equals(tokens[0])){
				doMessage(tokens[1]);
				
			}else{
				ChatServer.log("에러:알수 없는 요청명령("+tokens[0]+")");
			}
		}
		//4.자원정리
		
		br.close();
		pw.close();
		if(socket.isClosed()==false){
		socket.close();
		}
		}catch(IOException e){
			ChatServer.log("error:"+ e);
			doQuit(pw);
		}
	}
	private void doQuit(PrintWriter pw){
		//1.list에서 제거.(pw제거)
		removePrintWriter(pw);
		
		//2.00님이 퇴장하셨습니다.
		String data =nickname+"님이 퇴장하셨습니다.";
		broadcast(data);
	}
	private void doMessage(String message){
		String data = nickname+":"+message;
		broadcast(data);
	}
	private void doJoin(PrintWriter pw , String nickname){
		//1.닉네임저장
		this.nickname=nickname;
		//2.
		String message = nickname+"님이 입장했습니다.";
		broadcast(message);
		//3.
		addPrintWriter(pw);
		//4.ack
		pw.println("join:ok");
		pw.flush();
	}
	private void addPrintWriter(PrintWriter pw){//동시 접속해서 조인할경우 나중에 들어온 객체는 락걸림(스레드들끼리 한번씩만 )
		synchronized(listPrintWriters){
		listPrintWriters.add(pw);
		}
	}
	private void removePrintWriter(PrintWriter pw){//1.list에서 제거!!
		synchronized(listPrintWriters){
			listPrintWriters.remove(pw);
			}
	}
	private void broadcast(String data){
		data+="\n";
		
//		for(PrintWriter pw :listPrintWriters){			
//		}
		int count =listPrintWriters.size();
		for(int i =0;i<count;i++){
			PrintWriter pw = listPrintWriters.get(i);
			pw.println(data);
			pw.flush();
		}
	}
}
