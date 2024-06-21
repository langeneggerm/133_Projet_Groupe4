$(document).ready(function(){
var btn = document.getElementsByClassName("btnAcheter");
    $.getScript("../js/services/httpService.js", function () {
        console.log("servicesHttp.js chargé !");
        getAllNerf(successCallback, errorCallBack);
    });

})

function successCallback(data) {

    console.log(data);
    console.log("chargement des Nerfs effectué");



  }

  function successCallback(data) {
    var result = false;
    var lastNerf = null;
    var newData = JSON.parse(data);
    var parentElement = document.getElementById("produits");
    // Vider le contenu en affectant une chaîne vide à innerHTML
    parentElement.innerHTML = "";
    newData.forEach((nerf) => {
      try {
        var productDiv = document.createElement("div");
        productDiv.className = "produit";
        var h3 = document.createElement("h3");
        h3.innerText = nerf.nom;
        productDiv.appendChild(h3);
        var description = document.createElement("p");
        description.innerText = nerf.description;
        productDiv.appendChild(description);
        var stockP = document.createElement("p");
        stockP.innerHTML = `Stock: <span id="stock${nerf.id}">${nerf.quantite}</span>`;
        productDiv.appendChild(stockP);
        var prixP = document.createElement("p");
        prixP.innerHTML = `Prix: <span >${nerf.prix}</span>`;
        productDiv.appendChild(prixP);

        var button = document.createElement("button");
        button.innerText = "Acheter";
        button.id = nerf.id;
        button.onclick = function () {
      
        
        };


     parentElement.appendChild(button)
        parentElement.appendChild(productDiv);
        result = true;
        lastNerf = nerf;
      } catch (error) {
        result = false;
      }
    });
    if (result) {
      alert("Tous les nerfs ont été chargés");
    } else {
      alert(
        "Il y a eu un problème sur le chargement du nerf :" +
          lastNerf.id +
          " - " +
          lastNerf.nom
      );
    }
  }

function errorCallBack() { 
    console.log("chargement des Nerfs a échoué");
 }





// Appeler la fonction pour créer les boutons
