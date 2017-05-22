package br.com.ilegra.file_consumer.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.ilegra.file_consumer.exception.SaleException;

public class SaleTest {

	@Test(expected = SaleException.class)
	public void constructorErrorValidation1() {
		new Sale("");
	}

	@Test(expected = SaleException.class)
	public void constructorErrorValidation2() {
		new Sale("     ");
	}

	@Test(expected = SaleException.class)
	public void constructorErrorValidation3() {
		new Sale(null);
	}
	
	@Test
	public void getIdValidation() {
		Sale s = new Sale("1");
		
		assertEquals("1", s.getId());
	}
	
	@Test(expected = SaleException.class)
	public void setIdErrorValidation1() {
		Sale s = new Sale("1");
		s.setId("");
	}
	
	@Test(expected = SaleException.class)
	public void setIdErrorValidation2() {
		Sale s = new Sale("1");
		s.setId("       ");
	}
	
	@Test(expected = SaleException.class)
	public void setIdErrorValidation3() {
		Sale s = new Sale("1");
		s.setId(null);
	}
	
	@Test
	public void getTotalValidation() {
		Sale s = new Sale("1");
		
		Product p = new Product(0, 13, 2);
		s.addProduto(p);
		
		assertEquals(26, s.getTotal(), 0.000001);
		
		p = new Product(1, 5, 10);
		s.addProduto(p);
		
		assertEquals(76, s.getTotal(), 0.000001);
	}
}
