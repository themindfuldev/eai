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
import javax.persistence.Transient;

import play.data.validation.Constraints;

@SuppressWarnings("serial")
@Entity
@SequenceGenerator(name = "categoria_de_produto_seq", sequenceName = "categoria_de_produto_seq", allocationSize = 1)
public class CategoriaDeProduto implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_de_produto_seq")
	public Long id;

	@Basic(optional = false)
	@Constraints.Required
	private Boolean ativo;

	@Basic(optional = false)
	@Constraints.Required
	private String nome;
	
	@Basic(optional = false)
	@Constraints.Required
	private String descricao;
	
	@Transient
	private Long categoriaMestreId;
	
	@ManyToOne
	private CategoriaDeProduto categoriaMestre;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<CategoriaDeProduto> subcategorias;
	
	public CategoriaDeProduto() {
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
	
	public Long getCategoriaMestreId() {
		return categoriaMestreId;
	}

	public void setCategoriaMestreId(Long categoriaMestreId) {
		this.categoriaMestreId = categoriaMestreId;
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
