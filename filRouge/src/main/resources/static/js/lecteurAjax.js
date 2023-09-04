// Variable pour stocker le personneJs actuel
let currentLecteurJs;

window.onload=function(){
	allLecteurs();
	(document.getElementById("btnAjout"))
	   .addEventListener("click",addLecteur);   
}

// Gestion de l'update lecteur et de l'auto remplissage des champs
let tableBody = document.getElementById("table_body");

tableBody.addEventListener("click", function(event){
	
	if(event.target.classList.contains("updateBtn")){
		let idLecteur = event.target.getAttribute("data-idpersonne");
		let prenom = event.target.parentNode.parentNode.querySelector("td:nth-child(2)").textContent;
		let nom = event.target.parentNode.parentNode.querySelector("td:nth-child(3)").textContent;
		let email = event.target.parentNode.parentNode.querySelector("td:nth-child(4)").textContent;
		let telephone = event.target.parentNode.parentNode.querySelector("td:nth-child(5)").textContent;
		let adresse = event.target.parentNode.parentNode.querySelector("td:nth-child(6)").textContent;
	
	currentLecteurJs = {
		idPersonne: idLecteur,
		prenom: prenom,
		nom: nom,
		email: email,
		telephone: telephone,
		adresse: adresse,
	};
	console.log(currentLecteurJs);
	
	document.getElementById("inputUpdateLecteur").value = idLecteur;
	document.getElementById("inputUpdatePrenom").value = prenom;
	document.getElementById("inputUpdateNom").value = nom;
	document.getElementById("inputUpdateEmail").value = email;
	document.getElementById("inputUpdateTelephone").value = telephone;
	document.getElementById("inputUpdateAdresse").value = adresse;
	//document.getElementById("selectUpdateType").value = type
	}
	
	if (event.target.classList.contains("deleteBtn")){
		let idLecteur = event.target.getAttribute("data-idpersonne");
		deleteLecteur(idLecteur);
	}	
});


document.getElementById("btnUpdateLecteur").addEventListener("click", function(){
	if(currentLecteurJs != null){
		console.log(currentLecteurJs);
		console.log("Button clicked");
		updateLecteur(currentLecteurJs.idPersonne);
		console.log(currentLecteurJs);
		currentLecteurJs = null;
	}
});



function addLecteur(){	
	
	let prenom = (document.getElementById("inputPrenom")).value;
	let nom = (document.getElementById("inputNom")).value;
	let email = (document.getElementById("inputEmail")).value;
	let telephone = (document.getElementById("inputTelephone")).value;
	let adresse = (document.getElementById("inputAdresse")).value;
	
	let lecteurJs = { prenom : prenom,
	                 nom : nom,
	                 email : email, 
	                 telephone : telephone, 
	                 adresse : adresse, 
	                 };
	let lecteurJson = JSON.stringify(lecteurJs) ;  
	let wsUrl = "/filRouge/api-bibliotheque/lecteur";   
	makeAjaxPostRequest(wsUrl,lecteurJson,function (responseJson){
		console.log("responseJson="+responseJson);
		allLecteurs(); //pour rafraîchir le tableau avec nouveau livre ajoute
	});         
}

function allLecteurs(){	

	let wsUrl = "/filRouge/api-bibliotheque/lecteur";
	
	makeAjaxGetRequest(wsUrl,function(responseJson){
		let lecteursJs = JSON.parse(responseJson);
		
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le re-remplir
		for(let lecteur of lecteursJs){
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = lecteur.idPersonne;
			(row.insertCell(1)).innerHTML = lecteur.prenom;
			(row.insertCell(2)).innerHTML = lecteur.nom;
			(row.insertCell(3)).innerHTML = lecteur.email;
			(row.insertCell(4)).innerHTML = lecteur.telephone;
			(row.insertCell(5)).innerHTML = lecteur.adresse;


			
			let cellAction = row.insertCell(6); // Ajouter la cellule pour les boutons
            let updateBtn = document.createElement("button");
            updateBtn.className = "updateBtn button-light-blue";
            updateBtn.setAttribute("data-idpersonne", lecteur.idPersonne);
            updateBtn.textContent = "Modifier";
            
            let deleteBtn = document.createElement("button");
            deleteBtn.className = "deleteBtn button-grey";
            deleteBtn.setAttribute("data-idpersonne", lecteur.idPersonne);
            deleteBtn.textContent = "Supprimer";
            
            cellAction.appendChild(updateBtn);
            cellAction.appendChild(deleteBtn);
            
            
            
		}
	});
	
}

function deleteLecteur(idLecteur) {
    let wsUrl = "/filRouge/api-bibliotheque/lecteur/" + idLecteur;

    makeAjaxDeleteRequest(wsUrl, function(responseJson) {
        console.log("Lecteur supprimé : " + responseJson);
        allLecteurs(); // Actualisez le tableau après la suppression
    });
}

function updateLecteur(idLecteur){
	let wsUrl = "/filRouge/api-bibliotheque/lecteur/" + idLecteur;
		
	let updatedId = document.getElementById("inputUpdateLecteur").value;
	let updatedPrenom = document.getElementById("inputUpdatePrenom").value;
	let updatedNom = document.getElementById("inputUpdateNom").value;
	let updatedEmail = document.getElementById("inputUpdateEmail").value;
	let updatedTelephone = document.getElementById("inputUpdateTelephone").value;
	let updatedAdresse = document.getElementById("inputUpdateAdresse").value;
	
	let updatedLecteur = {
        idPersonne: updatedId,
        prenom: updatedPrenom,
        nom: updatedNom,
        email: updatedEmail,
        telephone: updatedTelephone,
        adresse: updatedAdresse,
    };
    
    console.log(updatedLecteur);	

    makeAjaxPutRequest(wsUrl, JSON.stringify(updatedLecteur), function(responseJson) {
        console.log("Lecteur mis a jour : " + responseJson);
        allLecteurs(); // Actualisez le tableau avec les données mises à jour
    });
}