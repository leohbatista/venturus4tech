var express = require('express');

var app = express();

var messages = [];

app.get('/',function(request,response) {
    response.send("<h1>Não é aqui</h1>");
    response.end();
});

app.get('/messages',function(request,response) {
    response.status(404);
    response.end();
});

app.listen(3000,function(error) {
    console.log('Server initialized');   
});