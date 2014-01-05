package repositories;

import java.util.List;

import play.db.jpa.JPA;
import br.com.casadocodigo.eai.modelos.CategoriaDeProduto;

public class RepositorioDeCategoriaDeProduto {
	@SuppressWarnings("unchecked")
	public static List<CategoriaDeProduto> listar() {
		return JPA.em().createQuery("FROM CategoriaDeProduto ORDER BY nome ASC").getResultList();
	}
	
	public static CategoriaDeProduto obter(Long id) {
		return JPA.em().find(CategoriaDeProduto.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public static List<CategoriaDeProduto> listarPossiveisMestres(Long id) {
		StringBuilder query = new StringBuilder();
		
		query.append("FROM CategoriaDeProduto c WHERE c.categoriaMestre = null");
		if (id != null) {
			query.append(" AND c.id != " + id);
		}
		query.append(" ORDER BY c.nome");
		
		return JPA.em().createQuery(query.toString()).getResultList();
	}
	
	public static void remover(CategoriaDeProduto categoriaDeProduto) {
		JPA.em().remove(categoriaDeProduto);
	}
	
	public static void salvar(CategoriaDeProduto categoriaDeProduto) {
		JPA.em().persist(categoriaDeProduto);
	}
}
