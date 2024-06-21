$(document).ready(function () {
  http = new HttpService();
  indexCtrl = new StockCtrl();
  StockCtrl.chargeCatalogue();
});

class StockCtrl {
  constructor() {
    $("#logout").on("click", StockCtrl.logout);
  }

  static logout(){
    http.deconnecter(StockCtrl.successCallbackdisconnect,StockCtrl.error);
  }
  
  static successCallbackdisconnect(){
    sessionStorage.clear();
    var newURL = '../index.html';
    alert("La déconnexion a réussi !");
    window.location.href = newURL;
  }

  static chargeCatalogue() {
    console.log("test");
    http.getAllNerf(StockCtrl.loadCatalogueSuccess, StockCtrl.error);
  }

  static loadCatalogueSuccess(data) {
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
        button.innerText = "Ajouter Stock";
        button.id = nerf.id;
        button.onclick = function () {
          StockCtrl.ajouterStock(nerf.id);
        };
        productDiv.appendChild(button);
        var button2 = document.createElement("button");
        button2.innerText = "Changer prix";
        button2.setAttribute("data-id", nerf.id);
        button2.setAttribute("data-prix", nerf.prix);
        button2.onclick = function () {
          StockCtrl.changerPrix(nerf.id, nerf.prix);
        };
        productDiv.appendChild(button2);
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

  static ajouterStock(id) {
    console.log(id);
    http.renouvelerStock(
      id,
      10,
      StockCtrl.ajoutStockSuccessCallback,
      StockCtrl.error
    );
  }

  static ajoutStockSuccessCallback(data) {
    alert("Le stock a été mis à jour!");
    StockCtrl.chargeCatalogue();
  }

  static changerPrix(id, prix){
    console.log(id + " " + prix);
    if((id != null) && (prix != null)){
        http.changerPrix(id, prix, StockCtrl.changerPrixSuccessCallback, StockCtrl.error);
    }
  }

  static changerPrixSuccessCallback(data){
    prompt("Le prix a été mis à jour!");
    StockCtrl.chargeCatalogue;
  }

  static error(data) {
    console.log("problème " + data);
    alert("erreur: " + data);
  }
}

