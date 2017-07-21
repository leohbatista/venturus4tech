var express = require('express');
var bodyParser = require('body-parser');

var app = express();

app.use(bodyParser.json());

var messages = [];

app.get('/',function(request,response) {
    response.send("<h1>Não é aqui</h1>");
    response.end();
});

app.get('/messages',function(request,response) {
    response.send(messages);
    response.end();
});

app.post('/messages',function(request,response) {
    var message = request.body;
    messages.push(message);
    response.send(message);
});

app.listen(3000,function(error) {
    console.log('Server initialized');   
});