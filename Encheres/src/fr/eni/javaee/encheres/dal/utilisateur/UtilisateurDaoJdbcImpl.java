package fr.eni.javaee.encheres.dal.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.ConnectionProvider;

public class UtilisateurDaoJdbcImpl implements UtilisateurDao{

	private static final String INSERT = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)"
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo=?, nom=?, prenom=?, email=?, telephone=?, rue=?, code_postal=?, "
			+ "ville=?, mot_de_passe=?, credit=?, administrateur=? WHERE no_utilisateur = ?";
	
	private static final String SELECT_VALID_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE pseudo = ?";
	
	private static final String SELECT_VALID_MDP = "SELECT * FROM UTLISATEURS WHERE pseudo = ? AND mot_de_passe = ?";
	


	@Override
	public Utilisateur insertUtilisateur(Utilisateur utilisateur) {

		if (utilisateur == null) {
			System.out.println("TO DO : gestion erreurs");
		} else {
			
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				
				cnx.setAutoCommit(false);

				try {
					PreparedStatement pStmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
					pStmt.setString(1, utilisateur.getPseudo());
					pStmt.setString(2, utilisateur.getNom());
					pStmt.setString(3, utilisateur.getPrenom());
					pStmt.setString(4, utilisateur.getEmail());
					pStmt.setString(5, utilisateur.getTelephone());
					pStmt.setString(6, utilisateur.getRue());
					pStmt.setString(7, utilisateur.getCodePostal());
					pStmt.setString(8, utilisateur.getVille());
					pStmt.setString(9, utilisateur.getMotDePasse());
					pStmt.setInt(10, utilisateur.getCredit());
					pStmt.setByte(11, (byte)(utilisateur.isAdministrateur()?1:0));
					
					int nbLigneInseree = pStmt.executeUpdate();
					
					if (nbLigneInseree == 0) {
						System.out.println("TO DO : gestion erreurs");
						//Voir DaoRepas pour exemple erreurs
					}
					
					//on récupère l'id généré
					ResultSet rs = pStmt.getGeneratedKeys();
					if(rs.next()) {
						utilisateur.setNoUtilisateur(rs.getInt(1));
					}
					cnx.commit(); //on valide
				} catch (Exception e) {
					cnx.rollback(); //on annule tout si problème
					System.out.println("TO DO : gestion erreurs");
					e.printStackTrace();
				}
			} catch (SQLException e) {
				System.out.println("TO DO : gestion erreurs");
				e.printStackTrace();
			}
		}
			return utilisateur;
	}


	@Override
	public Utilisateur updateUtilisateur(int noUtilisateur, Utilisateur utilisateur) {
		if (utilisateur == null) {
			System.out.println("TO DO : gestion erreurs");
		} else {
			
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				
				cnx.setAutoCommit(false);

				try {
					PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
					pStmt.setString(1, utilisateur.getPseudo());
					pStmt.setString(2, utilisateur.getNom());
					pStmt.setString(3, utilisateur.getPrenom());
					pStmt.setString(4, utilisateur.getEmail());
					pStmt.setString(5, utilisateur.getTelephone());
					pStmt.setString(6, utilisateur.getRue());
					pStmt.setString(7, utilisateur.getCodePostal());
					pStmt.setString(8, utilisateur.getVille());
					pStmt.setString(9, utilisateur.getMotDePasse());
					pStmt.setInt(10, utilisateur.getCredit());
					pStmt.setByte(11, (byte)(utilisateur.isAdministrateur()?1:0));
					pStmt.setInt(12,  noUtilisateur);
					
					int nbLigneInseree = pStmt.executeUpdate();
					
					if (nbLigneInseree != 1) {
						System.out.println("TO DO : gestion erreurs");
						//Voir DaoRepas pour exemple erreurs
					}
					
					cnx.commit(); //on valide
				} catch (Exception e) {
					cnx.rollback(); //on annule tout si problème
					System.out.println("TO DO : gestion erreurs");
					e.printStackTrace();
				}
			} catch (SQLException e) {
				System.out.println("TO DO : gestion erreurs");
				e.printStackTrace();
			}
		}
			return utilisateur;
	}


	@Override
	public void deleteUtilisateur(int noUtilisateur) {
		//Avant de del l'utilisateur il faut recupérer la liste des no_articles
				//de ses ventes afin de del toutes les encheres les concernant.
		
		if (noUtilisateur == 0) {
			System.out.println("TO DO : gestion erreurs");
		} else {
			
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				
				cnx.setAutoCommit(false);

				try {
					
					//1- Recupération dans la liste lstIdArticles des id_articles de l'utilisateur ayant pour id noUtilisateur
					PreparedStatement pStmt = cnx.prepareStatement("SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?");
					pStmt.setInt(1, noUtilisateur);
					
					List<Integer> lstIdArticles = new ArrayList<>();
					ResultSet rs = pStmt.executeQuery();
					while (rs.next()) {
						lstIdArticles.add(rs.getInt("id_article"));
					}
		
					
					//2- Effacer de la table ENCHERES les enchères concernant ces articles ou l'utilisateur puis les articles vendus dans la table ARTICLES_VENDUS
					
					//Effacer de ENCHERES les encheres sur les articles vendus par l'utilisateur que l'on supprime
					for (int idArticle : lstIdArticles) {
						PreparedStatement pStmt2 = cnx.prepareStatement("DELETE FROM ENCHERES WHERE no_article = ?");
						pStmt2.setInt(1, idArticle);
						
						int nbLigneEffacee2 = pStmt2.executeUpdate();
					}
					
					//Effacer de ENCHERES les encheres faites par l'utilisateur que l'on supprime
					PreparedStatement pStmt3 = cnx.prepareStatement("DELETE FROM ENCHERES WHERE no_utilisateur = ?");
					pStmt3.setInt(1, noUtilisateur);
					int nbLigneEffacee3 = pStmt3.executeUpdate();
					
					//Effacer de ARTICLES_VENDUS les articles vendus par l'utilisateur que l'on supprime
					PreparedStatement pStmt4 = cnx.prepareStatement("DELETE FROM ARTICLES_VENDUS WHERE no_utilisateur = ?");
					pStmt4.setInt(1, noUtilisateur);
					int nbLigneEffacee4 = pStmt4.executeUpdate();
					
					//Effacer de UTILISATEURS l'utilisateur que l'on supprime
					PreparedStatement pStmt5 = cnx.prepareStatement("DELETE FROM UTILISATEURS WHERE no_utilisateur = ?");
					pStmt5.setInt(1, noUtilisateur);
					int nbLigneEffacee5 = pStmt5.executeUpdate();
					if (nbLigneEffacee5 != 1) {
						System.out.println("TO DO : gestion erreurs");
						//Voir DaoRepas pour exemple erreurs
					}
					
					cnx.commit(); //on valide
				} catch (Exception e) {
					cnx.rollback(); //on annule tout si problème
					System.out.println("TO DO : gestion erreurs");
					e.printStackTrace();
				}
			} catch (SQLException e) {
				System.out.println("TO DO : gestion erreurs");
				e.printStackTrace();
			}
		}
		
	}


	@Override
	public int connexionValide(String pseudo, String mdp) {
		int retour = 0;
				
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt1 = cnx.prepareStatement(SELECT_VALID_UTILISATEUR);
			pStmt1.setString(1, pseudo);
			PreparedStatement pStmt2 = cnx.prepareStatement(SELECT_VALID_MDP);
			pStmt2.setString(1, pseudo);
			pStmt2.setString(1, mdp);
			
			ResultSet rs1 = pStmt1.executeQuery();
			ResultSet rs2 = pStmt2.executeQuery();
			
			int nbLignes1 = 0;
			int nbLignes2 = 0;
			
			while (rs1.next()) {
				nbLignes1++;
			}
			
			while (rs2.next()) {
				nbLignes2++;
			}
			
			if (nbLignes1 >= 1) {
				retour++;
				if (nbLignes2 >= 1) {
					retour++;
				}
			}
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs - erreur connexionValide");
		}
		
		return retour;
	}


	
	
	
}
