package br.com.casadocodigo.eai.modelo;

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
}
