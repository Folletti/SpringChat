var stompClient = null;
function setConnected(connected) {
    $("#enter").prop("disabled", connected);
    $("#quit").prop("disabled", !connected);
    $("#send").prop("disabled", !connected);
    if(connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#chat").html("");
}

var sessionId = "";
function enter() {
    var socket = new SockJS("/gs-guide-websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log("Connected: " + frame);
        stompClient.subscribe("/springchat/room", function(message) {
            showMessage(JSON.parse(message.body).content);
        });
        sessionId = /\/([^\/]+)\/websocket/.exec(socket._transport.url)[1];
        stompClient.send("/app/hello", {}, JSON.stringify(
                {
                    "name" : $("#name").val(),
                    "sessionId" : sessionId
                }
         ));

    });

}

function quit() {

    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    $("#chat").html("Disconnected");
    console.log("Disconnected");

}

function sendMessage() {
    stompClient.send("/app/chat", {}, JSON.stringify(
        {
            "text" : $("#text").val(),
            "sessionId" : sessionId
        }
    ));
}

function showMessage(message) {
    $("#chat").append("<tr><td>" + message + "</td></tr>");
}

$(function() {
    $("form").on("submit", function(e) {
        e.preventDefault();
    });
    $("#enter").click(function() {
        enter();
    });
    $("#quit").click(function() {
        quit();
    });
    $("#send").click(function() {
        sendMessage();
    });

});