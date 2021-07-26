package br.com.rdstation.customer.Entity;

public class Client {

	private int id;
	private int level;
	
	
	public Client(int id, int level) {
		this.id = id;
		this.level = level;
	}
	
	public int getId() {
		return id;
	}
	
	public int getLevel() {
		return level;
	}
	
	@Override
	public String toString() {
		return "Client: id[" + this.id + "] level[" + this.level + "]";
	}
}
