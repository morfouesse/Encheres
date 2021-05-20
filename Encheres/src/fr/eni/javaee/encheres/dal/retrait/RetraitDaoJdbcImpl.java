package fr.eni.javaee.encheres.dal.retrait;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.eni.javaee.encheres.bo.Retrait;
import fr.eni.javaee.encheres.dal.ConnectionProvider;

public class RetraitDaoJdbcImpl implements RetraitDao {

	private static final String INSERT = "INSERT INTO RETRAITS(no_article, rue, code_postal, ville ) VALUES(?, ?, ?, ?)";
	
	@Override
	public Retrait insertRetrait(Retrait retrait) {
		if (retrait == null) {
			System.out.println("TO DO : gestion erreurs - erreur insertRetrait - retrait == null");
		} else {


			try(Connection cnx = ConnectionProvider.getConnection()) {

				cnx.setAutoCommit(false);

				try {
					PreparedStatement pStmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
					pStmt.setInt(1, retrait.getNoRetrait());
					pStmt.setString(2, retrait.getRue());
					pStmt.setString(3, retrait.getCodePostal());
					pStmt.setString(4, retrait.getVille());

					int nbLigneInseree = pStmt.executeUpdate();

					if (nbLigneInseree == 0) {
						System.out.println("TO DO : gestion erreurs - erreur insertRetrait nbLigne == 0");
						//Voir DaoRepas pour exemple erreurs
					}

					//on récupère l'id généré
					ResultSet rs = pStmt.getGeneratedKeys();
					if(rs.next()) {
						//Pas nécessaire ? retrait.setNoRetrait(rs.getInt(1));
					}
					cnx.commit(); //on valide
				} catch (Exception e) {
					cnx.rollback(); //on annule tout si problème
					System.out.println("TO DO : gestion erreurs - erreur insertRetrait");
					e.printStackTrace();
				}
			} catch (SQLException e) {
				System.out.println("TO DO : gestion erreurs - erreur insertRetrait");
				e.printStackTrace();
			}
		}
			return retrait;
	}

}
