package verison5kryptering;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;
/**
 * Klass som hanterar ramverket för en User
 * @author Viktor
 *
 */
public class User implements Serializable{
	private String userName;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	
	public User(String userName){
		this.userName = userName;
		KeyPair keyPair = null;
		try {
			keyPair = buildKeyPair();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
	}
	public KeyPair buildKeyPair() throws NoSuchAlgorithmException {
	    int keySize = 1024;
	    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	    keyPairGenerator.initialize(keySize);      
	    return keyPairGenerator.genKeyPair();
	}
	public String getName(){
		return userName;
	}
	public PublicKey getPublicKey() {
		return publicKey;
	}
	public static byte[] encrypt(PrivateKey privateKey, String message) throws Exception {
	    Cipher cipher = Cipher.getInstance("RSA");  
	    cipher.init(Cipher.ENCRYPT_MODE, privateKey);  

	    return cipher.doFinal(message.getBytes());  
	}
	    
	public static byte[] decrypt(PublicKey publicKey, byte [] encrypted) throws Exception {
	    Cipher cipher = Cipher.getInstance("RSA");  
	    cipher.init(Cipher.DECRYPT_MODE, publicKey);
	        
	    return cipher.doFinal(encrypted);
	}
//	public PrivateKey getPrivateKey() {
//		return privateKey;
//	}
//	public static void main(String[] args) {
//		User user= new User("Kajsa");
//		System.out.println(user.getName());
//		System.out.println("publika nyckeln: " + user.getPublicKey());
//		System.out.println("privata nyckeln: " + user.getPrivateKey());
//		
//		byte[] signed = null;
//		try {
//			signed = encrypt(user.getPrivateKey(), "Dipak är en bajskorv");
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		System.out.println("1: " + new String(signed));
//		byte[] verified = null;
//		try {
//			verified = decrypt(user.getPublicKey(), signed);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}                                 
//		System.out.println("2: " + new String(verified)); 
//	}
}
