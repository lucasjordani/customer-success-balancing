package br.com.rdstation.customer.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.rdstation.customer.Entity.Client;

public class ClientDao {
	
	Map<Integer, Client> clients = new HashMap<>();
	
	public void saveOrUpdate(Client client) {
		clients.put(client.getId(), client);
	}
	
	public Client getCustomerById(int id) {
		return clients.get(id);
	}
	
	public List<Client> getClients(){
		return new ArrayList<Client>(clients.values());
	}

}
