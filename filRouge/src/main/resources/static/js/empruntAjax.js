let currentEmpruntJs;

window.onload=function(){
	allLivres();
	allLecteurs();
	allEmprunts();
	(document.getElementById("btnAjout"))
	   .addEventListener("click",addEmprunt);   
}

let tableBody = document.getElementById("table_body");

tableBody.addEventListener("click", function(event) {
   
    
    if (event.target.classList.contains("updateBtn")) {
        let idEmprunt = event.target.getAttribute("data-idEmprunt");
        let date_debut = event.target.parentNode.parentNode.querySelector("td:nth-child(2)").textContent;
        let date_fin = event.target.parentNode.parentNode.querySelector("td:nth-child(3)").textContent;
        let type = event.target.parentNode.parentNode.querySelector("td:nth-child(4)").textContent;
        let lecteur = event.target.parentNode.parentNode.querySelector("td:nth-child(5)").textContent;
        let livre = event.target.parentNode.parentNode.querySelector("td:nth-child(6)").textContent;
        
        // Stockez l'empruntJs actuel dans la variable currentEmpruntJs
        currentEmpruntJs = {
            idEmprunt: idEmprunt,
            date_debut: date_debut,
            date_fin: date_fin,
            type: type,
            lecteur: lecteur,
            livre: livre
        };

        
        console.log(currentEmpruntJs);
        
        // Remplissez les champs du formulaire de mise à jour
        document.getElementById("inputUpdateEmprunt").value = idEmprunt;
        document.getElementById("inputUpdateDateDebut").value = date_debut;
        document.getElementById("inputUpdateDateFin").value = date_fin;
        document.getElementById("selectUpdateType").value = type;
        document.getElementById("selectUpdateLecteur").value = lecteur;
        document.getElementById("selectUpdateLivre").value = livre;
        
    }

	if (event.target.classList.contains("deleteBtn")) {
        let idEmprunt = event.target.getAttribute("data-idEmprunt");
        deleteEmprunt(idEmprunt);
    }
});

document.getElementById("btnUpdateEmprunt").addEventListener( "click", function() {

	
    if (currentEmpruntJs != null) {
        console.log(currentEmpruntJs);
        console.log("Button clicked")
        updateEmprunt(currentEmpruntJs.idEmprunt);
       console.log(currentEmpruntJs);
        currentLivreJs = null; // Réinitialisez la variable après la mise à jour
        
    }
});

function addEmprunt(){	
	
	
		function formatDate(date) {
			const d = new Date(date);
			const year = d.getFullYear();
			const month = String(d.getMonth() + 1).padStart(2, '0'); // 
			const day = String(d.getDate()).padStart(2, '0');
		
			return `${year}-${month}-${day}`;
		}
	
	const today = formatDate(Date.now());


	let dateFin = (document.getElementById("inputDateFin")).value;
	let type = (document.getElementById("selectType")).value;

	// lecteur
	let lecteurId = document.getElementById("selectLecteur").value;
	let lecteurNom = document.getElementById("selectLecteur").options[document.getElementById("selectLecteur").selectedIndex].text;
	let lecteur = { idPersonne: lecteurId, nom: lecteurNom };

	// livre
	let livreId = (document.getElementById("selectLivre")).value;
	let livreTitre = document.getElementById("selectLivre").options[document.getElementById("selectLivre").selectedIndex].text;
	let livre = { idLivre: livreId, titre: livreTitre };
	
	
	let empruntJs = { date_debut : today,
	                 date_fin : dateFin,
	                 type : type, 
	                 lecteur : lecteur, 
	                 livre : livre    
	                  };
	let empruntJson = JSON.stringify(empruntJs) ;  
	console.log(empruntJson);
	let wsUrl = "./api-bibliotheque/emprunt";   
	makeAjaxPostRequest(wsUrl,empruntJson,function (responseJson){
		console.log("responseJson="+responseJson);
		allEmprunts(); //pour rafraîchir le tableau avec nouvel emprunt ajoute
	});         
}
	
function allLivres(){
	
	let wsUrl = "./api-bibliotheque/livre";
	
	makeAjaxGetRequest(wsUrl,function(responseJson){
		let livresJs = JSON.parse(responseJson);
	
		let selectElt = document.getElementById("selectLivre");
		for(let livre of livresJs){
			let option = document.createElement("option");
			option.value = livre.idLivre;
			option.innerHTML=livre.titre;
			selectElt.appendChild(option);
			}

		let selectUpdateElt = document.getElementById("selectUpdateLivre");
		for(let livre of livresJs){
			let option = document.createElement("option");
			option.value = livre.idLivre;
			option.innerHTML=livre.titre;
			selectUpdateElt.appendChild(option);
			}		

			});
	
}

