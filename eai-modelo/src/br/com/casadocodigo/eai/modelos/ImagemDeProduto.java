package br.com.casadocodigo.eai.modelos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
@SequenceGenerator(name = "imagem_de_produto_seq", sequenceName = "imagem_de_produto_seq")
public class ImagemDeProduto implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagem_de_produto_seq")
	public Long id;

	@Basic(optional = false)
	private Boolean ativo;

	@Basic(optional = false)
	private String url;

	@ManyToOne(optional = false)
	private Produto produto;
	
	public ImagemDeProduto() {
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
