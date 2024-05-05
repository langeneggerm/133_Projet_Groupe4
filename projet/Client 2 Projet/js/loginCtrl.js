

$(document).ready(function () {

    $.getScript("../js/services/httpService.js", function () {
        console.log("servicesHttp.js chargé !");
    });
    var butConnect = $("#buttonConnection");

    butConnect.on("click",function () {  
        var username = $('#username');
        var password = $('#password');
        console.log(username.val() + " " + password.val())
       connect(username.val(), password.val(),successCallback(), errorCallBack());
    })
});

function successCallback(){
    console.log("connecté");
}

function errorCallBack() {
    console.log("erreur");
  }