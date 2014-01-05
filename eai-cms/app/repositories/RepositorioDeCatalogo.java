package repositories;

import java.util.List;

import play.db.jpa.JPA;
import br.com.casadocodigo.eai.modelos.Catalogo;

public class RepositorioDeCatalogo {
	@SuppressWarnings("unchecked")
	public static List<Catalogo> listar() {
		return JPA.em().createQuery("FROM Catalogo ORDER BY nome ASC").getResultList();
	}
	
	public static Catalogo obter(Long id) {
		return JPA.em().find(Catalogo.class, id);
	}
	
	public static void remover(Catalogo catalogo) {
		JPA.em().remove(catalogo);
	}
	
	public static void salvar(Catalogo catalogo) {
		JPA.em().persist(catalogo);
	}
	
	public static void definirPadrao(Catalogo catalogoPadrao) {
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
