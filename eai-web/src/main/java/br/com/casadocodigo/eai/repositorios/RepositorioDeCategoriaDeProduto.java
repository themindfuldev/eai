package br.com.casadocodigo.eai.repositorios;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.eai.modelos.Catalogo;
import br.com.casadocodigo.eai.modelos.CategoriaDeProduto;

@Repository
public class RepositorioDeCategoriaDeProduto {
	public List<CategoriaDeProduto> listarCategoriasPrincipais() {
		String query = "FROM CategoriaDeProduto c WHERE c.categoriaMestre = null AND c.ativo = true";

		return JPA.em().createQuery(query).getResultList();
	}
}
