package com.bit2015.network.ehco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class EchoServerReceiveeThread extends Thread {
		private Socket socket =null;
		
		public EchoServerReceiveeThread(Socket socket){
			this.socket = socket;
			
		}
		//run구현

		@Override
		public void run() {
			InetSocketAddress inetSoketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();

			System.out.println("[서버] 연결됨 < From:"+
					inetSoketAddress.getHostName()+
					":"+
					inetSoketAddress.getPort());
			
			//InputStream is =null;
			//OutputStream os = null;
			
			
		
			try{
				BufferedReader reader= null;
				PrintWriter pw=null;
					//is = socket.getInputStream();
					//os = socket.getOutputStream();
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()) );
					pw =new PrintWriter(socket.getOutputStream());
					while(true){
					//byte[] buffer = new byte[128];
					//int readByteCount = is.read( buffer );
					//if( readByteCount < 0 ){//클라이언트가 정상적으로 종료
					//	System.out.println("[서버]클라이언트로부터 연결 끊기");
					//	break;
					//}
					//String data =new String (buffer,0,readByteCount,"UTF-8");
					
					String data=reader.readLine();
					if(data == null){
						break;
					}
					System.out.println("[서버] 데이터 수신:"+data);
					//os.write(data.getBytes("UTF-8"));
					//os.flush();
					
					pw.println(data);
					pw.flush();
					
				}
					reader.close();
					pw.close();
				//is.close();//스트림닫기
				//os.close();
				socket.close();//데이터 소켓닫기
				if(socket.isClosed() == false){
					socket.close();
				}
				}catch(IOException ex){
					System.out.println("[서버]에러"+ex);
				}
		}
		
}
