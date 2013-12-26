package repositories;

import java.util.List;

import play.db.jpa.JPA;
import br.com.casadocodigo.eai.modelos.CategoriaDeProduto;

public class RepositorioDeCategoriaDeProduto {
	@SuppressWarnings("unchecked")
	public static List<CategoriaDeProduto> listar() {
		return JPA.em().createQuery("FROM CategoriaDeProduto").getResultList();
	}
	
	public static CategoriaDeProduto obter(Long id) {
		return JPA.em().find(CategoriaDeProduto.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public static List<CategoriaDeProduto> listarPossiveisMestres(Long id) {
		return JPA.em().createQuery("FROM CategoriaDeProduto c WHERE c.id != " + id + " AND c.categoriaMestre == null ORDER BY c.nome").getResultList();
	}
	
	public static void remover(CategoriaDeProduto categoriaDeProduto) {
		JPA.em().remove(categoriaDeProduto);
	}
	
	public static void salvar(CategoriaDeProduto categoriaDeProduto) {
		JPA.em().persist(categoriaDeProduto);
	}
}
