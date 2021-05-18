/**
 *
 */
package fr.eni.javaee.encheres.bo;

import java.util.Date;

import fr.eni.javaee.encheres.tools.OutilDate;

/**
 * @author amorf
 *
 * Enchere des articles enchérit
 * par des utilisateurs
 */
public class Enchere {

	private Date dateEnchere;
	// equivaut au prix de vente d'une surenchere d'un ArticleVendu
	private int montantEnchere;

	private Utilisateur unUtilisateur;
	private ArticleVendu unArticleVendu;


	public Enchere() {

	}
	/*public Enchere(Date dateEnchere, int montantEnchere) {
		this();
		this.montantEnchere = montantEnchere;
	}*/

	public Enchere(Date dateEnchere, int montantEnchere, Utilisateur unUtilisateur, ArticleVendu unArticleVendu) {
		this();
		this.unUtilisateur = unUtilisateur;
		this.unArticleVendu = unArticleVendu;
		this.montantEnchere = montantEnchere;
		//changed by martin
		if(this.unArticleVendu.getEtatVente().equals(ArticleVendu.ETAT_VENTE_EN_COURS)) {
			this.dateEnchere = dateEnchere;
		}else {
			System.out.println("Erreur, la date d'enchere n'est pas compris entre la date de début d'enchere et celle"
					+ "de fin d'enchere");
			//TODO:exception
		}
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Enchere [dateEnchere=");
		builder.append(OutilDate.getStringFormatDateHeure(this.getDateEnchere()));
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
