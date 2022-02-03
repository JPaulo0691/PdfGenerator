package br.com.java.pdfsystem.maven.relatorio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import br.com.java.pdfsystem.maven.vendas.Produto;
import br.com.java.pdfsystem.maven.vendas.Venda;

public class RelatorioPdfSimples implements Relatorio{
	
	private Venda venda;
	private Document documentoPDF; //aqui vamos add txt,tabelas, datas.... etc
	private String caminhoRelatorio = "RelatorioSimplesPDF.pdf";
	
	public RelatorioPdfSimples(Venda venda) {
		
		this.venda = venda;
		this.documentoPDF = new Document();
		
		try {
			PdfWriter.getInstance(this.documentoPDF, new FileOutputStream(caminhoRelatorio));
			
			this.documentoPDF.open();
		} catch (DocumentException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	@Override
	public void gerarCabeçalho() {
		
		Paragraph paragrafoTitulo = new Paragraph();
		paragrafoTitulo.setAlignment(Element.ALIGN_CENTER);
		
		paragrafoTitulo.add(
				new Chunk(
						"RELATÓRIO DE VENDAS SIMPLES",
						new Font(Font.HELVETICA,24)
		));
		
		this.documentoPDF.add(paragrafoTitulo);
		
		this.documentoPDF.add(new Paragraph(" ")); //simula um pulador de linhas
		
		Paragraph paragrafoData = new Paragraph();
		paragrafoData.setAlignment(Element.ALIGN_CENTER);
		paragrafoData.add(new Chunk(this.venda.getDataVenda().toString())); //bloco de txt
		this.documentoPDF.add(paragrafoData);
		
		this.documentoPDF.add(new Paragraph(" "));
		this.documentoPDF.add(new Paragraph(" "));
		
		Paragraph paragrafoCliente = new Paragraph();
		paragrafoCliente.setAlignment(Element.ALIGN_CENTER);
		paragrafoCliente.add(
				new Chunk(
						"Cliente: " + this.venda.getNomeCliente(),
						new Font(Font.BOLD,16)
						)
				);
		
		this.documentoPDF.add(paragrafoCliente);
		
		Paragraph paragrafoSessao = new Paragraph("---------------------------------------------------------------------------");
		paragrafoSessao.setAlignment(Element.ALIGN_CENTER);
		
		this.documentoPDF.add(paragrafoSessao);
		this.documentoPDF.add(new Paragraph(" "));
			
	}

	@Override
	public void gerarCorpo() {
		
		Paragraph itensVendidos = new Paragraph();
		itensVendidos.setAlignment(Element.ALIGN_CENTER);
		
		itensVendidos.add(new Chunk("ITENS VENDIDOS ", new Font(Font.TIMES_ROMAN)));
		documentoPDF.add(new Paragraph(itensVendidos));
		
		for(Produto produto: this.venda.getProdutosVendidos()) {
			
			Paragraph nomeProduto = new Paragraph();
			nomeProduto.add(new Chunk(produto.getNome(), new Font(Font.COURIER,16)));
			
			Paragraph dadosProduto = new Paragraph();
			dadosProduto.add(
					"Quantidade:  " + produto.getQuantidade() +
					" - Preço unit.: R$ " +  produto.getValor() +
					" - Total: R$ " + produto.calcularPreço()
			);
			
			this.documentoPDF.add(nomeProduto);
			this.documentoPDF.add(dadosProduto);
			this.documentoPDF.add(new Paragraph("---------------------------------------"));
				
		}
		
		Paragraph totalProduto = new Paragraph();
		totalProduto.setAlignment(Element.ALIGN_RIGHT);
		totalProduto.add(
				new Chunk(
						"Total da Venda: R$ " + this.venda.calcularValorTotalCarrinho(),
						new Font(Font.TIMES_ROMAN,20)
						)
				);
		this.documentoPDF.add(totalProduto);
		
	}

	@Override
	public void gerarRodape() {
		
		Paragraph paragrafoSessao = new Paragraph("-----------------------------------------------------------------------");
		paragrafoSessao.setAlignment(Element.ALIGN_CENTER);
		this.documentoPDF.add(paragrafoSessao);
		
		this.documentoPDF.add(new Paragraph("  "));
		
		Paragraph rodape = new Paragraph();
		rodape.setAlignment(Element.ALIGN_CENTER);
		rodape.add(
				new Chunk(
						"www.impetussoft.com.br", new Font(Font.TIMES_ROMAN,14)
				)
			);
		this.documentoPDF.add(rodape);
	}

	@Override
	public void imprimir() {
		
		if(this.documentoPDF != null && this.documentoPDF.isOpen()) {
			this.documentoPDF.close();
		}
		
	}
	
	
}
