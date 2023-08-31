// Variable pour stocker le personneJs actuel
let currentPersonneJs;

window.onload=function(){
	allPersonnes();
	(document.getElementById("btnAjout"))
	   .addEventListener("click",addPersonne);   
}

// Gestion de l'update personne et de l'auto remplissage des champs
let tableBody = document.getElementById("table_body");

tableBody.addEventListener("click", function(event){
	
	if(event.target.classList.contains("updateBtn")){
		let idPersonne = event.target.getAttribute("data-idpersonne");
		let prenom = event.target.parentNode.parentNode.querySelector("td:nth-child(2)").textContent;
		let nom = event.target.parentNode.parentNode.querySelector("td:nth-child(3)").textContent;
		let email = event.target.parentNode.parentNode.querySelector("td:nth-child(4)").textContent;
		let telephone = event.target.parentNode.parentNode.querySelector("td:nth-child(5)").textContent;
		let adresse = event.target.parentNode.parentNode.querySelector("td:nth-child(6)").textContent;
	
	currentPersonneJs = {
		idPersonne: idPersonne,
		prenom: prenom,
		nom: nom,
		email: email,
		telephone: telephone,
		adresse: adresse,
	};
	console.log(currentPersonneJs);
	
	document.getElementById("inputUpdatePersonne").value = idPersonne;
	document.getElementById("inputUpdatePrenom").value = prenom;
	document.getElementById("inputUpdateNom").value = nom;
	document.getElementById("inputUpdateEmail").value = email;
	document.getElementById("inputUpdateTelephone").value = telephone;
	document.getElementById("inputUpdateAdresse").value = adresse;
	}
	
	if (event.target.classList.contains("deleteBtn")){
		let idPersonne = event.target.getAttribute("data-idpersonne");
		deletePersonne(idPersonne);
	}	
});


document.getElementById("btnUpdatePersonne").addEventListener("click", function(){
	if(currentPersonneJs != null){
		console.log(currentPersonneJs);
		console.log("Button clicked");
		updatePersonne(currentPersonneJs.idPersonne);
		console.log(currentPersonneJs);
		currentPersonneJs = null;
	}
});



function addPersonne(){	
	
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
			
			let cellAction = row.insertCell(6); // Ajouter la cellule pour les boutons
            let updateBtn = document.createElement("button");
            updateBtn.className = "updateBtn";
            updateBtn.setAttribute("data-idpersonne", personne.idPersonne);
            updateBtn.textContent = "Update";
            
            let deleteBtn = document.createElement("button");
            deleteBtn.className = "deleteBtn";
            deleteBtn.setAttribute("data-idpersonne", personne.idPersonne);
            deleteBtn.textContent = "Delete";
            
            cellAction.appendChild(updateBtn);
            cellAction.appendChild(deleteBtn);
		}
	});
	
}

function updatePersonne(idPersonne){
	let wsUrl = "./api-bibliotheque/personne/" + idPersonne;
	
	let updatedId = document.getElementById("inputUpdatePersonne").value;
	let updatedPrenom = document.getElementById("inputUpdatePrenom").value;
	let updatedNom = document.getElementById("inputUpdateNom").value;
	let updatedEmail = document.getElementById("inputUpdateEmail").value;
	let updatedTelephone = document.getElementById("inputUpdateTelephone").value;
	let updatedAdresse = document.getElementById("inputUpdateAdresse").value;
	
	let updatedPersonne = {
        idPersonne: updatedId,
        prenom: updatedPrenom,
        nom: updatedNom,
        email: updatedEmail,
        telephone: updatedTelephone,
        adresse: updatedAdresse,
    };
    
    console.log(updatedPersonne);	

    makeAjaxPutRequest(wsUrl, JSON.stringify(updatedPersonne), function(responseJson) {
        console.log("Personne mis a jour : " + responseJson);
        allPersonnes(); // Actualisez le tableau avec les données mises à jour
    });
}