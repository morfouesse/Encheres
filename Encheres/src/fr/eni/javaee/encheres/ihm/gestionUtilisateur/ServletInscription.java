package fr.eni.javaee.encheres.ihm.gestionUtilisateur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.javaee.encheres.bll.BllException;
import fr.eni.javaee.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.javaee.encheres.messages.LectureMessage;

/**
 * Servlet implementation class ServletInscription
 */
@WebServlet("/ServletInscription")
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String cp = request.getParameter("cp");
		String ville = request.getParameter("ville");
		String mdp = request.getParameter("mdp");

		UtilisateurManager um = new UtilisateurManager();
		// si il n'y pas pas d'erreur on continue vers la connection
		// sinon on revien sur l'inscription avec un ou pluieurs messages
		//d'erreur
		List<String> listeErreurs = new ArrayList<>();
		try {
			um.insertUtilisateur(pseudo, nom, prenom, email, telephone,
					 rue, cp, ville, mdp);
			this.getServletContext().getRequestDispatcher("/ServletConnexion").forward(request, response);
		} catch (BllException e) {
			for (Integer code : e.getListeCodesErreur()) {
				listeErreurs.add(LectureMessage.getMessageErreur(code));
			}
			request.setAttribute("listeErreurs", listeErreurs);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/inscription.jsp").forward(request, response);
		}


	}

}
