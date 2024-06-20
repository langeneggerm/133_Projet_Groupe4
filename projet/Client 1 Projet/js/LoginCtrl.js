$(document).ready(function() {
    http = new HttpService();
    indexCtrl = new LoginCtrl();
});

class LoginCtrl{

    constructor(){
        $("#btnConnect").on('click', LoginCtrl.login);
    }

    static login(){
        var username = $("#username").val();
        var pwd = $("#password").val();
        http.login(username, pwd, LoginCtrl.loginSuccess, LoginCtrl.error);
    }

    static loginSuccess(data){
        alert("Vous êtes désormais connecté!\nProfitez à présent de toutes les fonctionalités de manager");
    }

    static error(data){
        console.log("problème " + data);
        alert("erreur: " + data);
    }
}