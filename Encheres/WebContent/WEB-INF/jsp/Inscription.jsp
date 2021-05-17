<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">





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
					<form action="./AjouterUtilisateur" method="post">
						<div class="form-row">
							<div class="col form-group">
								<label>Pseudo </label> <input type="text" class="form-control"
									placeholder="" name="sPseudo" minlength="3" maxlength="30"
									required="required">
								<p>
									<font size="-1"><i>Entre 3 et 30 caractères</i></font>
								</p>
							</div>
							
							<div class="col form-group">
								<label>Nom</label> <input type="text" class="form-control"
									placeholder=" " name="sNom"
									pattern="[^0-9]{3,30}">
								<p>
									<font size="-1"><i>Entre 3 et 30 caractères</i></font>
								</p>
							</div>
							
						</div>
						
						<div class="form-row">
							<div class="col form-group">
								<label>Prenom </label> <input type="text" class="form-control"
									placeholder="" name="sPrenom" pattern="[^0-9]{3,30}"
									>
								<p>
									<font size="-1"><i>Entre 3 et 30 caractères</i></font>
								</p>
							</div>
							
							<div class="col form-group">
								<label>Email</label> <input type="email" class="form-control"
									placeholder="" name="sMail" required="required"> <small
									class="form-text text-muted" maxlength="50">Votre
									adresse mail ne sera pas partagée.</small>
							</div>
							
						</div>
						
						<div class="form-row">
							<div class="col form-group">
								<label>Téléphone </label> <input type="text"
									class="form-control" placeholder="" name="Telephone"
									placeholder=""
									pattern="[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}"
									required="required">
							</div>
							
							<div class="col form-group">
								<label>Adresse</label> <input type="text" class="form-control"
									placeholder="" name="sRue" maxlength="30" required="required">
							</div>
							
						</div>
						

						<div class="form-row">
							<div class="col form-group">
								<label>Code Postal </label> <input type="text"
									class="form-control" placeholder="" name="sCP" max="99999"
									pattern="[0-9]{5}" required="required">
							</div>
							
							<div class="col form-group">
								<label>Ville</label> <select id="inputVille"
									class="form-control" name="sVille" required="required">
									<option>Sélectionnez une ville</option>
									<option selected="">Nantes</option>
									<option>Paris</option>
									<option>Brest</option>
									<option>Angers</option>
									<option>Tours</option>
								</select>
							</div>
							
						</div>
						

						<div class="form-row">
							<div class="col form-group">
								<label>Mot de Passe </label> <input class="form-control"
									type="password" name="sMDP" maxlength="30">
							</div>
							
							<div class="col form-group">
								<label>Confirmation du mot de passe</label> <input
									class="form-control" type="password" name="sVMDP"
									maxlength="30">
							</div>
							
						</div>
						





						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">
								Inscription</button>
						</div>
						
					</form>
				</article>
				
				<div class="border-top card-body text-center">
					Déjà inscrit ? <a href="./ServletAccueil">Retour à la page d'accueil</a>
				</div>
			</div>
			
		</div>
		

	</div>
	


</div>
<%@include file="Footer.jsp"%>


</html>