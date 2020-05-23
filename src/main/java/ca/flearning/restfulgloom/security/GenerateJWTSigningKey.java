package ca.flearning.restfulgloom.security;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.KeyStoreException;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

/*******************************
 * At startup, either load a previously created JWT Signing key, or generate a new one
 *******************************/
@Component
public class GenerateJWTSigningKey implements ApplicationRunner{

	@Value("${ca.flearning.restfulgloom.api.security.jwtkeyfile}")
	private String JWT_KEY_FILE;

	private final int JWT_KEY_LEN = 32;

	@Autowired
	private ConfigurableApplicationContext ctx;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		createJWTSigningKey();
	}

	private void createJWTSigningKey() {
		try {
			BufferedReader br = new BufferedReader(new FileReader(JWT_KEY_FILE));
			String key = br.readLine();
			br.close();
			// check that they key is there and the right length
			if (key != null && Base64Utils.decodeFromString(key).length == JWT_KEY_LEN) {
				JWTToken.setSigningKey(key);
				System.out.println("    JWT signing key loaded from disk");
			} else {
				throw new KeyStoreException("JWT Signing key in invalid format.");
			}
		} catch (Exception e) {
			// something went wrong, let's create a new key
			File keyFile = new File(JWT_KEY_FILE);

			// delete it if it exists
			try {
				keyFile.delete();
			} catch (Exception ex) {
				// do nothing
			}

			// mkdir
			try {
				keyFile.getParentFile().mkdirs();
			} catch (Exception ex) {
				// do nothing
			}

			// create new key
			try {
				System.out.println("    Generating new JWT signing key");
				byte[] keybytes = new byte[JWT_KEY_LEN];
				SecureRandom.getInstanceStrong().nextBytes(keybytes);
				BufferedWriter writer = new BufferedWriter(new FileWriter(JWT_KEY_FILE));
				writer.write(Base64Utils.encodeToString(keybytes));
				writer.close();

				System.out.println("    JWT signing key written to "+JWT_KEY_FILE);
			} catch (Exception ex) {
				// This is fatal because it means we don't have a JWT signing key
				System.err.println("    FATAL: could not read or create a JWT signing key");
				System.err.println(ex);
				ctx.close();
			}
		}
	}
}
