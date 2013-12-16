package br.com.casadocodigo.eai.repositorios;

import java.util.List;

import play.db.jpa.JPA;
import br.com.casadocodigo.eai.modelos.Produto;
import br.com.casadocodigo.eai.modelos.TipoDeProduto;

public class RepositorioDeTipoDeProduto {
	@SuppressWarnings("unchecked")
	public List<Produto> listar() {
		return JPA.em().createQuery("FROM TipoDeProduto").getResultList();
	}
	
	public void remover(TipoDeProduto tipoDeProduto) {
		JPA.em().remove(tipoDeProduto);
	}
	
	public void salvar(TipoDeProduto tipoDeProduto) {
		JPA.em().persist(tipoDeProduto);
	}
}
