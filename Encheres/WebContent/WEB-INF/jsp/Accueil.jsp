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
	<br>

<div class="container">
	<div class="row justify-content-center">

	<article class="card-body">

		<div class="col-md-8">
			<div class="card">

				<div class="col form-group" style="text-align: left;">
						<label>Filtres :</label>
				</div>

				<!-- FORMULAIRE DE RECHERCHE -->

			<form action="./ServletRechercheVente" method="post" style="texte-align: left">
				<input type="search" style="width: 250px; display: block; text-align: left; margin: auto;"
						name="Recherche" placeholder="Le nom de l'article contient">

					<br>
					<br>

					<div class="col form-group" style="text-align: center;">
						<label>Catégories </label>
						 <select id="Catégories"
							style="width: 200px; display: block; margin: auto;"
							class="form-control" name="Categories">
							<option value="Toutes" selected>Toutes</option>
							<option value="Informatique">Informatique</option>
							<option value ="Ameublement">Ameublement</option>
							<option value="Vêtements">Vêtements</option>
							<option value="Sport et Loisirs">Sport &amp; Loisirs</option>
						</select>
					</div>

					<br>

			<div class="container">
  				<div class="row">

						<div class="col">
							<div>
  								<input type="radio" id="Achats" name="Achats" value="Achats" checked>
  								<label for="">Achats</label>
							</div>

							<div>
								<input type="checkbox" id="categorieVente" name="mesVentes">
								<label for="">Enchères ouvertes</label>
							</div>

							<div>
								<input type="checkbox" id="categorieEnchres" name="mesEnchere">
								<label for="">Mes enchères</label>
							</div>

							<div>
								<input type="checkbox" id="categorieAcquisitions" name="mesAcquisitions">
								<label for="">Mes enchères remportées</label>
							</div>
						</div>

						<div class="col">
						<div>
  							<input type="radio" id="Achats" name="Achats" value="Achats">
  							<label for="">Ventes</label>
						</div>

							<div>
								<input type="checkbox" id="categorieVente" name="mesVentes">
								<label for="">Mes ventes en cours</label>
							</div>

							<div>
								<input type="checkbox" id="categorieEnchres" name="mesEnchere">
								<label for="">Ventes non débutées</label>
							</div>

							<div>
								<input type="checkbox" id="categorieAcquisitions" name="mesAcquisitions">
								<label for="">Ventes terminées</label>
							</div>
						</div>

				</div>
			</div>

					<br>

				<div class="form-group">
						<input class="btn btn-primary btn-block"
						style="width: 200px; display: block; margin: auto; text-align: center;"
						type="submit" value="Rechercher">
				</div>

			</form>



			</div>

		</div>

</article>

</div>

</div>

<br>

<!-- LISTE DES DIFFERENTES ENCHERES -->

<div class="container">
	<div class="row justify-content-center">

		<c:import url="ListeEncheres.jsp"/>
	</div>
</div>

<br>
<br>

<footer>
	<c:import url="Footer.jsp" />
</footer>

</body>

</html>