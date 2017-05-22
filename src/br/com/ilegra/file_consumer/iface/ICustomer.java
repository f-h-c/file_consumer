package br.com.ilegra.file_consumer.iface;

public interface ICustomer {

	String getCnpj();

	void setCnpj(String cnpj);

	String getName();

	void setName(String name);

	String getBusinessArea();

	void setBusinessArea(String businessArea);

}