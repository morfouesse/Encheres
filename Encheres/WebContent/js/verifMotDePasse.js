var check = function() {
		  if ( document.getElementById('password').value ==
		    document.getElementById('verifPassword').value) {
		    document.getElementById('message').innerHTML = '';
		    document.getElementById('button').disabled = false;
		  } else {
		    document.getElementById('message').style.color = 'red';
		    document.getElementById('message').innerHTML = 'le mot de passe ne correspond pas';
		    document.getElementById('button').disabled = true;
		  }
		}