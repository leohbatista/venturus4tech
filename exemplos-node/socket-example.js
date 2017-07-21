var server = require('http').createServer(handler);
server.listen(3000);
var io = require('socket.io')(server);
var mongoose = require("mongoose");
var Schema = mongoose.Schema;

mongoose.connect('mongodb://172.24.30.53/ventuchat');

var messageSchema = new Schema({
    message: String,
    author: String,
    time: {type: Date, default:Date.now}
});

var MessageModel = mongoose.model('messages',messageSchema);

function handler(request,response) {
    console.log("Recebemos uma requisição");
    response.write("Coé rapaziada");
    response.end();
}

io.on('connection',function(client) {
    console.log("Client connected to the most friendly chat");

    MessageModel.find({},function(error,messages){
        if(error) {
            console.log('Load error');
        } else {
            messages.forEach(function(message) {
                client.emit('messages',message);
            });
        }
    });

    client.on('messages', function(message) {
        saveMessage(message);
    })
});

var saveMessage = function(message) {
    var newMessage = new MessageModel();
    newMessage.author = message.author;
    newMessage.message = message.message;
    
    newMessage.save(function(error){
        if(error) {
            console.log('Save error');
        } else {
            io.emit('messages',newMessage);
        }
    });
}