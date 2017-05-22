package br.com.ilegra.file_consumer.model;

import java.util.ArrayList;
import java.util.List;

import br.com.ilegra.file_consumer.exception.SaleException;
import br.com.ilegra.file_consumer.iface.ISale;

/**
 *
 * @author fhc
 */
public class Sale implements ISale {

	private String id;
	private List<Product> products = new ArrayList<>();

	public Sale(String id) {
		setId(id);
	}

	@Override
	public void addProduto(Product product) {
		if (products != null) {
			products.add(product);
		}
	}

	@Override
	public float getTotal() {
		float total = 0;

		if (products != null && products.size() > 0)
			for (int i = 0; i < products.size(); i++)
				total += products.get(i).getTotal();

		return total;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		if (id == null || id.trim().isEmpty())
			throw new SaleException("Invalid ID - " + id + ".");

		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sale other = (Sale) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		}
		else
			if (!id.equals(other.id))
				return false;
		if (products == null) {
			if (other.products != null)
				return false;
		}
		else
			if (!products.equals(other.products))
				return false;
		return true;
	}

}
