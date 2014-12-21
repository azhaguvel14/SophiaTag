package edu.ntust.csie.se.mdfk.sophiatag.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class IdentityAuthorizer {
	private static final String HASH_ARGORITHOM = "SHA-1";
	
	private IdentityAuthorizer() {
		
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public static boolean authorize(String account, String rawPassword, User user) {
		return account.equals(user.getAccount()) && hashPassword(rawPassword).equals(user.getHashedPassword());	
	}
	
	private static String hashPassword(String rawPassword) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance(HASH_ARGORITHOM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		byte[] message = digest.digest(rawPassword.getBytes());
		
		return convertByteMessageToString(message);
	}
	
	private static String convertByteMessageToString(byte[] message) {
		StringBuffer buffer = new StringBuffer();
		for (byte b: message) {
			buffer.append(String.format("%02x", 0xff & b));
		}
		return buffer.toString();
	}
}

