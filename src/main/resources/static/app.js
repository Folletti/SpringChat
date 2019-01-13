var stompClient = null;

function setConnected(connected) {
    $("#enter").prop("disabled", connected);
    $("#quit").prop("disabled", !connected);
    $("#send").prop("disabled", !connected);
    $("#text").prop("disabled", !connected);
    $("#name").prop("disabled", connected);
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
        sessionId = /\/([^\/]+)\/websocket/.exec(socket._transport.url)[1];
        console.log("Connected: " + frame);
        stompClient.subscribe("/springchat/room", function(message) {
            forUser = JSON.parse(message.body).forUser;
            if (forUser === sessionId || forUser === "all") {
                showMessage(JSON.parse(message.body).content);
            }
        });
        stompClient.send("/app/hello", {}, JSON.stringify(
                {
                    "name" : $("#name").val(),
                    "sessionId" : sessionId
                }
        ));
        setConnected(true);
        $("#name").val("");

    });

}

function sendQuitMessage() {
    stompClient.send("/app/goodbye", {}, JSON.stringify(
                    {
                        "name" : "",
                        "sessionId" : sessionId
                    }
            ));
}

function quit() {

    sendQuitMessage();
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
    $("#text").val("");

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
    $("#conversation").hide();

});