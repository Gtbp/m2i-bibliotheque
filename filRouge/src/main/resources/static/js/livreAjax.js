window.onload=function(){
	allLivres();
	(document.getElementById("btnAjout"))
	   .addEventListener("click",ajouterLivre);   
}

function ajouterLivre(){	
	
	let titre = (document.getElementById("inputTitre")).value;
	let auteur = (document.getElementById("inputAuteur")).value;
	let editeur = (document.getElementById("inputEditeur")).value;
	let domaine = (document.getElementById("inputDomaine")).value;
	
	let livreJs = { titre : titre,
	                 auteur : auteur,
	                 editeur : editeur, 
	                 dispo : true, 
	                 etat : "BON_ETAT", 
	                 domaine : domaine        
	                  };
	let livreJson = JSON.stringify(livreJs) ;  
	let wsUrl = "./api-biblio/livre";   
	makeAjaxPostRequest(wsUrl,livreJson,function (responseJson){
		console.log("responseJson="+responseJson);
		allLivres(); //pour rafraîchir le tableau avec nouveau livre ajoute
	});         
}
	
function allLivres(){	

	
	let wsUrl = "./api-biblio/livre";
	
	makeAjaxGetRequest(wsUrl,function(responseJson){
		let livresJs = JSON.parse(responseJson);
		
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le re-remplir
		for(let livre of livresJs){
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = livre.idLivre;
			(row.insertCell(1)).innerHTML = livre.titre;
			(row.insertCell(2)).innerHTML = livre.auteur;
			(row.insertCell(3)).innerHTML = livre.auteur;
			(row.insertCell(4)).innerHTML = livre.editeur;
			(row.insertCell(5)).innerHTML = livre.dispo;
			(row.insertCell(6)).innerHTML = livre.etat;
			(row.insertCell(7)).innerHTML = livre.domaine;
		}
	});
	
}