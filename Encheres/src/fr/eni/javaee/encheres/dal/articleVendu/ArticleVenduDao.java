package fr.eni.javaee.encheres.dal.articleVendu;

import java.util.List;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;

public interface ArticleVenduDao {

	ArticleVendu insertArticleVendu(ArticleVendu articleVendu);
	
	List<ArticleVendu> select(String extrait);
	List<ArticleVendu> select(Categorie categorie);
	List<ArticleVendu> select(String extrait, Categorie categorie);
	
	List<ArticleVendu> selectVentes(int noUtilisateur);
	List<ArticleVendu> selectVentes(int noUtilisateur, String extrait);
	List<ArticleVendu> selectVentes(int noUtilisateur, Categorie categorie);
	List<ArticleVendu> selectVentes(int noUtilisateur, String extrait, Categorie categorie);
	
	List<ArticleVendu> selectVentesEnCours(int noUtilisateur);
	List<ArticleVendu> selectVentesEnCours(int noUtilisateur, String extrait);
	List<ArticleVendu> selectVentesEnCours(int noUtilisateur, Categorie categorie);
	List<ArticleVendu> selectVentesEnCours(int noUtilisateur, String extrait, Categorie categorie);
	
	List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur);
	List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur, String extrait);
	List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur, Categorie categorie);
	List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur, String extrait, Categorie categorie);
	
	List<ArticleVendu> selectVentesTerminees(int noUtilisateur);
	List<ArticleVendu> selectVentesTerminees(int noUtilisateur, String extrait);
	List<ArticleVendu> selectVentesTerminees(int noUtilisateur, Categorie categorie);
	List<ArticleVendu> selectVentesTerminees(int noUtilisateur, String extrait, Categorie categorie);

	List<ArticleVendu> selectAchats(int noUtilisateur);
	List<ArticleVendu> selectAchats(int noUtilisateur, String extrait);
	List<ArticleVendu> selectAchats(int noUtilisateur, Categorie categorie);
	List<ArticleVendu> selectAchats(int noUtilisateur, String extrait, Categorie categorie);
	
	List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur);
	List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur, String extrait);
	List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur, Categorie categorie);
	List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur, String extrait, Categorie categorie);
	
	List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur);
	List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur, String extrait);
	List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur, Categorie categorie);
	List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur, String extrait, Categorie categorie);
	
	List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur);
	List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur, String extrait);
	List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur, Categorie categorie);
	List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur, String extrait, Categorie categorie);
	
	ArticleVendu updateArticleVendu(int noArticle, ArticleVendu articleVendu);
	
	ArticleVendu delete(ArticleVendu articleVendu);
	
	
}
