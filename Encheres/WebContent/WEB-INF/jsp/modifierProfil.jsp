<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Modification Profil</title>
	<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<script src="js/verifMotDePasse.js"></script>
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

	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card mb-4">

					<header class="card-header">
						<h4 class="card-title mt-2">Modification de mon profil</h4>
					</header>

					<div class="card-body">

						<form action="${pageContext.request.contextPath}/ServletModifProfil" method="post">

							<div class="form-row">

								<div class="col form-group">
									<label>Pseudo </label>
									<input type="text" class="form-control"
									placeholder="" name="pseudo" minlength="3" maxlength="30"
									required="required">
									<small class="form-text text-muted" maxlength="50"><i>Entre 3 et 30 caractères</i></small>
								</div>

								<div class="col form-group">
									<label>Nom</label>
									<input type="text" class="form-control"
									placeholder=" " name="nom"
									pattern="[^0-9]{3,30}">
									<small class="form-text text-muted" maxlength="50"><i>Entre 3 et 30 caractères</i></small>
								</div>

							</div>

							<div class="form-row">

								<div class="col form-group">
									<label>Prenom </label>
									<input type="text" class="form-control"
									placeholder="" name="prenom" pattern="[^0-9]{3,30}">
									<small class="form-text text-muted" maxlength="50"><i>Entre 3 et 30 caractères</i></small>
								</div>

								<div class="col form-group">
									<label>Email</label>
									<input type="email" class="form-control"
									 name="mail" required="required"> <small
									class="form-text text-muted" maxlength="50">Format : exemple@mail.com</small>
								</div>

							</div>

							<div class="form-row">

								<div class="col form-group">
									<label>Téléphone </label>
									<input type="text"
									class="form-control" placeholder="" name="telephone"
									placeholder=""
									pattern="[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}"
									required="required">
								</div>

								<div class="col form-group">
									<label>Rue</label>
									<input type="text" class="form-control"
									placeholder="" name="rue" maxlength="30" required="required">
								</div>

							</div>


							<div class="form-row">

								<div class="col form-group">
									<label>Code Postal </label>
									<input type="text"
									class="form-control" placeholder="" name="cp" max="99999"
									pattern="[0-9]{5}" required="required">
								</div>

								<div class="col form-group">
									<label>Ville</label>
									<select id="inputVille" class="form-control" name="ville" required="required">
										<option>Sélectionnez une ville</option>
										<option>Rennes</option>
										<option>Nantes</option>
										<option>Quimper</option>
										<option>Niort</option>
									</select>
								</div>

							</div>

							<div class="form-row">

								<div class="col form-group">
									<label>Mot de passe actuel</label>
									<input id="verifPassword" class="form-control" type="password" name="vmdp"
									 maxlength="30"
									 onkeyup='check();'>
									 <span id='message'></span>
								</div>
							</div>

							<div class="form-row">

								<div class="col form-group">
									<label>Nouveau mot de passe </label>
									<input id="password" class="form-control"
									 type="password" name="mdp" maxlength="30"
									 onkeyup='check();'>
								</div>

								<div class="col form-group">
									<label>Confirmation du mot de passe</label>
									<input id="verifPassword" class="form-control" type="password" name="vmdp"
									 maxlength="30"
									 onkeyup='check();'>
									 <span id='message'></span>
								</div>

							</div>



							<div class="form-group">
								<button id="button" type="submit" class="btn btn-primary btn-block"
								disabled>Enregistrer</button>
							</div>

						</form>

							<div>
								<a href="${pageContext.request.contextPath}/ServletSupprProfil"><button type="submit" class="btn btn-primary"
								style="justify-content-center">Supprimer mon compte</button></a>
							</div>

					</div> <!-- FIN CARD BODY -->


							<div class="border-top card-body text-center">
								<a href="${pageContext.request.contextPath}/ServletAccueil">Retour à la page d'accueil</a>
							</div>

				</div> <!-- FIN CARD -->

			</div>
		</div>
	</div>

<c:import url="footer.jsp" />

</body>
</html>