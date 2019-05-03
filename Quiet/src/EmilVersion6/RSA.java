package EmilVersion6;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.util.Base64;

import javax.crypto.Cipher;

/**
 * 
 * @author Emil Dahl 2019-05.-01
 *
 */

public class RSA {
	
	private String text;
	private GUIClient guiC;
	
	
	public RSA (String text) {
		this.text = guiC.getMessage();
	}
	
	
	/**
	 * Method that generates a keyPair
	 * @return
	 * @throws Exception
	 */
	public KeyPair generateKeyPair() throws Exception {
	    KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
	    generator.initialize(2048, new SecureRandom());
	    KeyPair pair = generator.generateKeyPair();

	    return pair;
	}
	
	
	/**
	 * Method that takes in a message and a public key and returns the same message but encrypted.
	 * @param message
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String message, PublicKey publicKey) throws Exception {
	    Cipher encryptCipher = Cipher.getInstance("RSA");
	    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

	    byte[] cipherText = encryptCipher.doFinal(message.getBytes("UTF_8"));

	    return Base64.getEncoder().encodeToString(cipherText);
	}
	
	
	/**
	 * Method that takes in a encrypted message and a privateKey, return the decrypted message.
	 * @param encryptedMSG
	 * @param privateKey
	 * @return 
	 * @throws Exception
	 */
	
	public static String decrypt(String encryptedMSG, PrivateKey privateKey) throws Exception {
	    byte[] bytes = Base64.getDecoder().decode(encryptedMSG);

	    Cipher decriptCipher = Cipher.getInstance("RSA");
	    decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

	    return new String(decriptCipher.doFinal(bytes), "UTF_8");
	}
	
	/**
	 * Method that the sender needs to "sign" the message to make sure that the person who made the privateKey is the actual sender
	 * @param message
	 * @param privateKey
	 * @return
	 * @throws Exception
	 */
	public static String sign(String message, PrivateKey privateKey) throws Exception {
	    Signature privateSignature = Signature.getInstance("SHA256withRSA");
	    privateSignature.initSign(privateKey);
	    privateSignature.update(message.getBytes("UTF_8"));

	    byte[] signature = privateSignature.sign();

	    return Base64.getEncoder().encodeToString(signature);
	}
	/**
	 * Method that verifies the message, checks the signature made in "sign" method, this method runs before you decrypt a received message
	 * @param plainText
	 * @param signature
	 * @param publicKey
	 * @return
	 * @throws Exception
	 */
	
	public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
	    Signature publicSignature = Signature.getInstance("SHA256withRSA");
	    publicSignature.initVerify(publicKey);
	    publicSignature.update(plainText.getBytes("UTF_8"));

	    byte[] signatureBytes = Base64.getDecoder().decode(signature);

	    return publicSignature.verify(signatureBytes);
	}
	
	

}
