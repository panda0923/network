package com.bit2015.mutiThread;

public class AlphabetThread extends Thread {

	@Override
	public void run() {
		for(char c= 'A';c<='Z';c++){
			System.out.print(c);//메인쓰레드,데이터주고 받기
			try{
				Thread.sleep(1000);//중간에 끊어가기
				
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}

	}
}