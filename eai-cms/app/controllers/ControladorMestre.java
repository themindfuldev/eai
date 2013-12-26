package controllers;

import play.mvc.Controller;
import utils.Message;

public class ControladorMestre extends Controller {
	static Message construirMensagem() {
		Message message = null;
		String messageText = flash("messageText");
		String messageClass = flash("messageClass");
		if (messageText != null && messageClass != null) {
			message = new Message(messageText, messageClass);
		}
		return message;
	}

}
