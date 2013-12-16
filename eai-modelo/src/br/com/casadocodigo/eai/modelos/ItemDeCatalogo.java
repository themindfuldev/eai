package br.com.casadocodigo.eai.modelos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
@SequenceGenerator(name = "item_de_catalogo_seq", sequenceName = "item_de_catalogo_seq")
public class ItemDeCatalogo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_de_catalogo_seq")
	public Long id;

	@Basic(optional = false)
	private Boolean ativo;

	@Basic(optional = false)
	private BigDecimal precoDeVenda;

	@Basic(optional = false)
	private BigDecimal prevoDeFabricacao;

	@ManyToOne(optional = false)
	private Catalogo catalogo;

	@ManyToOne(optional = false)
	private Produto produto;
	
	public ItemDeCatalogo() {
	}

	public Long getId() {
		return id;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public BigDecimal getPrecoDeVenda() {
		return precoDeVenda;
	}

	public void setPrecoDeVenda(BigDecimal precoDeVenda) {
		this.precoDeVenda = precoDeVenda;
	}

	public BigDecimal getPrevoDeFabricacao() {
		return prevoDeFabricacao;
	}

	public void setPrevoDeFabricacao(BigDecimal prevoDeFabricacao) {
		this.prevoDeFabricacao = prevoDeFabricacao;
	}

	public Catalogo getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Catalogo catalogo) {
		this.catalogo = catalogo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
