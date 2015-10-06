package com.bit2015.mutiThread;

public class MutiThreadEx03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread1 = new DigitThread();//Thread생성
		Thread thread2 = new Thread(new AlphabetRunnableImpl());
		
		
		thread1.start();
		thread2.start();
		
	}

}
