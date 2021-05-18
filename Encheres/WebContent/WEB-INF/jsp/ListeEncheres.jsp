<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>


<link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<title>Liste Enchères</title>
</head>
<body>

<div class="row justify-content-center">
	<div class="row col-md-12">
		
		<h2>Enchères : </h2>
		
            <div class="container">
    <div class="row">
    	<c:choose>
	        <c:when test="${!empty listeRetour}">
	        	<ul class="list-group col-12">
	        		<c:forEach var="articleVendu" items="${listeRetour}">
					        <div class="col-md-4">
					        
					            <div class="carbon-wrap">
					                <img src="./lib/MarteauEncheres.jpg" alt="LogoEnchere" style="width: 200px; heigth: 200px">
					                
					                <div class="carbon-text">
					                  <p>Nom : <c:out value="${articleVendu.nomArticle}" /></p>
					                  <p>Prix : ?</p>
					                  <p>Fin de l'enchère : JJ/MM/AAAA
					                  <br>
					                  <p>Vendeur :</p>
					                </div>
					            </div>
					        </div>
					</c:forEach>	        
		        </ul>
			</c:when>
        </c:choose>
        
        
        <div class="col-md-4">
            <div class="carbon-example flex-wrapper">
                <img src="./lib/MarteauEncheres.jpg" alt="LogoEnchere" style="width: 200px; heigth: 200px">
                <div class="carbon-text">
                  <p><u>Titre de l'article :</u></p>
                  <p>Prix : ?</p>
                  <p>Fin de l'enchère : JJ/MM/AAAA
                  <br>
                  <p>Vendeur :</p>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="carbon-example flex-wrapper">
                <img src="./lib/MarteauEncheres.jpg" alt="LogoEnchere" style="width: 200px; heigth: 200px">
                <div class="carbon-text">
                  <p><u>Titre de l'article :</u></p>
                  <p>Prix : ?</p>
                  <p>Fin de l'enchère : JJ/MM/AAAA
                  <br>
                  <p>Vendeur :</p>
                </div>
            </div>
        </div>
        
        
    </div>
   </div>
					
	</div>
</div>	

</body>
</html>