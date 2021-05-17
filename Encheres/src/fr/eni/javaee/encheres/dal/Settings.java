/**
 * 
 */
package fr.eni.javaee.encheres.dal;

import java.io.IOException;
import java.util.Properties;

/**
 * Classe en charge
 * @author mflechard2021
 * @version papeterie2 - v1.0
 * @date 19 avr. 2021 - 12:18:41
 */
public class Settings {
	private static Properties properties;
	
	static {
		properties = new Properties();
		try {
			properties.load(Settings.class.getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			System.out.println("Echec de la lecture de la configuration de l'application");
			e.printStackTrace();
			System.exit(-1); // Quitter le programme si on a pas réussi la configuration
		}
		
	}
	
	private Settings() {
		super();
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key);
	}
}
