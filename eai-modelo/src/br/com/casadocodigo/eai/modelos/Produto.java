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

import play.data.validation.Constraints;

@SuppressWarnings("serial")
@Entity
@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq")
public class Produto implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
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

	@ManyToOne(optional = false)
	@Constraints.Required
	private TipoDeProduto tipoDeProduto;

	@ManyToOne(optional = false)
	@Constraints.Required
	private CategoriaDeProduto categoriaDeProduto;

	@OneToMany(cascade = CascadeType.REMOVE)
	private List<ImagemDeProduto> imagensDeProduto;

	public Produto() {
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

	public TipoDeProduto getTipoDeProduto() {
		return tipoDeProduto;
	}

	public void setTipoDeProduto(TipoDeProduto tipoDeProduto) {
		this.tipoDeProduto = tipoDeProduto;
	}

	public CategoriaDeProduto getCategoriaDeProduto() {
		return categoriaDeProduto;
	}

	public void setCategoriaDeProduto(CategoriaDeProduto categoriaDeProduto) {
		this.categoriaDeProduto = categoriaDeProduto;
	}

	public List<ImagemDeProduto> getImagensDeProduto() {
		return imagensDeProduto;
	}

	public void setImagensDeProduto(List<ImagemDeProduto> imagensDeProduto) {
		this.imagensDeProduto = imagensDeProduto;
	}
	
}
