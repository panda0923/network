package com.bit2015.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NsLookup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan=new Scanner(System.in);

		try{
			while(true){
			System.out.println("도메인을 입력하세요");
			String host=scan.nextLine();
		
			//InetAddress[] inetAddress=InetAddress.getAllByName("www.naver.com");//배열로출력
				if("exit".equals(host)==true){
					break;
				}
				
				InetAddress[] inetAddress=InetAddress.getAllByName(host);
			for(int i=0; i<inetAddress.length;i++){
				System.out.println(inetAddress[i].getHostName()+" : "+inetAddress[i].getHostAddress());
				//System.out.println(inetAddress[i].getHostAddress());
				}
			
			}
		
			scan.close();
			
		}catch(UnknownHostException ex){
			//ex.printStackTrace();
			System.out.println("IP를 가져올 수 없습니다.");
			}
		
	}
}


