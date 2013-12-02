package br.com.casadocodigo.eai.modelo;

import java.io.Serializable;
import java.util.List;

public class Produto implements Serializable {
	private String nome;
	private String descricao;
	private boolean ativo;
	private TipoDeProduto tipoDeProduto;
	private CategoriaDeProduto categoriaDeProduto;
	private List<ImagemDeProduto> imagensDeProduto;
}
