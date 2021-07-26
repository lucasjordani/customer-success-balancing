package br.com.rdstation.customer.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class CustomerServiceTest {
	
	CustomerService customerService = new CustomerService();
	
	@Test
	public void testInsertLineWithCorrectValues() {
		String[] splitedLine = {"1", "2", "10"};
		customerService.insertCustomerLine(splitedLine);
		assertEquals(1, customerService.getAllCustomers().size());
	}

	@Test
	public void testInsertLineWithIdLessThanZero() {
		String[] splitedLine = {"1", "-2", "30"};
		customerService.insertCustomerLine(splitedLine);
		assertTrue(customerService.getAllCustomers().isEmpty());
	}
	
	@Test
	public void testInsertLineWithLevelLessThanZero() {
		String[] splitedLine = {"1", "2", "-10"};
		customerService.insertCustomerLine(splitedLine);
		assertTrue(customerService.getAllCustomers().isEmpty());
	}
	
	@Test
	public void testInsertLineWithIdEqualToZero() {
		String[] splitedLine = {"1", "0", "30"};
		customerService.insertCustomerLine(splitedLine);
		assertTrue(customerService.getAllCustomers().isEmpty());
	}
	
	@Test
	public void testInsertLineWithLevelEqualToZero() {
		String[] splitedLine = {"1", "2", "0"};
		customerService.insertCustomerLine(splitedLine);
		assertTrue(customerService.getAllCustomers().isEmpty());
	}
	
	@Test
	public void testInsertLineWithIdGreaterThanMax() {
		String[] splitedLine = {"1", String.valueOf(CustomerService.MAX_ID + 1), "-10"};
		customerService.insertCustomerLine(splitedLine);
		assertTrue(customerService.getAllCustomers().isEmpty());
	}
	
	@Test
	public void testInsertLineWithLevelGreaterThanMax() {
		String[] splitedLine = {"1", "2", String.valueOf(CustomerService.MAX_LEVEL + 1)};
		customerService.insertCustomerLine(splitedLine);
		assertTrue(customerService.getAllCustomers().isEmpty());
	}
	
	@Test
	public void testInsertLineWithCustomersSizeReachedMax() {
		insertCurtomers(customerService.MAX_ID + 10);
		assertEquals(customerService.MAX_ID, customerService.getAllCustomers().size());
	}
	
	@Test
	public void testInsertOffDutyLineWithNonexistentAccount() {
		String[] splitedLine = {"3", "1"};
		customerService.insertOffDutyLine(splitedLine);
		assertTrue(customerService.getAllCustomers().isEmpty());
	}
	
	@Test
	public void testInsertOffDutyLineWithValidAccount() {
		insertCurtomers(5);
		String[] splitedOffDuty= {"3", "1"};
		customerService.insertOffDutyLine(splitedOffDuty);
		assertEquals(5, customerService.getAllCustomers().size());
		assertEquals(1, customerService.getOffDutyCustomers().size());
	}
	
	@Test
	public void testInsertOffDutyLineWithMoreThanOffDutyCustomersAllowed() {
		insertCurtomers(11);
		insertOffDuty(20);

		assertEquals(5, customerService.getOffDutyCustomers().size());
		assertEquals(6, customerService.getOnDutyCustomers().size());
	}
	
	public void insertCurtomers(int iterations) {
		int id = 1;
		int level = 5;
		while (id <= iterations) {
			String[] splitedLine = {"1", String.valueOf(id), String.valueOf(level)};
			customerService.insertCustomerLine(splitedLine);
			id++;
			level++;
		}
	}
	
	public void insertOffDuty(int iterations) {
		int id = 1;
		while (id <= iterations) {
			String[] splitedLine = {"3", String.valueOf(id)};
			customerService.insertOffDutyLine(splitedLine);
			id++;
		}
	}

}
