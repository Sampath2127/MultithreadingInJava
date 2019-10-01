package com.love2code.deadlock2;

public class Main {

	public static void main(String[] args) {
		
		final PolitePerson sam=new PolitePerson("Sam");
		final PolitePerson bharathi=new PolitePerson("Bharathi");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				sam.sayHello(bharathi);
				
			}
		}).start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				bharathi.sayHello(sam);
				
			}
		}).start();
		
	}
	
	static class PolitePerson{
		private final String name;
		
		public PolitePerson(String name){
			this.name=name;
		}
		
		public String getName(){
			return name;
		}
		
		public synchronized void sayHello(PolitePerson person){
			System.out.format("%s : %s" + " has said hello to me!%n", this.name, person.getName());
			person.sayHelloBack(this);
		}
		
		public synchronized void sayHelloBack(PolitePerson person){
			System.out.format("%s : %s" + " has said hello to me!%n", this.name, person.getName());
		}
	}
}
