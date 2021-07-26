package br.com.rdstation.customer;

public class Application {
	
	static CustomerRunnable customerRunnable = new CustomerRunnable();
	
    public static void main( String[] args ) {
    	customerRunnable.runnable("CustomerSuccess.txt");
    }
}
