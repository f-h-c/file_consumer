package br.com.ilegra.file_consumer.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.ilegra.file_consumer.exception.CustomerException;
import br.com.ilegra.file_consumer.iface.ICustomer;

public class CustomerTest {
	
	@Test(expected = CustomerException.class)
	public void constructorErrorValidation1(){
		new Customer(null, "Jose da Silva", "Rural");
	}
	
	@Test(expected = CustomerException.class)
	public void constructorErrorValidation2(){
		new Customer("", "Jose da Silva", "Rural");
	}
	
	@Test(expected = CustomerException.class)
	public void constructorErrorValidation3(){
		new Customer("     ", "Jose da Silva", "Rural");
	}
	
	@Test(expected = CustomerException.class)
	public void constructorErrorValidation4(){
		new Customer("2345675434544345", null, "Rural");
	}
	
	@Test(expected = CustomerException.class)
	public void constructorErrorValidation5(){
		new Customer("2345675434544345", "", "Rural");
	}
	
	@Test(expected = CustomerException.class)
	public void constructorErrorValidation6(){
		new Customer("2345675434544345", "     ", "Rural");
	}
	
	@Test 
	public void getCnpjValidation() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		
		assertEquals("2345675434544345", cli.getCnpj());
	}
	
	@Test 
	public void getNameValidation() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		
		assertEquals("Jose da Silva", cli.getName());
	}
	
	@Test 
	public void getBusinessAreaValidation() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		
		assertEquals("Rural", cli.getBusinessArea());
	}
	
	@Test 
	public void setCnpjValidation() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		cli.setCnpj("1235675434544789");
		
		assertEquals("1235675434544789", cli.getCnpj());
	}
	
	@Test(expected = CustomerException.class)
	public void setCnpjErrorValidation1() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		cli.setCnpj("");
	}
	
	@Test(expected = CustomerException.class) 
	public void setCnpjErrorValidation2() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		cli.setCnpj("   ");
	}
	
	@Test(expected = CustomerException.class) 
	public void setCnpjErrorValidation3() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		cli.setCnpj(null);
	}
	
	@Test 
	public void setNameValidation() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		cli.setName("Maria da Silva");
		
		assertEquals("Maria da Silva", cli.getName());
	}
	
	@Test(expected = CustomerException.class)
	public void setNameErrorValidation1() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		cli.setName("");
	}
	
	@Test(expected = CustomerException.class) 
	public void setNameErrorValidation2() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		cli.setName("   ");
	}
	
	@Test(expected = CustomerException.class) 
	public void setNameErrorValidation3() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		cli.setName(null);
	}
	
	@Test 
	public void setBusinessAreaValidation() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		cli.setBusinessArea("Comércio");
		
		assertEquals("Comércio", cli.getBusinessArea());
	}
	
	@Test(expected = CustomerException.class)
	public void setBusinessAreaErrorValidation1() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		cli.setBusinessArea("");
	}
	
	@Test(expected = CustomerException.class) 
	public void setBusinessAreaErrorValidation2() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		cli.setBusinessArea("   ");
	}
	
	@Test(expected = CustomerException.class) 
	public void setBusinessAreaErrorValidation3() {
		ICustomer cli = new Customer("2345675434544345", "Jose da Silva", "Rural");
		cli.setBusinessArea(null);
	}

}
