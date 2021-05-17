<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
	
<div class="container">
	<div class="row justify-content-center">
	

		<div class="col-md-6">		
			<div class="card">
			
				<header class="card-header">
					<h4 class="card-title mt-2">Connexion</h4>
				</header>
				
				
				<!-- FORMULAIRE DE CONNEXION -->
				
				<article class="card-body">
					
					<form action="./Index.jsp" method="post">
					
						<div class="form-row">
							<div class="col form-group">
								<label>Identifiant </label> <input type="text"
									class="form-control" placeholder="Pseudo" name="Pseudo"
									required="required">
							</div>
							
						</div>
						
						<div class="form-row">
							<div class="col form-group">
								<label>Mot de Passe </label> <input class="form-control"
									type="password" placeholder="Mot de passe" name="MDP"
									required="required">
							</div>
							
						</div>
						
						<div class="form-check">
						<div class="col form-group">
							<input type="checkbox" class="form-check-input"
								id="Checkbox"> <label class="form-check-label"
								for="Checkbox"> Se souvenir de moi </label><br>
							<br><a href="./ServletOubliMDP" id="MDPOubli">Mot de passe oublié ?</a>
							</div>
						</div>
						
						<div class="form-group">
				<button type="submit" class="btn btn-primary btn-block"
					name="Connexion">Connexion</button>
			
			</div>
					</form>
						
						
				</article>
			</div>
		
		</div>
		
	</div>

</div>


<br>
<div class="row justify-content-center">
	<a href="./ServletInscription"><button type="button"
			class="btn btn-primary btn-lg">Créer un compte</button></a>
</div><br>



<%@include file="Footer.jsp" %>

</body>

</html>