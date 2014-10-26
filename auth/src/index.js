var express = require('express');
var path = require('path');
//Constants
var PORT = 8080;

//APP
var app = express();
//app.get('/', function(req,res){
//	res.send("Hello World\n");
//});

var staticPath = path.resolve(__dirname, '/public');
app.use(express.static(staticPath));

app.listen(PORT);
console.log('Running on http://localhost:' + PORT);
