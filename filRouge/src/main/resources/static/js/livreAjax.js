let currentLivreJs; // Variable pour stocker le livreJs actuel

window.onload=function(){
	allDomaines();
	allLivres();
	(document.getElementById("btnAjout"))
	   .addEventListener("click",addLivre);       
}

let tableBody = document.getElementById("table_body");

tableBody.addEventListener("click", function(event) {
   
    
    if (event.target.classList.contains("updateBtn")) {
        let idLivre = event.target.getAttribute("data-idlivre");
        let titre = event.target.parentNode.parentNode.querySelector("td:nth-child(2)").textContent;
        let auteur = event.target.parentNode.parentNode.querySelector("td:nth-child(3)").textContent;
        let editeur = event.target.parentNode.parentNode.querySelector("td:nth-child(4)").textContent;
        let domaine = event.target.parentNode.parentNode.querySelector("td:nth-child(7)").textContent;
        
        // Stockez le livreJs actuel dans la variable currentLivreJs
        currentLivreJs = {
            idLivre: idLivre,
            titre: titre,
            auteur: auteur,
            editeur: editeur,
            dispo: true,
            etat: " ",
            domaine: domaine
        };
        
        console.log(currentLivreJs);
        
        // Remplissez les champs du formulaire de mise à jour
        document.getElementById("inputUpdateLivre").value = idLivre;
        document.getElementById("inputUpdateTitre").value = titre;
        document.getElementById("inputUpdateAuteur").value = auteur;
        document.getElementById("inputUpdateEditeur").value = editeur;
        document.getElementById("selectUpdateDispo").value = currentLivreJs.dispo;
        document.getElementById("selectUpdateEtat").value = currentLivreJs.etat;
        document.getElementById("selectUpdateDomaine").value = domaine;
        
    }
    
    if (event.target.classList.contains("deleteBtn")) {
        let idLivre = event.target.getAttribute("data-idlivre");
        deleteLivre(idLivre);
    }
   
});


document.getElementById("btnUpdateLivre").addEventListener( "click", function() {
	
    if (currentLivreJs != null) {
        console.log(currentLivreJs);
        updateLivre(currentLivreJs.idLivre);
       console.log(currentLivreJs);
        currentLivreJs = null; // Réinitialisez la variable après la mise à jour
        
    }
});


function addLivre(){	
	
	let titre = (document.getElementById("inputTitre")).value;
	let auteur = (document.getElementById("inputAuteur")).value;
	let editeur = (document.getElementById("inputEditeur")).value;
	let dispo = document.getElementById("selectDispo").value;
    let etat =    document.getElementById("selectEtat").value;
	
	
	let domaineId = document.getElementById("selectDomaine").value;
    let domaineNom = document.getElementById("selectDomaine").options[document.getElementById("selectDomaine").selectedIndex].text;
    let domaine = { idDomaine: domaineId, nom: domaineNom }; // Créez un objet Domaine
	
	let livreJs = { titre : titre,
	                 auteur : auteur,
	                 editeur : editeur, 
	                 dispo : dispo, 
	                 etat : etat, 
	                 domaine : domaine       
	                  };
	let livreJson = JSON.stringify(livreJs) ;  
	let wsUrl = "./api-bibliotheque/livre";   
	makeAjaxPostRequest(wsUrl,livreJson,function (responseJson){
		console.log("responseJson="+responseJson);
		allLivres(); //pour rafraîchir le tableau avec nouveau livre ajoute
	});         
}

function allDomaines(){
	let wsUrl = "./api-bibliotheque/domaine";
	
	makeAjaxGetRequest(wsUrl,function(responseJson){
		let domainesJs = JSON.parse(responseJson);
        console.log(domainesJs);
		let selectElt = document.getElementById("selectDomaine");
		for(let domaine of domainesJs){
			let option = document.createElement("option");
			option.value = domaine.idDomaine;
			option.innerHTML=domaine.nom;
			selectElt.appendChild(option);
			}
			
        let selectUpdateElt = document.getElementById("selectUpdateDomaine");
    for(let domaine of domainesJs){
        let option = document.createElement("option");
        option.value = domaine.idDomaine;
        option.innerHTML=domaine.nom;
        selectUpdateElt.appendChild(option);
        }	
			
			});
}			
	
function allLivres(){	

	let wsUrl = "./api-bibliotheque/livre";
	
	makeAjaxGetRequest(wsUrl,function(responseJson){
		let livresJs = JSON.parse(responseJson);
		
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le re-remplir
		for(let livre of livresJs){
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = livre.idLivre;
			(row.insertCell(1)).innerHTML = livre.titre;
			(row.insertCell(2)).innerHTML = livre.auteur;
			(row.insertCell(3)).innerHTML = livre.editeur;
			(row.insertCell(4)).innerHTML = livre.dispo;
			(row.insertCell(5)).innerHTML = livre.etat;
			(row.insertCell(6)).innerHTML = livre.domaine.nom;
			
			let cellAction = row.insertCell(7); // Ajouter la cellule pour les boutons
            let updateBtn = document.createElement("button");
            updateBtn.className = "updateBtn btn btn-warning";
            updateBtn.setAttribute("data-idlivre", livre.idLivre);
            updateBtn.textContent = "Update";
            
            let deleteBtn = document.createElement("button");
            deleteBtn.className = "deleteBtn btn btn-danger";
            deleteBtn.setAttribute("data-idlivre", livre.idLivre);
            deleteBtn.textContent = "Delete";
            
            cellAction.appendChild(updateBtn);
            cellAction.appendChild(deleteBtn);
		}
	});
	
}

function deleteLivre(idLivre) {
    let wsUrl = "./api-bibliotheque/livre/" + idLivre;

    makeAjaxDeleteRequest(wsUrl, function(responseJson) {
        console.log("Livre supprimé : " + responseJson);
        allLivres(); // Actualisez le tableau après la suppression
    });
}

function updateLivre(idLivre) {
    let wsUrl = "./api-bibliotheque/livre/" + idLivre;

	let updatedId =	document.getElementById("inputUpdateLivre").value;
    let updatedTitre =   document.getElementById("inputUpdateTitre").value;
    let updatedAuteur =    document.getElementById("inputUpdateAuteur").value;
    let updatedEditeur =    document.getElementById("inputUpdateEditeur").value;
    let updatedDispo = document.getElementById("selectUpdateDispo").value;
    let updatedEtat =    document.getElementById("selectUpdateEtat").value;

    
 	let updatedDomaineId = document.getElementById("selectUpdateDomaine").value;
 	let updatedDomaineNom = document.getElementById("selectUpdateDomaine").options[document.getElementById("selectUpdateDomaine").selectedIndex].text;
	let updatedDomaine = { idDomaine: updatedDomaineId, nom: updatedDomaineNom };

    let updatedLivre = {
        idLivre: updatedId,
        titre: updatedTitre,
        auteur: updatedAuteur,
        editeur: updatedEditeur,
        dispo: updatedDispo,
        etat: updatedEtat,
        domaine: updatedDomaine
    };
    
    console.log(updatedLivre);	

    makeAjaxPutRequest(wsUrl, JSON.stringify(updatedLivre), function(responseJson) {
        console.log("Livre mis à jour : " + responseJson);
        allLivres(); // Actualisez le tableau avec les données mises à jour
    });
}
