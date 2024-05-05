$(document).ready(function () {

    $.getScript("../js/services/httpService.js", function () {
        console.log("servicesHttp.js chargé !");

        getCommande(pk_user, successCallback(), errorCallBack());


    });


})


function successCallback() {
    console.log("chargement des commandes terminé");
}

function errorCallBack() {
    console.log("chargement des commandes a échoué");
}