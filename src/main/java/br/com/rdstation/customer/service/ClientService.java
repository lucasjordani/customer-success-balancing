package br.com.rdstation.customer.service;

import java.util.List;

import br.com.rdstation.customer.Dao.ClientDao;
import br.com.rdstation.customer.Entity.Client;

public class ClientService {
	
	ClientDao clientDao = new ClientDao();
	
	protected static final int MAX_ID = 999999;
	protected static final int MAX_LEVEL = 99999;
	
	public void saveOrUpdate(Client client) {
		clientDao.saveOrUpdate(client);
	}

	public void insertClientLine(String[] splitedLine) {
		int clientId = Integer.parseInt(splitedLine[1]);
		int clientLevel =  Integer.parseInt(splitedLine[2]);
		if (isValidLine(clientId, clientLevel)) {
			saveOrUpdate(new Client(clientId, clientLevel));
		}
	}

	private boolean isValidLine(int id, int level) {
		return (id > 0 && id <= MAX_ID) && (level > 0 && level <= MAX_LEVEL) && getClients().size() < MAX_ID;
	}
	
	public List<Client> getClients(){
		return clientDao.getClients();
	}

}
