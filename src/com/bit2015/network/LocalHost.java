package com.bit2015.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			InetAddress inetAddress =InetAddress.getLocalHost();
			InetAddress.getLocalHost();//스택이기때문에 
			System.out.println(inetAddress.getHostName());//호스트 이름 출력
			System.out.println(inetAddress.getHostAddress());//호스트 IP주소 출력
			
			byte [] addresses = inetAddress.getAddress();
			for(int i=0; i<addresses.length;i++){
				int address=addresses[i]&0xff;
				System.out.println(address);
			}
			
		}catch(UnknownHostException ex){
			ex.printStackTrace();//에러가 어디서 발생하였는지 찾기
		}
		
	}

}
