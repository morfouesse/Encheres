<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>


<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<link href="css/listeEncheresStyleSheet.css" rel="stylesheet">

<title>Liste Enchères</title>
</head>
<body>

	    <div class="containerInternalGrid" >
	    	<c:choose>
		        <c:when test="${!empty listeRetour}">
		        		<c:forEach var="articleVendu" items="${listeRetour}">
							<div class="col colArticle">
						        <div class="col containerArticle">
						        
					                <div class="imageArticle">
					                	<img src="./lib/MarteauEncheres.jpg" alt="LogoEnchere" style="width: 200px; heigth: 200px">
					                </div>
					                
					                <div class="textArticle">
					                  <h5><c:out value="${articleVendu.nomArticle}" /></h5>
					                  <p>Prix : <c:out value="${articleVendu.prixVente}" /></p>
					                  <p>Fin : <c:out value="${articleVendu.dateFinEncheres}" /></p>
					                  <p>Vendeur : <c:out value="${articleVendu.unUtilisateur.pseudo}" /><i>sera indiqué quand l'offre sera créer depuis nouvelleVente.jsp</i></p>
					                </div>
					                
						        </div>
							</div>
						</c:forEach>	        
				</c:when>
				<c:otherwise>
	    			<p>Pas d'articles à afficher - Veuillez effectuer une autre recherche</p>
	    		</c:otherwise>
	        </c:choose>
	    </div>
		


</body>
</html>