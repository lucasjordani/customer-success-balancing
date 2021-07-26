package br.com.rdstation.customer.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ClientServiceTest {
	
	ClientService clientService = new ClientService();

	
	@Test
	public void testInsertLineWithCorrectValues() {
		String[] splitedLine = {"2", "2", "10"};
		clientService.insertClientLine(splitedLine);
		assertEquals(1, clientService.getClients().size());
	}

	@Test
	public void testInsertLineWithIdLessThanZero() {
		String[] splitedLine = {"2", "-2", "30"};
		clientService.insertClientLine(splitedLine);
		assertTrue(clientService.getClients().isEmpty());
	}
	
	@Test
	public void testInsertLineWithLevelLessThanZero() {
		String[] splitedLine = {"2", "2", "-10"};
		clientService.insertClientLine(splitedLine);
		assertTrue(clientService.getClients().isEmpty());
	}
	
	@Test
	public void testInsertLineWithIdEqualToZero() {
		String[] splitedLine = {"2", "0", "30"};
		clientService.insertClientLine(splitedLine);
		assertTrue(clientService.getClients().isEmpty());
	}
	
	@Test
	public void testInsertLineWithLevelEqualToZero() {
		String[] splitedLine = {"2", "2", "0"};
		clientService.insertClientLine(splitedLine);
		assertTrue(clientService.getClients().isEmpty());
	}
	
	@Test
	public void testInsertLineWithIdGreaterThanMax() {
		String[] splitedLine = {"2", String.valueOf(clientService.MAX_ID + 1), "-10"};
		clientService.insertClientLine(splitedLine);
		assertTrue(clientService.getClients().isEmpty());
	}
	
	@Test
	public void testInsertLineWithLevelGreaterThanMax() {
		String[] splitedLine = {"2", "2", String.valueOf(clientService.MAX_LEVEL + 1)};
		clientService.insertClientLine(splitedLine);
		assertTrue(clientService.getClients().isEmpty());
	}
	
}
