package br.com.casadocodigo.eai.modelos;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import play.data.validation.Constraints;

@SuppressWarnings("serial")
@Entity
public class ItemDeCatalogo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_de_catalogo_seq")
	@SequenceGenerator(name = "item_de_catalogo_seq", sequenceName = "item_de_catalogo_seq", allocationSize = 1)
	public Long id;

	@Basic(optional = false)
	@Constraints.Required
	private Boolean ativo;

	@Basic(optional = false)
	@Constraints.Required
	private BigDecimal precoDeVenda;

	@Basic(optional = false)
	@Constraints.Required
	private BigDecimal precoDeFabricacao;

	@ManyToOne(optional = false)
	@Constraints.Required
	private Catalogo catalogo;

	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@Constraints.Required
	private Produto produto;
	
	public ItemDeCatalogo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		return precoDeFabricacao;
	}

	public void setPrevoDeFabricacao(BigDecimal prevoDeFabricacao) {
		this.precoDeFabricacao = prevoDeFabricacao;
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
