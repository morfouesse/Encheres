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
import fr.eni.javaee.encheres.dal.ConnectionProvider;

public class ArticleVenduDaoJdbcImpl implements ArticleVenduDao{

	private static final String INSERT = "INSERT INTO ARTICLES_VENDUS(nom_article, description, date_debut_encheres, "
			+ "date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) "
			+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String SELECT_BY_EXTRAIT = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE '%?%'";
	private static final String SELECT_BY_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_categorie = ?";
	private static final String SELECT_BY_EXTRAIT_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE nom_article LIKE '%?%' AND no_categorie = ?";
	
	private static final String SELECT_VENTES_BY_NO_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?";
	private static final String SELECT_VENTES_BY_NO_UTILISATEUR_EXTRAIT = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE '%?%'";
	private static final String SELECT_VENTES_BY_NO_UTILISATEUR_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND no_categorie = ?";
	private static final String SELECT_VENTES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE '%?%' AND no_categorie = ?";
	
	private static final String SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?"
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, date_fin_encheres, GETDATE()) <= 0";
			
	private static final String SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR_EXTRAIT = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE '%?%'"
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, date_fin_encheres, GETDATE()) <= 0";
	private static final String SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND no_categorie = ?"
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, date_fin_encheres, GETDATE()) <= 0";
	private static final String SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE '%?%' AND no_categorie = ?"
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, date_fin_encheres, GETDATE()) <= 0";
	
	private static final String SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?"
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE()) < 0";
	private static final String SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR_EXTRAIT = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE '%?%'"
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE())< 0";
	private static final String SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND no_categorie = ?"
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE())< 0";
	private static final String SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE '%?%' AND no_categorie = ?"
			+ "AND DATEDIFF(dd, date_debut_encheres, GETDATE())< 0";
	
	private static final String SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ?"
			+ "AND DATEDIFF(dd, date_fin_encheres, GETDATE())> 0";
	private static final String SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR_EXTRAIT = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE '%?%'"
			+ "AND DATEDIFF(dd, date_fin_encheres, GETDATE())> 0";
	private static final String SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND no_categorie = ?"
			+ "AND DATEDIFF(dd, date_fin_encheres, GETDATE())> 0";
	private static final String SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT * FROM ARTICLES_VENDUS WHERE no_utilisateur = ? AND nom_article LIKE '%?%' AND no_categorie = ?"
			+ "AND DATEDIFF(dd, date_fin_encheres, GETDATE())> 0";
	
	private static final String SELECT_ACHATS_BY_NO_UTILISATEUR = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ?";
	private static final String SELECT_ACHATS_BY_NO_UTILISATEUR_EXTRAIT = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE '%?%'";
	private static final String SELECT_ACHATS_BY_NO_UTILISATEUR_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres,"
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ? AND no_categorie = ?";
	private static final String SELECT_ACHATS_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres,"
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE '%?%' AND no_categorie = ?";
	
	private static final String SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ?"
			+ "AND DATEDIFF(dd, av.date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) <= 0";
	private static final String SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR_EXTRAIT = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE '%?%'"
			+ "AND DATEDIFF(dd, av.date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) <= 0";
	private static final String SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres,"
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ? AND no_categorie = ?"
			+ "AND DATEDIFF(dd, av.date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) <= 0";
	private static final String SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres,"
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE '%?%' AND no_categorie = ?"
			+ "AND DATEDIFF(dd, av.date_debut_encheres, GETDATE()) >= 0 AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) <= 0";
	
	private static final String SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ?";
	private static final String SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR_EXTRAIT = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE '%?%'";
	private static final String SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres,"
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ? AND no_categorie = ?";
	private static final String SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres,"
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE '%?%' AND no_categorie = ?";
	
	private static final String SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ?"
			+ "AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) > 0v";
	private static final String SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR_EXTRAIT = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres, "
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE '%?%'"
			+ "AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) > 0";
	private static final String SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres,"
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ? AND no_categorie = ?"
			+ "AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) > 0";
	private static final String SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE = "SELECT av.no_article, av.nom_article, av. description, av.date_debut_encheres,"
			+ "av.date_fin_encheres, av.prix_initial, av.prix_vente, av.no_utilisateur, av.no_categorie"
			+ "FROM ARTICLES_VENDUS AS av RIGHT OUTER JOIN ENCHERES AS e ON av.no_article = e.no_article"
			+ "WHERE e.no_utilisateur = ? AND nom_article LIKE '%?%' AND no_categorie = ?"
			+ "AND DATEDIFF(dd, av.date_fin_encheres, GETDATE()) > 0";
	
	private static final String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article = '?', description = '?', date_debut_encheres = '?', date_fin_encheres = '?'"
			+ "prix_initial = ?, prix_vente = ?, no_utilisateur = ?, no_categorie = ? WHERE no_article = ?";
	
	
