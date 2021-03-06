var stompClient = null;
var wsCreateHandler = null;
var userId = null;

function connect() {
    var host = window.location.host; // 带有端口号
    userId = GetQueryString("userId");
    var socket = new SockJS("http://" + host + "/websocket");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
            writeToScreen("connected: " + frame);
            stompClient.subscribe('/topic', function (response) {
                writeToScreen("主题: topic, 消息: " + response.body);
            });

            stompClient.subscribe('/send2All', function (response) {
                writeToScreen("主题: send2All, 消息: " + response.body);
            });

            stompClient.subscribe('/user/' + userId + "/topic", function (response) {
                writeToScreen("主题: user, 消息: " + response.body);
            });

        }, function (error) {
            wsCreateHandler && clearTimeout(wsCreateHandler);
            wsCreateHandler = setTimeout(function () {
                console.log("重连...");
                connect();
                console.log("重连完成");
            }, 1000);
        }
    )
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    writeToScreen("disconnected");
}

function writeToScreen(message) {
    if (DEBUG_FLAG) {
        $("#debuggerInfo").val($("#debuggerInfo").val() + "\n" + message);
    }
}

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg); //获取url中"?"符后的字符串并正则匹配
    var context = "";
    if (r != null)
        context = r[2];
    reg = null;
    r = null;
    return context == null || context == "" || context == "undefined" ? "" : context;
}