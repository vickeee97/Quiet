package version3;

import java.io.Serializable;
/**
 * Klass som hanterar ramverket för en User
 * @author Viktor
 *
 */
public class User implements Serializable{
	private String userName;
	
	public User(String userName){
		this.userName = userName;
	}
	public String getName(){
		return userName;
	}

}
