class HttpService {

    BASE_URL = "http://localhost:8080/";

    constructor() {
    }

    getAllNerf(successCallback, errorCallback){
        console.log("getNerfs");
        $.ajax({
            type: "GET",
            url: this.BASE_URL + "getAllNerf",
            contentType: "json",
            success: successCallback,
            error: errorCallback
        })
    }

    getNerf(id, successCallback, errorCallback){
        console.log("getNerf " + id);
        $.ajax({
            type: "GET",
            url: this.BASE_URL + "getNerf",
            contentType: "json",
            success: successCallback,
            error: errorCallback
        })
    }

    login(login, passwd, successCallback, errorCallBack) {

        $.ajax({
            type: "POST",
            url: this.BASE_URL + "login",
            contentType: "application/json",
            data: JSON.stringify({
                username: login,
                password: passwd
            }),
            xhrFields: {
                withCredentials: true
            },
            success: successCallback,
            error: errorCallBack
        });
    }

    deconnecter(successCallback, errorCallback) {
        console.log("deconnect");
        $.ajax({
            type: "POST",
            url: this.BASE_URL + "logout",
            xhrFields: {
                withCredentials: true
            },
            success: successCallback,
            error: errorCallback
        })
    }

    renouvelerStock(id, qty, successCallback, errorCallback) {
        console.log("renouveler stock" + id + " " + qty);
        $.ajax({
            type: "POST",
            url: this.BASE_URL + "newStock",
            data: {
                id: id,
                addedQty: qty
            },
            xhrFields: {
                withCredentials: true
            },
            success: successCallback,
            error: errorCallback
        })
    }

    changerPrix(id, prix, successCallback, errorCallback) {
        console.log("changement de prix");
        $.ajax({
            type: "PUT",
            url: this.BASE_URL + "newPrice",
            data: {
                id: id,
                prix: prix
            },
            xhrFields: {
                withCredentials: true
            },
            success: successCallback,
            error: errorCallback
        })
    }

    ajouterNerf(nom, description, typeTir, prix, quantite, successCallback, errorCallback) {
        console.log("ajout de nerf");
        $.ajax({
            type: "POST",
            url: this.BASE_URL + "addNerf",
            data: {
                nom: nom,
                description: description,
                typeTir: typeTir,
                quantite: quantite, 
                img: null,
                prix: prix
            },
            xhrFields: {
                withCredentials: true
            },
            success: successCallback,
            error: errorCallback
        })
    }



}
