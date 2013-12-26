package utils;

public class Message {
	public String messageText;
	public String messageClass;

	public Message(String message, String messageClass) {
		this.messageText = message;
		this.messageClass = messageClass;
	}

	public String getMessageText() {
		return messageText;
	}

	public String getMessageClass() {
		return messageClass;
	}

}
