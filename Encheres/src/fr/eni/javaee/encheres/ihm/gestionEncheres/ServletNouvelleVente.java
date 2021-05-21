package fr.eni.javaee.encheres.ihm.gestionEncheres;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.encheres.bll.articleVendu.ArticleVenduManager;
import fr.eni.javaee.encheres.bll.retrait.RetraitManager;
import fr.eni.javaee.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.categorie.CategorieManager;
import fr.eni.javaee.encheres.tools.OutilDate;


@WebServlet("/ServletNouvelleVente")
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ServletNouvelleVente() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/nouvelleVente.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int noUtilisateur = (int)session.getAttribute("idUtilisateur");
		ArticleVenduManager avm = new ArticleVenduManager();
		UtilisateurManager um = new UtilisateurManager();
		CategorieManager ct = new CategorieManager();
		RetraitManager rm = new RetraitManager();

		String nomArticle = request.getParameter("nomArticle");
		String description = request.getParameter("description");
		Date dateDebutEncheres = OutilDate.getDateFromHtml(request.getParameter("dateDebutEncheres"));
		Date dateFinEncheres = OutilDate.getDateFromHtml(request.getParameter("dateFinEncheres"));
		int miseAPrix = Integer.parseInt(request.getParameter("miseAPrix"));
		int prixVente = miseAPrix;
		Utilisateur unUtilisateur = um.selectUtilisateurById(noUtilisateur);
		Categorie uneCategorie = ct.selectCategorieById(Integer.parseInt(request.getParameter("categorie")));
		Retrait unRetrait = new Retrait(unUtilisateur.getRue(), unUtilisateur.getCodePostal(), unUtilisateur.getVille());

		//TEST
		//------------------
		System.out.println(unRetrait);
		//------------------
		
		boolean testChamps = (
				((request.getParameter("rue") == null) || (request.getParameter("rue").isBlank()))
				&& ((request.getParameter("codePostal") == null) || (request.getParameter("codePostal").isBlank()))
				&& ((request.getParameter("ville") == null) || (request.getParameter("ville").isBlank()))
				);
		
		//Gestion du Retrait s'il est différent de l'adresse de l'utilisateur
		if (!testChamps){
			System.out.println("un/des champs du retrait est/sont remplis"); // TEST
			unRetrait.setRue(request.getParameter("rue"));
			unRetrait.setCodePostal(request.getParameter("codePostal"));
			unRetrait.setVille(request.getParameter("ville"));
		}
		
		int noArticle = avm.insertArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres,
				miseAPrix, prixVente, unUtilisateur, uneCategorie).getNoArticle();

		ArticleVendu articleVendu = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, 
				miseAPrix, prixVente, unUtilisateur, uneCategorie);
		
		articleVendu.setUnRetrait(unRetrait);
		
		

		unRetrait.setNoRetrait(noArticle);

		//Insertion du retrait lié au nouveau ArticleVendu dans la BDD
		rm.insertRetrait(unRetrait);
		request.setAttribute("noUtilisateur", noUtilisateur);
		request.setAttribute("articleVendu", articleVendu);

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp").forward(request, response);
	}

}
