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
}
