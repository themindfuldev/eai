package br.com.casadocodigo.eai.repositorios;

import java.util.List;

import play.db.jpa.JPA;
import br.com.casadocodigo.eai.modelos.Produto;

public class RepositorioDeProduto {
	@SuppressWarnings("unchecked")
	public List<Produto> listar() {
		return JPA.em().createQuery("FROM Produto").getResultList();
	}
	
	public void remover(Produto produto) {
		JPA.em().remove(produto);
	}
	
	public void salvar(Produto produto) {
		JPA.em().persist(produto);
	}
}
