package controllers;

import java.util.List;

import play.data.Form;
import play.i18n.Messages;
import play.mvc.Result;
import repositories.RepositorioDeProduto;
import utils.Message;
import views.html.Produto.editar;
import views.html.Produto.listar;
import views.html.Produto.ver;
import br.com.casadocodigo.eai.modelos.Produto;

public class ControladorDeProduto extends ControladorMestre {

	private static int TAMANHO_DA_PAGINA = 25;

	@play.db.jpa.Transactional
	public static Result listar(Integer numeroDaPagina) {
		Message message = null;
		List<Produto> produtos = null;
		
		numeroDaPagina = numeroDaPagina != null ? numeroDaPagina : 0;
		try {
			produtos = RepositorioDeProduto.listar(numeroDaPagina,
					TAMANHO_DA_PAGINA);

			message = construirMensagem();
		} catch (Exception e) {
			message = new Message(Messages.get("error.list"), "danger");
		}
		return ok(listar.render(produtos, message));
	}

	public static Result novo() {
		Form<Produto> form = Form.form(Produto.class);
		Produto produto = new Produto();
		produto.setAtivo(true);
		form = form.fill(produto);
		return ok(editar.render("Criar", form, construirMensagem()));
	}

	@play.db.jpa.Transactional
	public static Result ver(Long id) {
		try {
			Produto produto = RepositorioDeProduto.obter(id);
			if (produto != null) {
				return ok(ver.render(produto, construirMensagem()));
			} else {
				flash("messageText", Messages.get("error.notFound"));
				flash("messageClass", "danger");
				return redirect("/produto");
			}
		} catch (Exception e) {
			flash("messageText", Messages.get("error.view"));
			flash("messageClass", "danger");
			return redirect("/produto");
		}
	}

	@play.db.jpa.Transactional
	public static Result editar(Long id) {
		Form<Produto> form = Form.form(Produto.class);

		try {
			Produto produto = RepositorioDeProduto.obter(id);
			if (produto != null) {
				form = form.fill(RepositorioDeProduto.obter(id));
				return ok(editar.render("Editar", form, construirMensagem()));
			} else {
				flash("messageText", Messages.get("error.notFound"));
				flash("messageClass", "danger");
				return redirect("/produto");
			}
		} catch (Exception e) {
			flash("messageText", Messages.get("error.view"));
			flash("messageClass", "danger");
			return redirect("/produto");
		}
	}

	@play.db.jpa.Transactional
	public static Result salvar() {
		Form<Produto> form = Form.form(Produto.class).bindFromRequest();
		String idValue = form.field("id").value();
		Long id = idValue.isEmpty() ? null : Long.valueOf(idValue);
		String acao = (id == null) ? "Criar" : "Editar";

		if (form.hasErrors()) {
			flash("messageText", Messages.get("error.validation"));
			flash("messageClass", "danger");
			return badRequest(editar.render(acao, form, construirMensagem()));
		} else {
			String messageTextKey = (id == null) ? "create" : "edit";

			try {
				Produto produto = null;
				if (id != null) {
					Long idLong = Long.valueOf(id);
					produto = RepositorioDeProduto.obter(idLong);
					if (produto != null) {
						Produto produtoForm = form.get();
						produto.setAtivo(produtoForm.getAtivo());
						produto.setNome(produtoForm.getNome());
						produto.setDescricao(produtoForm.getDescricao());
					} else {
						return badRequest(editar.render(acao, form,
								new Message(Messages.get("error.notFound"),
										"danger")));
					}
				} else {
					produto = form.get();
				}

				RepositorioDeProduto.salvar(produto);

				flash("messageText", Messages.get("success." + messageTextKey));
				flash("messageClass", "success");
				return redirect("/produto");
			} catch (Exception e) {
				flash("messageText", Messages.get("error." + messageTextKey));
				flash("messageClass", "danger");
				return badRequest(editar.render(acao, form, new Message(
						Messages.get("error.notFound"), "danger")));
			}
		}
	}

	@play.db.jpa.Transactional
	public static Result remover(Long id) {
		try {
			Produto produto = RepositorioDeProduto.obter(id);
			if (produto != null) {
				RepositorioDeProduto.remover(produto);
				flash("messageText", Messages.get("success.remove"));
				flash("messageClass", "success");
			} else {
				flash("messageText", Messages.get("error.notFound"));
				flash("messageClass", "danger");
			}
		} catch (Exception e) {
			flash("messageText", Messages.get("error.remove"));
			flash("messageClass", "danger");
		}
		return redirect("/produto");
	}

}
