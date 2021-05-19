<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>Connexion | Enchères et en OS</title>

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


		<div class="col-md-6">
			<div class="card">

				<header class="card-header">
					<h4 class="card-title mt-2">Connexion</h4>
				</header>


				<!-- FORMULAIRE DE CONNEXION -->

				<article class="card-body">
					<c:choose>
						<c:when test="${!empty listeErreurs }">
		  					<c:forEach items="${ listeErreurs }" var="message">
		    				<p style="color: red;"><c:out value="${ message }" /></p>
		    				</c:forEach>
	  					</c:when>
  					</c:choose>
					<form action="${pageContext.request.contextPath}/ServletConnexion" method="post">

						<div class="form-row">
							<div class="col form-group">
								<label>Identifiant </label> <input type="text"
									class="form-control" placeholder="Pseudo" name="pseudo"
									required="required">
							</div>

						</div>

						<div class="form-row">
							<div class="col form-group">
								<label>Mot de Passe </label> <input class="form-control"
									type="password" placeholder="Mot de passe" name="mdp"
									required="required">
							</div>

						</div>
						<br>
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
	<a href="${pageContext.request.contextPath}/ServletInscription"><button type="button"
			class="btn btn-primary btn-lg">Créer un compte</button></a>
</div><br>



<c:import url="Footer.jsp" />


</body>

</html>