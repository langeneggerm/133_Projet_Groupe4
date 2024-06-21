

$(document).ready(function () {

    $.getScript("../js/services/httpService.js", function () {
        console.log("servicesHttp.js chargé !");
    });
    var butConnect = $("#buttonConnection");

    butConnect.on("click",function () {  
        var username = $('#username');
        var password = $('#password');
        console.log(username.val() + " " + password.val())
       connect(username.val(), password.val(),successCallback, errorCallBack);
    })

    function successCallback(data){
        console.log(data)
        var userAuth = $("#username").val();
        sessionStorage.setItem('user',userAuth)
        sessionStorage.setItem('userID',data)
        var newURL = '../index.html';
        alert("La connexion a réussi !");
        window.location.href = newURL;
    }
});



function errorCallBack() {
alert("ATTENTION les identifiants ne sont pas valides !!!!");
  }