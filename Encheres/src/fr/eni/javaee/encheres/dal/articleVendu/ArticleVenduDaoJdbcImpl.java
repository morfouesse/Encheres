package fr.eni.javaee.encheres.dal.articleVendu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.eni.javaee.encheres.bo.ArticleVendu;
import fr.eni.javaee.encheres.bo.Categorie;
import fr.eni.javaee.encheres.bo.Utilisateur;
import fr.eni.javaee.encheres.dal.ConnectionProvider;

public class ArticleVenduDaoJdbcImpl implements ArticleVenduDao{

	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, "
			+ "date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?) ";
	
	private static final String SELECT = "SELECT * FROM ARTICLES_VENDUS";
	private static final String SELECT_BY_EXTRAIT = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ?";
	private static final String SELECT_BY_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie = ?";
	private static final String SELECT_BY_EXTRAIT_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE ? AND no_categorie = ?";
	
	private static final String SELECT_VENTES_BY_NO_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";
	private static final String SELECT_VENTES_BY_NO_UTILISATEUR_EXTRAIT = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE ?";
	private static final String SELECT_VENTES_BY_NO_UTILISATEUR_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND no_categorie = ?";
	private static final String SELECT_VENTES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE ? AND no_categorie = ?";
	
	private static final String SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? "
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, date_fin_encheres, GETDATE()) <= 0";
	private static final String SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR_EXTRAIT = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE ? "
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, date_fin_encheres, GETDATE()) <= 0";
	private static final String SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND no_categorie = ? "
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, date_fin_encheres, GETDATE()) <= 0";
	private static final String SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE ? AND no_categorie = ? "
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, date_fin_encheres, GETDATE()) <= 0";
	
	private static final String SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? "
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE()) < 0";
	private static final String SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR_EXTRAIT = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE ? "
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE())< 0";
	private static final String SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND no_categorie = ? "
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE())< 0";
	private static final String SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE ? AND no_categorie = ? "
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE())< 0";
	
	private static final String SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? "
			+ "AND DATEDIFF(dd, date_fin_encheres, GETDATE())> 0";
	private static final String SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR_EXTRAIT = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE ? "
			+ "AND DATEDIFF(dd, date_fin_encheres, GETDATE())> 0";
	private static final String SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND no_categorie = ? "
			+ "AND DATEDIFF(dd, date_fin_encheres, GETDATE())> 0";
	private static final String SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE '%?%' AND no_categorie = ? "
			+ "AND DATEDIFF(dd, date_fin_encheres, GETDATE())> 0";
	
	private static final String SELECT_ACHATS_BY_NO_UTILISATEUR = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ?";
	private static final String SELECT_ACHATS_BY_NO_UTILISATEUR_EXTRAIT = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE ?";
	private static final String SELECT_ACHATS_BY_NO_UTILISATEUR_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? AND no_categorie = ?";
	private static final String SELECT_ACHATS_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE ? AND no_categorie = ?";
	
	private static final String SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? "
			+ "AND DATEDIFF(dd, av.date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) <= 0";
	private static final String SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR_EXTRAIT = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE ? "
			+ "AND DATEDIFF(dd, av.date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) <= 0";
	private static final String SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? AND no_categorie = ? "
			+ "AND DATEDIFF(dd, av.date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) <= 0";
	private static final String SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE ? AND no_categorie = ? "
			+ "AND DATEDIFF(dd, av.date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) <= 0";
	
	private static final String SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ?";
	private static final String SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR_EXTRAIT = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE ?";
	private static final String SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? AND no_categorie = ?";
	private static final String SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE ? AND no_categorie = ?";
	
	private static final String SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? "
			+ "AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) > 0";
	private static final String SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR_EXTRAIT = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE ? "
			+ "AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) > 0";
	private static final String SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? AND no_categorie = ? "
			+ "AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) > 0";
	private static final String SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie "
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article "
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE ? AND no_categorie = ? "
			+ "AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) > 0";
	
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, "
			+ "prix_initial = ?, prix_vente = ?, no_utilisateur = ?, no_categorie = ? WHERE no_article = ?";
	
	
	private static final String DELETE_FROM_ENCHERES = "DELETE FROM ENCHERES WHERE no_article = ?";
	
	private static final String DELETE_FROM_RETRAITS = "DELETE FROM RETRAITS WHERE no_article = ?";
	
	private static final String DELETE_FROM_ARTICLES_VENDUS = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";

	private static final String SELECT_ARTICLEVENDU_BY_NO_ARTICLEVENDU = "SELECT * FROM ARTICLES_VENDUS WHERE no_article = ?";

	private static final String SELECT_UTILISATEURS_BY_NO_UTILISATEUR = "SELECT * FROM UTILISATEURS WHERE no_utilisateur = ?";
	
	private static final String SELECT_CATEGORIE_NO_CATEGORIE = "SELECT * FROM CATEGORIES WHERE no_categorie = ?";
	
	
	
	@Override
	public ArticleVendu insertArticleVendu(ArticleVendu articleVendu) {

		if (articleVendu == null) {
			System.out.println("TO DO : gestion erreurs");
		} else {
			
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				
				cnx.setAutoCommit(false);

				try {
					PreparedStatement pStmt = cnx.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
					pStmt.setString(1, articleVendu.getNomArticle());
					pStmt.setString(2, articleVendu.getDescription());
					pStmt.setDate(3, new java.sql.Date(articleVendu.getDateDebutEncheres().getTime()));
					pStmt.setDate(4, new java.sql.Date(articleVendu.getDateFinEncheres().getTime()));
					pStmt.setInt(5, articleVendu.getMiseAPrix());
					pStmt.setInt(6, articleVendu.getPrixVente());
					pStmt.setInt(7, articleVendu.getUnUtilisateur().getNoUtilisateur());
					pStmt.setInt(8, articleVendu.getUneCategorie().getNoCategorie());
					
					int nbLigneInseree = pStmt.executeUpdate();
					
					if (nbLigneInseree == 0) {
						System.out.println("TO DO : gestion erreurs");
						//Voir DaoRepas pour exemple erreurs
					}
					
					//on récupère l'id généré
					ResultSet rs = pStmt.getGeneratedKeys();
					if(rs.next()) {
						articleVendu.setNoArticle(rs.getInt(1));
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
			return articleVendu;
	}

	
	@Override
	public List<ArticleVendu> select() {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}
	
	@Override
	public List<ArticleVendu> select(String extrait) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_EXTRAIT);
			pStmt.setString(1, "%"+extrait+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("erreur select(String extrait)");
		}
		return lst;
	}
	
	@Override
	public List<ArticleVendu> select(int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_CATEGORIE);
			pStmt.setInt(1, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}
	
	@Override
	public List<ArticleVendu> select(String extrait, int categorie) {
		
		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_EXTRAIT_CATEGORIE);
			pStmt.setString(1, "%"+extrait+"%");
			pStmt.setInt(2, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}
	
	
	@Override
	public List<ArticleVendu> selectVentes(int noUtilisateur) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_BY_NO_UTILISATEUR);
			pStmt.setInt(1, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentes(int noUtilisateur, String extrait) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_BY_NO_UTILISATEUR_EXTRAIT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentes(int noUtilisateur, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setInt(2, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentes(int noUtilisateur, String extrait, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			pStmt.setInt(3, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}


	@Override
	public List<ArticleVendu> selectVentesEnCours(int noUtilisateur) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR);
			pStmt.setInt(1, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs - erreur selectVentesEnCours(int)");
			e.printStackTrace();
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesEnCours(int noUtilisateur, String extrait) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR_EXTRAIT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs - erreur selectVentesEnCours(int, String)");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesEnCours(int noUtilisateur, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setInt(2, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesEnCours(int noUtilisateur, String extrait, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			pStmt.setInt(3, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	
	@Override
	public List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR);
			pStmt.setInt(1, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur, String extrait) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR_EXTRAIT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setInt(2, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur, String extrait, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			pStmt.setInt(3, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	
	@Override
	public List<ArticleVendu> selectVentesTerminees(int noUtilisateur) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR);
			pStmt.setInt(1, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesTerminees(int noUtilisateur, String extrait) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR_EXTRAIT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesTerminees(int noUtilisateur, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setInt(2, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesTerminees(int noUtilisateur, String extrait, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			pStmt.setInt(3, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	
	@Override
	public List<ArticleVendu> selectAchats(int noUtilisateur) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_BY_NO_UTILISATEUR);
			pStmt.setInt(1, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchats(int noUtilisateur, String extrait) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_BY_NO_UTILISATEUR_EXTRAIT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchats(int noUtilisateur, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setInt(2, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchats(int noUtilisateur, String extrait, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			pStmt.setInt(3, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	
	@Override
	public List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR);
			pStmt.setInt(1, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur, String extrait) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR_EXTRAIT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setInt(2, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur, String extrait, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			pStmt.setInt(3, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	
	@Override
	public List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR);
			pStmt.setInt(1, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur, String extrait) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR_EXTRAIT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setInt(2, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur, String extrait, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			pStmt.setInt(3, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}


	@Override
	public List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR);
			pStmt.setInt(1, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur, String extrait) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR_EXTRAIT);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setInt(2, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur, String extrait, int categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setInt(1, noUtilisateur);
			pStmt.setString(2, "%"+extrait+"%");
			pStmt.setInt(3, categorie);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	
	
	
	
	
	@Override
	public ArticleVendu updateArticleVendu(int noArticle, ArticleVendu articleVendu) {
		if (articleVendu == null) {
			System.out.println("TO DO : gestion erreurs");
		} else {
			
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				
				cnx.setAutoCommit(false);

				try {
					PreparedStatement pStmt = cnx.prepareStatement(UPDATE);
					pStmt.setString(1, articleVendu.getNomArticle());
					pStmt.setString(2, articleVendu.getDescription());
					pStmt.setDate(3, new java.sql.Date(articleVendu.getDateDebutEncheres().getTime()));
					pStmt.setDate(4, new java.sql.Date(articleVendu.getDateFinEncheres().getTime()));
					pStmt.setInt(5, articleVendu.getMiseAPrix());
					pStmt.setInt(6, articleVendu.getPrixVente());
					pStmt.setInt(7, articleVendu.getUnUtilisateur().getNoUtilisateur());
					pStmt.setInt(8, articleVendu.getUneCategorie().getNoCategorie());
					pStmt.setInt(9, noArticle);
					
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
			return articleVendu;
	}
	

	@Override
	public void deleteArticleVendu(int noArticle) {
		if (noArticle == 0) {
			System.out.println("TO DO : gestion erreurs");
		} else {
			
			
			try(Connection cnx = ConnectionProvider.getConnection()) {
				
				cnx.setAutoCommit(false);

				try {
					PreparedStatement pStmt = cnx.prepareStatement(DELETE_FROM_RETRAITS);
					pStmt.setInt(1, noArticle);
					
					pStmt.executeUpdate();
					
					PreparedStatement pStmt1 = cnx.prepareStatement(DELETE_FROM_ENCHERES);
					pStmt1.setInt(1, noArticle);
					
					pStmt1.executeUpdate();
					
					
					PreparedStatement pStmt2 = cnx.prepareStatement(DELETE_FROM_ARTICLES_VENDUS);
					pStmt2.setInt(1, noArticle);
					
					int nbLigneInseree2 = pStmt2.executeUpdate();
					
					if (nbLigneInseree2 != 1) {
						System.out.println("TO DO : gestion erreurs - là");
						//Voir DaoRepas pour exemple erreurs
					}
					
					cnx.commit(); //on valide
				} catch (Exception e) {
					cnx.rollback(); //on annule tout si problème
					System.out.println("TO DO : gestion erreurs - erreur deleteArticle");
					e.printStackTrace();
				}
			} catch (SQLException e) {
				System.out.println("TO DO : gestion erreurs - erreur deleteArticle");
				e.printStackTrace();
			}
		}
	}

	
	private List<ArticleVendu> remplirListeARetourner(ResultSet rs) throws SQLException {
		List<ArticleVendu> lst = new ArrayList<>();
		ArticleVendu article = null;
		while(rs.next()) {
			
			int noArticle = rs.getInt("no_article");
			//si un article n'existe pas encore ou si son id est différent
			if(article == null || article.getNoArticle() != noArticle) {					
				String nomArticle = rs.getString("nom_article");
				String description = rs.getString("description");
				Date dateDebutEncheres = rs.getDate("date_debut_encheres");
				Date dateFinEncheres = rs.getDate("date_fin_encheres");
				int prixInitial = rs.getInt("prix_initial");
				int prixVente = rs.getInt("prix_vente");
				int noCategorie = rs.getInt("no_Categorie");
				
				
				int noUtilisateur = rs.getInt("no_utilisateur");
				Utilisateur unUtilisateur = selectUtilisateurById(noUtilisateur);
				
				article = new ArticleVendu(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial
						, prixVente, unUtilisateur, new Categorie(noCategorie));
				
				lst.add(article);
			}
		}
		return lst;
	}


	@Override
	public ArticleVendu selectArticleVenduById(int noArticleVendu) {
		ArticleVendu articleVendu = null;
		try(Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ARTICLEVENDU_BY_NO_ARTICLEVENDU);
			pStmt.setInt(1, noArticleVendu);

			ResultSet rs = pStmt.executeQuery();
			
			int noArticle = 0;
			String nomArticle ="";
			String description = "";
			Date dateDebutEncheres = new Date();
			Date dateFinEncheres = new Date();
			int miseAPrix = 0;
			int prixVente = 0;
			int noUtilisateur = 0;
			int noCategorie = 0;
			
			if(rs.next()) {
				
			noArticle = rs.getInt(1);
			nomArticle = rs.getString(2);
			description = rs.getString(3);
			dateDebutEncheres = rs.getDate(4);
			dateFinEncheres = rs.getDate(5);
			miseAPrix = rs.getInt(6);
			prixVente = rs.getInt(7);
			noUtilisateur = rs.getInt(8);
			noCategorie = rs.getInt(9);
			}
			
			Utilisateur unUtilisateur = selectUtilisateurById(noUtilisateur);
			
			Categorie uneCategorie = selectCategorieById(noCategorie);
			
			
			articleVendu = new ArticleVendu(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, prixVente, unUtilisateur, uneCategorie);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs - erreur selectUtilisateurById ArticleVenduDoaJdbcImpl");
			e.printStackTrace();
		}
		return articleVendu;
	}
	
	
	
	public Utilisateur selectUtilisateurById(int id) {
		Utilisateur utilisateur = null;
		try(Connection cnx = ConnectionProvider.getConnection()) {

			PreparedStatement pStmt = cnx.prepareStatement(SELECT_UTILISATEURS_BY_NO_UTILISATEUR);
			pStmt.setInt(1, id);

			ResultSet rs = pStmt.executeQuery();
			
			int noUtilisateur = 0;
			String pseudo = "";
			String nom ="";
			String prenom = "";
			String email = "";
			String telephone = "";
			String rue = "";
			String code_postal = "";
			String ville = "";
			String mot_de_passe = "";
			int credit = 0;
			boolean administrateur = false;
			
			if(rs.next()) {
				
			noUtilisateur = rs.getInt(1);
			pseudo = rs.getString(2);
			nom = rs.getString(3);
			prenom = rs.getString(4);
			email = rs.getString(5);
			telephone = rs.getString(6);
			rue = rs.getString(7);
			code_postal = rs.getString(8);
			ville = rs.getString(9);
			mot_de_passe = rs.getString(10);
			credit = rs.getInt(11);
			administrateur = (rs.getByte(12) == 1 ? true: false);
			}
			

			utilisateur = new Utilisateur(noUtilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal,
					ville, mot_de_passe, credit, administrateur);

		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs - erreur selectUtilisateurById ArticleVenduDoaJdbcImpl");
			e.printStackTrace();
		}
		return utilisateur;
	}
	
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
