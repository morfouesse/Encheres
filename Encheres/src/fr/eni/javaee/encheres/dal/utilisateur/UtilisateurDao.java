package fr.eni.javaee.encheres.dal.utilisateur;

import fr.eni.javaee.encheres.bo.Utilisateur;

public interface UtilisateurDao {

	Utilisateur insertUtilisateur(Utilisateur utilisateur);
	
	Utilisateur updateUtilisateur(int noUtilisateur, Utilisateur utilisateur);
	
	void deleteUtilisateur(int noUtilisateur);
	
	/**
	 * @param pseudo
	 * @param mdp
	 * @return 0: utilisateur inexistant - 1: mdp invalide - 2:connexion reussie
	 */
	int connexionValide(String pseudo, String mdp);
	
	/**
	 * @param pseudo
	 * @param mdp
	 * @return le noUtilisateur correspondant à la pair pseudo/mdp fournie en paramètres
	 */
	int selectNoUtilisateur(String pseudo, String mdp);
	
}
