package br.com.casadocodigo.eai.modelos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
@SequenceGenerator(name = "categoria_de_produto_seq", sequenceName = "categoria_de_produto_seq")
public class CategoriaDeProduto implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_de_produto_seq")
	public Long id;

	@Basic(optional = false)
	private Boolean ativo;

	@Basic(optional = false)
	private String nome;
	
	@Basic(optional = false)
	private String descricao;
	
	@ManyToOne(optional = false)
	private CategoriaDeProduto categoriaMestre;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<CategoriaDeProduto> subcategorias;
	
	public CategoriaDeProduto() {
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

	public CategoriaDeProduto getCategoriaMestre() {
		return categoriaMestre;
	}

	public void setCategoriaMestre(CategoriaDeProduto categoriaMestre) {
		this.categoriaMestre = categoriaMestre;
	}

	public List<CategoriaDeProduto> getSubcategorias() {
		return subcategorias;
	}

	public void setSubcategorias(List<CategoriaDeProduto> subcategorias) {
		this.subcategorias = subcategorias;
	}
	
}
