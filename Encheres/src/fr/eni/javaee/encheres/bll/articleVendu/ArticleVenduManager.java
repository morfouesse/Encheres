/**
 *
 */
package fr.eni.javaee.encheres.bll.articleVendu;

import java.util.ArrayList;
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
	
	
	public List<ArticleVendu> select(String extrait, int categorie){
		List<ArticleVendu> retour = new ArrayList<>();
		
		if ((extrait == null) || (extrait.isBlank())&&(categorie == 0)) {
			retour = DaoFactory.getArticleVenduDao().select();
		} else if (categorie == 0) {
			retour = DaoFactory.getArticleVenduDao().select(extrait);
		} else {
			retour = DaoFactory.getArticleVenduDao().select(categorie);
		}
		
		return retour;
	}
	
	public List<ArticleVendu> selectVentes(int noUtilisateur, String extrait, int categorie){
		List<ArticleVendu> retour = new ArrayList<>();
		
		if (((extrait == null) || (extrait.isBlank()))&&(categorie ==0)) {
			retour = DaoFactory.getArticleVenduDao().selectVentes(noUtilisateur);
		} else if (categorie == 0) {
			retour = DaoFactory.getArticleVenduDao().selectVentes(noUtilisateur, extrait);
		} else if ((extrait == null) || (extrait.isBlank())){
			retour = DaoFactory.getArticleVenduDao().selectVentes(noUtilisateur, categorie);
		} else {
			retour = DaoFactory.getArticleVenduDao().selectVentes(noUtilisateur, extrait, categorie);
		}
		return retour;
	}
	
	public List<ArticleVendu> selectVentesEnCours(int noUtilisateur, String extrait, int categorie){
		List<ArticleVendu> retour = new ArrayList<>();
		
		if (((extrait == null) || (extrait.isBlank()))&&(categorie ==0)) {
			System.out.println("ICI4");
			retour = DaoFactory.getArticleVenduDao().selectVentesEnCours(noUtilisateur);
		} else if (categorie == 0) {
			System.out.println("ICI5");
			retour = DaoFactory.getArticleVenduDao().selectVentesEnCours(noUtilisateur, extrait);
		} else if ((extrait == null) || (extrait.isBlank())){
			System.out.println("ICI6");
			retour = DaoFactory.getArticleVenduDao().selectVentesEnCours(noUtilisateur, categorie);
		} else {
			System.out.println("ICI7");
			retour = DaoFactory.getArticleVenduDao().selectVentesEnCours(noUtilisateur, extrait, categorie);
		}
		return retour;
	}
	
	public List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur, String extrait, int categorie){
		List<ArticleVendu> retour = new ArrayList<>();
		
		if (((extrait == null) || (extrait.isBlank()))&&(categorie ==0)) {
			retour = DaoFactory.getArticleVenduDao().selectVentesNonDebutees(noUtilisateur);
		} else if (categorie == 0) {
			retour = DaoFactory.getArticleVenduDao().selectVentesNonDebutees(noUtilisateur, extrait);
		} else if ((extrait == null) || (extrait.isBlank())){
			retour = DaoFactory.getArticleVenduDao().selectVentesNonDebutees(noUtilisateur, categorie);
		} else {
			retour = DaoFactory.getArticleVenduDao().selectVentesNonDebutees(noUtilisateur, extrait, categorie);
		}
		return retour;
	}
	
	public List<ArticleVendu> selectVentesTerminees(int noUtilisateur, String extrait, int categorie){
		List<ArticleVendu> retour = new ArrayList<>();
		
		if (((extrait == null) || (extrait.isBlank()))&&(categorie ==0)) {
			retour = DaoFactory.getArticleVenduDao().selectVentesTerminees(noUtilisateur);
		} else if (categorie == 0) {
			retour = DaoFactory.getArticleVenduDao().selectVentesTerminees(noUtilisateur, extrait);
		} else if ((extrait == null) || (extrait.isBlank())){
			retour = DaoFactory.getArticleVenduDao().selectVentesTerminees(noUtilisateur, categorie);
		} else {
			retour = DaoFactory.getArticleVenduDao().selectVentesTerminees(noUtilisateur, extrait, categorie);
		}
		return retour;
	}
	
	public List<ArticleVendu> selectAchats(int noUtilisateur, String extrait, int categorie){
		List<ArticleVendu> retour = new ArrayList<>();
		
		if (((extrait == null) || (extrait.isBlank()))&&(categorie ==0)) {
			retour = DaoFactory.getArticleVenduDao().selectAchats(noUtilisateur);
		} else if (categorie == 0) {
			retour = DaoFactory.getArticleVenduDao().selectAchats(noUtilisateur, extrait);
		} else if ((extrait == null) || (extrait.isBlank())){
			retour = DaoFactory.getArticleVenduDao().selectAchats(noUtilisateur, categorie);
		} else {
			retour = DaoFactory.getArticleVenduDao().selectAchats(noUtilisateur, extrait, categorie);
		}
		return retour;
	}
	
	public List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur, String extrait, int categorie){
		List<ArticleVendu> retour = new ArrayList<>();
		
		if (((extrait == null) || (extrait.isBlank()))&&(categorie ==0)) {
			retour = DaoFactory.getArticleVenduDao().selectAchatsEncheresOuvertes(noUtilisateur);
		} else if (categorie == 0) {
			retour = DaoFactory.getArticleVenduDao().selectAchatsEncheresOuvertes(noUtilisateur, extrait);
		} else if ((extrait == null) || (extrait.isBlank())){
			retour = DaoFactory.getArticleVenduDao().selectAchatsEncheresOuvertes(noUtilisateur, categorie);
		} else {
			retour = DaoFactory.getArticleVenduDao().selectAchatsEncheresOuvertes(noUtilisateur, extrait, categorie);
		}
		return retour;
	}
	
	public List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur, String extrait, int categorie){
		List<ArticleVendu> retour = new ArrayList<>();
		
		if (((extrait == null) || (extrait.isBlank()))&&(categorie ==0)) {
			retour = DaoFactory.getArticleVenduDao().selectAchatsMesEncheres(noUtilisateur);
		} else if (categorie == 0) {
			retour = DaoFactory.getArticleVenduDao().selectAchatsMesEncheres(noUtilisateur, extrait);
		} else if ((extrait == null) || (extrait.isBlank())){
			retour = DaoFactory.getArticleVenduDao().selectAchatsMesEncheres(noUtilisateur, categorie);
		} else {
			retour = DaoFactory.getArticleVenduDao().selectAchatsMesEncheres(noUtilisateur, extrait, categorie);
		}
		return retour;
	}
	
	public List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur, String extrait, int categorie){
		List<ArticleVendu> retour = new ArrayList<>();
		
		if (((extrait == null) || (extrait.isBlank()))&&(categorie ==0)) {
			retour = DaoFactory.getArticleVenduDao().selectAchatsMesEncheresRemportees(noUtilisateur);
		} else if (categorie == 0) {
			retour = DaoFactory.getArticleVenduDao().selectAchatsMesEncheresRemportees(noUtilisateur, extrait);
		} else if ((extrait == null) || (extrait.isBlank())){
			retour = DaoFactory.getArticleVenduDao().selectAchatsMesEncheresRemportees(noUtilisateur, categorie);
		} else {
			retour = DaoFactory.getArticleVenduDao().selectAchatsMesEncheresRemportees(noUtilisateur, extrait, categorie);
		}
		return retour;
	}
	

	private boolean verificationArticle(String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, Utilisateur unUtilisateur, Categorie uneCategorie) {

		boolean verif = true;
		Date dateActuelle = new Date();

		if(nomArticle == null) {
			System.out.println("Erreur, nomArticle est null");
			verif = false;
		}else if(nomArticle.isBlank()) {
			System.out.println("Erreur, nomArticle est vide, pas de charactere");
			verif = false;
		}
		if(description == null) {
			System.out.println("Erreur, description est null");
			verif = false;
		}else if(description.isBlank()) {
			System.out.println("Erreur, description est vide, pas de charactere");
			verif = false;
		}
		if(dateDebutEncheres == null) {
			System.out.println("Erreur, dateDebutEncheres est null");
			verif = false;
		// si la dateDEbutEnchere est inférieur à la dateActuelle
		// il y a une erreur
		}/*else if(dateDebutEncheres.compareTo(dateActuelle) < 0) {
			System.out.println("Erreur, dateDebutEnchere est inférieur à la date actuelle ");
			verif = false;
		}*/
		if(dateFinEncheres == null) {
			System.out.println("Erreur, dateFinEncheres est null");
			verif = false;
		// si la dateFinEnchere est supérieur à la dateDEbutEnchere
		// il y a une erreur
		}/*else if(dateFinEncheres.compareTo(dateDebutEncheres) < 0) {
			System.out.println("Erreur, dateFinEncheres est inférieure à la date de début d'enchère ");
			verif = false;
		}*/
		if(miseAPrix <= 0 ) {
			System.out.println("Erreur, miseAPRix est infé ou égale à 0 ");
			verif = false;
		}
		if(prixVente < 0 ) {
			System.out.println("Erreur, prixVente est infé à 0 ");
			verif = false;
		}
		if(unUtilisateur == null) {
			System.out.println("Erreur, il y a pas d'utilisateur");
			verif = false;
		}
		if(uneCategorie == null) {
			System.out.println("Erreur, il y a pas de categorie");
			verif = false;
		}
		return verif;
	}


}
