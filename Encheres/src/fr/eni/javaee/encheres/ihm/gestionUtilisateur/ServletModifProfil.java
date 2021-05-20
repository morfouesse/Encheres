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
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.messages.LectureMessage;


@WebServlet("/ServletModifProfil")
public class ServletModifProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public ServletModifProfil() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		int idUtilisateur = (int) session.getAttribute("idUtilisateur");
		UtilisateurManager um = new UtilisateurManager();
		Utilisateur u = um.selectUtilisateurById(idUtilisateur);

		request.setAttribute("pseudo", u.getPseudo());
		request.setAttribute("nom", u.getNom());
		request.setAttribute("prenom", u.getPrenom());
		request.setAttribute("mail", u.getEmail());
		request.setAttribute("telephone", u.getTelephone());
		request.setAttribute("rue", u.getRue());
		request.setAttribute("cp", u.getCodePostal());
		request.setAttribute("ville", u.getVille());
		request.setAttribute("mdp", u.getMotDePasse());

		this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/modifierProfil.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession session = request.getSession();
		int idUtilisateur = (int) session.getAttribute("idUtilisateur");
		UtilisateurManager um = new UtilisateurManager();
		Utilisateur u = um.selectUtilisateurById(idUtilisateur);

		String pseudo = u.getPseudo();
		String nom = u.getNom();
		String prenom = u.getPrenom();
		String email = u.getEmail();
		String telephone = u.getEmail();
		String rue = u.getEmail();
		String cp = u.getCodePostal();
		String ville = u.getVille();
		String mdp = u.getMotDePasse();
		int credit = u.getCredit();
		if(request.getParameter("pseudo") != null) pseudo = request.getParameter("pseudo");

		if(request.getParameter("nom") != null) nom = request.getParameter("nom");

		if(request.getParameter("prenom") != null) prenom = request.getParameter("prenom");

		if(request.getParameter("mail") != null) email = request.getParameter("mail");

		if(request.getParameter("telephone") != null) telephone = request.getParameter("telephone");

		if(request.getParameter("rue") != null) rue = request.getParameter("rue");

		if(request.getParameter("cp") != null) cp = request.getParameter("cp");

		if(request.getParameter("ville") != null) ville = request.getParameter("ville");

		if(request.getParameter("mdp") != null) mdp = request.getParameter("mdp");


		// si il n'y pas pas d'erreur on continue vers la connection
		// sinon on revien sur l'inscription avec un ou pluieurs messages
		//d'erreur
		List<String> listeErreurs = new ArrayList<>();
		try {

			um.updateUtilisateur(idUtilisateur ,pseudo, nom, prenom, email, telephone,
					 rue, cp, ville, mdp, credit);
			this.getServletContext().getRequestDispatcher("/ServletProfil").forward(request, response);
		} catch (BllException e) {
			for (Integer code : e.getListeCodesErreur()) {
				listeErreurs.add(LectureMessage.getMessageErreur(code));
			}
			request.setAttribute("listeErreurs", listeErreurs);



			request.setAttribute("pseudo", u.getPseudo());
			request.setAttribute("nom", u.getNom());
			request.setAttribute("prenom", u.getPrenom());
			request.setAttribute("mail", u.getEmail());
			request.setAttribute("telephone", u.getTelephone());
			request.setAttribute("rue", u.getRue());
			request.setAttribute("cp", u.getCodePostal());
			request.setAttribute("ville", u.getVille());
			request.setAttribute("mdp", u.getMotDePasse());

			this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/modifierProfil.jsp").forward(request, response);
		}
	}

}