private static final String DELETE = "DELETE FROM ENCHERES WHERE no_article = ?"
		+ "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";
	
	
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
	public List<ArticleVendu> select(String extrait) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_EXTRAIT);
			pStmt.setString(1, extrait);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}
	
	@Override
	public List<ArticleVendu> select(Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_CATEGORIE);
			pStmt.setInt(1, categorie.getNoCategorie());
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}
	
	@Override
	public List<ArticleVendu> select(String extrait, Categorie categorie) {
		
		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_BY_EXTRAIT_CATEGORIE);
			pStmt.setString(1, extrait);
			pStmt.setInt(2, categorie.getNoCategorie());
			
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
			pStmt.setString(1, extrait);
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentes(int noUtilisateur, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, categorie.getNoCategorie());
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentes(int noUtilisateur, String extrait, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setString(1, extrait);
			pStmt.setInt(2, categorie.getNoCategorie());
			pStmt.setInt(3, noUtilisateur);
			
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
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesEnCours(int noUtilisateur, String extrait) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR_EXTRAIT);
			pStmt.setString(1, extrait);
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesEnCours(int noUtilisateur, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, categorie.getNoCategorie());
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesEnCours(int noUtilisateur, String extrait, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_EN_COURS_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setString(1, extrait);
			pStmt.setInt(2, categorie.getNoCategorie());
			pStmt.setInt(3, noUtilisateur);
			
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
			pStmt.setString(1, extrait);
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, categorie.getNoCategorie());
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesNonDebutees(int noUtilisateur, String extrait, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_NON_DEBUTEES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setString(1, extrait);
			pStmt.setInt(2, categorie.getNoCategorie());
			pStmt.setInt(3, noUtilisateur);
			
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
			pStmt.setString(1, extrait);
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesTerminees(int noUtilisateur, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, categorie.getNoCategorie());
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectVentesTerminees(int noUtilisateur, String extrait, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_VENTES_TERMINEES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setString(1, extrait);
			pStmt.setInt(2, categorie.getNoCategorie());
			pStmt.setInt(3, noUtilisateur);
			
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
			pStmt.setString(1, extrait);
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchats(int noUtilisateur, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, categorie.getNoCategorie());
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchats(int noUtilisateur, String extrait, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setString(1, extrait);
			pStmt.setInt(2, categorie.getNoCategorie());
			pStmt.setInt(3, noUtilisateur);
			
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
			pStmt.setString(1, extrait);
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, categorie.getNoCategorie());
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsEncheresOuvertes(int noUtilisateur, String extrait, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_ENCHERES_OUVERTES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setString(1, extrait);
			pStmt.setInt(2, categorie.getNoCategorie());
			pStmt.setInt(3, noUtilisateur);
			
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
			pStmt.setString(1, extrait);
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, categorie.getNoCategorie());
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsMesEncheres(int noUtilisateur, String extrait, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_MES_ENCHERES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setString(1, extrait);
			pStmt.setInt(2, categorie.getNoCategorie());
			pStmt.setInt(3, noUtilisateur);
			
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
			pStmt.setString(1, extrait);
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR_CATEGORIE);
			pStmt.setInt(1, categorie.getNoCategorie());
			pStmt.setInt(2, noUtilisateur);
			
			ResultSet rs = pStmt.executeQuery();
			
			lst = remplirListeARetourner(rs);
			
		}catch (SQLException e) {
			System.out.println("TO DO : gestion erreurs");
		}
		return lst;
	}

	@Override
	public List<ArticleVendu> selectAchatsMesEncheresRemportees(int noUtilisateur, String extrait, Categorie categorie) {

		List<ArticleVendu> lst = null;
		
		try(Connection cnx = ConnectionProvider.getConnection()) {
			 
			PreparedStatement pStmt = cnx.prepareStatement(SELECT_ACHATS_MES_ENCHERES_REMPORTEES_BY_NO_UTILISATEUR_EXTRAIT_CATEGORIE);
			pStmt.setString(1, extrait);
			pStmt.setInt(2, categorie.getNoCategorie());
			pStmt.setInt(3, noUtilisateur);
			
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
					PreparedStatement pStmt = cnx.prepareStatement(DELETE);
					pStmt.setInt(1, noArticle);
					pStmt.setInt(2, noArticle);
					
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
				int noUtilisateur = rs.getInt("no_utilisateur");
				int noCategorie = rs.getInt("no_Categorie");
				
				
				//Faire constructeur avec id et attributs sauf les listes
//				article = new ArticleVendu(noArticle, nomArticle, description, dateDebutEncheres, dateFinEncheres, prixInitial
//						, prixVente, new Utilisateur(noUtilisateur), new Categorie(noCategorie));
//				
//				lst.add(article);
			}
		}
		return lst;
	}


	
	
	
}
