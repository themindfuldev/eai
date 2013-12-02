package br.com.casadocodigo.eai.modelo;

import java.io.Serializable;
import java.util.List;

public class CategoriaDeProduto implements Serializable {
	private String nome;
	private String descricao;
	private CategoriaDeProduto categoriaMestre;
	private List<CategoriaDeProduto> subcategorias;
	private boolean ativo;
}
