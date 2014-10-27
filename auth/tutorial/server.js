//server.js

// set up

//get all the tools we need
var express = require('express');
var app = express();
var port = process.env.PORT || 8080;
//var mongoose = require('mongoose');
var passport = require('passport');
var flash = require('connect-flash');
var morgan = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var session = require('express-session');
//var configDB = require('./config/database.js');

//configuration

//mongoose.connect(configDB.url);//connect to db

require('./config/passport')(passport);//pass passport for config


app.use(morgan('dev'));//log every request
app.use(cookieParser()); // read cookies
app.use(bodyParser());//get info from forms

app.set('view engine' , 'ejs'); //set ejs for templates

//required for passport
app.use(session({secret: 'alicevsjabberwock'}));

app.use(passport.initialize());

app.use(passport.session()); //persistent login sessions

app.use(flash()); //use connect-flash for flash messages 

//routes
require('./app/routes.js')(app,passport);

//launch
app.listen(port);
console.log('The magic happens on port' + port);









