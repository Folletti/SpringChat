var stompClient = null;
function setConnected(connected) {
    $("#enter").prop("disabled", connected);
    $("#quit").prop("disabled", !connected);
    $("#name").prop("disabled", connected);
    if(connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#chat").html("");
}

function enter() {
    var socket = new SockJS("/gs-guide-websocket");
    var sessionId;
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log("Connected: " + frame);
        sessionId = /\/([^\/]+)\/websocket/.exec(socket._transport.url)[1];
        stompClient.subscribe("/springchat/greeting", function(greeting) {
            showMessage(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe("/springchat/room", function(message) {
            showMessage(JSON.parse(message.body).user,
            JSON.parse(message.body).message);
        })
    }
    sendName();
}

function quit() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
     setConnected(false);
     console.log("Disconnected");

}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify(
        { "name" : $("#name").val(); }
    ));
}


function sendMessage() {
    stompClient.send("/app/chat", {}, JSON.stringify(
        {
            "message" : $("#message").val();
            "sessionId": sessionId;
        }
    ));
}

function showMessage(user, message) {
    $("#chat").append("<tr><td>" + user + ": " + message + "</td></tr>");
}

$(function() {
    //$("#send").prop("disabled", true);
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