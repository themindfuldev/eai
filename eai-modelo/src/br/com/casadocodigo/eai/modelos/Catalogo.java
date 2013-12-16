package br.com.casadocodigo.eai.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
@SequenceGenerator(name = "catalogo_seq", sequenceName = "catalogo_seq")
public class Catalogo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "catalogo_seq")
	public Long id;
	
	@Basic(optional = false)
	private Boolean ativo;

	@Basic(optional = false)
	private String nome;

	@Basic(optional = false)
	private String descricao;

	@Basic(optional = false)
	private Date dataDeInicio;

	@Basic(optional = false)
	private Date dataDeTermino;

	@OneToMany(cascade = CascadeType.REMOVE)
	private List<ItemDeCatalogo> itensDeCatalogo;
	
	public Catalogo() {
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

	public Date getDataDeInicio() {
		return dataDeInicio;
	}

	public void setDataDeInicio(Date dataDeInicio) {
		this.dataDeInicio = dataDeInicio;
	}

	public Date getDataDeTermino() {
		return dataDeTermino;
	}

	public void setDataDeTermino(Date dataDeTermino) {
		this.dataDeTermino = dataDeTermino;
	}

	public List<ItemDeCatalogo> getItensDeCatalogo() {
		return itensDeCatalogo;
	}

	public void setItensDeCatalogo(List<ItemDeCatalogo> itensDeCatalogo) {
		this.itensDeCatalogo = itensDeCatalogo;
	}
	
}
