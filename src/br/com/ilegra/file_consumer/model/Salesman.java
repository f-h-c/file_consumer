
package br.com.ilegra.file_consumer.model;

import java.util.ArrayList;
import java.util.List;

import br.com.ilegra.file_consumer.exception.SalesmanException;
import br.com.ilegra.file_consumer.iface.ISale;
import br.com.ilegra.file_consumer.iface.ISalesman;

/**
 *
 * @author fhc
 */
public class Salesman implements ISalesman {

	private String name;
	private String cpf;
	private double salary;
	private List<Sale> sales = new ArrayList<Sale>();

	public Salesman(String cpf, String name, double salary) {
		setName(name);
		setCpf(cpf);
		setSalary(salary);
	}

	@Override
	public void addSale(Sale sale) {
		if (sale != null) {
			this.sales.add(sale);
		}
	}

	@Override
	public ISale getGreaterSale() {
		ISale result = null;

		if (sales != null && sales.size() > 0) {
			float greater = sales.get(0).getTotal();
			result = sales.get(0);

			for (int i = 1; i < sales.size(); i++)
				if (greater < sales.get(i).getTotal()) {
					greater = sales.get(i).getTotal();
					result = sales.get(i);
				}
		}

		return result;
	}

	@Override
	public double getTotalSaleValue() {
		double total = 0;

		if (sales != null && sales.size() > 0) {
			for (int i = 0; i < sales.size(); i++)
				total += sales.get(i).getTotal();
		}

		return total;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		if (name == null || name.trim().isEmpty())
			throw new SalesmanException("Invalid Name - " + name + ".");

		this.name = name;
	}

	@Override
	public String getCpf() {
		return cpf;
	}

	@Override
	public void setCpf(String cpf) {
		if (cpf == null || cpf.trim().isEmpty())
			throw new SalesmanException("Invalid CPF - " + cpf + ".");

		this.cpf = cpf;
	}

	@Override
	public double getSalary() {
		return salary;
	}

	@Override
	public void setSalary(double salary) {
		if (salary < 0)
			throw new SalesmanException("Invalid Salary - " + salary + ".");

		this.salary = salary;
	}

}
