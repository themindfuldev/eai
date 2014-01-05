package br.com.casadocodigo.eai.repositorios;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.eai.modelos.CategoriaDeProduto;
import br.com.casadocodigo.eai.modelos.ItemDeCatalogo;
import br.com.casadocodigo.eai.modelos.Produto;
import br.com.casadocodigo.eai.modelos.TipoDeProduto;

@Repository
public class RepositorioDeItemDeCatalogo {
	public List<ItemDeCatalogo> listarPorCategoriaDeProduto(
			Long idCategoriaDeProduto, int numeroDaPagina,
			int tamanhoDaPagina) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT i ").append("FROM Catalogo c ")
				.append("JOIN ItemDeCatalogo i ON c.id = i.catalogo.id ")
				.append("JOIN Produto p ON p.id = i.produto.id ")
				.append("WHERE c.ativo = true ")
				.append("WHERE i.ativo = true ")
				.append("AND p.categoriaDeProduto.id = ").append(idCategoriaDeProduto)
				.append("AND p.ativo = true ").append("ORDER BY p.nome ASC");

		return JPA.em().createQuery(query.toString())
				.setFirstResult(numeroDaPagina * tamanhoDaPagina)
				.setMaxResults(tamanhoDaPagina).getResultList();
	}

	public List<ItemDeCatalogo> listarPorTipoDeProduto(Long idTipoDeProduto,
			int numeroDaPagina, int tamanhoDaPagina) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT i ")
				.append("FROM Catalogo c ")
				.append("JOIN ItemDeCatalogo i ON c.id = i.catalogo.id ")
				.append("JOIN Produto p ON p.id = i.produto.id ")
				.append("WHERE c.ativo = true ")
				.append("WHERE i.ativo = true ")
				.append("AND p.tipoDeProduto.id = ").append(idTipoDeProduto)
				.append("AND p.ativo = true ")
				.append("ORDER BY p.nome ASC");

		return JPA.em().createQuery(query.toString())
				.setFirstResult(numeroDaPagina * tamanhoDaPagina)
				.setMaxResults(tamanhoDaPagina).getResultList();
	}

	public ItemDeCatalogo obter(Long id) {
		StringBuilder query = new StringBuilder();
		query.append("SELECT i ")
				.append("FROM Catalogo c ")
				.append("JOIN ItemDeCatalogo i ON c.id = i.catalogo.id ")
				.append("JOIN Produto p ON p.id = i.produto.id ")
				.append("WHERE c.ativo = true ")
				.append("WHERE i.ativo = true ")
				.append("AND p.id = ").append(id)
				.append("AND p.ativo = true ");

		return JPA.em().createQuery(query.toString()).getFirstResult();
		;
	}
}
