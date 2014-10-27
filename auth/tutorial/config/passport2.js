// config/passport.js

// load all the things we need
var LocalStrategy   = require('passport-local').Strategy;


// load up the user model
var User       		= require('../app/models/user');


var mysql = require('mysql');

var connection = mysql.createConnection({
					host	 : 'localhost',
					user	 : 'alice',
					password : 'iamadmin'
					});

connection.query('USE Jabberwocky');

// expose this function to our app using module.exports
module.exports = function(passport) {

    // =========================================================================
    // passport session setup ==================================================
    // =========================================================================
    // required for persistent login sessions
    // passport needs ability to serialize and unserialize users out of session

    // used to serialize the user for the session
    passport.serializeUser(function(user, done) {
        done(null, user.id);
    });

    // used to deserialize the user
  //  passport.deserializeUser(function(id, done) {
    //    User.findById(id, function(err, user) {
      //      done(err, user);
       // });
   // });

     passport.deserializeUser(function(id, done){
		connection.query("select * from users where id = "+id,function(err,rows){
		done(err,rows[0]);
	});	
});



 	// =========================================================================
    // LOCAL SIGNUP ============================================================
    // =========================================================================
    // we are using named strategies since we have one for login and one for signup
	// by default, if there was no name, it would just be called 'local'

    passport.use('local-signup', new LocalStrategy({
        // by default, local strategy uses username and password, we will override with email
        usernameField : 'email',
        passwordField : 'password',
        passReqToCallback : true // allows us to pass back the entire request to the callback
    },
    function(req, email, password, done) {

        // asynchronous
        // User.findOne wont fire unless data is sent back

	connection.query("select * from users where email = '"+email+"'",function(err,rows){
			console.log(rows);
			console.log("above row object");
			if(err)
		return done(err);
			if(rows.length){
			return done(null,false, req.flash('signupMessage','That email is already taken.'));
		}else{
		//create the user
		var newUserMysql = new Object();
			
			newUserMysql.email = email;
			newUserMysql.password = password; //use generateHash function

				var insertQuery = "INSERT INTO users(email,password) values('"+email+"','"+password+"')";
					console.log(insertQuery);
				connection.query(insertQuery,function(err,rows){
				newUserMysql.id = rows.insertId;

				return done(null, newUserMysql);
				});
}

	});
}));



// Local Login

passport.use('local-login', new LocalStrategy({
	//by default, Local strategy uses username and password, will override with email
	usernameField : 'email',
	passwordField : 'password',
	passReqToCallback : true //allows us to pass back the entire request to the callback
},
function(req,email,password,done){
	connection.query("SELECT * FROM `users` WHERE `email` = '"+email+"'",function(err,rows){
		if(err)
		  return done(err);
		if(!rows.length){
			return done(null,false,req.flash('loginMessage','No user found.'));
		}
		if(!(rows[0].password ==password))
			return done(null, false, req.flash('loginMessage','Oops! Wrong password.'));

		return done(null,rows[0]);
		});
	}));
};

