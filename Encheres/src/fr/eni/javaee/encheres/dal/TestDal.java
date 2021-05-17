package fr.eni.javaee.encheres.dal;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Date;
import java.util.List;

import fr.eni.javaee.encheres.bo.ArticleVendu;

public class TestDal {

	public void main(String args[]) {
		
		
		
		
//		ArticleVendu articleVendu1 = new ArticleVendu("article1", "description1", new Date(2021-05-10), new Date(2021-06-09), 30, 0, 8, 2);
//		ArticleVendu articleVendu2 = new ArticleVendu("article2", "description2", new Date(2021/02/01), new Date(2021/04/10), 30, 0, 8, 2);
//		ArticleVendu articleVendu3 = new ArticleVendu("article3", "description3", new Date(22/07/2021), new Date(13/08/2021), 30, 0, 8, 2);
//		
//		List<ArticleVendu> articlesVendus = new ArrayList<>();
//		articlesVendus.add(articleVendu1);
//		articlesVendus.add(articleVendu2);
//		articlesVendus.add(articleVendu3);
//		
//		
//		
//		for (ArticleVendu article : articlesVendus) { 
//			DaoFactory.getArticleVenduDao().insertArticleVendu(article);
//		
//		}
//		
//		
		
		
		
	}
	
}
=======
import java.util.List;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.tools.OutilDate;

public class TestDal {

	public static void main(String args[]) {
		
		
		Categorie categorie1 = new Categorie("Vêtements");
		Categorie categorie2 = new Categorie("Meubles");
		//Categorie categorie3 = new Categorie("Véhicules");
		
		categorie1.setNoCategorie(1);
		categorie2.setNoCategorie(2);
		
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
		
		utilisateur3.setPseudo("newPseudo3");
		DaoFactory.getUtilisateurDao().updateUtilisateur(3, utilisateur3);
		
		
		ArticleVendu articleVendu1 = new ArticleVendu("article1", "description1", OutilDate.getDateFormatDate("10/05/2021"),OutilDate.getDateFormatDate("09/06/2021"), 30, 0, utilisateur1, categorie1);
		ArticleVendu articleVendu2 = new ArticleVendu("article2", "description2", OutilDate.getDateFormatDate("01/02/2021"), OutilDate.getDateFormatDate("10/04/2021"), 30, 0, utilisateur2, categorie1);
		ArticleVendu articleVendu3 = new ArticleVendu("article3", "description3", OutilDate.getDateFormatDate("22/07/2021"), OutilDate.getDateFormatDate("13/08/2021"), 30, 0, utilisateur2, categorie2);
		
		List<ArticleVendu> articlesVendus = new ArrayList<>();
		articlesVendus.add(articleVendu1);
		articlesVendus.add(articleVendu2);
		articlesVendus.add(articleVendu3);
		
		for (ArticleVendu article : articlesVendus) { 
			DaoFactory.getArticleVenduDao().insertArticleVendu(article);
		}
		
		
		
		
		
	}
	
}




>>>>>>> refs/heads/miseEnCommun
