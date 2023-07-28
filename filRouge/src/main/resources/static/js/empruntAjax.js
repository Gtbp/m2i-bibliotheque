window.onload=function(){
	allEmprunts();
	(document.getElementById("btnAjout"))
	   .addEventListener("click",ajouterEmprunt);   
}

function ajouterEmprunt(){	
	
	let dateFin = (document.getElementById("inputDateFin")).value;
	let type = (document.getElementById("inputType")).value;
	let lecteur = (document.getElementById("inputLecteur")).value;
	let livre = (document.getElementById("inputLivre")).value;
	
	
	let empruntJs = { date_debut : titre,
	                 date_fin : dateFin,
	                 type : type, 
	                 lecteur : lecteur, 
	                 livre : livre       
	                  };
	let empruntJson = JSON.stringify(empruntJs) ;  
	let wsUrl = "./api-bibliotheque/emprunt";   
	makeAjaxPostRequest(wsUrl,empruntJson,function (responseJson){
		console.log("responseJson="+responseJson);
		allEmprunts(); //pour rafraîchir le tableau avec nouveau livre ajoute
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
			(row.insertCell(4)).innerHTML = emprunt.lecteur.prenom;
			(row.insertCell(5)).innerHTML = emprunt.livre.titre;
			
		}
	});
	
}