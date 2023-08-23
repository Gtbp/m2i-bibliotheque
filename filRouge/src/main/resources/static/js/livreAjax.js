window.onload=function(){
	allDomaines();
	allLivres();
	(document.getElementById("btnAjout"))
	   .addEventListener("click",addLivre);
	  
/*	(document.getElementById("btnDelete"))
	.addEventListener("click",deleteLivre);*/
	
/*	(document.getElementById("btnUpdate"))
	.addEventListener("click",updateLivre);*/      
}

function addLivre(){	
	
	let titre = (document.getElementById("inputTitre")).value;
	let auteur = (document.getElementById("inputAuteur")).value;
	let editeur = (document.getElementById("inputEditeur")).value;
	let domaine = (document.getElementById("selectDomaine")).value;
	let livreJs = { titre : titre,
	                 auteur : auteur,
	                 editeur : editeur, 
	                 dispo : true, 
	                 etat : "BON_ETAT", 
	                 domaine : parseInt(domaine)       
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
	
		let selectElt = document.getElementById("selectDomaine");
		for(let domaine of domainesJs){
			let option = document.createElement("option");
			option.value = domaine.idDomaine;
			option.innerHTML=domaine.nom;
			selectElt.appendChild(option);
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
			(row.insertCell(6)).innerHTML = livre.domaine;
		}
	});
	
}
function deleteLivre(){
	let wsUrl= "./api-bibliotheque/${idLivre}";
	makeAjaxDeleteRequest(wsUrl);
};

function updateLivre(){
	let titre = (document.getElementById("inputTitre")).value;
	let auteur = (document.getElementById("inputAuteur")).value;
	let editeur = (document.getElementById("inputEditeur")).value;
	
	let livreJs = { titre : titre,
	                 auteur : auteur,
	                 editeur : editeur, 
	                 dispo : true, 
	                 etat : "BON_ETAT", 
	                 domaine : 1       
	                  };
	let livreJson = JSON.stringify(livreJs) ;  
	let wsUrl = "./api-bibliotheque/livre";   
	makeAjaxPutRequest(wsUrl,livreJson,function (responseJson){
		console.log("responseJson="+responseJson);
		allLivres(); //pour rafraîchir le tableau avec nouveau livre ajoute
	});         
};