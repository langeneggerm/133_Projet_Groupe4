$(document).ready(function () {
    const commandeDate = null;
    $.getScript("../js/services/httpService.js", function () {
        console.log("servicesHttp.js chargé !");
        chargerCommende();
});




})

function chargerCommende() {
    if(sessionStorage.getItem('user')){
        console.log("connect");
            // Changer la valeur de l'input avec l'ID 'username'
        $("#username").text("Nom d'utilisateur :" + sessionStorage.getItem('user'));
        
            getCommande(sessionStorage.getItem('userID'), successCallbackCmd, errorCallBack);
        
            getSolde(pk_user,successCallbackSolde,errorCallBackSolde)
        }else{
            alert("il faut etre connecte pour accéder a cette page");
            window.history.back();
        }
  }

function successCallbackCmd(data) {
    console.log(data);
    var newData = JSON.parse(data);
  
   
    newData.forEach((commande) => {
        commandeDate = commande.DateCommande;
        getNerf(commande.fkNerf,successCallbackNerf,errorCallBackNerf);

    });
    console.log("chargement des commandes terminé");
    
}

function successCallbackNerf(data){
    console.log(data)
    var parentElement = document.getElementById("sectionCommande");
        var label = document.createElement("label");
        label.innerText = "Date de la commande :" + commandeDate + ", article commandé: " +data.nom;
        parentElement.appendChild(label);
}
function errorCallBackNerf(){
    console.log("error")
}
function errorCallBack() {
    console.log("chargement des commandes a échoué");
}

function successCallbackSolde(data){
    console.log(data);
}

function errorCallBackSolde(){
    
}
