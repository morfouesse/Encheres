/**
 * 
 */

var  enchereTerminee = function() {
	var today = new Date();
	if (today.getTime() < document.getElementById('dateFinEncheres')){
		document.getElementById('boutonEncherir').className = 'btn btn-secondary btn-block';
	document.getElementById('boutonEncherir').innerHTML = 'Enchères terminées';
	document.getElementById('boutonEncherir').disabled = true;
	}
}



var check = function() {
		  
			var prixVente = Number(document.getElementById('prixVente').innerText);
			console.log(prixVente);
			console.log(typeof prixVente);
			var montantEnchere = Number(document.getElementById('montantEnchere').value);
			console.log(montantEnchere);
			console.log(typeof montantEnchere);
			if (  prixVente >= montantEnchere) {
		    document.getElementById('message').innerHTML = 'Votre enchère doit dépasser la mise à prix';
		    document.getElementById('message').style.color = 'red';
			document.getElementById('boutonEncherir').disabled = true;
		  } else {
		    document.getElementById('message').innerHTML = '';
		    document.getElementById('boutonEncherir').disabled = false;
		  }
		}