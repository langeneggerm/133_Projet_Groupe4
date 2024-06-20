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

    login(login, mdp, successCallback, errorCallback) {
        console.log("connect");
        $.ajax({
            type: "POST",
            url: this.BASE_URL + "login",
            data: {
                username: login,
                pwd: mdp
            },
            success: successCallback,
            error: errorCallback
        })
    }

    deconnecter(successCallback, errorCallback) {
        console.log("deconnect");
        $.ajax({
            type: "POST",
            url: this.BASE_URL + "logout",
            success: successCallback,
            error: errorCallback
        })
    }

    renouvelerStock(id, qty, successCallback, errorCallback) {
        console.log("renouveler stock");
        $.ajax({
            type: "PUT",
            url: this.BASE_URL + "newStock",
            data: {
                id: id,
                addedQty: qty
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
            success: successCallback,
            error: errorCallback
        })
    }



}
