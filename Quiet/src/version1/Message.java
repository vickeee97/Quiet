package version1;

import java.util.LinkedList;

/**
 * Klass som hanterar ramverket f�r ett message objekt.
 * @author Viktor
 *
 */
public class Message {
	private String text;
	private String key;
	private User sender;
	private LinkedList<User> recievers = new LinkedList<User>();
	

	public Message(String text, String key, User sender, LinkedList<User> recievers) {
		this.text = text;
		this.key = key;
		this.sender = sender;
		this.recievers = recievers;
	}
	
	public Message (String text, User sender, LinkedList<User> recievers) {
		this.text = text;
		this.sender = sender;
		this.recievers = recievers;
	}

	public Message(String message, User sender) {
		this.text = message;
		this.sender = sender;
	}

	public void setText(String text){
		this.text = text;
	}
	
	public String getText() {
		return this.text;
	}
	
	public User getSender() {
		return sender;
	}
	
	public void setReciever(LinkedList<User> recievers){
		this.recievers = recievers;
	}
	
	public LinkedList<User> getRecievers(){
		return recievers;
	}

	public String getKey() {
		return key;
	}
}
