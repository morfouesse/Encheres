package fr.eni.javaee.encheres.ihm.gestionEncheres;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.encheres.bll.BllException;
import fr.eni.javaee.encheres.bll.articleVendu.ArticleVenduManager;
import fr.eni.javaee.encheres.bll.enchere.EnchereManager;
import fr.eni.javaee.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletEncherir
 */
@WebServlet("/ServletEncherir")
public class ServletEncherir extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletEncherir() {
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
		HttpSession session = request.getSession();
		int noUtilisateur = (int)session.getAttribute("idUtilisateur");
		
		UtilisateurManager um = new UtilisateurManager();
		Utilisateur unUtilisateur = um.selectUtilisateurById(noUtilisateur);
		
		ArticleVenduManager avm = new ArticleVenduManager();
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));
		ArticleVendu unArticleVendu = avm.selectArticleVenduById(noArticle);
		
		int montantEnchere = Integer.parseInt(request.getParameter("montantEnchere"));
		
		Date dateEnchere = new Date();
		
		Enchere enchere = new Enchere(dateEnchere, montantEnchere, unUtilisateur, unArticleVendu);
		
		EnchereManager em = new EnchereManager();
		em.insertEnchere(enchere);
		
		unArticleVendu.setPrixVente(montantEnchere);
		avm.updateArticleVendu(noArticle, unArticleVendu);
		try {
			um.updateUtilisateur(noUtilisateur, unUtilisateur.getPseudo(),unUtilisateur.getNom(), unUtilisateur.getPrenom(),
					unUtilisateur.getEmail(), unUtilisateur.getTelephone(), unUtilisateur.getRue(), unUtilisateur.getCodePostal(),
					unUtilisateur.getVille(), unUtilisateur.getMotDePasse(), unUtilisateur.getCredit());
		} catch (BllException e) {
			e.printStackTrace();
		}
		request.setAttribute("noUtilisateur", noUtilisateur);
		request.setAttribute("articleVendu", unArticleVendu);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/detailVente.jsp");
		rd.forward(request, response);
		
		
	}

}
