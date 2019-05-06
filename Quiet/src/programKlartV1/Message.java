package programKlartV1;

import java.io.Serializable;
import java.util.LinkedList;

public class Message implements Serializable{
	private String text;
	private User sender;
	private User reciever;
	
	public Message(String text, User sender, User reciever) {
		this.text=text;
		this.sender=sender;
		this.reciever=reciever;
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
//	public void setReceiver(LinkedList<User> receivers) {
//		this.receivers = receivers;
//	}
//	public LinkedList<User> getReceivers() {
//		return receivers;
//	}
	public User getReciever() {
		return reciever;
	}
	
}
