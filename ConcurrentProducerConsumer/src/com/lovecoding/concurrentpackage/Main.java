package com.lovecoding.concurrentpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
	
	public static final String EOF="EOF";
	
	public static void main(String[] args) {
		List<String> buffer=new ArrayList<>();
		
		MyProducer producer=new MyProducer(buffer, ThreadColor.ANSI_BLUE);
		MyConsumer consumer1=new MyConsumer(buffer, ThreadColor.ANSI_PURPLE);
		MyConsumer consumer2=new MyConsumer(buffer, ThreadColor.ANSI_CYAN);
		
		new Thread(producer).start();
		new Thread(consumer1).start();
		new Thread(consumer2).start();
	}

}

class MyProducer implements Runnable{

	
	private List<String> buffer;
	private String color;
	
	
	
	public MyProducer(List<String> buffer, String color) {
		this.buffer = buffer;
		this.color = color;
	}

	@Override
	public void run() {
		Random random=new Random();
		String[] nums={"1","2","3","4","5","6","7"};
		
		for(String num:nums){
			try{
				System.out.println(color+" Adding..."+ num);
				synchronized(buffer){
				buffer.add(num);
				}
				Thread.sleep(random.nextInt(1000));
			}catch(InterruptedException ex){
				System.out.println("Producer is interrupted...!");
			}
		}
		System.out.println(color+ "Adding EOF and exiting process..!");
		synchronized(buffer){
		buffer.add(Main.EOF);
		}
	}
}

class MyConsumer implements Runnable{

	private List<String> buffer;
	private String color;
	
	
	public MyConsumer(List<String> list, String color) {
		this.buffer = list;
		this.color = color;
	}


	@Override
	public void run() {
		while(true){
			synchronized (buffer) {
				
			if(buffer.isEmpty()){
				continue;
			}
			if(buffer.get(0).equals(Main.EOF)){
				System.out.println(color+" Exiting..!");
				break;
			}else{
				System.out.println(color+ "Removed " + buffer.remove(0));
				
			}
		}
			}
		
	}
}
