<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Inscription | Enchères et en OS</title>

<!-- BOOTSTRAP -->
	<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="js/verifMotDePasse.js"></script>
</head>
<body>
	<div class="jumbotron text-center">   <h1><b>Bienvenue sur Enchères et en OS !</b></h1>

	  <b>Création d'un compte</b>
	</div>
<div class="container">
	<div class="row justify-content-center">
		<div class="col-md-6">
			<div class="card">
				<header class="card-header">
					<h4 class="card-title mt-2">Inscription</h4>
				</header>
				<article class="card-body">
				<c:choose>
					<c:when test="${!empty listeErreurs }">
	  					<c:forEach items="${ listeErreurs }" var="message">
	    				<p style="color: red;"><c:out value="${ message }" /></p>
	    				</c:forEach>
  					</c:when>
  				</c:choose>
					<form action="${pageContext.request.contextPath}/ServletInscription" method="post">
						<div class="form-row">
							<div class="col form-group">
								<label>Pseudo </label> <input type="text" class="form-control"
									placeholder="" name="pseudo" minlength="3" maxlength="30"
									value="" required="required">
								<p>
									<font size="-1"><i>Entre 3 et 30 caractères</i></font>
								</p>
							</div>

							<div class="col form-group">
								<label>Nom</label> <input type="text" class="form-control"
									placeholder=" " name="nom"
									pattern="[^0-9]{3,30}">
								<p>
									<font size="-1"><i>Entre 3 et 30 caractères</i></font>
								</p>
							</div>

						</div>

						<div class="form-row">
							<div class="col form-group">
								<label>Prenom </label> <input type="text" class="form-control"
									placeholder=" " name="prenom" pattern="[^0-9]{3,30}"
									>
								<p>
									<font size="-1"><i>Entre 3 et 30 caractères</i></font>
								</p>
							</div>

							<div class="col form-group">
								<label>Email</label> <input type="email" class="form-control"
									 name="email" required="required"> <small
									class="form-text text-muted" maxlength="50">Votre
									adresse mail ne sera pas partagée.</small>
							</div>

						</div>

						<div class="form-row">
							<div class="col form-group">
								<label>Téléphone </label> <input type="text"
									class="form-control" placeholder="" name="telephone"
									placeholder=""
									pattern="[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}"
									required="required">
							</div>

							<div class="col form-group">
								<label>Rue</label> <input type="text" class="form-control"
									placeholder="" name="rue" maxlength="30" required="required">
							</div>

						</div>


						<div class="form-row">
							<div class="col form-group">
								<label>Code Postal </label> <input type="text"
									class="form-control" placeholder="" name="cp" max="99999"
									pattern="[0-9]{5}" required="required">
							</div>

							<div class="col form-group">
								<label>Ville</label>
								<input id="inputVille" type="text" class="form-control"
								placeholder="" name="ville" maxlength="30" required="required"
								pattern="[^0-9]{3,30}">

							</div>

						</div>


						<div class="form-row">
							<div class="col form-group">
								<label>Mot de Passe </label> <input id="password" class="form-control"
									 type="password" name="mdp" maxlength="30"
									 onkeyup='check();' required="required">
							</div>

							<div class="col form-group">
								<label>Confirmation du mot de passe</label> <input
									id="verifPassword" class="form-control" type="password" name="vmdp"
									 maxlength="30"
									 onkeyup='check();' required="required">
									 <span id='message'></span>
							</div>

						</div>






						<div class="form-group">
							<button id="button" type="submit" class="btn btn-primary btn-block" disabled>
								Inscription</button>
						</div>

					</form>
				</article>

				<div class="border-top card-body text-center">
					Déjà inscrit ? <a href="${pageContext.request.contextPath}/ServletAccueil">Retour à la page d'accueil</a>
				</div>
			</div>

		</div>
	</div>
</div>





<c:import url="Footer.jsp" />

</body>
</html>