document.addEventListener("DOMContentLoaded", function () {
    var socket = new SockJS('/ws');
    var stompClient = Stomp.over(socket);

    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/orders', function (orderNotification) {
            var order = JSON.parse(orderNotification.body);
            console.log('Order Notification:', order);
            showNotification(order);
        });
    });

    function showNotification(order) {
        const total = `
            Total: ${order.total} DHS`;
        const nbrproduits = `
            Number of Products: ${order.nbrproduit}`;
        const nomclient = `
            Client: ${order.clientInfo.firstName} ${order.clientInfo.lastName}`;


        !function (i) {
            "use strict";
            i.NotificationApp.send("Nouvelle Commande!", [total, nbrproduits, nomclient], "top-right", "rgba(0,0,0,0.2)", "info")
        }(window.jQuery);
    }
});