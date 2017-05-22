package br.com.ilegra.file_consumer.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.ilegra.file_consumer.exception.SalesmanException;

public class SalesmanTest {

	@Test(expected = SalesmanException.class)
	public void constructorErrorValidation1() {
		new Salesman("", "Diego", 50000);
	}

	@Test(expected = SalesmanException.class)
	public void constructorErrorValidation2() {
		new Salesman("    ", "Diego", 50000);
	}

	@Test(expected = SalesmanException.class)
	public void constructorErrorValidation3() {
		new Salesman(null, "Diego", 50000);
	}

	@Test(expected = SalesmanException.class)
	public void constructorErrorValidation4() {
		new Salesman("1234567891234", "", 50000);
	}

	@Test(expected = SalesmanException.class)
	public void constructorErrorValidation5() {
		new Salesman("1234567891234", "    ", 50000);
	}

	@Test(expected = SalesmanException.class)
	public void constructorErrorValidation6() {
		new Salesman("1234567891234", null, 50000);
	}

	@Test(expected = SalesmanException.class)
	public void constructorErrorValidation7() {
		new Salesman("1234567891234", "Diego", -50000);
	}

	@Test
	public void addSaleAndGetTotalSaleValueValidation() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		Sale s = new Sale("1");
		
		assertEquals(0, sm.getTotalSaleValue(), 0.00001);

		Product p = new Product(0, 13, 2);
		s.addProduto(p);
		p = new Product(1, 5, 10);
		s.addProduto(p);
		
		sm.addSale(s);
		
		assertEquals(76, sm.getTotalSaleValue(), 0.00001);
		
		sm.addSale(null);
		
		assertEquals(76, sm.getTotalSaleValue(), 0.00001);
		
		s = new Sale("2");
		
		p = new Product(2, 15, 10);
		s.addProduto(p);
		p = new Product(3, 100, 2);
		s.addProduto(p);
		
		sm.addSale(s);
		
		assertEquals(76+350, sm.getTotalSaleValue(), 0.00001);
	}

	@Test
	public void getGreaterSaleValidation() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		Sale s1 = new Sale("1");
		
		assertEquals(null, sm.getGreaterSale());

		Product p = new Product(0, 13, 2);
		s1.addProduto(p);
		p = new Product(1, 5, 10);
		s1.addProduto(p);
		
		sm.addSale(s1);
		
		assertEquals(s1, sm.getGreaterSale());
		
		Sale s2 = new Sale("2");
		
		p = new Product(2, 15, 10);
		s2.addProduto(p);
		p = new Product(3, 100, 2);
		s2.addProduto(p);
		
		sm.addSale(s2);
		
		assertEquals(s2, sm.getGreaterSale());
		
		Sale s3 = new Sale("3");
		
		p = new Product(4, 5, 3);
		s3.addProduto(p);
		p = new Product(5, 150, 1);
		s3.addProduto(p);
		
		sm.addSale(s3);
		
		assertEquals(s2, sm.getGreaterSale());
	}

	@Test
	public void getNameValidation() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		assertEquals("Diego", sm.getName());
	}

	@Test
	public void setNameValidation() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		assertEquals("Diego", sm.getName());
		
		sm.setName("Maria");
		assertEquals("Maria", sm.getName());
	}

	@Test(expected = SalesmanException.class)
	public void setNameErrorValidation1() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		assertEquals("Diego", sm.getName());
		
		sm.setName("");
	}

	@Test(expected = SalesmanException.class)
	public void setNameErrorValidation2() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		assertEquals("Diego", sm.getName());
		
		sm.setName("       ");
	}

	@Test(expected = SalesmanException.class)
	public void setNameErrorValidation3() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		assertEquals("Diego", sm.getName());
		
		sm.setName(null);
	}

	@Test
	public void getCpfValidation() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		assertEquals("1234567891234", sm.getCpf());
	}

	@Test
	public void setCpfValidation() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		assertEquals("1234567891234", sm.getCpf());
		
		sm.setCpf("4321987654321");
		assertEquals("4321987654321", sm.getCpf());
	}

	@Test(expected = SalesmanException.class)
	public void setCpfErrorValidation1() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		assertEquals("1234567891234", sm.getCpf());
		
		sm.setCpf("");
	}

	@Test(expected = SalesmanException.class)
	public void setCpfErrorValidation2() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		assertEquals("1234567891234", sm.getCpf());
		
		sm.setCpf("       ");
	}

	@Test(expected = SalesmanException.class)
	public void setCpfErrorValidation3() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		assertEquals("1234567891234", sm.getCpf());
		
		sm.setCpf(null);
	}

	@Test
	public void getSalaryValidation() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		assertEquals(50000, sm.getSalary(), 0.0000001);
	}

	@Test
	public void setSalaryValidation() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		assertEquals(50000, sm.getSalary(), 0.0000001);
		
		sm.setSalary(100000);
		assertEquals(100000, sm.getSalary(), 0.0000001);
	}

	@Test(expected = SalesmanException.class)
	public void setSalaryErrorValidation() {
		Salesman sm = new Salesman("1234567891234", "Diego", 50000);
		assertEquals(50000, sm.getSalary(), 0.0000001);
		
		sm.setSalary(-100000);
	}

}
