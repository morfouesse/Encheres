package fr.eni.javaee.encheres.ihm.gestionUtilisateur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.javaee.encheres.bll.utilisateur.UtilisateurManager;
import fr.eni.javaee.encheres.bo.Utilisateur;


@WebServlet("/ServletProfil")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ServletProfil() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/profil.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();
        int noUtilisateur = (int)session.getAttribute("idUtilisateur");
        
        
        UtilisateurManager um = new UtilisateurManager();
        
        Utilisateur unUtilisateur = um.selectUtilisateurById(noUtilisateur);
        
        request.setAttribute("unUtilisateur", unUtilisateur);
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/profil.jsp");
        rd.forward(request, response);
        
	}
	

}
