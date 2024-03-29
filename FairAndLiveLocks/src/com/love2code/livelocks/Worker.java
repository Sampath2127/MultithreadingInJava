package com.love2code.livelocks;

public class Worker {

	private String name;
	private boolean active;
	
	public Worker(String name, boolean active) {
		this.name = name;
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public synchronized void work(SharedResource sharedResource, Worker anotherWorker){
		while(active){
			if(sharedResource.getOwner() != this){
				try{
					wait(10);
				}catch(InterruptedException e){
					
				}
				continue;
			}
			
		if(anotherWorker.isActive()){
			System.out.println(getName()+ " : give resource to the worker "+ anotherWorker.getName());
			sharedResource.setOwner(anotherWorker);
			continue;
		}
		System.out.println(getName()+": working on the common resource");
		active = false;
		sharedResource.setOwner(anotherWorker);
	}
	}
	
}


/*
volatile keyword are updated in main memory by threads as soon as values of varibale thread holds is modified

*/