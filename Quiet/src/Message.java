/**
 * Klass som hanterar ramverket för ett message objekt.
 * @author Viktor
 *
 */
public class Message {
	private String text;
	private String key;

	public Message(String text, String key) {
		this.text = text;
		this.key = key;
	}

	public String getText() {
		return text;
	}

	public String getKey() {
		return key;
	}
}
