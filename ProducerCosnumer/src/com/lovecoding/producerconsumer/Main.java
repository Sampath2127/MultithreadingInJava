package com.lovecoding.producerconsumer;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		Message message=new Message();
		
		(new Thread(new Writer(message))).start();
		(new Thread(new Reader(message))).start();
	}

}

class Message{
	
	private String message;
	private boolean empty=true;
	
	public synchronized String read(){
		while(empty){
			try{
				wait();
			}catch(InterruptedException ex){
				System.out.println("Interrupted wait");
			}
		}
		empty=true;
		notifyAll();
		return message;
	}
	
	public synchronized void write(String message){
		while(!empty){
			try{
				wait();
			}catch(InterruptedException ex){
				System.out.println("Interrupted wait");
			}
		}
		empty=false;
		this.message=message;
		notifyAll();

	}
}

class Writer implements Runnable{

	private Message message;
	
	public Writer(Message message){
		this.message=message;
	}
	@Override
	public void run() {
		String messages[]={
				"hello message 1",
				"hello message 2",
				"hello message 3",
				"hello message 4"
		};
		Random random=new Random();
		
		for(int i=0;i<messages.length; i++){
			message.write(messages[i]);
			try{
				Thread.sleep(random.nextInt(2000));
			}catch(InterruptedException ex){
				System.out.println("Thread is INterrupted");
			}
		}
		message.write("finished..!");
				
	}	
}

class Reader implements Runnable{
	
	private Message message;
	
	public Reader(Message message){
		this.message=message;
	}
	@Override
	public void run() {
		Random random=new Random();
		for(String latestMessage=message.read(); !latestMessage.equals("finished");
				latestMessage = message.read()){
			System.out.println(latestMessage);
			try{
				Thread.sleep(random.nextInt(2000));
			}catch(InterruptedException ex){
				System.out.println("Thread Interrupted..!");
			}
		}
	}
}