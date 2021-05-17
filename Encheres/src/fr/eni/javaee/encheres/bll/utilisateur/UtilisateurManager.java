/**
 *
 */
package fr.eni.javaee.encheres.bll.utilisateur;


import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.DaoFactory;

/**
 * @author amorf
 *
 */
public class UtilisateurManager {

	public Utilisateur insertUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit) {

		verificationUtilisateur(pseudo, nom, prenom, email, telephone,
		 rue, codePostal, ville, motDePasse, credit);


		boolean administrateur = false;
		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone,
		 rue, codePostal, ville, motDePasse, credit, administrateur);

		return DaoFactory.getUtilisateurDao().insertUtilisateur(utilisateur);
	}

	private boolean verificationUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, int credit) {

		boolean verif = true;

		if(pseudo.equals(null)) {
			System.out.println("Erreur, pseudo est null");
			verif = false;
		}else if(pseudo.isBlank()) {
			System.out.println("Erreur, pseudo est vide, pas de charactere");
			verif = false;
		}
		if(nom.equals(null)) {
			System.out.println("Erreur, nom est null");
			verif = false;
		}else if(nom.isBlank()) {
			System.out.println("Erreur, nom est vide, pas de charactere");
			verif = false;
		}
		if(prenom.equals(null)) {
			System.out.println("Erreur, prenom est null");
			verif = false;
		}else if(prenom.isBlank()) {
			System.out.println("Erreur, prenom est vide, pas de charactere");
			verif = false;
		}
		if(email.equals(null)) {
			System.out.println("Erreur, email est null");
			verif = false;
		}else if(email.isBlank()) {
			System.out.println("Erreur, email est vide, pas de charactere");
			verif = false;
		}
		if(rue.equals(null)) {
			System.out.println("Erreur, rue est null");
			verif = false;
		}else if(rue.isBlank()) {
			System.out.println("Erreur, rue est vide, pas de charactere");
			verif = false;
		}
		if(codePostal.equals(null)) {
			System.out.println("Erreur, codePostal est null");
			verif = false;
		}else if(codePostal.isBlank()) {
			System.out.println("Erreur, codePostal est vide, pas de charactere");
			verif = false;
		}
		if(ville.equals(null)) {
			System.out.println("Erreur, ville est null");
			verif = false;
		}else if(ville.isBlank()) {
			System.out.println("Erreur, ville est vide, pas de charactere");
			verif = false;
		}
		if(motDePasse.equals(null)) {
			System.out.println("Erreur, motDePasse est null");
			verif = false;
		}else if(motDePasse.isBlank()) {
			System.out.println("Erreur, motDePasse est vide, pas de charactere");
			verif = false;
		}
		if(credit < 0 ) {
			System.out.println("Erreur, credit est infé à 0 ");
			verif = false;
		}

		return verif;
	}
}
