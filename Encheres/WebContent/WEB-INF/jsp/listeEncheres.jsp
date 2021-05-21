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
									<div>
					                	<a href="${pageContext.request.contextPath}/ServletDetailVente">
					                		<div class="imageArticle">
					                		<img src="images/MarteauEncheres.svg" alt="LogoEnchere" style="width: 200px; heigth: 200px">
					                		</div>
					                	</a>
									</div>

					                <div class="textArticle">
					                  <h5><a href="${pageContext.request.contextPath}/ServletDetailVente?noArticle=${articleVendu.noArticle}"><c:out value="${articleVendu.nomArticle}" /></a></h5>
					                  <p>Prix : <c:out value="${articleVendu.prixVente}" /></p>
					                  <p>Fin : <c:out value="${articleVendu.dateFinEncheres}" /></p>
					                  <p>Vendeur : <c:out value="${articleVendu.unUtilisateur.pseudo}" /></p>
					                </div>

						        </div>
							</div>
						</c:forEach>
				</c:when>
				<c:otherwise>
	    			<p class="textArticle">Effectuez une recher pour afficher des articles.</p>
	    		</c:otherwise>
	        </c:choose>
	    </div>



</body>
</html>