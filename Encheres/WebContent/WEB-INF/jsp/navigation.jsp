<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #a9a9a9;">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/ServletAccueil">
      <img src="images/MarteauEncheres.svg" alt="" width="35" height="35" class="ml-2">Enchères et en OS !
    </a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">

				<c:choose>
					<c:when test="${sessionScope.idUtilisateur == null}">
						<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/ServletInscription">S'inscrire</a></li>
						<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/ServletConnexion">Connexion</a></li>
	  				</c:when>
	  				 <c:otherwise>
						<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/ServletNouvelleVente">Vendre un article</a></li>
						<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/ServletProfil">Mon Profil</a></li>
						<li class="nav-item"><a class="nav-link"
						href="${pageContext.request.contextPath}/ServletDeconnexion">Déconnexion</a></li>
        		 </c:otherwise>
  				</c:choose>


		</ul>
	</div>
</nav>