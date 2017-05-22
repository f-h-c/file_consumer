
package br.com.ilegra.file_consumer.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.ilegra.file_consumer.iface.IFileConsumer;
import br.com.ilegra.file_consumer.iface.ISale;
import br.com.ilegra.file_consumer.iface.ISalesman;
import br.com.ilegra.file_consumer.model.Customer;
import br.com.ilegra.file_consumer.model.Product;
import br.com.ilegra.file_consumer.model.Sale;
import br.com.ilegra.file_consumer.model.Salesman;

/**
 * Classe que será responsável por consumir o arquivo de entrada e gerar o arquivo de saída necessário
 * @author fhc
 */
public class FileConsumer extends Thread implements IFileConsumer{
  /**
   * Nome e caminho do arquivo de entrada
   */
  private String fileIn = "";

  /**
   * Nome e caminho do arquivo de saída
   */
  private String fileOut = "";

  private String defaultSeparator;
  private String productsSeparator;
  private String productSeparator;

  private List<Customer> customers = new ArrayList<Customer>();
  private List<Salesman> salesmen = new ArrayList<Salesman>();

  public FileConsumer(String fileIn, String fileOut, String defaultSeparator, String productsSeparator, String productSeparator) {
    this.fileIn = fileIn;
    this.fileOut = fileOut;
    this.defaultSeparator = defaultSeparator;
    this.productsSeparator = productsSeparator;
    this.productSeparator = productSeparator;
  }

  @Override
	public void run() {
    try {
      System.out.println("Processando o arquivo " + fileIn + "...");
      consumeFile(fileIn);
    }
    catch (FileNotFoundException ex) {
      System.out.println("Arquivo " + fileIn + " não encontrado!");
      ex.printStackTrace();
    }
    catch (IOException ex) {
      System.out.println("Problemas na leitura do arquivo " + fileIn + "!");
      ex.printStackTrace();
    }

    ISale maiorVenda = getGreaterSale();
    ISalesman piorVendedor = getWorstSalesman();

    List<String> linhas = new ArrayList<String>();

    linhas.add(Integer.toString(customers.size()));
    linhas.add(Integer.toString(salesmen.size()));

    if (maiorVenda != null)
      linhas.add(maiorVenda.getId());

    if (piorVendedor != null)
      linhas.add(piorVendedor.getName());

    try {
      generateOutputFile(fileOut, linhas);
    }
    catch (IOException ex) {
      System.out.println("Problemas na geração do arquivo " + fileOut + "!");
      ex.printStackTrace();
    }
  }

  private void generateOutputFile(String file, List<String> lines) throws IOException{
    BufferedWriter out = new BufferedWriter(new FileWriter(file));

    for (String line : lines) {
      out.write(line);
      out.newLine();
    }
    out.close();
  }

  private ISale getGreaterSale() {
    ISale result = null;

    if (salesmen != null && salesmen.size() > 0) {
      result = salesmen.get(0).getGreaterSale();

      for (Salesman salesman : salesmen)
        if (result.getTotal() < salesman.getGreaterSale().getTotal())
          result = salesman.getGreaterSale();
    }

    return result;
  }

  private ISalesman getWorstSalesman() {
  	ISalesman result = null;

    if (salesmen != null && salesmen.size() > 0) {
      result = salesmen.get(0);

      for (Salesman salesman : salesmen)
        if (result.getTotalSaleValue() > salesman.getTotalSaleValue())
          result = salesman;
    }

    return result;
  }

  private void consumeFile(String file) throws FileNotFoundException, IOException {
    BufferedReader in = new BufferedReader(new FileReader(file));

    while (in.ready()) {
      consumeLine(in.readLine());
    }

    in.close();
  }
  
  private void consumeLine(String line) {
    String[] values = line.split(defaultSeparator);

    if (values.length > 0)
      switch (Integer.parseInt(values[0])) {
        case 1:
          Salesman salesman = consumeSalesman(line);

          if (salesman != null)
            salesmen.add(salesman);

          break;
        case 2:
          Customer customer = consumeCustomer(line);

          if (customer != null)
            customers.add(customer);
          
          break;
        case 3:
          consumeSale(line);
          
          break;
        default:
          System.out.println("Não foi identificado o tipo de informação na linha: \"" + line + "\"");
      }
  }

  private Salesman consumeSalesman(String line) {
    Salesman result = null;
    String[] values = line.split(defaultSeparator);

    if (values.length == 4) {
      result = new Salesman(values[1], values[2], Double.parseDouble(values[3]));
    }
    else
      System.out.println("O número de informações da linha a seguir não está de acordo com o esperado: \"" + line + "\"");

    return result;
  }

  private Customer consumeCustomer(String line) {
    Customer result = null;
    String[] values = line.split(defaultSeparator);

    if (values.length == 4) {
      result = new Customer(values[1], values[2], values[3]);
    }
    else
      System.out.println("O número de informações da linha a seguir não está de acordo com o esperado: \"" + line + "\"");

    return result;
  }

  private ISalesman getSalesman(String name) {
  	ISalesman result = null;

    Iterator<Salesman> it = salesmen.iterator();

    while (it.hasNext() && result == null) {
    	ISalesman aux = it.next();

      if (aux.getName().equalsIgnoreCase(name))
        result = aux;
    }

    return result;
  }

  private void consumeSale(String line) {
    String[] values = line.split(defaultSeparator);

    if (values.length == 4) {
      Sale sale = new Sale(values[1]);

      List<Product> products = consumeProducts(values[2].substring(1, values[2].length()-1));

      if (products != null && products.size() > 0)
        for (Product product : products)
          sale.addProduto(product);

      ISalesman salesman = getSalesman(values[3]);

      if (salesman != null)
        salesman.addSale(sale);
    }
    else
      System.out.println("O número de informações da linha a seguir não está de acordo com o esperado: \"" + line + "\"");
  }

  private List<Product> consumeProducts(String line) {
    List<Product> products = new ArrayList<Product>();
    String[] values = line.split(productsSeparator);

    if (values != null) {
      for (String value : values) {
        Product product = consumeProduct(value);
        
        if (product != null)
          products.add(product);
      }
    }

    return products;
  }

  private Product consumeProduct(String line) {
    Product result = null;
    String[] values = line.split(productSeparator);

    if (values.length == 3) {
      result = new Product(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Float.parseFloat(values[2]));
    }
    else
      System.out.println("O número de informações do produto a seguir não está de acordo com o esperado: \"" + line + "\"");

    return result;
  }

}
