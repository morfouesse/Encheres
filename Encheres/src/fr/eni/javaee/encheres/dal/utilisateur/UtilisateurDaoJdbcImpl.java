package fr.eni.javaee.encheres.dal.utilisateur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.ConnectionProvider;

public class UtilisateurDaoJdbcImpl implements UtilisateurDao{

	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	
	@Override
	public Utilisateur addUtilisateur(Utilisateur utilisateur) {

		if (utilisateur == null) {
			System.out.println("TO DO : gestion erreurs");
		} else {
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
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
				pStmt.setByte(11, (byte)(utilisateur.getAdministrateur() ? 1 : 0));
				
				int nbLigneInseree = pStmt.executeUpdate();
				
				if (nbLigneInseree == 0) {
					System.out.println("TO DO : gestion erreurs");
				}
				
				//on récupère l'id généré
				ResultSet rs = pStmt.getGeneratedKeys();
				if(rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}
			} catch (SQLException e) {
				System.out.println("TO DO : gestion erreurs");
				e.printStackTrace();
			}
		}
			return utilisateur;
	}


	
	
	
}
