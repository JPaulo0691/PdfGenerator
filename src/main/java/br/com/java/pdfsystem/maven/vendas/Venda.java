package br.com.java.pdfsystem.maven.vendas;

import java.time.LocalDate;
import java.util.List;

public class Venda {
	
	private LocalDate dataVenda;
	private String nomeCliente;
	private List<Produto> produtosVendidos;
	
	public Venda() {
		
	}
	
	public Venda(String nomeCliente, List<Produto> arrayList) {
		
		this.dataVenda = LocalDate.now();
		this.nomeCliente = nomeCliente;
		this.produtosVendidos = arrayList;
	}
	
	public double calcularValorTotalCarrinho() {
		
		double total = 0;
		
		for(Produto produto: produtosVendidos) {
			total += produto.calcularPreço();
		}
		return total;
	}
	
	public void addProdutoAoCarrinho(Produto produto) {
		this.produtosVendidos.add(produto);
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public List<Produto> getProdutosVendidos() {
		return produtosVendidos;
	}
	
	
	
}
