

var BASE_URL = "http://localhost:8080/"

function connect(login, passwd, successCallback, errorCallBack) {

    $.ajax({
        type: "POST",
        url: BASE_URL + "login",
        contentType: "application/json",
        data: JSON.stringify({
            username: login,
            password: passwd
        }),    xhrFields: {
            withCredentials: true
        },
        success: successCallback,
        error: errorCallBack
    });
}

function disconnect(successCallback, errorCallback) {
    $.ajax({
        type: "POST",
        url: BASE_URL + "logout",
        xhrFields: {
            withCredentials: true
        },
        success: successCallback,
        error: errorCallback
    });
}

function getCommande(pk_user, successCallback, errorCallBack) {
    $.ajax({
        type: "GET",
        url: BASE_URL + "getCommandes",
        data: {
            pk_user: pk_user
        },
        xhrFields: {
            withCredentials: true
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
        url: BASE_URL + " achatNerf",
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

function getAllNerf(successCallback, errorCallback) {

    $.ajax({
        type: "GET",
        url: BASE_URL + "getAllNerf",
        contentType: "json",
        success: successCallback,
        error: errorCallback
    })
}



function getNerf(id, successCallback, errorCallback){
    console.log("getNerf " + id);
    $.ajax({
        type: "GET",
        url: BASE_URL + "getNerf",
        data: {
            id: id
        },
        success: successCallback,
        error: errorCallback
    })
}