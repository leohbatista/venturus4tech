var http = require("http");

var server = http.createServer(serverCallback);

function serverCallback(request,response) {
    if (request.url == "/messages") {
        console.log("Recebemos uma requisição em messages");
        response.write("Hello Messages!!");
    } else {
        console.log("Recebemos uma requisição qualquer");
        response.write("Hello World!!");
    }
    response.end();
}

server.listen(3000);


