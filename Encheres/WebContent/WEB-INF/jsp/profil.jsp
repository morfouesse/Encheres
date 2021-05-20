<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>Mon profil</title>
		<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	</head>

<body>

<c:import url="navigation.jsp"/>

<div class="jumbotron">
		<div class="text-center" id="titre">
			<h1 id="titre">
				<b>Bienvenue sur Enchères et en OS !</b>
			</h1>
		</div>
	</div>

	<article class="container">
		<div class="row justify-content-center">
			<div class="col-md-4">
				<div class="card">

					<header class="card-header">
						<h4 class="card-title mt-2">Mon profil</h4>
					</header>

				
					<div class="card-body">
						<div class="form-row">
							<div class="form-group col-md-12" style="text-align:left;">
								<p>Pseudo : <c:out value="${ unUtilisateur.pseudo }"/></p>
            					<p>Nom : <c:out value="${ unUtilisateur.nom }"/></p>
            					<p>Prénom : <c:out value="${ unUtilisateur.prenom }"/></p>
            					<p>Numéro de téléphone : <c:out value="${ unUtilisateur.telephone }"/></p>
            					<p>Email : <c:out value="${ unUtilisateur.email }"/></p>
            					<p>Rue : <c:out value="${ unUtilisateur.rue }"/></p>
            					<p>Code Postal : <c:out value="${ unUtilisateur.cp }"/></p>
            					<p>Ville : <c:out value="${ unUtilisateur.ville }"/></p>
            				</div>
						</div>
					</div>
				


					<div class="card-body">
						<a href="${pageContext.request.contextPath}/ServletModifProfil"><button type="button"
							class="btn btn-primary btn-block" style="width: 250px; display: block;
  							margin : auto; ">Modifier</button>
  						</a><br>
					</div>

				</div>
			</div>
		</div>
	</article><br>

<c:import url="footer.jsp" />

</body>

</html>