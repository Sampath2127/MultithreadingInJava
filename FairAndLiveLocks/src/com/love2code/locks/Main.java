package com.love2code.locks;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
 private static ReentrantLock lock=new ReentrantLock(true);
	public static void main(String[] args) {
		Thread t1=new Thread(new Worker(ThreadColor.ANSI_BLUE), "Priority 8");
		Thread t2=new Thread(new Worker(ThreadColor.ANSI_GREEN), "Priority 2");
		Thread t3=new Thread(new Worker(ThreadColor.ANSI_YELLOW), "Priority 1");
		Thread t4=new Thread(new Worker(ThreadColor.ANSI_CYAN), "Priority 10");
		
		t1.setPriority(8);
		t2.setPriority(2);
		t3.setPriority(1);
		t4.setPriority(10);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
	
	private static class Worker implements Runnable{
		
		private int runCount =1;
		private String threadColor;
		
		
		public Worker(String threadColor){
			this.threadColor=threadColor;
		}
		
		@Override
		public void run() {
			
			for(int i=0; i<50; i++){
				lock.lock();
				try{
				System.out.format(threadColor + "%s: runcount = %d\n", Thread.currentThread().getName(), runCount++);
			}
			finally{
				lock.unlock();
			}
			}
		}
	}
}