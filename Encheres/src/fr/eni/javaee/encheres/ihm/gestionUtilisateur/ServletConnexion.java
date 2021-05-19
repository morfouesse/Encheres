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
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.messages.LectureMessage;


@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ServletConnexion() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String pseudo = request.getParameter("pseudo");
		String mdp = request.getParameter("mdp");

		UtilisateurManager um = new UtilisateurManager();
		List<String> listeErreurs = new ArrayList<>();
		try {
			um.Connection(pseudo, mdp);
			this.getServletContext().getRequestDispatcher("/ServletAccueil").forward(request, response);

		//	um.getIdUtilisateur(pseudo, mdp);
			} catch (BllException e) {
			for (Integer code : e.getListeCodesErreur()) {
				listeErreurs.add(LectureMessage.getMessageErreur(code));
			}
			request.setAttribute("listeErreurs", listeErreurs);
			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Connexion.jsp").forward(request, response);
		}
	}

}
