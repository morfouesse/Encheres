package fr.eni.javaee.encheres.ihm.gestionEncheres;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.encheres.bll.articleVendu.ArticleVenduManager;

/**
 * Servlet implementation class SupprVente
 */
@WebServlet("/ServletSupprVente")
public class ServletSupprVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSupprVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		int noUtilisateur = (int) session.getAttribute("idUtilisateur");
		
		int noArticle = Integer.parseInt(request.getParameter("noArticle"));

		new ArticleVenduManager().deleteArticleVendu(noArticle);
		this.getServletContext().getRequestDispatcher("/ServletAccueil").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
