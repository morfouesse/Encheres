/**
 *
 */
package fr.eni.javaee.encheres.bll.articleVendu;

import java.util.Date;
import java.util.List;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.DaoFactory;

/**
 * @author amorf
 *
 */
public class ArticleVenduManager {

	public ArticleVendu insertArticleVendu(String nomArticle, String description,
			Date dateDebutEncheres, Date dateFinEncheres, int miseAPrix, int prixVente,
			Utilisateur unUtilisateur, Categorie uneCategorie) {



		verificationArticle(nomArticle, description,
				dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente,
				unUtilisateur, uneCategorie);

		ArticleVendu articleVendu = new ArticleVendu(nomArticle, description,
				dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente,
				unUtilisateur, uneCategorie);

		System.out.println("ok");


		return DaoFactory.getArticleVenduDao().insertArticleVendu(articleVendu);
	}

	private boolean verificationArticle(String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, Utilisateur unUtilisateur, Categorie uneCategorie) {

		boolean verif = true;
		Date dateActuelle = new Date();

		if(nomArticle.equals(null)) {
			System.out.println("Erreur, nomArticle est null");
			verif = false;
		}else if(nomArticle.isBlank()) {
			System.out.println("Erreur, nomArticle est vide, pas de charactere");
			verif = false;
		}
		if(description.equals(null)) {
			System.out.println("Erreur, description est null");
			verif = false;
		}else if(description.isBlank()) {
			System.out.println("Erreur, description est vide, pas de charactere");
			verif = false;
		}
		if(dateDebutEncheres.equals(null)) {
			System.out.println("Erreur, dateDebutEncheres est null");
			verif = false;
		// si la dateDEbutEnchere est inférieur à la dateActuelle
		// il y a une erreur
		}else if(dateDebutEncheres.compareTo(dateActuelle) < 0) {
			System.out.println("Erreur, dateDebutEnchere est inférieur à la date actuelle ");
			verif = false;
		}
		if(dateFinEncheres.equals(null)) {
			System.out.println("Erreur, dateFinEncheres est null");
			verif = false;
		// si la dateFinEnchere est supérieur à la dateDEbutEnchere
		// il y a une erreur
		}else if(dateFinEncheres.compareTo(dateDebutEncheres) > 0) {
			System.out.println("Erreur, dateFinEncheres est supérieur à la date actuelle ");
			verif = false;
		}
		if(miseAPrix <= 0 ) {
			System.out.println("Erreur, miseAPRix est infé ou égale à 0 ");
			verif = false;
		}
		if(prixVente < 0 ) {
			System.out.println("Erreur, prixVente est infé à 0 ");
			verif = false;
		}
		if(unUtilisateur.equals(null)) {
			System.out.println("Erreur, il y a pas d'utilisateur");
			verif = false;
		}
		if(uneCategorie.equals(null)) {
			System.out.println("Erreur, il y a pas de categorie");
			verif = false;
		}
		return verif;
	}


}
