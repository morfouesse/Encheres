/**
 *
 */
package fr.eni.javaee.encheres.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author amorf
 *
 * lesArticles venduent d'un user et
 *
 */
public class ArticleVendu {
	private int noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private int etatVente;

	private List<Enchere> enchereLst = new ArrayList<>();
	private Utilisateur unUtilisateur;
	private Categorie uneCategorie;
	private Retrait unRetrait;


	public ArticleVendu() {

	}

	public ArticleVendu(int noArticle) {
		this();
		this.noArticle = noArticle;
	}

	public ArticleVendu(String nomArticle, String description, Date dateDebutEncheres, Date dateFinEncheres,
			int miseAPrix, int prixVente, int etatVente, List<Enchere> enchereLst, Utilisateur unUtilisateur) {
		this();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.enchereLst = enchereLst;
		this.unUtilisateur = unUtilisateur;
	}
	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, int etatVente) {
		this(noArticle);
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
	}
	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, int etatVente, List<Enchere> enchereLst) {
		this(noArticle);
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.etatVente = etatVente;
		this.enchereLst = enchereLst;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticleVendu [noArticle=");
		builder.append(noArticle);
		builder.append(", nomArticle=");
		builder.append(nomArticle);
		builder.append(", description=");
		builder.append(description);
		builder.append(", dateDebutEncheres=");
		builder.append(dateDebutEncheres);
		builder.append(", dateFinEncheres=");
		builder.append(dateFinEncheres);
		builder.append(", miseAPrix=");
		builder.append(miseAPrix);
		builder.append(", prixVente=");
		builder.append(prixVente);
		builder.append(", etatVente=");
		builder.append(etatVente);
		builder.append(", enchereLst=");
		builder.append(enchereLst);
		builder.append("]");
		return builder.toString();
	}

	public void addEnchere(Enchere enchere){
		this.enchereLst.add(enchere);
	}

	public void removeEnchere(Enchere enchere){
		this.enchereLst.remove(enchere);
	}


	public int getNoArticle() {
		return noArticle;
	}
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	public String getNomArticle() {
		return nomArticle;
	}
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}
	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	public int getMiseAPrix() {
		return miseAPrix;
	}
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}
	public int getPrixVente() {
		return prixVente;
	}
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}
	public int getEtatVente() {
		return etatVente;
	}
	public void setEtatVente(int etatVente) {
		this.etatVente = etatVente;
	}
	public List<Enchere> getEnchereLst() {
		return enchereLst;
	}
	public void setEnchereLst(List<Enchere> enchereLst) {
		this.enchereLst = enchereLst;
	}
	public Utilisateur getUnUtilisateur() {
		return unUtilisateur;
	}

	public Categorie getUneCategorie() {
		return uneCategorie;
	}

	public Retrait getUnRetrait() {
		return unRetrait;
	}



}
