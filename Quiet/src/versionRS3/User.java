package versionRS3;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
/**
 * Klass som hanterar ramverket f�r en User
 * @author Viktor och Kajsa 
 *
 */
public class User implements Serializable{
	private String userName;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	/**
	 * Konstruktor som skapar ett User objekt
	 * @param userName
	 */
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
	/**
	 * Metod som skapar keypair, en privat och en publik nyckel
	 * @return returnerar keypair
	 * @throws NoSuchAlgorithmException
	 */
	public KeyPair buildKeyPair() throws NoSuchAlgorithmException {
	    int keySize = 1024;
	    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	    keyPairGenerator.initialize(keySize);
	    return keyPairGenerator.genKeyPair();
	}
	/**
	 * Metod som returnerar namnet p� usern
	 * @return userName
	 */
	public String getName(){
		return userName;
	}
	/**
	 * Metod som ger den publika nyckeln f�r usern
	 * @return publicKey
	 */
	public PublicKey getPublicKey() {
		return publicKey;
	}
	/**
	 * Metod som hanterar avkryptering av meddelande
	 * @param encrypted, str�ngen man avkrypterar
	 * @return returnerar byte[]
	 * @throws Exception
	 */
	public String decrypt(String encrypted) throws Exception {
		System.out.println("public key " + publicKey);
		byte[] klar= Base64.getDecoder().decode(encrypted);
	    Cipher cipher = Cipher.getInstance("RSA");
	    cipher.init(Cipher.DECRYPT_MODE, privateKey);
	    klar=cipher.doFinal(klar);

	    return new String(klar, "UTF-8");
	}
}
