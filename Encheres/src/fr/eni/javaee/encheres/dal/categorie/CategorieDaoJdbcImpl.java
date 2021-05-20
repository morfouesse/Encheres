package fr.eni.javaee.encheres.dal.categorie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.dal.ConnectionProvider;

public class CategorieDaoJdbcImpl implements CategorieDao{

	private static final String SELECT_CATEGORIE_NO_CATEGORIE = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
	
	
	@Override
	public Categorie selectCategorieById(int noCategorie) {
		Categorie categorie = null;

		try(Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(SELECT_CATEGORIE_NO_CATEGORIE);
			pStmt.setInt(1, noCategorie);

			ResultSet rs = pStmt.executeQuery();
			
			String libelle ="";

			while(rs.next()) {
				libelle = rs.getString(2);
			}
			
			categorie = new Categorie(noCategorie, libelle);

		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("TO DO : gestion erreurs - erreur selectCategorieById");
		}
		return categorie;
	}

	
	
}
