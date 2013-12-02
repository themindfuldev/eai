package br.com.casadocodigo.eai.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Catalogo implements Serializable {
	private String nome;
	private String descricao;
	private Date dataDeInicio;
	private Date dataDeTermino;
	private boolean ativo;
	private List<ItemDeCatalogo> itensDeCatalogo;
}
