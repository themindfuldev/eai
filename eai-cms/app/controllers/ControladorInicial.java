package controllers;

import play.mvc.Result;
import views.html.index;

public class ControladorInicial extends ControladorMestre {

    public static Result index() {
        return ok(index.render(null));
    }

}
