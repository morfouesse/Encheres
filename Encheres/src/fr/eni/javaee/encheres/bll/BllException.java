/**
 *
 */
package fr.eni.javaee.encheres.bll;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amorf
 *
 */
public class BllException extends Exception{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private List<Integer> listeCodesErreur;

	public BllException() {
		listeCodesErreur = new ArrayList<Integer>();
	}

	public List<Integer> getListeCodesErreur() {
		return listeCodesErreur;
	}

	public void ajouterErreur(int codeErreur) {
		listeCodesErreur.add(codeErreur);
	}

	public boolean hasErreurs() {
		return !listeCodesErreur.isEmpty();
	}
}
