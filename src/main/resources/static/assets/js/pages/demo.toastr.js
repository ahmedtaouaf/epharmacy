!function (i) {
    "use strict";
    i("#toastr-one").on("click", function (t) {
        i.NotificationApp.send("Heads up!", "This alert needs your attention, but it is not super important.", "top-right", "rgba(0,0,0,0.2)", "info")
    })
}(window.jQuery);