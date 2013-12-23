package controllers;

import java.util.List;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.RepositorioDeTipoDeProduto;
import views.html.TipoDeProduto.listar;
import views.html.TipoDeProduto.novo;
import views.html.TipoDeProduto.ver;
import br.com.casadocodigo.eai.modelos.TipoDeProduto;

public class ControladorDeTipoDeProduto extends Controller {
	public static Result listar() {
		List<TipoDeProduto> tiposDeProduto = RepositorioDeTipoDeProduto.listar();
        return ok(listar.render(tiposDeProduto));
    }
	
	public static Result novo() {
        return ok(novo.render());
    }
	
	public static Result ver(Long id) {
		TipoDeProduto tipoDeProduto = RepositorioDeTipoDeProduto.obter(id);
        return ok(ver.render(tipoDeProduto));
    }
	
	public static Result salvar() {
		Form<TipoDeProduto> form = Form.form(TipoDeProduto.class);
		TipoDeProduto tipoDeProduto = form.bindFromRequest().get();
		try {
			RepositorioDeTipoDeProduto.salvar(tipoDeProduto);
			return ok();
		} catch (Exception e) {
			return internalServerError(e.getMessage());
		}
    }
	
	public static Result remover(Long id) {
		try {
			TipoDeProduto tipoDeProduto = RepositorioDeTipoDeProduto.obter(id);
			if (tipoDeProduto != null) {
				RepositorioDeTipoDeProduto.remover(tipoDeProduto);
				return ok();
			}
			else {
				return notFound();
			}
		} catch (Exception e) {
			return internalServerError(e.getMessage());
		}
    }
}
