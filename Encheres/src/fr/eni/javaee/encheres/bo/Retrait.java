/**
 *
 */
package fr.eni.javaee.encheres.bo;

/**
 * @author amorf
 *
 */
public class Retrait {
	private String rue;
	private String codePostal;
	private String ville;

	private ArticleVendu unArticleVendu;


	public Retrait() {
	}

	public Retrait(String codePostal, String ville) {
		this();
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public Retrait(String rue, String codePostal, String ville) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Retrait [rue=");
		builder.append(rue);
		builder.append(", codePostal=");
		builder.append(codePostal);
		builder.append(", ville=");
		builder.append(ville);
		builder.append("]");
		return builder.toString();
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public ArticleVendu getUnArticleVendu() {
		return unArticleVendu;
	}



}
