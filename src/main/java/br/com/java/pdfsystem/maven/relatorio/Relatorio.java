package br.com.java.pdfsystem.maven.relatorio;

public interface Relatorio {
	
	public void gerarCabeçalho();
	public void gerarCorpo();
	public void gerarRodape();
	public void imprimir();
}
