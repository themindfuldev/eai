package controllers;

import java.util.List;

import play.data.Form;
import play.i18n.Messages;
import play.mvc.Result;
import repositories.RepositorioDeCatalogo;
import utils.Message;
import views.html.Catalogo.editar;
import views.html.Catalogo.listar;
import views.html.Catalogo.ver;
import br.com.casadocodigo.eai.modelos.Catalogo;

public class ControladorDeCatalogo extends ControladorMestre {
	@play.db.jpa.Transactional
	public static Result listar() {
		Message message = null;
		
		List<Catalogo> catalogos = null;
		try {
			catalogos = RepositorioDeCatalogo.listar();
			
			message = construirMensagem();
		}
		catch (Exception e) {
			message = new Message(Messages.get("error.list"), "danger");
		}
        return ok(listar.render(catalogos, message));
    }

	public static Result novo() {
		Form<Catalogo> form = Form.form(Catalogo.class);
		Catalogo catalogo = new Catalogo();
		catalogo.setAtivo(true);
		form = form.fill(catalogo);
        return ok(editar.render("Criar", form, construirMensagem()));
    }
	
	@play.db.jpa.Transactional
	public static Result ver(Long id) {
		try {
			Catalogo catalogo = RepositorioDeCatalogo.obter(id);
			if (catalogo != null) {
				return ok(ver.render(catalogo, construirMensagem()));
			}
			else {
				flash("messageText", Messages.get("error.notFound"));
				flash("messageClass", "danger");
				return redirect("/catalogo");
			}
		} catch (Exception e) {
			flash("messageText", Messages.get("error.view"));
			flash("messageClass", "danger");
			return redirect("/catalogo");
		}
    }
	
	@play.db.jpa.Transactional
	public static Result editar(Long id) {
		Form<Catalogo> form = Form.form(Catalogo.class);
		
		try {
			Catalogo catalogo = RepositorioDeCatalogo.obter(id);
			if (catalogo != null) {
				form = form.fill(RepositorioDeCatalogo.obter(id));
				return ok(editar.render("Editar", form, construirMensagem()));
			}
			else {
				flash("messageText", Messages.get("error.notFound"));
				flash("messageClass", "danger");
				return redirect("/catalogo");
			}
		} catch (Exception e) {
			flash("messageText", Messages.get("error.view"));
			flash("messageClass", "danger");
			return redirect("/catalogo");
		}
    }
	
	@play.db.jpa.Transactional
	public static Result salvar() {
		Form<Catalogo> form = Form.form(Catalogo.class).bindFromRequest();
		String idValue = form.field("id").value();
		Long id = idValue.isEmpty() ? null : Long.valueOf(idValue);
		String acao = (id == null) ? "Criar" : "Editar";
		
		if (form.hasErrors()) {
			flash("messageText", Messages.get("error.validation"));
			flash("messageClass", "danger");
		    return badRequest(editar.render(acao, form, construirMensagem()));
		} else {
			String messageTextKey = (id == null)? "create": "edit"; 

			try {
				Catalogo catalogo = null;
				if (id != null) {
					Long idLong = Long.valueOf(id);
					catalogo = RepositorioDeCatalogo.obter(idLong);
					if (catalogo != null) {
						Catalogo catalogoForm = form.get();
						catalogo.setAtivo(catalogoForm.getAtivo());
						catalogo.setNome(catalogoForm.getNome());
						catalogo.setDescricao(catalogoForm.getDescricao());
					}
					else {
						return badRequest(editar.render(acao, form, new Message(Messages.get("error.notFound"), "danger")));
					}
				} else {
					catalogo = form.get();
				}
	
				RepositorioDeCatalogo.salvar(catalogo);
				
				flash("messageText", Messages.get("success." + messageTextKey));
				flash("messageClass", "success");
				return redirect("/catalogo");
			} catch (Exception e) {
				flash("messageText", Messages.get("error." + messageTextKey));
				flash("messageClass", "danger");
				return badRequest(editar.render(acao, form, new Message(Messages.get("error.notFound"), "danger")));
			}
		}
    }
	
	@play.db.jpa.Transactional
	public static Result remover(Long id) {
		try {
			Catalogo catalogo = RepositorioDeCatalogo.obter(id);
			if (catalogo != null) {
				RepositorioDeCatalogo.remover(catalogo);
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
		return redirect("/catalogo");
    }
		
}
