package EmilVersion6;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;


public class ClientController {
	private Client client;
	private GUIInlog uiI;
	private GUIClient uiC;
	private PublicKey publicKey;
	private PrivateKey privateKey;
	private KeyPair pair;
	private RSA rsa;
	
	public ClientController(Client client, GUIInlog uiI, GUIClient uiC) {
		this.client=client;
		this.uiI=uiI;
		this.uiC=uiC;
		client.setController(this);
		uiI.setController(this);
		uiC.setController(this);
	}
	public void createUser(String userName) {
		client.createUser(userName);
	}
	public LinkedList<User> getUserList() {
		return client.getUserList();
	}
	public void setUserList(LinkedList<User> userList) {
		uiC.setUserList(userList);
	}
	public GUIClient getClient() {
		return uiC;
	}
	public void setTextArea(String s) {
		System.out.println("11");
		uiC.setTextArea(s);
	}
	public void sendMessage() throws Exception {
		
		LinkedList<User> users=client.getUserList();
		LinkedList<User> selectedUsers=new LinkedList<User>();
		List<String> selectedU=uiC.getSelectedUsers();
		
		for(int i=0; i<selectedU.size(); i++) {
			for(int j=0; j<users.size(); j++) {
				if(selectedU.get(i)==users.get(j).getName()) {
					selectedUsers.add(users.get(j));
				}
			}
		}
		
		Message message= new Message(uiC.getMessage(), client.getUser(), selectedUsers);
		client.sendMessage(message);
	}
	
	public void getKeys() {
		try {
			
			pair = rsa.generateKeyPair();
			setKeys(pair);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void setKeys (KeyPair pair) {
		publicKey = pair.getPublic();
		privateKey = pair.getPrivate();
		client.setKeysClient( publicKey,  privateKey);
		}
	
	public boolean checkClientKeys () {
		publicKey = client.getPublicKey();
		privateKey = client.getPrivateKey();
		if(publicKey == null) {
			return false;
		} if(privateKey == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void deCryptMsg(String text) {
		client.decryptreceived(text );
	}
	
	public String getDecrypted () {
		return client.getDecrypted();
		}

	
	
}
