package fr.eni.javaee.encheres.ihm.gestionEncheres;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.encheres.bll.articleVendu.ArticleVenduManager;
import fr.eni.javaee.encheres.bo.ArticleVendu;


/**
 * Servlet implementation class ServletRechercheVente
 */
@WebServlet("/ServletRechercheVente")
public class ServletRechercheVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRechercheVente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		HttpSession session = request.getSession();
//		int noUtilisateur = (int)session.getAttribute("idUtilisateur");
		
		int noUtilisateur = 1;
		
		String extrait = request.getParameter("extrait");
		String categorieStr = request.getParameter("categorie");
		int categorie = Integer.parseInt(categorieStr);
		
		List<ArticleVendu> listeRetour = new ArrayList<>();
		
		ArticleVenduManager manager = new ArticleVenduManager();
		
		if (request.getParameter("choixAchatsVentes") == null) {
		//Affichage de tous les articles de la bdd (radio buttons "achats" et "ventes" pas sélectionnés)
			System.out.println("you are here");
			System.out.println(extrait);
			System.out.println(categorie);
			listeRetour = manager.select(extrait, categorie);
		} else if ("mesVentesCheked".equals(request.getParameter("mesVentes"))) {
		//Recherche dans "Ventes" uniquement ; selon quelle chekbox est sélectionnée
			if ("checked".equals(request.getParameter("mesVentesEnCours"))) {
				listeRetour = manager.selectVentesEnCours(noUtilisateur, extrait, categorie);
			} else if ("checked".equals(request.getParameter("ventesNonDebutees"))) {
				listeRetour = manager.selectVentesNonDebutees(noUtilisateur, extrait, categorie);
			}else if ("checked".equals(request.getParameter("ventesTerminees"))) {
				listeRetour = manager.selectVentesTerminees(noUtilisateur, extrait, categorie);
			} else {
			//Acune checkbox sélectionnée
				listeRetour = manager.selectVentes(noUtilisateur, extrait, categorie);
			}
		} else if ("achatsCheked".equals(request.getParameter("achats"))) {
		//Recherche dans "Achats" uniquement ; selon quelle chekbox est sélectionnée
			if ("checked".equals(request.getParameter("encheresOuvertes"))) {
				listeRetour = manager.selectAchatsEncheresOuvertes(noUtilisateur, extrait, categorie);
			} else if ("checked".equals(request.getParameter("mesEncheres"))) {
				listeRetour = manager.selectAchatsMesEncheres(noUtilisateur, extrait, categorie);
			}else if ("checked".equals(request.getParameter("mesEncheresRemportees"))) {
				listeRetour = manager.selectAchatsMesEncheresRemportees(noUtilisateur, extrait, categorie);
			} else {
			//Acune checkbox sélectionnée
				listeRetour = manager.selectAchats(noUtilisateur, extrait, categorie);
			}
		}
		
		System.out.println(listeRetour);
		request.setAttribute("listeRetour", listeRetour);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Accueil.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
