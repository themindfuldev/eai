package repositories;

import java.util.List;

import play.db.jpa.JPA;
import br.com.casadocodigo.eai.modelos.CategoriaDeProduto;

public class RepositorioDeCategoriaDeProduto {
	@SuppressWarnings("unchecked")
	public List<CategoriaDeProduto> listar() {
		return JPA.em().createQuery("FROM CategoriaDeProduto").getResultList();
	}
	
	public void remover(CategoriaDeProduto categoriaDeProduto) {
		JPA.em().remove(categoriaDeProduto);
	}
	
	public void salvar(CategoriaDeProduto categoriaDeProduto) {
		JPA.em().persist(categoriaDeProduto);
	}
}
