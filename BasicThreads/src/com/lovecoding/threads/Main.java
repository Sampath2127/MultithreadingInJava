package com.lovecoding.threads;

public class Main {

	public static void main(String[] args) {
		System.out.println("From main class");
		
		Thread appThread=new AppThread();
		appThread.setName("==Another thread==");
		appThread.start();
		
		
		//appThread.run();//runs form Main class not from created one
		new Thread(){
			public void run(){
				System.out.println("Running from annonymous thread..!");
			}
		}.start();
		Thread runnable=new Thread(new MyRunnable(){

			@Override
			public void run() {
				
				System.out.println("Annonymous implementation for MyRunnable run menthod..!");
				
				try {
					appThread.join(2000);
					System.out.println("Another thread terminated, or timedout, I'm running again..!");
				} catch (InterruptedException e) {
					System.out.println("I am interrupted while waiting..!");
				}
			}
			});
		runnable.start();
		//appThread.interrupt();
		
		System.out.println("From Main Class Again..!");
	}
}
