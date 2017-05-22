package br.com.ilegra.file_consumer.iface;

import br.com.ilegra.file_consumer.model.Product;

public interface ISale {

	void addProduto(Product product);

	float getTotal();

	String getId();

	void setId(String id);

}