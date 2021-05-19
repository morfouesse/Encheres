/**
 *
 */
package fr.eni.javaee.encheres.bll.utilisateur;


import fr.eni.javaee.encheres.bll.BllException;
import fr.eni.javaee.encheres.bll.CodesErreuresBll;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.DaoFactory;

/**
 * @author antoine
 *
 */
public class UtilisateurManager {


	public Utilisateur insertUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse) throws BllException{

		BllException be = new BllException();
		verificationUtilisateur(pseudo, nom, prenom, email, telephone,
		 rue, codePostal, ville, motDePasse, be);

		if(be.hasErreurs()) {
			throw be;
		}


		boolean administrateur = false;
		// nb de credit pour tous les utilisateur apres inscription
		int credit = 100;
		Utilisateur utilisateur = new Utilisateur(pseudo, nom, prenom, email, telephone,
		 rue, codePostal, ville, motDePasse, credit, administrateur);

		return DaoFactory.getUtilisateurDao().insertUtilisateur(utilisateur);
	}


	public void Connection(String pseudo, String motDePasse) throws BllException{


		BllException be = new BllException();
		verificationUtilisateur(pseudo, motDePasse, be);


		if(DaoFactory.getUtilisateurDao().connexionValide(pseudo, motDePasse) == 0) {
			be.ajouterErreur(CodesErreuresBll.PSEUDO_CONNEXION_NEXISTE_PAS_ERREUR);
		}
		else if(DaoFactory.getUtilisateurDao().connexionValide(pseudo, motDePasse) == 1) {
			be.ajouterErreur(CodesErreuresBll.MOTDEPASSE_CONNEXION_NEXISTE_PAS_ERREUR);
		}


		if(be.hasErreurs()) {
			throw be;
		}
	}

	/*public int getIdUtilisateur(String pseudo, String motDePasse){


		return DaoFactory.getUtilisateurDao().connexionValide(pseudo, motDePasse);
	}*/

 	private void verificationUtilisateur(String pseudo, String motDePasse, BllException be) {
 		if(pseudo == null) {
			be.ajouterErreur(CodesErreuresBll.PSEUDO_NULL_ERREUR);
			System.out.println("Erreur, pseudo est null");

		}else if(pseudo.isBlank()) {
			be.ajouterErreur(CodesErreuresBll.PSEUDO_ISBLANK_ERREUR);
			System.out.println("Erreur, pseudo est vide, pas de charactere");
		}
 		if(motDePasse == null) {
			be.ajouterErreur(CodesErreuresBll.MOTDEPASSE_NULL_ERREUR);
			System.out.println("Erreur, motDePasse est null");

		}else if(motDePasse.isBlank()) {
			be.ajouterErreur(CodesErreuresBll.MOTDEPASSE_ISBLANK_ERREUR);
			System.out.println("Erreur, motDePasse est vide, pas de charactere");

		}
	}


	private void verificationUtilisateur(String pseudo, String nom, String prenom, String email, String telephone,
			String rue, String codePostal, String ville, String motDePasse, BllException be) {

		if(pseudo == null) {
			be.ajouterErreur(CodesErreuresBll.PSEUDO_NULL_ERREUR);
			System.out.println("Erreur, pseudo est null");

		}else if(pseudo.isBlank()) {
			be.ajouterErreur(CodesErreuresBll.PSEUDO_ISBLANK_ERREUR);
			System.out.println("Erreur, pseudo est vide, pas de charactere");

		}
		if(nom == null) {
			be.ajouterErreur(CodesErreuresBll.NOM_NULL_ERREUR);
			System.out.println("Erreur, nom est null");

		}else if(nom.isBlank()) {
			be.ajouterErreur(CodesErreuresBll.NOM_ISBLANK_ERREUR);
			System.out.println("Erreur, nom est vide, pas de charactere");

		}
		if(prenom == null) {
			be.ajouterErreur(CodesErreuresBll.PRENOM_NULL_ERREUR);
			System.out.println("Erreur, prenom est null");

		}else if(prenom.isBlank()) {
			be.ajouterErreur(CodesErreuresBll.PRENOM_ISBLANK_ERREUR);
			System.out.println("Erreur, prenom est vide, pas de charactere");

		}
		if(email == null) {
			be.ajouterErreur(CodesErreuresBll.EMAIL_NULL_ERREUR);
			System.out.println("Erreur, email est null");

		}else if(email.isBlank()) {
			be.ajouterErreur(CodesErreuresBll.EMAIL_ISBLANK_ERREUR);
			System.out.println("Erreur, email est vide, pas de charactere");

		}
		if(rue == null) {
			be.ajouterErreur(CodesErreuresBll.RUE_NULL_ERREUR);
			System.out.println("Erreur, rue est null");

		}else if(rue.isBlank()) {
			be.ajouterErreur(CodesErreuresBll.RUE_ISBLANK_ERREUR);
			System.out.println("Erreur, rue est vide, pas de charactere");

		}
		if(codePostal == null) {
			be.ajouterErreur(CodesErreuresBll.CODEPOSTAL_NULL_ERREUR);
			System.out.println("Erreur, codePostal est null");

		}else if(codePostal.isBlank()) {
			be.ajouterErreur(CodesErreuresBll.CODEPOSTAL_ISBLANK_ERREUR);
			System.out.println("Erreur, codePostal est vide, pas de charactere");

		}
		if(ville == null) {
			be.ajouterErreur(CodesErreuresBll.VILLE_NULL_ERREUR);
			System.out.println("Erreur, ville est null");

		}else if(ville.isBlank()) {
			be.ajouterErreur(CodesErreuresBll.VILLE_ISBLANK_ERREUR);
			System.out.println("Erreur, ville est vide, pas de charactere");

		}
		if(motDePasse == null) {
			be.ajouterErreur(CodesErreuresBll.MOTDEPASSE_NULL_ERREUR);
			System.out.println("Erreur, motDePasse est null");

		}else if(motDePasse.isBlank()) {
			be.ajouterErreur(CodesErreuresBll.MOTDEPASSE_ISBLANK_ERREUR);
			System.out.println("Erreur, motDePasse est vide, pas de charactere");

		}
	}
}
