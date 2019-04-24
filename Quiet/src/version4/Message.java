package version4;

import java.io.Serializable;
import java.util.LinkedList;

public class Message implements Serializable{
	private String text;
	private User sender;
	private LinkedList<User> receivers= new LinkedList<User>();
	
	public Message(String text, User sender, LinkedList<User> receivers) {
		this.text=text;
		this.sender=sender;
		this.receivers=receivers;
	}
	public String getText() {
		return this.text;
	}
	public void setText(String txt) {
		this.text = txt;
	}
	public User getSender() {
		return sender;
	}
	public void setReceiver(LinkedList<User> receivers) {
		this.receivers = receivers;
	}
	public LinkedList<User> getReceivers() {
		return receivers;
	}

}
