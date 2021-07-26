package br.com.rdstation.customer.Dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.rdstation.customer.Entity.Customer;

public class CustomerDao {
	
	Map<Integer, Customer> customers = new HashMap<>();

	public void saveOrUpdate(Customer customer) {
		List<Customer> customersList = new ArrayList<>(customers.values());
		for (int i = 0; i < customersList.size(); i++) {
			// Check if already has a customer with the same level (if is true the customer is ignored)
			if (customersList.get(i).getLevel() == customer.getLevel()) {
				return;
			}
		}
		customers.put(customer.getId(), customer);
	}
	
	public Customer getCustomerById(int id) {
		return customers.get(id);
	}
	
	public List<Customer> getAllCustomers(){
		return new ArrayList<Customer>(customers.values());
	}
	
	public List<Customer> getOffDutyCustomers(){
		List<Customer> offDutyCustomers = new ArrayList<>();
		List<Customer> customersList = new ArrayList<>(customers.values());
		for (int i = 0; i < customersList.size(); i++) {
			if (customersList.get(i).isOffDuty()) {
				offDutyCustomers.add(customersList.get(i));
			}
		}
		return offDutyCustomers;			
	}

	public List<Customer> getOnDutyCustomers() {
		List<Customer> onDutyCustomers = new ArrayList<>();
		List<Customer> customersList = new ArrayList<>(customers.values());
		for (int i = 0; i < customersList.size(); i++) {
			if (customersList.get(i).isOnDuty()) {
				onDutyCustomers.add(customersList.get(i));
			}
		}
		return onDutyCustomers;
	}

}
