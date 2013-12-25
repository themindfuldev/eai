package controllers;

import java.util.List;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.RepositorioDeTipoDeProduto;
import views.html.TipoDeProduto.listar;
import views.html.TipoDeProduto.editar;
import views.html.TipoDeProduto.ver;
import br.com.casadocodigo.eai.modelos.TipoDeProduto;

public class ControladorDeTipoDeProduto extends Controller {
	@play.db.jpa.Transactional
	public static Result listar() {
		List<TipoDeProduto> tiposDeProduto = RepositorioDeTipoDeProduto.listar();
        return ok(listar.render(tiposDeProduto));
    }
	
	public static Result novo() {
		Form<TipoDeProduto> form = Form.form(TipoDeProduto.class);
        return ok(editar.render("Criar", form));
    }
	
	@play.db.jpa.Transactional
	public static Result ver(Long id) {
		TipoDeProduto tipoDeProduto = RepositorioDeTipoDeProduto.obter(id);
        return ok(ver.render(tipoDeProduto));
    }
	
	@play.db.jpa.Transactional
	public static Result editar(Long id) {
		Form<TipoDeProduto> form = Form.form(TipoDeProduto.class);
		form.fill(RepositorioDeTipoDeProduto.obter(id));
        return ok(editar.render("Editar",  form));
    }
	
	@play.db.jpa.Transactional
	public static Result salvar() {
		Form<TipoDeProduto> form = Form.form(TipoDeProduto.class);
		TipoDeProduto tipoDeProduto = form.bindFromRequest().get();
		try {
			RepositorioDeTipoDeProduto.salvar(tipoDeProduto);
			return listar();
		} catch (Exception e) {
			return internalServerError(e.getMessage());
		}
    }
	
	@play.db.jpa.Transactional
	public static Result remover(Long id) {
		try {
			TipoDeProduto tipoDeProduto = RepositorioDeTipoDeProduto.obter(id);
			if (tipoDeProduto != null) {
				RepositorioDeTipoDeProduto.remover(tipoDeProduto);
				return listar();
			}
			else {
				return notFound();
			}
		} catch (Exception e) {
			return internalServerError(e.getMessage());
		}
    }
}
