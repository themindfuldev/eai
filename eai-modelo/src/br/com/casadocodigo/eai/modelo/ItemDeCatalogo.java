package br.com.casadocodigo.eai.modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
@SequenceGenerator(name = "item_de_catalogo_seq", sequenceName = "item_de_catalogo_seq")
public class ItemDeCatalogo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_de_catalogo_seq")
	public Long id;

	@Basic(optional = false)
	private Boolean ativo;

	@Basic(optional = false)
	private BigDecimal precoDeVenda;

	@Basic(optional = false)
	private BigDecimal prevoDeFabricacao;

	@ManyToOne(optional = false)
	private Catalogo catalogo;

	@ManyToOne(optional = false)
	private Produto produto;
}
