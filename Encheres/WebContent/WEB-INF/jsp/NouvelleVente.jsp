<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="java.util.*"%>


<!DOCTYPE html>

<html>
<head>


<title>Nouvelle Vente</title>

<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>

<div class="jumbotron text-center">  

 <h1><b>Bienvenue sur Enchères et en OS !</b></h1>

</div>
	
<%@include file="Navigation.jsp"%>
<div class="col-md-12">
	

	<form action="./ServletNouvelleVente" method="post">

		<div class="form-row">
			<div class="form-group col-md-12" style="text-align:center;">
				<label for="Article">Article : </label> <input style="width: 250px; display: block;
  					margin : auto; " type="text"
					class="form-control" id="Article"
					placeholder="Entrez le nom de l'article" name="Article"
					required="required">
			</div>
				<div class="form-group col-md-12" style="text-align:center;">
					<label for="Description">Description</label>
					<textarea style="width: 250px; display: block;
 					 margin : auto; " class="form-control" id="description"
					placeholder="Entrez une description" name="Description"
					required="required"></textarea>
				</div>
			</div>
		
		
		<div class="form-group col-md-12" style="text-align:center;">
    		<label for="UploadFile">Photo de l'article</label>
    		<input type="file" class="form-control-file" id="UploadFile">
  		</div>
  		
  			<!--  INSERTION IMAGE à GAUCHE DE L'ECRAN-->
  			
			
			<div class="input-group mb-12" >
				<div class="form-group col-md-12" style="text-align:center;">
					<label> Catégorie : </label> <select id="inputVille"
							class="form-control" name="Categories" style="width: 250px;display: block;
  							margin : auto">
							<option>Selectionner</option>
							<option>Informatique</option>
							<option>Ameublement</option>
							<option>Vêtements</option>
							<option>Sport &amp; Loisirs</option>
						</select>
				</div>
			</div>
		
		<div class="input-group mb-12" >
			<div class="form-group col-md-12" style="text-align:center;">
				<label for="PrixInitial">Prix initial :</label> <input style="width: 250px; display: block;
  					margin : auto;" type="number"
				class="form-control" id="prix" name="Prix" min="0"
				required="required">
			</div>
		</div>
					
		
		<div class="form-group col-md-12">
			<div class="form-group col-md-12" style="text-align:center;">
				<label for="FinEnchere">Début de l'enchère :</label> <input style="width: 250px; display: block;
  					margin : auto;" type="date" 
  					class="form-control" id="date" min="2021-05-01" name="Date"
					required="required">
			</div>
		</div>
		
			<div class="form-group col-md-12">
				<div class="form-group col-md-12" style="text-align:center;">
				<label for="FinEnchere">Fin de l'enchère :</label> <input style="width: 250px; display: block;
  					margin : auto;" type="date" 
  					class="form-control" id="date" min="2021-05-01" name="Date"
					required="required">
				</div>
			</div>
			
			<div class="col-md-12">		
			<div class="card" style="texte-align:center">
			
			<div class="form-group col-md-12" style="text-align:center;">
				<label for="rue">Rue :</label> <input style="width: 250px; display: block;
  					margin : auto;"" type="text"
					class="form-control" id="retraitRue" value="" name="rue">
				<label for="cp">Code Postal :</label> <input style="width: 250px; display: block;
  					margin : auto;"" type="text"
					class="form-control" id="retraitCP" value="" name="cp">
				<label for="ville">Ville :</label> <input style="width: 250px; display: block;
  					margin : auto;"" type="text"
					class="form-control" id="retraitVille" value="" name="ville">
					
			</div>
				
			</div>
			</div>

		
		<div class="form-group col-md-12" style="text-align:center;">
			<button type="submit" class="btn btn-primary" name="Enregistrer"value="">Enregistrer</button>
			<button type="submit" class="btn btn-primary" name="Publier" value = "">Publier</button>
		</div>
		
	</form>
</div>



<div class="form-group col-md-12">

	<br>
	
	<a href="ServletAccueil"><button type="submit" class="btn btn-primary">Annuler</button></a>
	
</div>


</html>