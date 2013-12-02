package br.com.casadocodigo.eai.modelo;

import java.io.Serializable;

public class ItemDeCatalogo implements Serializable {
	private Catalogo catalogo;
	private Produto produto;
	private float precoDeVenda;
	private float prevoDeFabricacao;
	private boolean ativo;
}
