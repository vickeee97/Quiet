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
 * Class that handles the framework for a User
 * @author Viktor och Kajsa 
 *
 */
public class User implements Serializable{
	private String userName;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	/**
	 * Constructor who creates a User object
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
	 * Method that creates keypair, a private and a public key
	 * @return return keypair
	 * @throws NoSuchAlgorithmException
	 */
	public KeyPair buildKeyPair() throws NoSuchAlgorithmException {
	    int keySize = 1024;
	    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	    keyPairGenerator.initialize(keySize);
	    return keyPairGenerator.genKeyPair();
	}
	/**
	 * Method that returns the name of the user
	 * @return userName
	 */
	public String getName(){
		return userName;
	}
	/**
	 * Method that gives the public key for user
	 * @return publicKey
	 */
	public PublicKey getPublicKey() {
		return publicKey;
	}
	/**
	 * Method that handles the decryption of the messages
	 * @param encrypted, the string that's going to get decrypted
	 * @return return byte[]
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
