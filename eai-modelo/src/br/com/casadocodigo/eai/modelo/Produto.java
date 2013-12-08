package br.com.casadocodigo.eai.modelo;

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
@SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq")
public class Produto implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
	public Long id;

	@Basic(optional = false)
	private Boolean ativo;

	@Basic(optional = false)
	private String nome;
	
	@Basic(optional = false)
	private String descricao;
	
	@ManyToOne(optional = false)
	private TipoDeProduto tipoDeProduto;
	
	@ManyToOne(optional = false)
	private CategoriaDeProduto categoriaDeProduto;
	
	@OneToMany(cascade = CascadeType.REMOVE)
	private List<ImagemDeProduto> imagensDeProduto;
}
