/**
 *
 */
package fr.eni.javaee.encheres.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.javaee.encheres.tools.OutilDate;

/**
 * @author amorf
 *
 * lesArticles venduent d'un utilisateur et la gestions
 * des encheres pour chaque article
 *
 */
public class ArticleVendu {

	public static final String ETAT_VENTE_CREE = "CREE";
	public static final String ETAT_VENTE_EN_COURS = "EN_COURS";
	public static final String ETAT_VENTE_ENCHERES_TERMINEES = "ENCHERES_TERMINEES";
	//public static final String ETAT_VENTE_RETRAIT = "RETRAIT";

	private int noArticle;
	private String nomArticle;
	private String description;
	private Date dateDebutEncheres;
	private Date dateFinEncheres;
	private int miseAPrix;
	private int prixVente;
	private String etatVente = ArticleVendu.ETAT_VENTE_CREE;

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
			int miseAPrix, int prixVente) {
		this();
		this.nomArticle = nomArticle;
		this.description = description;
		this.miseAPrix = miseAPrix;
		this.prixVente = prixVente;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;

		Date dateActuelle = new Date();
		// si la date de début d'enchere est supéreur à la date actuelle
		//alors l'enchere n'est pas en cours
		if(this.dateDebutEncheres.compareTo(dateActuelle) < 0) {
			System.out.println("l'enchere est en cours ! ");
			this.setEtatVente(ArticleVendu.ETAT_VENTE_EN_COURS);
			System.out.println("etat : " + this.getEtatVente());
			if(this.dateFinEncheres.compareTo(dateActuelle) < 0) {

				System.out.println("l'enchere est terminé ! ");
				this.setEtatVente(ArticleVendu.ETAT_VENTE_ENCHERES_TERMINEES);
				System.out.println("etat : " + this.getEtatVente());
			}
		}
	}
	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente) {
		this(nomArticle, description, dateDebutEncheres, dateFinEncheres,
			 miseAPrix, prixVente);
		this.noArticle = noArticle;
	}


	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, Utilisateur unUtilisateur,
			Categorie uneCategorie) {
		this(noArticle, nomArticle, description, dateDebutEncheres,
			 dateFinEncheres, miseAPrix, prixVente);

		this.unUtilisateur = unUtilisateur;
		this.uneCategorie = uneCategorie;

		this.unRetrait = new Retrait(this.unUtilisateur.getRue(),this.unUtilisateur.getCodePostal(),
				this.unUtilisateur.getVille());

	}


	public ArticleVendu(int noArticle, String nomArticle, String description, Date dateDebutEncheres,
			Date dateFinEncheres, int miseAPrix, int prixVente, List<Enchere> enchereLst) {
		this(noArticle, nomArticle, description, dateDebutEncheres,
				 dateFinEncheres, miseAPrix, prixVente);
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
		builder.append(OutilDate.getStringFormatDate(this.getDateDebutEncheres()));
		builder.append(", dateFinEncheres=");
		builder.append(OutilDate.getStringFormatDate(this.getDateFinEncheres()));
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
	public String getEtatVente() {
		return etatVente;
	}
	public void setEtatVente(String etatVente) {
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

	public void setUnRetrait(Retrait unRetrait) {
		this.unRetrait = unRetrait;
	}



}
