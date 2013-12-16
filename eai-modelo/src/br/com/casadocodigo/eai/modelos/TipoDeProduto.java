package br.com.casadocodigo.eai.modelos;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
@SequenceGenerator(name = "tipo_de_produto_seq", sequenceName = "tipo_de_produto_seq")
public class TipoDeProduto implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_de_produto_seq")
	public Long id;

	@Basic(optional = false)
	private Boolean ativo;

	@Basic(optional = false)
	private String nome;

	@Basic(optional = false)
	private String descricao;
	
	public TipoDeProduto() {
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
