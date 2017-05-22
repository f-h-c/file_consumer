package br.com.ilegra.file_consumer.model;

import br.com.ilegra.file_consumer.exception.CustomerException;
import br.com.ilegra.file_consumer.iface.ICustomer;

/**
 *
 * @author fhc
 */
public class Customer implements ICustomer {

	private String cnpj;
	private String name;
	private String businessArea;

	public Customer(String cnpj, String name, String businessArea) {
		setCnpj(cnpj);
		setName(name);
		setBusinessArea(businessArea);
	}

	@Override
	public String getCnpj() {
		return cnpj;
	}

	@Override
	public void setCnpj(String cnpj) {
		if (cnpj == null || cnpj.trim().isEmpty())
			throw new CustomerException("Invalid CNPJ - " + cnpj + ".");

		this.cnpj = cnpj;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		if (name == null || name.trim().isEmpty())
			throw new CustomerException("Invalid Name - " + name + ".");

		this.name = name;
	}

	@Override
	public String getBusinessArea() {
		return businessArea;
	}

	@Override
	public void setBusinessArea(String businessArea) {
		if (businessArea == null || businessArea.trim().isEmpty())
			throw new CustomerException("Invalid Business Area Name - " + businessArea + ".");

		this.businessArea = businessArea;
	}

}
