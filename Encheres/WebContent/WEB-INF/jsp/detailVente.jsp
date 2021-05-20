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
							<h4>Mon profil</h4>
						</header><br>

					<form action="${pageContext.request.contextPath}/ServletEncherir" method="post">


	            				<p>Nom de l'article : <c:out value="${ nomArticle }"/></p>

	            				<p>Descritption : <c:out value="${ description }"/></p>

	            				<p>Categorie : <c:out value="${ categorie }"/></p>

	            				<p>Meilleure offre : <c:out value="${ meilleureOffre }"/></p>

	            				<p>Mise à prix : <c:out value="${ miseAPrix }"/></p>

	            				<p>Fin de l'enchère : <c:out value="${ finEnchere }"/></p>

            					<p>Retrait : <c:out value="${Rue}"/><br> <c:out value="${CP} ${Ville}"/></p>

            					<p>Vendeur : <c:out value="${Rue}"/><br> <c:out value="${CP} ${Ville}"/></p>

            			<div class="row">

       	 					<div class="col">
       	 						<label for="ticketNum">Ma proposition :</label>
       	 						<input type="number" id="prix" name="Prix"
       									min="0" value="100" step="10" style="width:70px">
       						</div>

							<div class="col">
								<a href="${pageContext.request.contextPath}/ServletEncherir"><button type="submit"
								class="btn btn-primary btn-block" style="width: 250px; display: block;
	  							margin : auto; ">Encherir</button></a><br>
							</div>

						</div>

					</form> <!-- FIN FOMRULAIRE -->

					</div>

				</div>
			</div>

	</body>

	</html>