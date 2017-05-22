package br.com.ilegra.file_consumer.iface;

import br.com.ilegra.file_consumer.model.Sale;

public interface ISalesman {

	void addSale(Sale sale);

	ISale getGreaterSale();

	double getTotalSaleValue();

	String getName();

	void setName(String name);

	String getCpf();

	void setCpf(String cpf);

	double getSalary();

	void setSalary(double salary);

}