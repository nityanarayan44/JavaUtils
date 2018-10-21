/**
 * @author Ashutosh Mishra
 */
// Package
package org.nng.utils.oauth.google;

// Import
import java.security.GeneralSecurityException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.nng.utils.oauth.google.Base32String;
import org.nng.utils.oauth.google.PasscodeGenerator;

// Class
public class Compute {

	// Computing PIN for Google A/c
	/**
	 * It compute the the google auth key for the provided secret key 
	 * @param secret
	 * @return
	 */
	public static String computePinFromSecret(String secret) {
		// Check for NULL of secret key
		if (secret == null || secret.length() == 0) {
			return "Null or empty secret";
		}
		// Compute the PIN
		try {
			
			// get the byte array for the Base32String of the given secret key
			final byte[] keyBytes = Base32String.decode(secret);
			
			// Get the Cryptography instance as "MHAC SHA1"
			Mac mac = Mac.getInstance("HMACSHA1");
			
			// Initialize the cryptography for the given secret key
			mac.init(new SecretKeySpec(keyBytes, ""));
			
			// Now, Generate the pass code from that cryptography instance
			PasscodeGenerator pcg = new PasscodeGenerator(mac);
			
			// Now, return the timeout code based on system time
			return pcg.generateTimeoutCode();
		} catch (GeneralSecurityException e) {
			return "General security exception";
		} catch (Base32String.DecodingException e) {
			return "Decoding exception";
		}
	}
	
} // EOClass
