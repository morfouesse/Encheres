<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Nouvelle Vente</title>
	<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<link href="css/nouvelleVenteStyleSheet.css" rel="stylesheet">
	<script src="js/nouvelleVenteScript.js"></script>
</head>

<body>

<c:import url="Navigation.jsp" />

	<div class="jumbotron">
		<div class="text-center" id="titre">
			<h1 id="titre">
			<b>Bienvenue sur Enchères et en OS !</b>
			</h1>
		</div>
	</div>
	


	<form action="./ServletNouvelleVente" method="post">

		<div class="form-row">
			<div class="form-group col-md-12" style="text-align:center;">
				<label for="Article">Article : </label> 
				<input style="width: 250px; display: block;
  					margin : auto; " type="text"
					class="form-control" id="nomArticle"
					placeholder="Entrez le nom de l'article" name="nomArticle"
					required="required">
			</div>
			
			<div class="form-group col-md-12" style="text-align:center;">
				<label for="Description">Description</label>
				<textarea style="width: 250px; display: block;
 					margin : auto; " class="form-control" id="description"
					placeholder="Entrez une description" name="description"
					required="required"></textarea>
			</div>
		
		
			<div class="form-group col-md-12" style="text-align:center;">
    			<label for="UploadFile">Photo de l'article</label>
    			<input type="file" class="" id="UploadFile">
  			</div>
  		</div>
  			<!--  INSERTION IMAGE à GAUCHE DE L'ECRAN-->
  			
	<div class="form-row">	
		<div class="input-group mb-12" >
			<div class="form-group col-md-12" style="text-align:center;">
				<label> Catégorie : </label> 
					<select id="categorie" class="form-control" name="categorie" 
							style="width: 250px;display: block; margin : auto">
						<option value="1">Vêtements</option>
						<option value ="2">Ameublement</option>
						<option value="3">Informatique</option>
						<option value="4">Sports &amp; Loisirs</option>
						<option value="5">Véhicules</option>
					</select>
			</div>
		</div>
		
		<div class="input-group mb-12" >
			<div class="form-group col-md-12" style="text-align:center;">
				<label for="PrixInitial">Prix initial :</label> 
				<input style="width: 250px; display: block;
  					margin : auto;" type="number"
				class="form-control" id="miseAPrix" name="miseAPrix" min="0"
				required="required">
			</div>
		</div>
					
		
		<div class="form-group col-md-12">
			<div class="form-group col-md-12" style="text-align:center;">
				<label for="FinEnchere">Début de l'enchère :</label> 
				<input style="width: 250px; display: block;
  					margin : auto;" type="date" 
  					class="form-control" min="2021-05-01" id="dateDebutEncheres" name="dateDebutEncheres"
					required="required">
			</div>
		</div>
		
			<div class="form-group col-md-12">
				<div class="form-group col-md-12" style="text-align:center;">
				<label for="FinEnchere">Fin de l'enchère :</label> 
				<input style="width: 250px; display: block;
  					margin : auto;" type="date" 
  					class="form-control" min="2021-05-01" max="2021-08-01" 
  					id="dateFinEncheres" name="dateFinEncheres" required="required">
				</div>
			</div>
		
				
	<div class="container">
		<div class="row justify-content-center">	
					
			<div class="card">
			<div class="col-md-12">
			
			<div class="infoRetrait">
				<p>*Ne remplir que si l'adresse de retrait est<br>différente de votre adresse d'utilisateur</p>
			</div>
				
			
			<div class="form-group col-md-12" style="text-align:center;">
				<label for="rue">Rue :</label> 
				<input style="width: 250px; display: block;
  					margin : auto;" type="text"
					class="form-control" id="rue" name="rue">
					
				<label for="cp">Code Postal :</label> 
				<input style="width: 250px; display: block;
  					margin : auto;" type="text"
					class="form-control" id="codePostal" name="codePostal">
					
				<label for="ville">Ville :</label> 
				<input style="width: 250px; display: block;
  					margin : auto;" type="text"
					class="form-control" id="ville" name="ville">	
			</div>
				
			</div>
			</div>
			
	</div><br> <!-- fin "form-row" -->
		
		</div>
	</div>
		
			
			<div class="form-group col-md-12" style="text-align:center;">
				<button type="submit" class="btn btn-primary" name="Enregistrer"value="">Enregistrer</button>
				<a href="ServletAccueil"><button type="submit" class="btn btn-primary" 
				style="justify-content-center">Annuler</button></a>
			</div>
		
	</form><br>

<c:import url="Footer.jsp" />

</body>

</html>