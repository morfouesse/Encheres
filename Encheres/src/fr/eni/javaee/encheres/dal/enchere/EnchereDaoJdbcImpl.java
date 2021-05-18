package fr.eni.javaee.encheres.dal.enchere;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.javaee.encheres.bo.Enchere;
import fr.eni.javaee.encheres.dal.ConnectionProvider;

public class EnchereDaoJdbcImpl implements EnchereDao {
	
	private static final String INSERT = "INSERT INTO ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur)"
			+ "VALUES(?, ?, ?, ?)";
	

	@Override
	public Enchere insertEnchere(Enchere enchere) {
		if (enchere == null) {
			System.out.println("TO DO : gestion erreurs");
		} else {
			
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				
				cnx.setAutoCommit(false);

				try {
					PreparedStatement pStmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
					pStmt.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
					pStmt.setInt(2, enchere.getMontantEnchere());
					pStmt.setInt(3, enchere.getUnArticleVendu().getNoArticle());
					pStmt.setInt(4, enchere.getUnUtilisateur().getNoUtilisateur());
					
					int nbLigneInseree = pStmt.executeUpdate();
					
					if (nbLigneInseree == 0) {
						System.out.println("TO DO : gestion erreurs");
						//Voir DaoRepas pour exemple erreurs
					}
					
					//on récupère l'id généré
					ResultSet rs = pStmt.getGeneratedKeys();
					if(rs.next()) {
						//Pas necessaire ? : enchere.set(rs.getInt(1));
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
			return enchere;
	}


}
