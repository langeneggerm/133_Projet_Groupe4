$(document).ready(function () {
  http = new HttpService();
  indexCtrl = new StockCtrl();
  StockCtrl.chargeCatalogue;
});

class StockCtrl {
  constructor() {
    $("#btnConnect").on("click", this.login);
  }

  static chargeCatalogue() {
    http.getAllNerf(StockCtrl.loadCatalogueSuccess, StockCtrl.error);
  }

  static loadCatalogueSuccess(data) {
    result = false;
    lastNerf = null;
    parentElement = document.getElementById("produits");
    // Vider le contenu en affectant une chaîne vide à innerHTML
    parentElement.innerHTML = "";
    data.forEach((nerf) => {
      try {
        productDiv = document.createElement("div");
        productDiv.className = "produit";
        h3 = document.createElement("h3");
        h3.innerText = nerf.nom;
        productDiv.appendChild(h3);
        description = document.createElement("p");
        description.innerText = nerf.description;
        productDiv.appendChild(description);
        stockP = document.createElement("p");
        stockP.innerHTML = `Stock: <span id="stock${id}">${nerf.quantite}</span>`;
        productDiv.appendChild(stockP);
        prixP = document.createElement("p");
        prixP.innerHTML = `Prix: <span >${nerf.prix}</span>`;
        productDiv.appendChild(prixP);
        button = document.createElement("button");
        button.innerText = "Ajouter Stock";
        button.id = nerf.id;
        button.onclick = function () {
          StockCtrl.ajouterStock(nerf.id);
        };
        productDiv.appendChild(button);
        button2 = document.createElement("button");
        button.innerText = "Changer pris";
        button2.setAttribute("data-id", nerf.id);
        button2.setAttribute("data-prix", nerf.prix);
        button.onclick = function () {
          StockCtrl.changerPrix(nerf.id, nerf.prix);
        };
        productDiv.appendChild(button);
        result = true;
        lastNerf = nerf;
      } catch (error) {
        result = false;
      }
    });
    if (result) {
      prompt("Tous les nerfs ont été chargés");
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
    prompt("Le stock a été mis à jour!");
    StockCtrl.chargeCatalogue;
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
