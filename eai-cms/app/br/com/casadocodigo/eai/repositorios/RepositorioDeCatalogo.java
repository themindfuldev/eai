package br.com.casadocodigo.eai.repositorios;

import java.util.List;

import play.db.jpa.JPA;
import br.com.casadocodigo.eai.modelos.Catalogo;

public class RepositorioDeCatalogo {
	@SuppressWarnings("unchecked")
	public List<Catalogo> listar() {
		return JPA.em().createQuery("FROM Catalogo").getResultList();
	}
	
	public void remover(Catalogo catalogo) {
		JPA.em().remove(catalogo);
	}
	
	public void salvar(Catalogo catalogo) {
		JPA.em().persist(catalogo);
	}
	
	public void definirPadrao(Catalogo catalogoPadrao) {
		List<Catalogo> catalogos = listar();
		
		for (Catalogo catalogo: catalogos) {
			if (catalogo.getId().equals(catalogoPadrao.getId())) {
				catalogo.setAtivo(true);
			}
			else {
				catalogo.setAtivo(false);
			}
			salvar(catalogo);
		}
		
	}
}
