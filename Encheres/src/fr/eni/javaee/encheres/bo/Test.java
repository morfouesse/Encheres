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

		    String nomArticle1 = "art1";
			String description1 = "descrip1";
			Date dateDebutEncheres1 = OutilDate.getDateFormatDate("01/01/1998");

			Date dateFinEncheres1 = OutilDate.getDateFormatDate("01/01/2022");
			int miseAPrix1 = 5;
			int prixVente1 = 5;
			ArticleVendu a1 = new ArticleVendu(nomArticle1, description1, dateDebutEncheres1, dateFinEncheres1,
		 miseAPrix1, prixVente1);

			System.out.println(a1.toString());
	}

}
