package fr.eni.javaee.encheres.ihm.gestionUtilisateur;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.encheres.bll.utilisateur.UtilisateurManager;


@WebServlet("/ServletSupprProfil")
public class ServletSupprProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ServletSupprProfil() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession session = request.getSession();
		int noUtilisateur = (int) session.getAttribute("idUtilisateur");

		new UtilisateurManager().deleteUtilisateurById(noUtilisateur);
		session.invalidate();
		this.getServletContext().getRequestDispatcher("/ServletAccueil").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
