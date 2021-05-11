/**
 *
 */
package fr.eni.javaee.encheres.bo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author amorf
 *
 */
public class Categorie {
	private int noCategorie;
	private String libelle;
	private List<ArticleVendu> articleVenduLst = new ArrayList<>();


	public Categorie() {
	}

	public Categorie(int noCategorie) {
		this();
		this.noCategorie = noCategorie;
	}
	public Categorie(String libelle, List<ArticleVendu> articleVenduLst) {
		this();
		this.libelle = libelle;
		this.articleVenduLst = articleVenduLst;
	}



	public Categorie(int noCategorie, String libelle) {
		this(noCategorie);
		this.libelle = libelle;
	}

	public Categorie(int noCategorie, String libelle, List<ArticleVendu> articleVenduLst) {
		this(noCategorie);

		this.libelle = libelle;
		this.articleVenduLst = articleVenduLst;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categorie [noCategorie=");
		builder.append(noCategorie);
		builder.append(", libelle=");
		builder.append(libelle);
		builder.append(", articleVenduLst=");
		builder.append(articleVenduLst);
		builder.append("]");
		return builder.toString();
	}

	public void addArticleVendu(ArticleVendu articleVendu){
		this.articleVenduLst.add(articleVendu);
	}

	public void removeArticleVendu(ArticleVendu articleVendu){
		this.articleVenduLst.remove(articleVendu);
	}


	public int getNoCategorie() {
		return noCategorie;
	}
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public List<ArticleVendu> getArticleVenduLst() {
		return articleVenduLst;
	}
	public void setArticleVenduLst(List<ArticleVendu> articleVenduLst) {
		this.articleVenduLst = articleVenduLst;
	}



}
