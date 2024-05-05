$(document).ready(function() {
    http = new HttpService();
    indexCtrl = new LoginCtrl();
});

class LoginCtrl{

    constructor(){
        $("#btnConnect").on('click', this.login);
    }

    static login(){
        username = $("#username").val();
        pwd = $("#password").val();
        http.login(pk, LoginCtrl.loginSuccess, LoginCtrl.error);
    }

    static loginSuccess(data){
        prompt("Vous êtes désormais connecté!\nProfitez à présent de toutes les fonctionalités de manager");
    }

    static error(data){
        console.log("problème " + data);
        alert("erreur: " + data);
    }
}