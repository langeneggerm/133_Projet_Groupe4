

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
    var userAuth = document.getElementById("txtUser").value;
    sessionStorage.setItem('user',userAuth)
    var newURL = '../index.html';
    alert("La connexion a réussi !");
    window.location.href = newURL;
}

function errorCallBack() {
    console.log("erreur");
  }