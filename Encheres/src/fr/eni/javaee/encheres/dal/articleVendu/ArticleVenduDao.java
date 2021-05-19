package fr.eni.javaee.encheres.dal.articleVendu;

import java.util.List;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;

public interface ArticleVenduDao {

	ArticleVendu insertArticleVendu(ArticleVendu articleVendu);
	
	List<ArticleVendu> select();
	List<ArticleVendu> select(String extrait);
	List<ArticleVendu> select(int categorie);
	List<ArticleVendu> select(String extrait, int categorie);
	
	List<ArticleVendu> selectVentes(int noUtilisateur);
	List<ArticleVendu> selectVentes(int noUtilisateur, String extrait);
	List<ArticleVendu> selectVentes(int noUtilisateur, int categorie);
	List<ArticleVendu> selectVentes(int noUtilisateur, String extrait, int categorie);
	
	List<ArticleVendu> selectVentesEnCours(int noUtilisateur);
	List<ArticleVendu> selectVentesEnCours(int noUtilisateur, String extrait);
	List<ArticleVendu> selectVentesEnCours(int noUtilisateur, int categorie);
	List<ArticleVendu> selectVentesEnCours(int noUtilisateur, String extrait, int categorie);
	
	List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur);
	List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur, String extrait);
	List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur, int categorie);
	List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur, String extrait, int categorie);
	
	List<ArticleVendu> selectVentesTerminees(int noUtilisateur);
	List<ArticleVendu> selectVentesTerminees(int noUtilisateur, String extrait);
	List<ArticleVendu> selectVentesTerminees(int noUtilisateur, int categorie);
	List<ArticleVendu> selectVentesTerminees(int noUtilisateur, String extrait, int categorie);

	List<ArticleVendu> selectAchats(int noUtilisateur);
	List<ArticleVendu> selectAchats(int noUtilisateur, String extrait);
	List<ArticleVendu> selectAchats(int noUtilisateur, int categorie);
	List<ArticleVendu> selectAchats(int noUtilisateur, String extrait, int categorie);
	
	List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur);
	List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur, String extrait);
	List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur, int categorie);
	List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur, String extrait, int categorie);
	
	List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur);
	List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur, String extrait);
	List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur, int categorie);
	List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur, String extrait, int categorie);
	
	List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur);
	List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur, String extrait);
	List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur, int categorie);
	List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur, String extrait, int categorie);
	
	ArticleVendu updateArticleVendu(int noArticle, ArticleVendu articleVendu);
	
	void deleteArticleVendu(int noArticle);
	
	
}