function allLecteurs(){
		
	let wsUrl = "./api-bibliotheque/lecteur";
	
	makeAjaxGetRequest(wsUrl,function(responseJson){
		let lecteursJs = JSON.parse(responseJson);
	
		let selectElt = document.getElementById("selectLecteur");
		for(let lecteur of lecteursJs){
			let option = document.createElement("option");
			option.value = lecteur.idPersonne;
			option.innerHTML=lecteur.nom;
			selectElt.appendChild(option);
			}

		let selectUpdateElt = document.getElementById("selectUpdateLecteur");
		for(let lecteur of lecteursJs){
			let option = document.createElement("option");
			option.value = lecteur.idPersonne;
			option.innerHTML=lecteur.nom;
			selectUpdateElt.appendChild(option);
		}
	});

		
}

function allEmprunts(){	

	let wsUrl = "./api-bibliotheque/emprunt";
	
	makeAjaxGetRequest(wsUrl,function(responseJson){
		let empruntJs = JSON.parse(responseJson);
		
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le re-remplir
		for(let emprunt of empruntJs){
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = emprunt.idEmprunt;
			(row.insertCell(1)).innerHTML = emprunt.date_debut;
			(row.insertCell(2)).innerHTML = emprunt.date_fin;
			(row.insertCell(3)).innerHTML = emprunt.type;
			(row.insertCell(4)).innerHTML = emprunt.lecteur.nom;
			(row.insertCell(5)).innerHTML = emprunt.livre.titre;
			
			let cellAction = row.insertCell(6); // Ajouter la cellule pour les boutons
            let updateBtn = document.createElement("button");
            updateBtn.className = "updateBtn button-light-blue";
            updateBtn.setAttribute("data-idEmprunt", emprunt.idEmprunt);
            updateBtn.textContent = "Modifier";
            
            let deleteBtn = document.createElement("button");
            deleteBtn.className = "deleteBtn button-grey";
            deleteBtn.setAttribute("data-idEmprunt", emprunt.idEmprunt);
            deleteBtn.textContent = "Supprimer";
            
            cellAction.appendChild(updateBtn);
            cellAction.appendChild(deleteBtn);
		}
	});

}

function deleteEmprunt(idEmprunt) {
    let wsUrl = "./api-bibliotheque/emprunt/" + idEmprunt;

    makeAjaxDeleteRequest(wsUrl, function(responseJson) {
        console.log("Emprunt supprimé : " + responseJson);
        allEmprunts(); // Actualisez le tableau après la suppression
    });
}


function updateEmprunt(idEmprunt) {
    let wsUrl = "./api-bibliotheque/emprunt/" + idEmprunt;

	let updatedId =	document.getElementById("inputUpdateEmprunt").value;
    let updatedDateDebut =   document.getElementById("inputUpdateDateDebut").value;
    let updatedDateFin =    document.getElementById("inputUpdateDateFin").value;
    let updatedType =    document.getElementById("selectUpdateType").value;

    let updatedLecteurId = document.getElementById("selectUpdateLecteur").value;
	let updatedLecteurNom = document.getElementById("selectUpdateLecteur").options[document.getElementById("selectUpdateLecteur").selectedIndex].text;
	let updatedLecteur = { idPersonne: updatedLecteurId, nom: updatedLecteurNom };

	let updatedLivreId =    document.getElementById("selectUpdateLivre").value;
	let updatedLivreNom =    document.getElementById("selectUpdateLivre").options[document.getElementById("selectUpdateLivre").selectedIndex].text;
	let updatedLivre = { idLivre: updatedLivreId, titre: updatedLivreNom };

    let updatedEmprunt = { 
		idEmprunt : updatedId,
		date_debut : updatedDateDebut,
		date_fin : updatedDateFin,
		type : updatedType, 
		lecteur : updatedLecteur, 
		livre : updatedLivre    
		 };
    
    console.log(updatedEmprunt);	

    makeAjaxPutRequest(wsUrl, JSON.stringify(updatedEmprunt), function(responseJson) {
        console.log("Emprunt mis à jour : " + responseJson);
        allEmprunts(); // Actualisez le tableau avec les données mises à jour
    });
}
