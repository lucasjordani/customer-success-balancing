package br.com.rdstation.customer;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

import br.com.rdstation.customer.Entity.Customer;
import br.com.rdstation.customer.service.ClientService;
import br.com.rdstation.customer.service.CustomerService;


public class CustomerRunnableTest {
	
	CustomerRunnable customerRunnable = new CustomerRunnable();
	CustomerService customerService = new CustomerService();
	ClientService clientService = new ClientService();
	
	@Before
	public void setUpStreams() {
		
	}
	
    @Test
    public void testCustomerSuccessBalancingWithSuccess() {
    	insertCustomers(5);
    	insertClients(3);
		
		Customer customer = customerRunnable.customerSuccessBalancing(customerService.getOnDutyCustomers(), clientService.getClients());
        assertEquals(1, customer.getId());
        assertEquals(3, customer.getClients().size());
    }
	
    @Test
    public void testCustomerSuccessBalancingWithOffDutyCustomers() {
    	insertCustomers(5);
    	insertClients(3);
		
		String[] splitedLine = {"3", "1"};
		customerService.insertOffDutyLine(splitedLine);
		
		Customer customer = customerRunnable.customerSuccessBalancing(customerService.getOnDutyCustomers(), clientService.getClients());
        assertEquals(2, customer.getId());
        assertEquals(3, customer.getClients().size());
    }
	
    @Test
    public void testCustomerSuccessBalancingWithClientWithoutCustomer() {
    	insertCustomers(5);
    	insertClients(3);
		
		String[] splitedLine = {"2", "4", "500"};
		clientService.insertClientLine(splitedLine);
		
		Customer customer = customerRunnable.customerSuccessBalancing(customerService.getOnDutyCustomers(), clientService.getClients());
        assertEquals(1, customer.getId());
        assertEquals(3, customer.getClients().size());
    }

    @Test
    public void testCustomerSuccessBalancing() {
    	final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    	System.setOut(new PrintStream(outputStreamCaptor));
    	
    	customerRunnable.runnable("CustomerSuccess.txt");
        assertEquals("1", outputStreamCaptor.toString().trim());
    }
    
    public void insertCustomers(int iterations) {
    	int id = 1;
		int level = 5;
		while (id <= iterations) {
			String[] splitedLine = {"1", String.valueOf(id), String.valueOf(level * 15)};
			customerService.insertCustomerLine(splitedLine);
			id++;
			level++;
		}
    }
    
    public void insertClients(int iterations) {
		int id = 1;
		int level = 4;
		while (id <= iterations) {
			String[] splitedLine = {"2", String.valueOf(id), String.valueOf(level * 10)};
			clientService.insertClientLine(splitedLine);
			id++;
			level++;
		}
    }
        
}
