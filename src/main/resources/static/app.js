var stompClient = null;

function setConnected(connected) {
    $("#enter").prop("disabled", connected);
    $("#quit").prop("disabled", !connected);
    $("#name").prop("disabled", connected);
}
var sessionId = "";

function enter() {
    var socket = new SockJS("/gs-guide-websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        sessionId = /\/([^\/]+)\/websocket/.exec(socket._transport.url)[1];
        console.log("Connected: " + frame);

        stompClient.send("/app/hello", {}, JSON.stringify(
                {
                    "name" : $("#name").val(),
                    "sessionId" : sessionId
                }
        ));
        stompClient.send("/app/history", {}, JSON.stringify(
                {
                    "name" : $("#name").val(),
                    "sessionId" : sessionId
                }
        ));
        setConnected(true);
        $("#name").val("");

    });

}

$(function() {
    $("form").on("submit", function(e) {
        e.preventDefault();
    });
    $("#enter").click(function() {
        enter();
    });

});