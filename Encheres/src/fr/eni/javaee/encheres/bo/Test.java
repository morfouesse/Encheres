/**
 *
 */
package fr.eni.javaee.encheres.bo;

import java.util.Date;

import fr.eni.javaee.encheres.tools.OutilDate;

/**
 * @author amorf
 *
 * tester des éléments de la bo
 *
 */
public class Test {

	 public static void main (String[] args){


		 	int noUtilisateur = 1;
			String pseudo = "pseudo1";
			String nom = "nom1";
			String prenom = "prenom1";
			String email = "email1";
			String telephone = "07";
			String rue = "rue1";
			String codePostal = "codePostal1";
			String ville = "ville1";
			String motDePasse = "mdp1";
			int credit = 1;
			boolean administrateur = false;
			Utilisateur u1 = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone,
			rue, codePostal, ville, motDePasse, credit, administrateur);


		    String nomArticle1 = "art1";
			String description1 = "descrip1";
			Date dateDebutEncheres1 = OutilDate.getDateFormatDate("01/01/1998");
			Date dateFinEncheres1 = OutilDate.getDateFormatDate("01/01/2022");
			int miseAPrix1 = 5;
			int prixVente1 = 5;
			ArticleVendu a1 = new ArticleVendu(nomArticle1, description1, dateDebutEncheres1, dateFinEncheres1,
		    miseAPrix1, prixVente1);

			Date dateEnchere1 = OutilDate.getDateFormatDateHeure("01/01/1998 12:30:05");
			int montant1 = 10;

			Enchere e1 = new Enchere(dateEnchere1, montant1,u1, a1);

			System.out.println(a1.toString());
			System.out.println(e1.toString());
	}

}
