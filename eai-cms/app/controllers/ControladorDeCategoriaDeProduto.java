package controllers;

import java.util.List;

import play.data.Form;
import play.i18n.Messages;
import play.mvc.Result;
import repositories.RepositorioDeCategoriaDeProduto;
import utils.Message;
import views.html.CategoriaDeProduto.editar;
import views.html.CategoriaDeProduto.listar;
import views.html.CategoriaDeProduto.ver;
import br.com.casadocodigo.eai.modelos.CategoriaDeProduto;

public class ControladorDeCategoriaDeProduto extends ControladorMestre {
	@play.db.jpa.Transactional
	public static Result listar() {
		Message message = null;
		
		List<CategoriaDeProduto> categoriasDeProduto = null;
		try {
			categoriasDeProduto = RepositorioDeCategoriaDeProduto.listar();
			
			message = construirMensagem();
		}
		catch (Exception e) {
			message = new Message(Messages.get("error.list"), "danger");
		}
        return ok(listar.render(categoriasDeProduto, message));
    }

	@play.db.jpa.Transactional
	public static Result novo() {
		Form<CategoriaDeProduto> form = Form.form(CategoriaDeProduto.class);
		
		CategoriaDeProduto categoriaDeProduto = new CategoriaDeProduto();
		categoriaDeProduto.setAtivo(true);
		form = form.fill(categoriaDeProduto);
		
		List<CategoriaDeProduto> possiveisMestres = RepositorioDeCategoriaDeProduto.listarPossiveisMestres(null);

		return ok(editar.render("Criar", form, possiveisMestres, construirMensagem()));
    }
	
	@play.db.jpa.Transactional
	public static Result ver(Long id) {
		try {
			CategoriaDeProduto categoriaDeProduto = RepositorioDeCategoriaDeProduto.obter(id);
			if (categoriaDeProduto != null) {
				return ok(ver.render(categoriaDeProduto, construirMensagem()));
			}
			else {
				flash("messageText", Messages.get("error.notFound"));
				flash("messageClass", "danger");
				return redirect("/categoria-de-produto");
			}
		} catch (Exception e) {
			flash("messageText", Messages.get("error.view"));
			flash("messageClass", "danger");
			return redirect("/categoria-de-produto");
		}
    }
	
	@play.db.jpa.Transactional
	public static Result editar(Long id) {
		Form<CategoriaDeProduto> form = Form.form(CategoriaDeProduto.class);
		
		try {
			CategoriaDeProduto categoriaDeProduto = RepositorioDeCategoriaDeProduto.obter(id);
			if (categoriaDeProduto != null) {
				form = form.fill(RepositorioDeCategoriaDeProduto.obter(id));
				
				List<CategoriaDeProduto> possiveisMestres = RepositorioDeCategoriaDeProduto.listarPossiveisMestres(id);
				
				return ok(editar.render("Editar", form, possiveisMestres, construirMensagem()));
			}
			else {
				flash("messageText", Messages.get("error.notFound"));
				flash("messageClass", "danger");
				return redirect("/categoria-de-produto");
			}
		} catch (Exception e) {
			flash("messageText", Messages.get("error.view"));
			flash("messageClass", "danger");
			return redirect("/categoria-de-produto");
		}
    }
	
	@play.db.jpa.Transactional
	public static Result salvar() {
		Form<CategoriaDeProduto> form = Form.form(CategoriaDeProduto.class).bindFromRequest();
		String idValue = form.field("id").value();
		Long id = idValue.isEmpty() ? null : Long.valueOf(idValue);
		String acao = (id == null) ? "Criar" : "Editar";
		List<CategoriaDeProduto> possiveisMestres = RepositorioDeCategoriaDeProduto.listarPossiveisMestres(id);
		
		if (form.hasErrors()) {
			flash("messageText", Messages.get("error.validation"));
			flash("messageClass", "danger");
			
		    return badRequest(editar.render(acao, form, possiveisMestres, construirMensagem()));
		} else {
			String messageTextKey = (id == null)? "create": "edit"; 

			try {
				CategoriaDeProduto categoriaDeProduto = null;
				if (id != null) {
					Long idLong = Long.valueOf(id);
					categoriaDeProduto = RepositorioDeCategoriaDeProduto.obter(idLong);
					if (categoriaDeProduto != null) {
						CategoriaDeProduto categoriaDeProdutoForm = form.get();
						categoriaDeProduto.setAtivo(categoriaDeProdutoForm.getAtivo());
						categoriaDeProduto.setNome(categoriaDeProdutoForm.getNome());
						categoriaDeProduto.setDescricao(categoriaDeProdutoForm.getDescricao());
					}
					else {
						return badRequest(editar.render(acao, form, possiveisMestres, new Message(Messages.get("error.notFound"), "danger")));
					}
				} else {
					categoriaDeProduto = form.get();
				}
	
				RepositorioDeCategoriaDeProduto.salvar(categoriaDeProduto);
				
				flash("messageText", Messages.get("success." + messageTextKey));
				flash("messageClass", "success");
				return redirect("/categoria-de-produto");
			} catch (Exception e) {
				flash("messageText", Messages.get("error." + messageTextKey));
				flash("messageClass", "danger");
				return badRequest(editar.render(acao, form,  possiveisMestres,new Message(Messages.get("error.notFound"), "danger")));
			}
		}
    }
	
	@play.db.jpa.Transactional
	public static Result remover(Long id) {
		try {
			CategoriaDeProduto categoriaDeProduto = RepositorioDeCategoriaDeProduto.obter(id);
			if (categoriaDeProduto != null) {
				RepositorioDeCategoriaDeProduto.remover(categoriaDeProduto);
				flash("messageText", Messages.get("success.remove"));
				flash("messageClass", "success");
			}
			else {
				flash("messageText", Messages.get("error.notFound"));
				flash("messageClass", "danger");
			}
		} catch (Exception e) {
			flash("messageText", Messages.get("error.remove"));
			flash("messageClass", "danger");
		}
		return redirect("/categoria-de-produto");
    }
	
}
