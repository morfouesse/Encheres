package fr.eni.javaee.encheres.dal;

import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.tools.OutilDate;

public class TestDal {

	public static void main(String args[]) {
		
		
		Categorie categorie1 = new Categorie("Vêtements");
		Categorie categorie2 = new Categorie("Meubles");
		categorie1.setNoCategorie(1);
		categorie2.setNoCategorie(2);
		
		//Insertion dans UTILISATEURS
		//---------------------------------------
		Utilisateur utilisateur1 = new Utilisateur("pseudo1", "nom1", "prenom1", "email1@domaine.com", "0111111111", "rue1", "11111", "ville1", "mdp1", 500, false);
		Utilisateur utilisateur2 = new Utilisateur("pseudo2", "nom2", "prenom2", "email2@domaine.com", "0122222222", "rue2", "22222", "ville2", "mdp2", 2500, false);
		Utilisateur utilisateur3 = new Utilisateur("pseudo3", "nom3", "prenom3", "email3@domaine.com", "0123333333", "rue3", "33333", "ville3", "mdp3", 40, true);

		List<Utilisateur> utilisateurs = new ArrayList<>();
		utilisateurs.add(utilisateur1);
		utilisateurs.add(utilisateur2);
		utilisateurs.add(utilisateur3);
		
		for (Utilisateur utilisateur : utilisateurs) { 
			DaoFactory.getUtilisateurDao().insertUtilisateur(utilisateur);
		}
		//---------------------------------------
		
		
		//Test de l'update utilisateur
		//---------------------------------------
		utilisateur3.setPseudo("newPseudo3");
		DaoFactory.getUtilisateurDao().updateUtilisateur(3, utilisateur3);
		//---------------------------------------
		
		
		//Insertion dans ARTICLES_VENDUS
		//---------------------------------------
		ArticleVendu articleVendu1 = new ArticleVendu("article1", "description1", OutilDate.getDateFormatDate("10/05/2021"),OutilDate.getDateFormatDate("09/06/2021"), 30, 0, utilisateur1, categorie1);
		ArticleVendu articleVendu2 = new ArticleVendu("article2", "description2", OutilDate.getDateFormatDate("01/02/2021"), OutilDate.getDateFormatDate("10/04/2021"), 100, 0, utilisateur2, categorie1);
		ArticleVendu articleVendu3 = new ArticleVendu("article3", "description3", OutilDate.getDateFormatDate("22/07/2021"), OutilDate.getDateFormatDate("13/08/2021"), 10, 0, utilisateur2, categorie2);
		
		List<ArticleVendu> articlesVendus = new ArrayList<>();
		articlesVendus.add(articleVendu1);
		articlesVendus.add(articleVendu2);
		articlesVendus.add(articleVendu3);
		
		for (ArticleVendu article : articlesVendus) { 
			DaoFactory.getArticleVenduDao().insertArticleVendu(article);
		}
		//---------------------------------------
		
		//Insertion dans ENCHERES
		//---------------------------------------
		Enchere enchere1 = new Enchere(OutilDate.getDateFormatDate("15/05/2021"), 600, utilisateur2, articleVendu1);
		DaoFactory.getEnchereDao().insertEnchere(enchere1);
		//---------------------------------------
		
		//Test de l'update articleVendu
		//---------------------------------------
		articleVendu3.setMiseAPrix(35);
		DaoFactory.getArticleVenduDao().updateArticleVendu(3, articleVendu3);
		//---------------------------------------
		
		//Test du delete articleVendu
		//---------------------------------------
		DaoFactory.getArticleVenduDao().deleteArticleVendu(2);
		//---------------------------------------
		
		
		//Test du delete utilisateur
		DaoFactory.getUtilisateurDao().deleteUtilisateur(3);
	}
	
}




