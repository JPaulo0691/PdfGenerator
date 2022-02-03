
package br.com.java.pdfsystem.maven;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.java.pdfsystem.maven.relatorio.Relatorio;
import br.com.java.pdfsystem.maven.relatorio.RelatorioPdfSimples;
import br.com.java.pdfsystem.maven.vendas.Produto;
import br.com.java.pdfsystem.maven.vendas.Venda;

public class Application {
  public static void main(String[] args) {
	  
	  Scanner input = new Scanner(System.in);
	  List<Produto> produtos = new ArrayList<>();
	  Venda venda = new Venda("João Paulo Apolinário Costa", produtos);
	  
	  
	  for(int i = 0; i < 3; i++) {
		  System.out.print("Digite o nome do produto:");
		  String produto = input.nextLine();		  
		
		  System.out.println("Digite a quantidade do produto: ");
		  int qtd = input.nextInt();
		  
		  System.out.println("Digite o Preço do Produto: ");
		  double preço = input.nextDouble();
		  input.nextLine();
		  
		  venda.addProdutoAoCarrinho(new Produto(produto, qtd, preço));
		  
	  }
	  input.close();
	  
	  Relatorio relatorioSimples = new RelatorioPdfSimples(venda);
	  
	  relatorioSimples.gerarCabeçalho();
	  relatorioSimples.gerarCorpo();
	  relatorioSimples.gerarRodape();
	  relatorioSimples.imprimir();
	  
	  
  }
}
