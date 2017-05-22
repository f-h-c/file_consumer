package br.com.ilegra.file_consumer.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.ilegra.file_consumer.exception.ProductException;

public class ProductTest {

	@Test(expected = ProductException.class)
	public void constructorErrorValidation1() {
		new Product(-1, 1, 1.0);
	}
	
	@Test(expected = ProductException.class)
	public void constructorErrorValidation2() {
		new Product(1, -1, 1.0);
	}
	
	@Test(expected = ProductException.class)
	public void constructorErrorValidation3() {
		new Product(1, 0, -1.0);
	}
	
	@Test
	public void getIdValidation() {
		Product p = new Product(0, 1, 2);
		
		assertEquals(0, p.getId());
	}
	
	@Test
	public void getAmountValidation() {
		Product p = new Product(0, 1, 2);
		
		assertEquals(1, p.getAmount());
	}
	
	@Test
	public void getPriceValidation() {
		Product p = new Product(0, 1, 2);
		
		assertEquals(2, p.getPrice(), 0.000001);
	}
	
	@Test
	public void getTotalValidation() {
		Product p = new Product(0, 13, 2);
		
		assertEquals(2*13, p.getTotal(), 0.000001);
	}
	
	@Test
	public void setIdValidation() {
		Product p = new Product(0, 13, 2);
		p.setId(5);
		
		assertEquals(5, p.getId());
	}
	
	@Test(expected = ProductException.class)
	public void setIdErrorValidation() {
		Product p = new Product(0, 13, 2);
		p.setId(-23);
	}
	
	@Test
	public void setAmountValidation() {
		Product p = new Product(0, 13, 2);
		p.setAmount(5);
		
		assertEquals(5, p.getAmount());
		assertEquals(5*2, p.getTotal(), 0.000001);
	}
	
	@Test(expected = ProductException.class)
	public void setAmountdErrorValidation() {
		Product p = new Product(0, 13, 2);
		p.setAmount(-23);
	}
	
	@Test
	public void setPriceValidation() {
		Product p = new Product(0, 13, 2);
		p.setPrice(5);
		
		assertEquals(5, p.getPrice(), 0.000001);
		assertEquals(13*5, p.getTotal(), 0.000001);
	}
	
	@Test(expected = ProductException.class)
	public void setPriceErrorValidation() {
		Product p = new Product(0, 13, 2);
		p.setPrice(-23);
	}
}
