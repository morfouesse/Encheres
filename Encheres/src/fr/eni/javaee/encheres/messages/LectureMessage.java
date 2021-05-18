/**
 *
 */
package fr.eni.javaee.encheres.messages;

import java.util.ResourceBundle;

/**
 * @author amorf
 *
 */
public class LectureMessage {

	private static ResourceBundle rb;

	static {
		try {
			rb = ResourceBundle.getBundle("fr.eni.javaee.encheres.messages.messagesErreur");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getMessageErreur(int code) {
		String message = "";

		try {
			if(rb != null) {
				message = rb.getString(String.valueOf(code));
			} else {
				message = "Code erreur inexistant";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "Erreur inconnue : " + e.getMessage();
		}

		return message;
	}

}