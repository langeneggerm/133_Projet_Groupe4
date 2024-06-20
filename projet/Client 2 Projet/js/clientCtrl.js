$(document).ready(function () {

    $.getScript("../js/services/httpService.js", function () {
        console.log("servicesHttp.js chargé !");

  

    });


    getCommande(pk_user, successCallback(), errorCallBack());

    getSolde(pk_user,successCallbackSolde(),errorCallBackSolde())

})


function successCallback() {
    console.log("chargement des commandes terminé");
}

function errorCallBack() {
    console.log("chargement des commandes a échoué");
}

function successCallbackSolde(data){
    console.log(data);
}

function errorCallBackSolde(){
    
}