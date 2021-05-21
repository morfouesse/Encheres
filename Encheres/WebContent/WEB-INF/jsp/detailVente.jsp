<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Détail vente</title>
		<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
		<script src="js/detailVenteScript.js" type="text/javascript"></script>
	</head>

<body>

	<c:import url="navigation.jsp"/>

		<div class="jumbotron text-center">
			<h1><b>Détails de la vente</b></h1>
		</div>

			<div class="container">
				<div class="row justify-content-center">

					<div class="col-md-6">

						<header>
							<h4><c:out value="${articleVendu.nomArticle}"/></h4>
						</header><br>

					

	            				<p>Description : <c:out value="${articleVendu.description }"/></p>

	            				<p>Categorie : <c:out value="${articleVendu.uneCategorie.libelle}"/></p>

	            				<p>Meilleure offre : <span id="prixVente"><c:out value="${articleVendu.prixVente}"/></span></p>

	            				<p id="dateFinEncheres">Fin de l'enchère : <c:out value="${ articleVendu.dateFinEncheres }"/></p>

            					<h6>Retrait : </h6>
            					<p><c:out value="${articleVendu.unRetrait.rue}"/><br>
            					<c:out value="${articleVendu.unRetrait.codePostal}"/><br>
            					<c:out value="${articleVendu.unRetrait.ville}"/></p>

            					<p>Vendeur : <c:out value="${articleVendu.unUtilisateur.pseudo}"/></p> 

			
					
			<c:choose>
		        <c:when test="${(noUtilisateur != articleVendu.unUtilisateur.noUtilisateur)}">
					
					<form action="${pageContext.request.contextPath}/ServletEncherir?noArticle=${articleVendu.noArticle}" method="post">
            			<div class="row">
       	 					<div class="col">
       	 						<label for="ticketNum">Ma proposition :</label>
       	 						<input type="number" id="montantEnchere" name="montantEnchere" onkeyup='check();'
       									min="0" value="${articleVendu.prixVente}" step="1" style="width:70px">
       						<span id='message'></span>
       						</div>

							<div class="col">
								<button type="submit" id="boutonEncherir"
								class="btn btn-primary btn-block" style="width: 250px; display: block;
	  							margin : auto; ">Encherir</button><br>
							</div>

						</div>

					</form> <!-- FIN FOMRULAIRE -->
					
					</c:when>
					<c:when test="${(noUtilisateur == articleVendu.unUtilisateur.noUtilisateur)}">
						<div>
							<a href="${pageContext.request.contextPath}/ServletSupprVente?noArticle=${articleVendu.noArticle}"><button type="submit" class="btn btn-primary"
							style="justify-content-center">Supprimer mon annonce</button></a>
						</div>
					</c:when>
					<c:otherwise><i></i></c:otherwise>
			</c:choose>
			
					</div>

				</div>
			</div>
	</body>

	</html>