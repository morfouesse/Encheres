
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #a9a9a9;">
	<a class="navbar-brand" href="#">Menu</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">

				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/ServletAccueil">Accueil<span class="sr-only"></span></a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/ServletNouvelleVente">Vendre un article</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/ServletProfil">Mon Profil</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/ServletConnexion">Connexion</a></li>

		</ul>
	</div>
</nav>