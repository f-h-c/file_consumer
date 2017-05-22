package br.com.ilegra.file_consumer.app;

import br.com.ilegra.file_consumer.service.DirectoryObserver;

public class Main {

  public static void main(String[] args)
  {
  	DirectoryObserver obs = new DirectoryObserver("dados/in/", "dados/out/", "ç", ",", "-", "done", 10000);

    System.out.println("\n Iniciando a busca de arquivos .dat no diretório padrão...\n");

    obs.start();
  }
}
