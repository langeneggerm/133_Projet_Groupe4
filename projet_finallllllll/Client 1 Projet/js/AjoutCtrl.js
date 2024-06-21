$(document).ready(function() {
    http = new HttpService();
    indexCtrl = new AjoutCtrl();
});

class AjoutCtrl{

    constructor(){
        $("#addBtn").on('click', AjoutCtrl.add);
    }

    static add(){
        nom = $("#nerf-name").val();
        description = $("#nerf-description").val();
        stock = $("#nerf-stock").val();
        prix = $("#nerf-prix").val();
        type = $("#nerf-tir").val();
        if((nom != null) && (description != null) && (stock != null) && (prix != null) &&(type != null)){
            http.ajouterNerf(nom, description, type, prix, stock, AjoutCtrl.addSuccess, AjoutCtrl.error);
        } else {
            alert("Veuillez remplir tous les champs");
        }
    }

    static addSuccess(data){
        prompt("Le nerf a été ajouté au catalogue!\nVeuillez vous rendre sur l'onglet Stock pour le voir");
    }

    static error(data){
        console.log("problème " + data);
        alert("erreur: " + data);
    }
}

function logout(){
    deconnecter(successCallbackdisconnect,errorCallBackdiconnect);
}

function successCallbackdisconnect(){
    sessionStorage.clear();
    var newURL = '../index.html';
    alert("La déconnexion a réussi !");
    window.location.href = newURL;
}

function errorCallBackdiconnect(){
alert("erreur")
}