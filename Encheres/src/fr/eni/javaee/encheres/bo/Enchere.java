/**
 *
 */
package fr.eni.javaee.encheres.bo;

import java.util.Date;

/**
 * @author amorf
 *
 * Enchere des articles enchérit
 * par des utilisateurs
 */
public class Enchere {

	private Date dateEnchere;
	private int montantEnchere;

	private Utilisateur unUtilisateur;
	private ArticleVendu unArticleVendu;


	public Enchere() {

	}
	public Enchere(Date dateEnchere, int montantEnchere) {
		this();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enchere [dateEnchere=");
		builder.append(dateEnchere);
		builder.append(", montantEnchere=");
		builder.append(montantEnchere);
		builder.append("]");
		return builder.toString();
	}
	public Date getDateEnchere() {
		return dateEnchere;
	}
	public void setDateEnchere(Date dateEnchere) {
		this.dateEnchere = dateEnchere;
	}
	public int getMontantEnchere() {
		return montantEnchere;
	}
	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}
	public Utilisateur getUnUtilisateur() {
		return unUtilisateur;
	}

	public ArticleVendu getUnArticleVendu() {
		return unArticleVendu;
	}

}
