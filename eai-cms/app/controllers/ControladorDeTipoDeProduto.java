package controllers;

import java.util.List;

import play.data.Form;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.RepositorioDeTipoDeProduto;
import utils.Message;
import views.html.TipoDeProduto.editar;
import views.html.TipoDeProduto.listar;
import views.html.TipoDeProduto.ver;
import br.com.casadocodigo.eai.modelos.TipoDeProduto;

public class ControladorDeTipoDeProduto extends Controller {
	@play.db.jpa.Transactional
	public static Result listar() {
		Message message = null;
		
		List<TipoDeProduto> tiposDeProduto = null;
		try {
			tiposDeProduto = RepositorioDeTipoDeProduto.listar();
			
			message = construirMensagem();
		}
		catch (Exception e) {
			message = new Message(Messages.get("error.list"), "danger");
		}
        return ok(listar.render(tiposDeProduto, message));
    }

	public static Result novo() {
		Form<TipoDeProduto> form = Form.form(TipoDeProduto.class);
		TipoDeProduto tipoDeProduto = new TipoDeProduto();
		tipoDeProduto.setAtivo(true);
		form = form.fill(tipoDeProduto);
        return ok(editar.render("Criar", form, construirMensagem()));
    }
	
	@play.db.jpa.Transactional
	public static Result ver(Long id) {
		try {
			TipoDeProduto tipoDeProduto = RepositorioDeTipoDeProduto.obter(id);
			if (tipoDeProduto != null) {
				return ok(ver.render(tipoDeProduto, construirMensagem()));
			}
			else {
				flash("messageText", Messages.get("error.notFound"));
				flash("messageClass", "danger");
				return redirect("/tipo-de-produto");
			}
		} catch (Exception e) {
			flash("messageText", Messages.get("error.view"));
			flash("messageClass", "danger");
			return redirect("/tipo-de-produto");
		}
    }
	
	@play.db.jpa.Transactional
	public static Result editar(Long id) {
		Form<TipoDeProduto> form = Form.form(TipoDeProduto.class);
		
		try {
			TipoDeProduto tipoDeProduto = RepositorioDeTipoDeProduto.obter(id);
			if (tipoDeProduto != null) {
				form = form.fill(RepositorioDeTipoDeProduto.obter(id));
				return ok(editar.render("Editar", form, construirMensagem()));
			}
			else {
				flash("messageText", Messages.get("error.notFound"));
				flash("messageClass", "danger");
				return redirect("/tipo-de-produto");
			}
		} catch (Exception e) {
			flash("messageText", Messages.get("error.view"));
			flash("messageClass", "danger");
			return redirect("/tipo-de-produto");
		}
    }
	
	@play.db.jpa.Transactional
	public static Result salvar() {
		Form<TipoDeProduto> form = Form.form(TipoDeProduto.class).bindFromRequest();
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
				TipoDeProduto tipoDeProduto = null;
				if (id != null) {
					Long idLong = Long.valueOf(id);
					tipoDeProduto = RepositorioDeTipoDeProduto.obter(idLong);
					if (tipoDeProduto != null) {
						TipoDeProduto tipoDeProdutoForm = form.get();
						tipoDeProduto.setAtivo(tipoDeProdutoForm.getAtivo());
						tipoDeProduto.setNome(tipoDeProdutoForm.getNome());
						tipoDeProduto.setDescricao(tipoDeProdutoForm.getDescricao());
					}
					else {
						return badRequest(editar.render(acao, form, new Message(Messages.get("error.notFound"), "danger")));
					}
				} else {
					tipoDeProduto = form.get();
				}
	
				RepositorioDeTipoDeProduto.salvar(tipoDeProduto);
				
				flash("messageText", Messages.get("success." + messageTextKey));
				flash("messageClass", "success");
				return redirect("/tipo-de-produto");
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
			TipoDeProduto tipoDeProduto = RepositorioDeTipoDeProduto.obter(id);
			if (tipoDeProduto != null) {
				RepositorioDeTipoDeProduto.remover(tipoDeProduto);
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
		return redirect("/tipo-de-produto");
    }
	
	private static Message construirMensagem() {
		Message message = null;
		String messageText = flash("messageText");
		String messageClass = flash("messageClass");
		if (messageText != null && messageClass != null) {
			message = new Message(messageText, messageClass);
		}
		return message;
	}
	
}
