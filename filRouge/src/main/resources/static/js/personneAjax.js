window.onload=function(){
	allLivres();
	(document.getElementById("btnAjout"))
	   .addEventListener("click",ajouterPersonne);   
}

function ajouterPersonne(){	
	
	let prenom = (document.getElementById("inputPrenom")).value;
	let nom = (document.getElementById("inputNom")).value;
	let email = (document.getElementById("inputEmail")).value;
	let telephone = (document.getElementById("inputTelephone")).value;
	let adresse = (document.getElementById("inputAdresse")).value;
	
	let personneJs = { prenom : prenom,
	                 nom : nom,
	                 email : email, 
	                 telephone : telephone, 
	                 adresse : adresse, 
	                  };
	let personneJson = JSON.stringify(personneJs) ;  
	let wsUrl = "./api-bibliotheque/personne";   
	makeAjaxPostRequest(wsUrl,personneJson,function (responseJson){
		console.log("responseJson="+responseJson);
		allPersonnes(); //pour rafraîchir le tableau avec nouveau livre ajoute
	});         
}

function allPersonnes(){	

	
	let wsUrl = "./api-bibliotheque/personne";
	
	makeAjaxGetRequest(wsUrl,function(responseJson){
		let personnesJs = JSON.parse(responseJson);
		
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le re-remplir
		for(let personne of personnesJs){
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = personne.idPersonne;
			(row.insertCell(1)).innerHTML = personne.prenom;
			(row.insertCell(2)).innerHTML = personne.nom;
			(row.insertCell(3)).innerHTML = personne.email;
			(row.insertCell(4)).innerHTML = personne.telephone;
			(row.insertCell(5)).innerHTML = personne.adresse;
		}
	});
	
}