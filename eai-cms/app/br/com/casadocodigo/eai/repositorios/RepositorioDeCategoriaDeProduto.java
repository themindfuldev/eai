package br.com.casadocodigo.eai.repositorios;

import java.util.List;

import play.db.jpa.JPA;
import br.com.casadocodigo.eai.modelos.CategoriaDeProduto;
import br.com.casadocodigo.eai.modelos.Produto;

public class RepositorioDeCategoriaDeProduto {
	@SuppressWarnings("unchecked")
	public List<Produto> listar() {
		return JPA.em().createQuery("FROM CategoriaDeProduto").getResultList();
	}
	
	public void remover(CategoriaDeProduto categoriaDeProduto) {
		JPA.em().remove(categoriaDeProduto);
	}
	
	public void salvar(CategoriaDeProduto categoriaDeProduto) {
		JPA.em().persist(categoriaDeProduto);
	}
}
