package com.love2code.livelocks;

public class SharedResource {
  private Worker owner;

  
	public SharedResource(Worker owner) {
		this.owner = owner;
	}

	public Worker getOwner() {
		return owner;
	}

	public void setOwner(Worker owner) {
		this.owner = owner;
	}

}
