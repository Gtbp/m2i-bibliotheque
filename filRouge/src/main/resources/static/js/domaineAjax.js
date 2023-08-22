window.onload=function(){
	allDomaines();
	(document.getElementById("btnAjout"))
	   .addEventListener("click",ajouterDomaine);   
}

function ajouterDomaine(){	
	
	let nom = (document.getElementById("inputNom")).value;
	let description = (document.getElementById("inputDescription")).value;
	
	
	
	let domaineJs = { nom : nom,
	                 description : description     
	                  };
	let domaineJson = JSON.stringify(domaineJs) ;  
	let wsUrl = "./api-bibliotheque/domaine";   
	makeAjaxPostRequest(wsUrl,domaineJson,function (responseJson){
		console.log("responseJson="+responseJson);
		allDomaines(); //pour rafraîchir le tableau avec nouveau domaine ajoute
	});         
}
	
function allDomaines(){	

	
	let wsUrl = "./api-bibliotheque/domaine";
	
	makeAjaxGetRequest(wsUrl,function(responseJson){
		let domaineJs = JSON.parse(responseJson);
		
		let bodyElt = document.getElementById("table_body");
		bodyElt.innerHTML="";//vider le tableau avant de le re-remplir
		for(let domaine of domaineJs){
			let row = bodyElt.insertRow(-1);
			(row.insertCell(0)).innerHTML = domaine.idDomaine;
			(row.insertCell(1)).innerHTML = domaine.nom;
			(row.insertCell(2)).innerHTML = domaine.description;
			
		}
	});
	
}