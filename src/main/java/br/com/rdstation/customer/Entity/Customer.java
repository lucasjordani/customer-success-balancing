package br.com.rdstation.customer.Entity;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	private int id;
	private List<Client> clients = new ArrayList<Client>();
	private int level;
	private boolean offDuty;

	
	public Customer() {}
	
	public Customer(int id, int level) {
		this.id = id;
		this.level = level;
		this.offDuty = false;
	}
	
	public int getId() {
		return id;
	}
	
	public List<Client> getClients() {
		return clients;
	}
	
	public int getLevel() {
		return level;
	}

	public boolean isOffDuty() {
		return offDuty;
	}
	
	public boolean isOnDuty() {
		return !offDuty;
	}

	public void setOffDuty(boolean offDuty) {
		this.offDuty = offDuty;
	}
	
	@Override
	public String toString() {
		String clientsString = clients.isEmpty() ? null : clients.toString();
		return "Customer: id[" + this.id + "] level[" + this.level + "] offDuty[" + this.offDuty + "] clients[" + clientsString + "]";
	}
	
	
}
