package br.com.rdstation.customer.service;

import java.util.List;

import br.com.rdstation.customer.Dao.CustomerDao;
import br.com.rdstation.customer.Entity.Customer;

public class CustomerService {
	
	CustomerDao customerSuccessDao = new CustomerDao();
	
	protected static final int MAX_ID = 999;
	protected static final int MAX_LEVEL = 9999;
	
	public void saveOrUpdate(Customer customer) {
		customerSuccessDao.saveOrUpdate(customer);
	}
	
	public Customer getCustomerById(int id) {
		return customerSuccessDao.getCustomerById(id);
	}

	public void insertCustomerLine(String[] splitedLine) {
		int customerId = Integer.parseInt(splitedLine[1]);
		int customerLevel =  Integer.parseInt(splitedLine[2]);
		if (isValidLine(customerId, customerLevel)) {
			saveOrUpdate(new Customer(customerId, customerLevel));
		}
	}
	public void insertOffDutyLine(String[] splitedLine) {
		Customer customer = getCustomerById(Integer.parseInt(splitedLine[1]));
		// Check if the numbers of off duty customers is half of the total (if is not true than the customer are added)
		if (customer != null && getOffDutyCustomers().size() < Math.floor(getAllCustomers().size()/2)) {
			customer.setOffDuty(true);
			saveOrUpdate(customer);
		}
	}

	private boolean isValidLine(int id, int level) {
		return (id > 0 && id <= MAX_ID) && (level > 0 && level <= MAX_LEVEL) && getAllCustomers().size() < MAX_ID;
	}

	public List<Customer> getAllCustomers(){
		return customerSuccessDao.getAllCustomers();
	}

	public List<Customer> getOnDutyCustomers(){
		return customerSuccessDao.getOnDutyCustomers();
	}
	
	public List<Customer> getOffDutyCustomers(){
		return customerSuccessDao.getOffDutyCustomers();
	}
}
