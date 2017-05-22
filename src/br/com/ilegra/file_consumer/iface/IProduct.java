package br.com.ilegra.file_consumer.iface;

public interface IProduct {

	double getTotal();

	int getAmount();

	void setAmount(int amount);

	double getPrice();

	void setPrice(double price);

	int getId();

	void setId(int id);

}