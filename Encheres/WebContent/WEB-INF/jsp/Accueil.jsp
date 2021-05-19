<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
	<title>Enchères et en OS</title>

<!-- BOOTSTRAP -->
	<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
	<link href="css/accueilStyleSheet.css" rel="stylesheet">

</head>

<body>

	<div class="jumbotron">

		<div class="text-center" id="titre">
			<h1 id="titre">
				<b>Bienvenue sur Enchères et en OS !</b>
			</h1>
		</div>
	</div>


	<c:import url="Navigation.jsp" />


<div id="titrePage"><h2>Nos enchères</h2></div>

<div class="containerGrid">
	<div class="filtresCol">
		
		<div class="filtresBox">
	
				<div class="filtresbody">
	
					<div class="col form-group">
							<label>Filtres :</label>
					</div>
	
					<!-- FORMULAIRE DE RECHERCHE -->
	
					<form action="./ServletRechercheVente" method="get" style="texte-align: left">
						<input type="search" style="width: 250px; display: block; text-align: left; margin: auto;"
								name="extrait" placeholder="Le nom de l'article contient">
		
							<div class="col form-group" style="text-align: center;">
								<label>Catégories </label>
								 <select id="catégorie"
									style="width: 200px; display: block; margin: auto;"
									class="form-control" name="categorie">
									<option value="0" selected>Toutes</option>
									<option value="1">Vêtements</option>
									<option value ="2">Ameublement</option>
									<option value="3">Informatique</option>
									<option value="4">Sports &amp; Loisirs</option>
									<option value="5">Sports &amp; Véhicules</option>
								</select>
							</div>
		
					<div class="mx-auto">
		  				<div class="row">
		
								<div class="col doubleFile">
									<div class="radio1">
		  								<input type="radio" id="achats" name="choixAchatsVentes" value="achatsChecked">
		  								<label for="">Achats</label>
									</div>
		
									<div>
										<input type="radio" id="encheresOuvertes" name="choixAchats" value="encheresOuvertes">
										<label for="">Enchères ouvertes</label>
									</div>
		
									<div>
										<input type="radio" id="mesEnchere" name="choixAchats" value="mesEnchere">
										<label for="">Mes enchères</label>
									</div>
		
									<div>
										<input type="radio" id="mesEncheresRemportees" name="choixAchats" value="mesEncheresRemportees">
										<label for="">Mes enchères remportées</label>
									</div>
								</div>
		
								<div class="col doubleFile">
								<div class="radio1">
		  							<input type="radio" id="mesVentes" name="choixAchatsVentes" value="mesVentesChecked">
		  							<label for="">Mes ventes</label>
								</div>
		
									<div>
										<input type="radio" id="mesVentesEnCours" name="choixVentes" value="mesVentesEnCours">
										<label for="">Mes ventes en cours</label>
									</div>
		
									<div>
										<input type="radio" id="ventesNonDébutées" name="choixVentes" value="ventesNonDébutées">
										<label for="">Ventes non débutées</label>
									</div>
		
									<div>
										<input type="radio" id="ventesTerminees" name="choixVentes" value="ventesTerminees">
										<label for="">Ventes terminées</label>
									</div>
								</div>
		
						</div>
					</div>
		
		
						<div class="form-group">
								<input class="btn btn-primary btn-block"
								style="width: 200px; display: block; margin: auto; text-align: center;"
								type="submit" value="rechercher">
						</div>
		
					</form>
	
			</div>
		
		</div>
	

	</div>


	<!-- LISTE DES DIFFERENTES ENCHERES -->
	
	<div class="listeCol">
	
		<c:import url="ListeEncheres.jsp"/>
	</div>
</div>


<footer>
	<c:import url="Footer.jsp" />
</footer>

</body>

</html>