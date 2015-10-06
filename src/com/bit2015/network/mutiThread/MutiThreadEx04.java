package com.bit2015.mutiThread;

public class MutiThreadEx04 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread1 = new DigitThread();
		Thread thread2 = new Thread(new AlphabetRunnable02());
		
		thread1.start();
		thread2.start();
	}

}
