package fr.eni.javaee.encheres.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.encheres.bll.articleVendu.ArticleVenduManager;
import fr.eni.javaee.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.tools.OutilDate;

/**
 * Servlet implementation class ServletTest
 */
@WebServlet("/ServletTest")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



		String pseudo = "fred";
		String nom = "frederic";
		String prenom ="roger";
		String email = "truc@dd.com";
		String telephone = "07841630";
		String rue = "7 rue machin";
		String codePostal = "35500";
		String ville = "rennes";
		String motDePasse = "motDePasse";
		int credit = 50;

		UtilisateurManager um = new UtilisateurManager();

		um.insertUtilisateur(
				pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);


		/*String nomArticle = "nom1";
		String description = "descrip1";
		Date dateDebutEncheres = OutilDate.getDateFormatDate("01/01/2021");
		Date dateFinEncheres = OutilDate.getDateFormatDate("01/01/2022");
		int miseAPrix = 5;
		int prixVente = 0;
		Categorie uneCategorie;

		ArticleVenduManager am = new ArticleVenduManager().insertArticleVendu(
				nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente,
				unUtilisateur, uneCategorie);*/
	}

}
