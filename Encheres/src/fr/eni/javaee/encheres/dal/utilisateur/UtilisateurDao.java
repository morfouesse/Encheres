package fr.eni.javaee.encheres.dal.utilisateur;

import fr.eni.javaee.encheres.bo.Utilisateur;

public interface UtilisateurDao {

	Utilisateur insertUtilisateur(Utilisateur utilisateur);
	
	Utilisateur updateUtilisateur(int noUtilisateur, Utilisateur utilisateur);
	
	void deleteUtilisateur(int noUtilisateur);
	
	

	
}
