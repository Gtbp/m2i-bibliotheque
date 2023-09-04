let currentDomaineJs;

window.onload=function(){
	allDomaines();
	(document.getElementById("btnAjout"))
	   .addEventListener("click",addDomaine);   
}

let tableBody = document.getElementById("table_body");

tableBody.addEventListener("click", function(event) {
   
    
    if (event.target.classList.contains("updateBtn")) {
        let idDomaine = event.target.getAttribute("data-idDomaine");
        let nom = event.target.parentNode.parentNode.querySelector("td:nth-child(2)").textContent;
        let description = event.target.parentNode.parentNode.querySelector("td:nth-child(3)").textContent;
       
        
        // Stockez le livreJs actuel dans la variable currentLivreJs
        currentDomaineJs = {
            idDomaine: idDomaine,
            nom : nom,
			description
        };
        
        console.log(currentDomaineJs);
        
        // Remplissez les champs du formulaire de mise à jour
        document.getElementById("inputUpdateDomaine").value = idDomaine;
        document.getElementById("inputUpdateNom").value = nom;
        document.getElementById("inputUpdateDescription").value = description;
        
    }
    
    if (event.target.classList.contains("deleteBtn")) {
        let idDomaine = event.target.getAttribute("data-idDomaine");
        deleteDomaine(idDomaine);
    }
   
});


document.getElementById("btnUpdateDomaine").addEventListener( "click", function() {
	
    if (currentDomaineJs != null) {
        console.log(currentDomaineJs);
        updateDomaine(currentDomaineJs.idDomaine);
       console.log(currentDomaineJs);
	   currentDomaineJs = null; // Réinitialisez la variable après la mise à jour
        
    }
});


function addDomaine(){	
	
	let nom = (document.getElementById("inputNom")).value;
	let description = (document.getElementById("inputDescription")).value;

	let domaineJs = { nom : nom,
	                 description : description     
	                  };
	let domaineJson = JSON.stringify(domaineJs) ;  
	let wsUrl = "/filRouge/api-bibliotheque/domaine";   
	makeAjaxPostRequest(wsUrl,domaineJson,function (responseJson){
		console.log("responseJson="+responseJson);
		allDomaines(); //pour rafraîchir le tableau avec nouveau domaine ajoute
	});         
}
	
function allDomaines(){	

	
	let wsUrl = "/filRouge/api-bibliotheque/domaine";
	
	makeAjaxGetRequest(wsUrl,function(responseJson){
		let domaineJs = JSON.parse(responseJson);
		
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le re-remplir
		for(let domaine of domaineJs){
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = domaine.idDomaine;
			(row.insertCell(1)).innerHTML = domaine.nom;
			(row.insertCell(2)).innerHTML = domaine.description;

			let cellAction = row.insertCell(3); // Ajouter la cellule pour les boutons
            let updateBtn = document.createElement("button");
            updateBtn.className = "updateBtn button-light-blue";
            updateBtn.setAttribute("data-idDomaine", domaine.idDomaine);
            updateBtn.textContent = "Modifier";
            
            let deleteBtn = document.createElement("button");
            deleteBtn.className = "deleteBtn button-grey";
            deleteBtn.setAttribute("data-idDomaine", domaine.idDomaine);
            deleteBtn.textContent = "Supprimer";
            
            cellAction.appendChild(updateBtn);
            cellAction.appendChild(deleteBtn);
			
		}
	});
	
}

function deleteDomaine(idDomaine) {
    let wsUrl = "/filRouge/api-bibliotheque/domaine/" + idDomaine;

    makeAjaxDeleteRequest(wsUrl, function(responseJson) {
        console.log("Domaine supprimé : " + responseJson);
        allDomaines(); // Actualisez le tableau après la suppression
    });
}


function updateDomaine(idDomaine) {
    let wsUrl = "/filRouge/api-bibliotheque/domaine/" + idDomaine;

	let updatedId =	document.getElementById("inputUpdateDomaine").value;
    let updatedNom =   document.getElementById("inputUpdateNom").value;
    let updatedDescription =    document.getElementById("inputUpdateDescription").value;
 
    let updatedDomaine = {
        idDomaine: updatedId,
        nom: updatedNom,
        description: updatedDescription
    };
    
    console.log(updatedDomaine);	

    makeAjaxPutRequest(wsUrl, JSON.stringify(updatedDomaine), function(responseJson) {
        console.log("Domaine mis à jour : " + responseJson);
        allDomaines(); // Actualisez le tableau avec les données mises à jour
    });
}