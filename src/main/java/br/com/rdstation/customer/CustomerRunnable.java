package br.com.rdstation.customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import br.com.rdstation.customer.Entity.Client;
import br.com.rdstation.customer.Entity.Customer;
import br.com.rdstation.customer.service.ClientService;
import br.com.rdstation.customer.service.CustomerService;

public class CustomerRunnable {

	CustomerService customerService = new CustomerService();
	ClientService clientService = new ClientService();
	
	public void runnable(String filePath) {
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filePath);
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] splitedLine = line.split(";");
		    	switch (splitedLine[0]) {
				case "1":
					customerService.insertCustomerLine(splitedLine);
					break;
				case "2":
					clientService.insertClientLine(splitedLine);
					break;
				case "3":
					customerService.insertOffDutyLine(splitedLine);
					break;
				default:
					break;
				}
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
		Customer customer = customerSuccessBalancing(customerService.getOnDutyCustomers(), clientService.getClients());
		System.out.println(customer.getId());
	}
	
	protected Customer customerSuccessBalancing(List<Customer> customers, List<Client> clients) {
		
		Customer customerWithMaxClients = new Customer();
		// Iterating over the clients first
		for (int i = 0; i < clients.size(); i++) {
			Client client = clients.get(i);
			
			Customer previousCs = null;
			int previousDiff = Integer.MAX_VALUE;
			
			// Iterating over the customer later (1 customer for m clients)
			for (int j = 0; j < customers.size(); j++) {
				
				Customer currentCs = customers.get(j);
				int currentlDiff = currentCs.getLevel() - client.getLevel();
				
				// Discovering if the client level is closer to the customer level
				// The difference must be greater than or equal to zero
				if (currentlDiff >= 0 && currentlDiff < previousDiff) {
					previousDiff = currentlDiff;
					previousCs = currentCs;
				}
				
				// Discovering the customer with maximum number of clients
				if (customerWithMaxClients.getClients().size() <= currentCs.getClients().size()) {
					customerWithMaxClients = currentCs;
				}
			}
			
			// Saving the client on the correct customer
			if(previousCs != null) {
				previousCs.getClients().add(client);
				customerService.saveOrUpdate(previousCs);
			}
		}
		return customerWithMaxClients;
	}
}
