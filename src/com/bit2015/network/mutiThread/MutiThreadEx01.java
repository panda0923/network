package com.bit2015.mutiThread;

public class MutiThreadEx01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread = new DigitThread();
		thread.start();
		
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


