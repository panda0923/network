package com.bit2015.mutiThread;

public class AlphabetRunnableImpl implements Runnable {//일반 코드 new thread해도 구현되지 않는다.

	@Override
	public void run() {//반드시 run()구현해야한다.
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
