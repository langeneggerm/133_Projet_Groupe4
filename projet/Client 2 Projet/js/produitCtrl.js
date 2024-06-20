$(document).ready(function(){
var btn = document.getElementsByClassName("btnAcheter");
    $.getScript("../js/services/httpService.js", function () {
        console.log("servicesHttp.js chargé !");
        getAllNerf(successCallback(), errorCallBack());
    });

    btn.on("click",function(){
        console.log("tes btn acheter");
    })
})

function successCallback(data) {

    console.log(data);
    console.log("chargement des Nerfs effectué");



  }

function errorCallBack() { 
    console.log("chargement des Nerfs a échoué");
 }