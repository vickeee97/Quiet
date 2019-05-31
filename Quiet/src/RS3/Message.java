package RS3;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Class that manages the framework for a message object
 * @author Viktor och Kajsa 
 *
 */

public class Message implements Serializable{
	private String text;
	private User sender;
	private User reciever;
	
	/**
	 * Constructor who creates a message object
	 * @param text, message
	 * @param sender, person who sent the message
	 * @param reciever, person who's recieving the message
	 */
	
	public Message(String text, User sender, User reciever) {
		this.text=text;
		this.sender=sender;
		this.reciever=reciever;
	}
	
	/**
	 * method that returns the text
	 * @return text
	 */
	
	public String getText() {
		return this.text;
	}
	
	/**
	 * Method that puts the text
	 * @param txt
	 */
	
	public void setText(String txt) {
		this.text = txt;
	}
	
	/**
	 * Method that returns the sender
	 * @return sender
	 */
	
	public User getSender() {
		return sender;
	}
	
	/**
	 * Method that returns the reciever
	 * @return reciever
	 */
	
	public User getReciever() {
		return reciever;
	}
}