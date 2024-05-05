

var BASE_URL = "https://backend-4.emf4you.ch/"

function connect(login, passwd, successCallback, errorCallBack) {

    $.ajax({
        type: "POST",
        url: BASE_URL + "login",
        data: {
            login: login,
            passwd: passwd
        },
        success: successCallback,
        error: errorCallBack
    });
}

function disconnect(successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        url: BASE_URL + "logout",
        success: successCallback,
        error: errorCallback
    });
}

function getCommande(pk_user, successCallback, errorCallBack) {
    $.ajax({
        type: "GET",
        url: BASE_URL,
        data: {
            pk: pk_user
        },
        success: successCallback,
        error: errorCallBack
    });
}



function changeSolde(pk_user, montant, successCallback, errorCallBack) {

    $.ajax({
        type: "POST",
        url: BASE_URL,
        data: {
            pk: pk_user,
            montant: montant
        },
        success: successCallback,
        error: errorCallBack
    });
}

function acheterNerf(date, pk_nerf, pk_user, montant, successCallback, errorCallBack) {
    $.ajax({
        type: "POST",
        url: BASE_URL,
        data: {
            date: date,
            pk_nerf: pk_nerf,
            pk_user: pk_user,
            montant: montant
        },
        success: successCallback,
        error: errorCallBack
    });
}

function getAllNerf(successCallback,errorCallBack){
    $.ajax({
        type: "GET",
        url: BASE_URL + "getAllNerf",
        success: successCallback,
        error: errorCallBack
    });
}