package fr.eni.javaee.encheres.dal.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.ConnectionProvider;

public class UtilisateurDaoJdbcImpl implements UtilisateurDao{

	private static final String INSERT = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur)"
			+ "VALUES('?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String UPDATE = "UPDATE UTILISATEURS SET pseudo='?', nom='?', prenom='?', email='?', telephone='?', rue='?', code_postal='?', "
			+ "ville='?', mot_de_passe='?', credit='?', administrateur='?' WHERE no_utilisateur = ?";
	

	


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
		//TO DO: Avant de del l'utilisateur il faut recupérer la liste des no_articles
				//de ses ventes afin de del toutes les encheres les concernant.
		// peut on utiliser un selectVentes(noUtilisateur) de ArticleVenduDaoJdbcImpl ?
		
		if (noUtilisateur == 0) {
			System.out.println("TO DO : gestion erreurs");
		} else {
			
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				
				cnx.setAutoCommit(false);

				try {
					PreparedStatement pStmt = cnx.prepareStatement("SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?");
					pStmt.setInt(1, noUtilisateur);
					
					ResultSet rs = pStmt.executeQuery();
		
					// A CONTINUER
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
		
		//List<ArticleVendu> lstArticlesVendu = ArticleVenduDaoJdbcImpl.selectVentes(noUtilisateur);
		
	}


	
	
	
}
