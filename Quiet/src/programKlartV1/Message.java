package programKlartV1;

import java.io.Serializable;
import java.util.LinkedList;
/**
 * Klass som hanterar ramverket f�r ett message objekt
 * @author Viktor och Kajsa 
 *
 */
public class Message implements Serializable{
	private String text;
	private User sender;
	private User reciever;
	/**
	 * Konstruktor som skapar ett message objekt
	 * @param text, meddelande
	 * @param sender, personen som skickade
	 * @param reciever, person som ska ta emot meddelandet
	 */
	public Message(String text, User sender, User reciever) {
		this.text=text;
		this.sender=sender;
		this.reciever=reciever;
	}
	/**
	 * metod som returnerar texten
	 * @return text
	 */
	public String getText() {
		return this.text;
	}
	/**
	 * Metod som s�tter texten
	 * @param txt
	 */
	public void setText(String txt) {
		this.text = txt;
	}
	/**
	 * Metod som ger sendern
	 * @return sender
	 */
	public User getSender() {
		return sender;
	}
	/**
	 * Metod som get reciever
	 * @return reciever
	 */
	public User getReciever() {
		return reciever;
	}
	
}
