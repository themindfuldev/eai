package repositories;

import java.util.List;

import play.db.jpa.JPA;
import br.com.casadocodigo.eai.modelos.TipoDeProduto;

public class RepositorioDeTipoDeProduto {
	@SuppressWarnings("unchecked")
	public static List<TipoDeProduto> listar() {
		return JPA.em().createQuery("FROM TipoDeProduto").getResultList();
	}
	
	public static TipoDeProduto obter(Long id) {
		return JPA.em().find(TipoDeProduto.class, id);
	}
	
	public static void remover(TipoDeProduto tipoDeProduto) {
		JPA.em().remove(tipoDeProduto);
	}
	
	public static void salvar(TipoDeProduto tipoDeProduto) {
		JPA.em().persist(tipoDeProduto);
	}
}
