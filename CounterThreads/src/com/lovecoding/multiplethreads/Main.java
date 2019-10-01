package com.lovecoding.multiplethreads;

public class Main {

	
	public static void main(String[] args) {
		
		Countdown countdown1=new Countdown();
		//Countdown countdown2=new Countdown();
		
		Countdownthread t1=new Countdownthread(countdown1);
		t1.setName("Thread 1");
		
		Countdownthread t2=new Countdownthread(countdown1);
		t2.setName("Thread 2");
		t1.start();
		t2.start();
/*		try{
		 t1.join();
		t2.join();
		}catch(InterruptedException e){
			System.out.println("interrupted ..!");
		} */
	}
}

class Countdown{
	private int i;
	public void countdown(){
	 String color;
	 
	 switch(Thread.currentThread().getName()){
	 
	 case "Thread 1":
		 color=ThreadColor.ANSI_CYAN;
		 break;
	 case "Thread 2":
		 color=ThreadColor.ANSI_PURPLE;
		 break;
	 case "Thread 3":
		 color=ThreadColor.ANSI_GREEN;
		 break;
	default:
		color=ThreadColor.ANSI_BLUE;		
	}
	 
	 synchronized(this){
	 for(i=10; i>0; i--){
		 System.out.println(color + Thread.currentThread().getName() + ": i =" +i);
	 }
	 }
	}
}

class Countdownthread extends Thread{
	private Countdown threadCountDown;
	
	public Countdownthread(Countdown countdown){
		this.threadCountDown=countdown;
	}
	
	public void run(){
		threadCountDown.countdown();
	}
}
