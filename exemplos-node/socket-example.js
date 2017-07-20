var server = require('http').createServer(handler);
server.listen(3000);
var cors = require('cors');
var io = require('socket.io')(server);

server.use(cors());

io.on('connection',function(client) {
    console.log("Client connected");
    client.emit('messages', {"author":"Leo","message":"Olá"});
});

function handler(request,response) {
    console.log("Recebemos uma requisição");
    response.write("Coé rapaziada");
    response.end();
}