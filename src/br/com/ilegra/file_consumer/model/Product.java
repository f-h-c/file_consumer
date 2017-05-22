package br.com.ilegra.file_consumer.model;

import br.com.ilegra.file_consumer.exception.ProductException;
import br.com.ilegra.file_consumer.iface.IProduct;

/**
 *
 * @author fhc
 */
public class Product implements IProduct {

	private int id;
	private int amount;
	private double price;

	public Product(int id, int amount, double price) {
		setId(id);
		setAmount(amount);
		setPrice(price);
	}

	@Override
	public double getTotal() {
		return amount * price;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		if (id < 0)
			throw new ProductException("Invalid ID - " + id + ".");
		
		this.id = id;
	}

	@Override
	public int getAmount() {
		return amount;
	}

	@Override
	public void setAmount(int amount) {
		if (amount < 0)
			throw new ProductException("Invalid amount - " + amount + ".");
		
		this.amount = amount;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public void setPrice(double price) {
		if (price < 0)
			throw new ProductException("Invalid price - " + price + ".");
		
		this.price = price;
	}

}
