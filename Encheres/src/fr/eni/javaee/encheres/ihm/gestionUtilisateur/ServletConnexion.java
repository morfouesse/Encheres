package fr.eni.javaee.encheres.ihm.gestionUtilisateur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.encheres.bll.BllException;
import fr.eni.javaee.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.javaee.encheres.messages.LectureMessage;


@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ServletConnexion() {
        super();
        // TODO Auto-generated constructor
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");

		UtilisateurManager um = new UtilisateurManager();
		List<String> listeErreurs = new ArrayList<>();
		try {
			//on fait la connection
			um.Connection(pseudo, mdp);

			// l'utilisateur courrant
			sessionUtilisateur(pseudo, mdp, um, request);

			this.getServletContext().getRequestDispatcher("/ServletAccueil").forward(request, response);

			} catch (BllException e) {
			for (Integer code : e.getListeCodesErreur()) {
				listeErreurs.add(LectureMessage.getMessageErreur(code));
			}
			request.setAttribute("listeErreurs", listeErreurs);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);
		}
	}


	// l'utilisateur courrant
	private void sessionUtilisateur(String pseudo, String mdp, UtilisateurManager um, HttpServletRequest request) {

		/* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

		int idUtilisateurAccesPendantSession = um.getIdUtilisateur(pseudo, mdp);
		session.setAttribute("idUtilisateur", idUtilisateurAccesPendantSession);

	}

}
