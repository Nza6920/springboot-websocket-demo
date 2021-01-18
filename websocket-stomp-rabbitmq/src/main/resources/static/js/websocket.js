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

            // 订阅 exchange 点对点
            stompClient.subscribe('/exchange/send2User/user' + userId, function (response) {
                writeToScreen("主题 exchange-点对点消息: " + response.body);
            });

            // 订阅 exchange 群发
            stompClient.subscribe('/exchange/all/user', function (response) {
                writeToScreen("主题 exchange-群发消息: " + response.body);
            });

            // 订阅 queue 点对点
            stompClient.subscribe('/queue/user' + userId, function (response) {
                writeToScreen("主题 queue-点对点消息: " + response.body);
            });

            // 订阅 amq queue
            stompClient.subscribe('/amq/queue/amqQueue', function (response) {
                writeToScreen("主题 amqQueue消息: " + response.body);
            });

            // 订阅 topic
            stompClient.subscribe('/topic/user' + userId, function (response) {
                writeToScreen("主题 topic消息: " + response.body);
            });

            // 订阅 topic 群发
            stompClient.subscribe('/topic/chat', function (response) {
                writeToScreen("主题 topic群发消息: " + response.body);
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